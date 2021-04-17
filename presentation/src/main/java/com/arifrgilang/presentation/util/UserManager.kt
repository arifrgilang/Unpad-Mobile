package com.arifrgilang.presentation.util

import com.arifrgilang.presentation.util.Constant.ACCESS_TOKEN
import com.arifrgilang.presentation.util.Constant.IS_USER_LOGGED_IN
import com.orhanobut.hawk.Hawk


/**
 * Created by arifrgilang on 4/17/2021
 */
class UserManager {
    fun startUserSession(accesToken: String) {
        Hawk.put(IS_USER_LOGGED_IN, true)
        Hawk.put(ACCESS_TOKEN, accesToken)
    }

    fun isSessionActive(): Boolean {
        return Hawk.get(IS_USER_LOGGED_IN)
    }

    fun endUserSession() {
        Hawk.put(IS_USER_LOGGED_IN, false)
        Hawk.delete(ACCESS_TOKEN)
    }
}