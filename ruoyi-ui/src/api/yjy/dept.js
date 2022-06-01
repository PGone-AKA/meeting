import request from '@/utils/request'

// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/yjy/dept/treeselect',
    method: 'get'
  })
}

// 根据角色ID查询部门树结构
export function roleDeptTreeselect(roleId) {
  return request({
    url: '/yjy/dept/roleDeptTreeselect/' + roleId,
    method: 'get'
  })
}

// 查询部门表列表
export function listDept(query) {
  return request({
    url: '/yjy/dept/list',
    method: 'get',
    params: query
  })
}

// 查询部门表详细
export function getDept(deptId) {
  return request({
    url: '/yjy/dept/' + deptId,
    method: 'get'
  })
}

// 新增部门表
export function addDept(data) {
  return request({
    url: '/yjy/dept',
    method: 'post',
    data: data
  })
}

// 修改部门表
export function updateDept(data) {
  return request({
    url: '/yjy/dept',
    method: 'put',
    data: data
  })
}

// 删除部门表
export function delDept(deptId) {
  return request({
    url: '/yjy/dept/' + deptId,
    method: 'delete'
  })
}
