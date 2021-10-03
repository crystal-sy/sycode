<template>
  <div class="register">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form">
      <h3 class="title">注册新用户</h3>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="registerForm.userId === undefined" label="登录账号" prop="userName">
            <el-input style="width: 220px;" v-model="registerForm.userName" placeholder="请输入登录账号" maxlength="30" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="registerForm.userId === undefined" label="登录密码" prop="password">
            <el-input style="width: 220px;" v-model="registerForm.password" placeholder="请输入登录密码" type="password" maxlength="20" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="用户昵称" prop="nickName">
            <el-input style="width: 220px;" v-model="registerForm.nickName" placeholder="请输入用户昵称" maxlength="30" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="手机号码" prop="phoneNumber">
            <el-input style="width: 220px;" v-model="registerForm.phoneNumber" placeholder="请输入手机号码" maxlength="11" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="用户邮箱" prop="email" style="margin-left: 10px;">
            <el-input style="width: 220px;" v-model="registerForm.email" placeholder="请输入用户邮箱" maxlength="50" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:40%;margin-left: 35px;"
          @click="submitForm"
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:40%;"
          @click="goLogin()"
        >
          <span>返 回 登 录</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { register } from "@/api/system/user";

export default {
  name: "Register",
  data() {
    return {
      registerForm: {
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phoneNumber: undefined,
        email: undefined
      },
      registerRules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
        ],
        email: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phoneNumber: [
          {
            required: true,
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      },
      loading: false,
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  methods: {
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["registerForm"].validate(valid => {
        if (valid) {
          register(this.registerForm).then(response => {
            if (response.code === 500) {
              this.msgError(response.msg);
            } else {
              this.msgSuccess("注册成功！");
              this.$router.push("/login");
            }
          });
        }
      });
    },
    goLogin() {
      this.$router.push("/login");
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #707070;
}

.register-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
</style>
