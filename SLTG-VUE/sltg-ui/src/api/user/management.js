import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sltg";

// 查询用户新闻列表
export function userNewsList(query) {
  return request({
    url: '/user/management/list',
    method: 'get',
    params: query
  })
}

// 查询用户新闻详细
export function getUserNews(newsId) {
  return request({
    url: '/user/management/detail/' + praseStrEmpty(newsId),
    method: 'get'
  })
}
