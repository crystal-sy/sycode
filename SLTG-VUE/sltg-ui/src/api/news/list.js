import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sltg";

// 查询新闻列表
export function newsList(query) {
  return request({
    url: '/system/news/list',
    method: 'get',
    params: query
  })
}

// 查询新闻详细
export function getNews(newsId) {
  return request({
    url: '/system/news/detail/' + praseStrEmpty(newsId),
    method: 'get'
  })
}
