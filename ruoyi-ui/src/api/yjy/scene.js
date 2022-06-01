import request from '@/utils/request'

// 查询场景签到列表
export function listScene(query) {
  return request({
    url: '/yjy/scene/list',
    method: 'get',
    params: query
  })
}

// 查询场景签到详细
export function getScene(sceneId) {
  return request({
    url: '/yjy/scene/' + sceneId,
    method: 'get'
  })
}

// 新增场景签到
export function addScene(data) {
  return request({
    url: '/yjy/scene',
    method: 'post',
    data: data
  })
}

// 修改场景签到
export function updateScene(data) {
  return request({
    url: '/yjy/scene',
    method: 'put',
    data: data
  })
}

// 删除场景签到
export function delScene(sceneId) {
  return request({
    url: '/yjy/scene/' + sceneId,
    method: 'delete'
  })
}
