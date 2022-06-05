<template>
  <div class="app-container home">

    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">




        <!--        <blockquote class="text-warning" style="font-size: 14px">-->
<!--          领取阿里云通用云产品1888优惠券-->
<!--          <br />-->
<!--          <el-link-->
<!--            href="https://www.aliyun.com/minisite/goods?userCode=brki8iof"-->
<!--            type="primary"-->
<!--            target="_blank"-->
<!--            >https://www.aliyun.com/minisite/goods?userCode=brki8iof</el-link-->
<!--          >-->
<!--          <br />-->
<!--          领取腾讯云通用云产品2860优惠券-->
<!--          <br />-->
<!--          <el-link-->
<!--            href="https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console"-->
<!--            type="primary"-->
<!--            target="_blank"-->
<!--            >https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console</el-link-->
<!--          >-->
<!--          <br />-->
<!--          阿里云服务器折扣区-->
<!--          <el-link href="http://aly.ruoyi.vip" type="primary" target="_blank"-->
<!--            >>☛☛点我进入☚☚</el-link-->
<!--          >-->
<!--          &nbsp;&nbsp;&nbsp; 腾讯云服务器秒杀区-->
<!--          <el-link href="http://txy.ruoyi.vip" type="primary" target="_blank"-->
<!--            >>☛☛点我进入☚☚</el-link-->
<!--          ><br />-->
<!--          <h4 class="text-danger">-->
<!--            云产品通用红包，可叠加官网常规优惠使用。(仅限新用户)-->
<!--          </h4>-->
<!--        </blockquote>-->

        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>江理在线签到系统-Yjy</h2>
        <p>
          一场活动执行过程中，考验的第一关就是签到核对。传统的签到方式先根据参会人员名单进行打印纸质签到表，线上报名表单无法和线下签到打通，往往就造成了签到处人员拥挤、工作人员手忙脚乱，费时费力还容易出错。这种现象经常出现再我们生活中。
          软件的开发的目的就是为了服务人类，给大家带来各方面方面，面对生活中的大家的需求开发出一款贴合实际生活，解决生活问题的一款实用性软件——在线签到系统。
          该签到系统功能简单易操作，大大节约了入场排队时间，集互动性强、签到效率高为一体，能够实时查看签到和未签到人员名单，一键导出签到和未签到人员名单，极大便利了会议的签到环境.

        <p>
          <b>当前版本:</b> <span>v1.0</span>
        </p>
        <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>SpringCloud</li>
              <li>Nacos</li>
              <li>Sentinel</li>
              <li>Seata</li>
              <li>Minio</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Echarts</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.5.0",
    };
  },
  mounted() {
    this.drawChart();
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },


  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

