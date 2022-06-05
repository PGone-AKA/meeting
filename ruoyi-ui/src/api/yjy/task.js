import request from '@/utils/request'

// 查询任务表列表
export function listTask(query) {
  return request({
    url: '/yjy/task/list',
    method: 'get',
    params: query
  })
}

// 查询任务表详细
export function getTask(taskId) {
  return request({
    url: '/yjy/task/' + taskId,
    method: 'get'
  })
}

// 新增任务表
export function addTask(data) {
  return request({
    url: '/yjy/task',
    method: 'post',
    data: data
  })
}

// 修改任务表
export function updateTask(data) {
  return request({
    url: '/yjy/task',
    method: 'put',
    data: data
  })
}

// 删除任务表
export function delTask(taskId) {
  return request({
    url: '/yjy/task/' + taskId,
    method: 'delete'
  })
}
// 删除人员信息
export function showInfo(stuId) {
  return request({
    url: '/yjy/task/show?tid=' + stuId,
    method: 'get'
  })
}
// 更新状元
export function updateStatus(tid,status) {
  return request({
    url: '/yjy/task/status?tid=' + tid+"&status="+status,
    method: 'get'
  })
}

