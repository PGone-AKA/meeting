import request from '@/utils/request'

// 查询人员信息列表
export function listStudent(query) {
  return request({
    url: '/yjy/student/list',
    method: 'get',
    params: query
  })
}

// 查询人员信息详细
export function getStudent(stuId) {
  return request({
    url: '/yjy/student/' + stuId,
    method: 'get'
  })
}

// 新增人员信息
export function addStudent(data) {
  return request({
    url: '/yjy/student',
    method: 'post',
    data: data
  })
}

// 修改人员信息
export function updateStudent(data) {
  return request({
    url: '/yjy/student',
    method: 'put',
    data: data
  })
}

// 删除人员信息
export function delStudent(stuId) {
  return request({
    url: '/yjy/student/' + stuId,
    method: 'delete'
  })
}
