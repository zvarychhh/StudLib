package com.zvarych.studlib.classes

class Book {
    var title: String? = null
    var author: String? = null
    var date: String? = null
    var uid: String? = null


    constructor() {}
    constructor(title: String, author: String, date: String, uid: String) {
        this.title = title
        this.author = author
        this.date = date
        this.uid = uid
    }
}