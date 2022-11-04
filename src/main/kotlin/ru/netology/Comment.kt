/* +++++++++++   Start of tasks 1,2,3 for "Домашнее задание к занятию «3.1. Исключения»"  ++++++++++++ */
package ru.netology

data class Comment (
    val id: Int = 0x0000,
    val fromId: Int = 0x0000,
    val date: Int = 0x0000,
    val text: String = "enter your comment here",
    val replyToUser: Int = 0x0000,
    val replyToComment: Int = 0x0000,
    val attachments: Array<Attachment> =emptyArray<Attachment>(),
    val parentsStack: Array<Comment> = emptyArray<Comment>(),
    val thread: Thread = Thread(),
    val replyToPost: Int = 0x0000
)
/* +++++++++++    End of tasks 1,2,3 for "Домашнее задание к занятию «3.1. Исключения»"   ++++++++++++++ */