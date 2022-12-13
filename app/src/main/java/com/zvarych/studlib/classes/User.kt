package com.zvarych.studlib.classes

import android.text.BoringLayout

class User {
    var username: String? = null
    var email: String? = null
    var uid: String? = null
    var user_type: String? = null
    var department: String? = null


    constructor() {}

    constructor(
        username: String?,
        email: String?,
        user_type: String?,
        department: String?,
        uid: String?
    ) {
        this.username = username
        this.email = email
        this.user_type = user_type
        this.department = department
        this.uid = uid

    }
}