import {get} from './http';
import {postformdata} from './http';
import {getformdata} from './http';
// 集成平台服务器状态
export const InitIndex = p => getformdata(`Init`)

export const Predict = p => get(`Predict/`+p)

export const formdata = p =>postformdata(`Build`,p)
export const PREDICTHeight = p => postformdata(`PREDICTHeight`,p)
export const PREDICTHeightWeight = p => postformdata(`PREDICTHeightWeight`,p)