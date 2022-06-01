<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="签到者" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入签到者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签到任务" prop="recordTid">

        <el-select v-model="queryParams.recordTid" placeholder="请选择签到任务" @keyup.enter.native="handleQuery" clearable>
          <el-option
            v-for="item in task"
            :key="item.taskId"
            :label="item.taskTitle+'('+item.deptName+')'"
            :value="item.taskId"
          ></el-option>
        </el-select>
<!--        <el-input-->
<!--          v-model="queryParams.recordTid"-->
<!--          placeholder="请输入任务ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
      </el-form-item>
      <el-form-item label="签到时间" prop="signInTime">
        <el-date-picker clearable
          v-model="queryParams.signInTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择签到时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="签退时间" prop="signOutTime">
        <el-date-picker clearable
          v-model="queryParams.signOutTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择签退时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['yjy:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['yjy:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['yjy:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['yjy:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="recordId" />
      <el-table-column label="签到任务" align="center" prop="taskName" />
      <el-table-column label="签到者姓名" align="center" prop="name" />
      <el-table-column label="签到时间" align="center" prop="signInTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signInTime)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="签退时间" align="center" prop="signOutTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signOutTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['yjy:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['yjy:record:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改签到记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="选择任务" prop="recordTid">
          <el-select v-model="form.recordTid" placeholder="请选择签到任务">
            <el-option
              v-for="item in task"
              :key="item.taskId"
              :label="item.taskTitle+'('+item.deptName+')'"
              :value="item.taskId"
            ></el-option>
          </el-select>
<!--          <el-input v-model="form.recordTid" placeholder="请输入任务ID" />-->
        </el-form-item>
        <el-form-item label="签到者" prop="name">
          <el-input v-model="form.name" placeholder="请输入签到者姓名" />
        </el-form-item>
        <el-form-item label="签到时间" prop="signInTime">
          <el-date-picker clearable
            v-model="form.signInTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择签到时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="签退时间" prop="signOutTime">
          <el-date-picker clearable
            v-model="form.signOutTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择签退时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/yjy/record";
import { listTask } from "@/api/yjy/task";
import {listScene} from "@/api/yjy/scene";

export default {
  name: "Record",
  data() {
    return {
      //签到任务列表
      task:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 签到记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordTid: null,
        name: null,
        signInTime: null,
        signOutTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        recordTid: [
          { required: true, message: "任务ID不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "签到者姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getListTask();
  },
  methods: {

    getListTask(){
      listTask().then(response => {
        this.task = response.rows;
      });
    },

    /** 查询签到记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        recordId: null,
        recordTid: null,
        name: null,
        signInTime: null,
        signOutTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加签到记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recordId = row.recordId || this.ids
      getRecord(recordId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改签到记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const recordIds = row.recordId || this.ids;
      this.$modal.confirm('是否确认删除签到记录编号为"' + recordIds + '"的数据项？').then(function() {
        return delRecord(recordIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('yjy/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
