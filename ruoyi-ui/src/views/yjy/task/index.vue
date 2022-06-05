<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会议主题" prop="taskTitle">
        <el-input
          v-model="queryParams.taskTitle"
          placeholder="请输入会议主题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="beginTime">
        <el-date-picker clearable
          v-model="queryParams.beginTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="overTime">
        <el-date-picker clearable
          v-model="queryParams.overTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="部门ID" prop="taskDid">-->

<!--        <el-input-->
<!--          v-model="queryParams.taskDid"-->
<!--          placeholder="请输入部门ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="场景ID" prop="taskSid">-->
<!--        <el-input-->
<!--          v-model="queryParams.taskSid"-->
<!--          placeholder="请输入场景ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryParams.taskStatus" placeholder="请选择任务状态" clearable>
          <el-option
            v-for="dict in dict.type.yjy_task_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['yjy:task:add']"
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
          v-hasPermi="['yjy:task:edit']"
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
          v-hasPermi="['yjy:task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['yjy:task:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="会议主题" align="center" prop="taskTitle" />
      <el-table-column label="会议内容" align="center" prop="taskContent" />
      <el-table-column label="所属部门" align="center" prop="deptName" />
      <el-table-column label="签到场景" align="center" prop="sceneName" />
      <el-table-column label="开始时间" align="center" prop="beginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="overTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.overTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建者" align="center" prop="createBy" />-->



      <el-table-column label="任务状态" align="center" prop="taskStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yjy_task_status" :value="scope.row.taskStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['yjy:task:edit']"
          >修改</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-data-line"
            @click="showProgress(scope.row)"
            v-hasPermi="['yjy:task:progress']"
          >查看签到进度</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['yjy:task:remove']"
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

    <!-- 添加或修改任务表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会议主题" prop="taskTitle">
          <el-input v-model="form.taskTitle" placeholder="请输入会议主题" />
        </el-form-item>
        <el-form-item label="会议内容">
          <editor v-model="form.taskContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="开始时间" prop="beginTime">
          <el-date-picker clearable
            v-model="form.beginTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="overTime">
          <el-date-picker clearable
            v-model="form.overTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="部门ID" prop="taskDid">

          <treeselect v-model="form.taskDid" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
<!--          <el-input v-model="form.taskDid" placeholder="请输入部门ID" />-->
        </el-form-item>
        <el-form-item label="场景ID" prop="taskSid" >
          <el-select v-model="form.taskSid" placeholder="请选择签到场景">
            <el-option
              v-for="item in task"
              :key="item.sceneId"
              :label="item.sceneName"
              :value="item.sceneId"
            ></el-option>
          </el-select>
<!--          <el-input v-model="form.taskSid" placeholder="请输入场景ID" />-->
        </el-form-item>
        <el-form-item label="任务状态" prop="taskStatus">
          <el-select v-model="form.taskStatus" placeholder="请选择任务状态">
            <el-option
              v-for="dict in dict.type.yjy_task_status"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
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
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/yjy/task";
import {treeselect} from "@/api/yjy/dept";
import {listScene} from "@/api/yjy/scene";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Task",
  dicts: ['yjy_task_status'],
  components: { Treeselect },
  data() {
    return {
      // 部门树选项
      deptOptions: undefined,
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 部门名称
      deptName: undefined,

      //任务
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
      // 任务表表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskTitle: null,
        taskContent: null,
        beginTime: null,
        overTime: null,
        taskDid: null,
        taskSid: null,
        taskStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskTitle: [
          { required: true, message: "会议主题不能为空", trigger: "blur" }
        ],
        beginTime: [
          { required: true, message: "开始时间不能为空", trigger: "blur" }
        ],
        overTime: [
          { required: true, message: "结束时间不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        taskDid: [
          { required: true, message: "部门ID不能为空", trigger: "blur" }
        ],
        taskSid: [
          { required: true, message: "场景ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },

  created() {
    this.getList();
    this.getTreeselect();
    this.getListScene();
  },

  methods: {

    showProgress(row){
      this.reset();
      const taskId = row.taskId || this.ids

      //this.$tab.openPage("签到进度", "/progress",{tid:taskId});
      this.$router.push({
        path:'/progress',
        query:{
          tid:taskId,
        }
      })
    },


    getListScene(){
      listScene().then(response => {
        this.task = response.rows;
      });
    },

    /** 查询部门下拉树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.deptOptions = response.data;
      });
    },

    /** 查询任务表列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
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
        taskId: null,
        taskTitle: null,
        taskContent: null,
        beginTime: null,
        overTime: null,
        createTime: null,
        createBy: null,
        taskDid: null,
        taskSid: null,
        taskStatus: null
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
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const taskId = row.taskId || this.ids
      getTask(taskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateTask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then(response => {
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
      const taskIds = row.taskId || this.ids;
      this.$modal.confirm('是否确认删除任务表编号为"' + taskIds + '"的数据项？').then(function() {
        return delTask(taskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('yjy/task/export', {
        ...this.queryParams
      }, `task_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
