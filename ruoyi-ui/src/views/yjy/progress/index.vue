<template>


  <el-container>
    <el-header height="150px">
      <div style="display: flex">
        <div style="width: 20%;height: 150px" @click="changeStatus">
          <el-button v-if="status==1" type="danger" style="width: 150px;height: 150px;border-radius: 90px">结束签到</el-button>
          <el-button v-if="status==0" type="success" style="width: 150px;height: 150px;border-radius: 90px">开启签到</el-button>
        </div>

        <div style="display: flex;height: 150px;align-items:center;width: 80%;justify-content:space-around">
          <div style="width: 200px;height: 100px;background-color: white;line-height: 100px;font-weight: bold">
            总人数：{{this.studentList.length}}
          </div >
          <div style="width: 200px;height: 100px;background-color: white;line-height: 100px;font-weight: bold">
            已签到：{{this.signRecord.length}}
          </div>
          <div style="width: 200px;height: 100px;background-color: white;line-height: 100px;font-weight: bold">
            未签到：{{this.studentList.length-this.signRecord.length}}
          </div>
          <div style="width: 200px;height: 100px;background-color: white;line-height: 100px;font-weight: bold">
            签到率：{{Math.round((this.signRecord.length /this.studentList.length) * 10000) / 100 + '％'}}
          </div>
        </div>
      </div>
    </el-header>
    <el-container>
      <el-aside width="600px">
        <div style="margin: 10px auto;width:550px;background-color: white;border-radius: 8px">
            <h3 >{{ this.title }}</h3>
          <div ref="qrCodeUrl" style="width: 480px;margin: 0 auto">
          </div>
          <h3>微信扫一扫签到</h3>
        </div>



      </el-aside>
      <el-main>
        <div style="margin: 10px auto;height: 600px;background-color: white;border-radius: 8px">
          <el-table
            :data="signRecord"
            height="580"
            border
            style="width: 100%"
          >
            <el-table-column
              prop="name"
              label="姓名"
              width="180">
            </el-table-column>

            <el-table-column
              prop="signInTime"
              label="签到时间">
            </el-table-column>
            <el-table-column
              prop="remark"
              label="备注">
            </el-table-column>

          </el-table>
        </div>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
import {showInfo,updateStatus} from "@/api/yjy/task";
import QRCode from 'qrcodejs2'
export default {
  data() {
    return {
      urls: [
        'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
      ],
      // 表单参数
      form: {},
      qrcode:null,
      task:null,
      studentList:[],
      signRecord:[],
      status:'0',
      tid:null,
      title:"在线签到"



    };
  },
  created() {
    console.log("created")
  },
  mounted() {
    document.querySelector("body").setAttribute("style", "background-color:#f0f2f5");

    let tid = 0;
    if (this.$route.query.tid!==undefined){
      tid = this.$route.query.tid;
    }
    this.qrcode = new QRCode(this.$refs.qrCodeUrl, {
      width : 480,
      height : 480,
      correctLevel: QRCode.CorrectLevel.H, // 校正水准
    });
    //console.log(tid)
    this.tid = tid;
    this.getSceneInfo(tid);
    this.makeCode(tid);

  },

  methods: {
    changeStatus(){
      if (this.status==='1'){
        updateStatus(this.tid,0).then(response => {

          if (response.code===200){
            console.log(response.code)
            this.status='0'
          }
        });
      }else {
        updateStatus(this.tid,1).then(response => {
          if (response.code===200){
            console.log(response.code)
            this.status='1'
          }
        });
      }
    },
    makeCode (tid) {
      console.log(tid)
      let url = "http://yang.4w3w.com/sign/form?tid="+tid;
      this.qrcode.makeCode(url);
    },
    getSceneInfo(tid){
      console.log(tid);
      showInfo(tid).then(response => {
        this.task = response.task;
        this.status = response.task.taskStatus;
        this.title = response.task.taskTitle;
        this.studentList = response.students;
        this.signRecord = response.record;
        //console.log(response);
      });
    }
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #d9e7fc;
  color: #333;
  text-align: center;
  line-height: 120px;
}

.el-aside {
  background-color: #f8f8f8;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #f8f8f8;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.el-table__header{
  height: 50px;
}

</style>
