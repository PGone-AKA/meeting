import request from '@/utils/request'

// 查询签到记录列表
export function listRecord(query) {
  return request({
    url: '/yjy/record/list',
    method: 'get',
    params: query
  })
}

// 查询签到记录详细
export function getRecord(recordId) {
  return request({
    url: '/yjy/record/' + recordId,
    method: 'get'
  })
}

// 新增签到记录
export function addRecord(data) {
  return request({
    url: '/yjy/record',
    method: 'post',
    data: data
  })
}

// 修改签到记录
export function updateRecord(data) {
  return request({
    url: '/yjy/record',
    method: 'put',
    data: data
  })
}

// 删除签到记录
export function delRecord(recordId) {
  return request({
    url: '/yjy/record/' + recordId,
    method: 'delete'
  })
}
