import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sltg";

// 查询知识库列表
export function knowledgeList(query) {
  return request({
    url: '/news/knowledge/list',
    method: 'get',
    params: query
  })
}

// 查询知识库新闻详细
export function getKnowledgeInfo(newsId) {
  return request({
    url: '/news/knowledge/detail/' + praseStrEmpty(newsId),
    method: 'get'
  })
}
