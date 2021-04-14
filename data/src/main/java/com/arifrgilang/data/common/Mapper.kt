package com.arifrgilang.data.common


/**
 * Created by arifrgilang on 4/14/2021
 */
interface Mapper<Response, Model> {
    fun transform(response: Response): Model
}