import request from '@/utils/request'

// 查询会议注意列表
export function listTheme(query) {
  return request({
    url: '/yjy/theme/list',
    method: 'get',
    params: query
  })
}

// 查询会议注意详细
export function getTheme(id) {
  return request({
    url: '/yjy/theme/' + id,
    method: 'get'
  })
}

// 新增会议注意
export function addTheme(data) {
  return request({
    url: '/yjy/theme',
    method: 'post',
    data: data
  })
}

// 修改会议注意
export function updateTheme(data) {
  return request({
    url: '/yjy/theme',
    method: 'put',
    data: data
  })
}

// 删除会议注意
export function delTheme(id) {
  return request({
    url: '/yjy/theme/' + id,
    method: 'delete'
  })
}
