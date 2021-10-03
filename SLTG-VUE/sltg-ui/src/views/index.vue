<template>
  <div class="search-page home">
    <h3 class="title">新闻检索</h3>
    <el-row :gutter="20" style="margin-left: 180px;">
      <el-col :span="12" style="width: 70%;">
        <el-input v-model="searchValue"/>
        <svg-icon class-name="search-icon" icon-class="search" @click.stop="click" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: 'homeSearch',
    data() {
      return {
        searchValue: ''
      }
    },
    methods: {
      click() {
        console.info(this.searchValue);
        this.$router.push("/news/table");
      },
    }
  }
</script>

<style scoped lang="scss">
.search-page {
  padding: 150px;
  // background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}

.home {
  .search-icon {
    cursor: pointer;
    font-size: 25px;
    position: absolute;
    vertical-align: middle;
    margin: 6px;
    text-align: center;
  }

  .title {
    margin: 0 auto 30px auto;
    text-align: center;
    font-size: 30px;
    color: #606266;
  }
}
</style>

