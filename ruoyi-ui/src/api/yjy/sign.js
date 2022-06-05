import request from '@/utils/request'

// 查询任务是否开放
export function getWork(tid) {
  return request({
    url: '/yjy/sign/check?tid='+tid,
    method: 'get',
  })
}

// 签到
export function signIn(data) {
  return request({
    url: '/yjy/sign/in',
    method: 'post',
    data: data
  })
}
