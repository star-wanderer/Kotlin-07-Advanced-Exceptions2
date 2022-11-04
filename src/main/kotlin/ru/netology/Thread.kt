package ru.netology

data class Thread (
    val count: Int = 0,
    val items: Array<Comment> = emptyArray<Comment>(),
    val canPost: Boolean = false,
    val showReplyButton: Boolean = true,
    val groupsCanPost: Boolean = false
)