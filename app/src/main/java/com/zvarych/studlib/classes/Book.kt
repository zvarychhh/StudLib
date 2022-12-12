package com.zvarych.studlib.classes

class Book {
    var title: String? = null
    var author: String? = null
    var date: String? = null
    var uid: String? = null
    var count: Int? = null


    constructor() {}
    constructor(title: String, author: String, date: String, uid: String, count: Int) {
        this.title = title
        this.author = author
        this.date = date
        this.uid = uid
        this.count = count
    }
}