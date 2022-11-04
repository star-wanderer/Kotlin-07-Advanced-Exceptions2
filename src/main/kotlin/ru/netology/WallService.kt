package ru.netology

object WallService {
    private var posts = emptyArray<Post>()

/* +++++++++++   Start of tasks 1,2,3 for "Домашнее задание к занятию «3.1. Исключения»"  ++++++++++++ */

    private var comments = emptyArray<Comment>()
    private var reportComments = emptyArray<ReportComment>()

    fun createComment(postId: Int, comment: Comment): Comment {
        for ((index) in posts.withIndex()){
            if (posts[index].id == postId) {
                comments += comment.copy (id = if (comments.isEmpty()) 0x0001 else (comments.last().id + 1) , replyToPost = postId)
                return comments.last() }
        }
        throw PostNotFoundException(postId)
    }

    fun reportComment(commentId: Int, reportComment: ReportComment): ReportComment {
        for ((index) in comments.withIndex()){
            if (comments[index].id == commentId) {
                reportComments += reportComment.copy(commentId = if (reportComments.isEmpty()) 0x0001 else (reportComments.last().commentId + 1))
                return reportComments.last() }
        }
        throw CommentNotFoundException(commentId)
    }

/* +++++++++++    End of tasks 1,2,3 for "Домашнее задание к занятию «3.1. Исключения»"   ++++++++++++++ */

    fun clear(){
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        posts += post.copy(id =  if (posts.isEmpty()) 0x0001 else posts.last().id + 1 )
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index,postOnWall) in posts.withIndex()){
            if (postOnWall.id == post.id) {
                posts[index] = posts[index].copy( id = post.id,
                                                  ownerId = post.ownerId,
                                                  // fromId  -> Excluding as per task
                                                  // createdBy -> Excluding as per task
                                                  // date -> Excluding as per task
                                                  text = post.text,
                                                  replyOwnerId = post.replyOwnerId,
                                                  replyPostId = post.replyPostId,
                                                  friendsOnly = post.friendsOnly,
                                                  likes = post.likes,
                                                  comments = post.comments)
                return true
            }
        }
        return false
    }
}