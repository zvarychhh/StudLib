package com.zvarych.studlib.classes

class Book {
    var tittle: String? = null
    var author: String? = null
    var year_of_publication: String? = null

    constructor() {}
    constructor(tittle: String, author: String, year_of_publication: String) {
        this.tittle = tittle
        this.author = author
        this.year_of_publication = year_of_publication
    }
}