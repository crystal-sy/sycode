<template>
  <div class="app-container">
    <!--新闻数据-->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="新闻标题" prop="newsTitle">
        <el-input
          v-model="queryParams.newsTitle"
          placeholder="请输入新闻标题"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="新闻分类" prop="newsType">
        <el-select
          v-model="queryParams.newsType"
          placeholder="新闻分类"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检测类型" prop="detectionType">
        <el-select
          v-model="queryParams.detectionType"
          placeholder="检测类型"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in detectionTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="newsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="新闻编号" align="center" key="newsId" prop="newsId" v-if="columns[0].visible" />
      <el-table-column label="新闻标题" align="center" key="newsTitle" prop="newsTitle" v-if="columns[1].visible" :show-overflow-tooltip="true" width="400"/>
      <el-table-column label="新闻分类" align="center" key="newsType" prop="newsType" v-if="columns[2].visible" :show-overflow-tooltip="true" >
        <template slot-scope="scope">
          <dict-tag :options="typeOptions" :value="scope.row.newsType"/>
        </template>
      </el-table-column>
      <el-table-column label="检测类型" align="center" key="detectionType" v-if="columns[3].visible" >
        <template slot-scope="scope">
          <dict-tag :options="detectionTypeOptions" :value="scope.row.detectionType"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[4].visible" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        width="160"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDetail(scope.row.newsId)"
            v-hasPermi="['system:user:edit']"
          >详情</el-button>
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
  </div>
</template>

<script>
  import { knowledgeList, getKnowledgeInfo } from "@/api/news/knowledge";

  export default {
    name: "News",
    data() {
      return {
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
        // 新闻表格数据
        newsList: null,
        // 日期范围
        dateRange: [],
        // 新闻类型数据字典
        typeOptions: [],
        // 新闻检测类型数据字典
        detectionTypeOptions: [],
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          newsTitle: undefined,
          newsType: undefined,
          detectionType: undefined
        },
        // 列信息
        columns: [
          { key: 0, label: `新闻编号`, visible: true },
          { key: 1, label: `新闻标题`, visible: true },
          { key: 2, label: `新闻分类`, visible: true },
          { key: 3, label: `检测类型`, visible: true },
          { key: 4, label: `创建时间`, visible: true }
        ],
        // 表单校验
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_news_type").then(response => {
        this.typeOptions = response.data;
      });
      this.getDicts("sys_detection_type").then(response => {
        this.detectionTypeOptions = response.data;
      });
    },
    methods: {
      /** 查询新闻列表 */
      getList() {
        this.loading = true;
        knowledgeList(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.newsList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      // 筛选节点
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      // 节点单击事件
      handleNodeClick(data) {
        this.getList();
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.userId);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 搜索按钮操作 */
      handleDetail(newsId) {
        this.loading = true;
        getKnowledgeInfo(newsId).then(response => {
            this.newsInfo = response.data;
            this.loading = false;
          }
        );
      },
    }
  };
</script>
