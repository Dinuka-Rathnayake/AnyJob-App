package com.anyjob.anyjob.activities.model

class CommentModel {
    var id: String = ""
    var user: String = ""
    var date: String = ""
    var time: String = ""
    var comment: String = ""
    var isDeleted: Boolean = false


    constructor(
        id: String,
        user: String,
        date: String,
        time: String,
        comment: String,
        isDeleted: Boolean
    ) {
        this.id = id
        this.user = user
        this.date = date
        this.time = time
        this.comment = comment
        this.isDeleted = isDeleted
        this.isDeleted = isDeleted
    }

    constructor() {
    }
}