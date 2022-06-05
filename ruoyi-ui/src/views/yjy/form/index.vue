<template>
  <div>
    <div v-if="isOpen">
      <el-empty description="当前不在签到时间范围内"></el-empty>
    </div>

    <div v-else class="content">

      <h1 class="title">会议在线签到模板</h1>

      <div class="longin-boder">
        <input name="name" class="input" type="text" placeholder="*输入姓名" required/>
      </div>
      <div class="longin-boder">
        <input name="remark" class="input" type="text" placeholder="备注(选填)"/>
      </div>
      <div>
        <button class="button" @click="onCheck"> 签到</button>
      </div>
    </div>
  </div>
</template>

<script>
import {getScene} from "@/api/yjy/scene";
import {getWork,signIn} from "@/api/yjy/sign"
export default {
  data() {
    return {
      isCheck: false,
      isOpen: false,
    };
  },
  created() {
    // if (this.$route.params.tid!==undefined){
    //   const tid = this.$route.params.tid;
    //   getWork(tid).then(response => {
    //     console.log(response)
    //   });
    //
    // }

  },
  methods: {
    onCheck() {
      this.tip();

    },
    tip() {
      this.$alert('暂未开放编辑', '测试', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${action}`
          });
        }
      });
    }

  }
}
</script>

<style scoped>
.content {
  width: 100%;

  text-align: center;
}

.longin-boder {
  width: 80%;
  height: 40px;
  margin-top: 10px;
  margin-left: 10%;
  line-height: 40px;
  text-align: center;
  border: 1px solid #dddddd;
  border-radius: 5px;
  background-color: #efefef;
}

.image {
  width: 10%;
  margin-top: 5px;
  float: left;
  text-align: right;
}

.input {
  width: 80%;
  float: left;
  margin-left: 5%;
  height: 37px;
  line-height: 37px;
  border: 0px;
  color: #333333;
  font-size: 16px;
  background-color: #efefef;

}

.submit {
  margin: 0 auto;
}

.button {

  height: 40px;
  width: 80%;
  margin-top: 20px;
  text-align: center;
  font-size: 16px;
  font-family: "微软雅黑";
  font-weight: bold;
  line-height: 38px;
  border-radius: 5px;
  color: #ffffff;
  background-color: #37B4CA;

}

.button:active {
  background-color: #37a3b9;
}

.title {
  align-content: center;
}

</style>
