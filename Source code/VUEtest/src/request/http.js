import axios from 'axios';
import QS from 'qs';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
// 请求超时时间
axios.defaults.timeout = 100000;
// 环境的切换
if (process.env.NODE_ENV == 'development') {    
    axios.defaults.baseURL = '/api';
} else if (process.env.NODE_ENV == 'debug') {    
    axios.defaults.baseURL = '';
} else if (process.env.NODE_ENV == 'production') {
    axios.defaults.baseURL = $mainConfig.httpUrl;
}


export function get(url, params){    
    return new Promise((resolve, reject) =>{        
        axios.get(url, QS.stringify(params)).then(res => {
            resolve(res.data);
        }).catch(err =>{
            reject(err.data)        
    })    
});}
export function post(url, params) {
    return new Promise((resolve, reject) => {
         axios.post(url, QS.stringify(params))
        .then(res => {
            resolve(res.data);
        })
        .catch(err =>{
            reject(err.data)
        })
    });
}

export function postformdata(url, file) {
    return axios({
        url: url,
        method: 'post',
        data: file,
        headers: {
            'Content-Type': 'text/plain'
        }
    })

}
export function getformdata(url, file) {
    return axios({
        url: url,
        method: 'get',
        data: file,
        headers: {
            'Content-Type': 'text/plain'
        }
    })

}