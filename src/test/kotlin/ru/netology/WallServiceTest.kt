package ru.netology

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun ClearBeforeTest(){
        WallService.clear()
    }

    @Test
    fun addNewPost() {

        // Arrange
        val postIdOfFirstPost = 0x0001
        // Act -> Adding first post to the Wall
        var myPost = WallService.add(Post(text = "This is my FIRST post"))
        // Assert -> Checking id of Post after adding it to the Wall
        assertTrue(myPost.id == postIdOfFirstPost)
    }

    @Test
    fun updateExistingPostSuccess() {

        // Arrange
        WallService.add(Post(text = "This is my FIRST post"))
        val myPost = WallService.add(Post(text = "This is my SECOND post"))
        WallService.add(Post(text = "This is my THIRD post"))

        // Act -> Adding third post to the Wall
        var postUpdatePassed = WallService.update(myPost.copy(text = "This is my SECOND post and there will be more" ))

        // Assert -> Checking existing post updates passed
        assertTrue(postUpdatePassed)
    }

    @Test
    fun updateNonExistingPostFailure() {

        // Arrange
        WallService.add(Post(text = "This is my FIRST post"))
        WallService.add(Post(text = "This is my SECOND post"))
        WallService.add(Post(text = "This is my THIRD post"))

        // Act -> Adding third post to the Wall
        var postUpdateFailed = WallService.update(Post(text = "This is my FOURTH post"))

        // Assert -> Checking non-existing post updates failed
        assertFalse(postUpdateFailed)
    }

    @Test
    fun checkVideoDurationWhen_LiveVideoOn() {

        // Arrange -> create video attachment
        val videoAttachment = VideoAttachment(video=Video(live = 1, duration = 0x64))

        // Act & Assert -> check duration
         assertEquals(0x00, videoAttachment.video.duration)
    }

    @Test
    fun checkVideoDurationWhen_LiveVideoOff() {

        // Arrange -> create video attachment
        val videoAttachment = VideoAttachment(video = Video(live = 0, duration = 0x64))

        // Act & Assert -> check duration
        assertEquals(0x64, videoAttachment.video.duration)
    }

    @Test
    fun checkVideoUpcomingWhen_LiveVideoOn() {

        // Arrange -> create video attachment
        val videoAttachment = VideoAttachment(video=Video(live = 1))

        // Act & Assert -> check upcoming value
        assertEquals(0x01, videoAttachment.video.upcoming)
    }

    @Test
    fun checkVideoUpcomingWhen_LiveVideoOff() {

        // Arrange -> create video attachment
        val videoAttachment = VideoAttachment(video=Video(live = 0))

        // Assert -> check upcoming value
        assertEquals(-0x01, videoAttachment.video.upcoming)
    }

    @Test
    fun checkSetWikiPageAttachmentProperty() {

        // Arrange -> create wiki page attachment
        val wikiPageAttachment = WikiPageAttachment()

        // Act & Assert -> check property
        assertEquals(true, wikiPageAttachment.wikiPage is WikiPage)
    }

    @Test
    fun checkPostAttachmentsQuantity() {

        // Arrange -> create wiki page attachment
        var post = Post(
            text = "Just a post with 4 attachments",
            attachment = arrayOf(
                WikiPageAttachment(),
                VideoAttachment(),
                AudioAttachment(),
                NoteAttachment()
            )
        )

        // Act & Assert -> check array size
        assertEquals(4, post.attachment.size)
    }

/* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
/* +++++++++++   Start of tasks 1,2,3 for "Домашнее задание к занятию «3.1. Исключения»"  ++++++++++++ */

    @Test
    fun addCommentToExistingPost() {

        // Arrange -> create several posts
        WallService.add(Post(text = "This is my FIRST post"))
        WallService.add(Post(text = "This is my SECOND post"))
        WallService.add(Post(text = "This is my THIRD post"))
        var comment = Comment(text ="Comment to the SECOND post")

        // Act & Assert -> Make comment to the existing post and check result
        assertEquals(comment.text, WallService.createComment(0x0002,comment).text)
        }

    @Test (expected = PostNotFoundException::class)
    fun addCommentToMissingPost() {

        // Arrange -> create several posts
        WallService.add(Post(text = "This is my FIRST post"))
        WallService.add(Post(text = "This is my SECOND post"))
        var comment = Comment(text ="Comment to the FIFTH post")

        // Act -> make comment to the non-existing post
        WallService.createComment(0x0005,comment)
    }

    @Test
    fun addReportCommentToExistingComment() {

        // Arrange -> create several posts
        WallService.add(Post(text = "This is my FIRST post"))
        WallService.add(Post(text = "This is my SECOND post"))
        var comment =WallService.createComment(0x0002,Comment(text ="Comment to the SECOND post"))
        var reportComment = WallService.reportComment(comment.id,ReportComment(reason = 0))

        // Assert -> Make comment to the existing post and check result
        assertEquals(comment.id, reportComment.commentId)
    }

    @Test (expected = CommentNotFoundException::class)
    fun addReportCommentToMissingComment() {

        // Arrange -> create several posts
        WallService.add(Post(text = "This is my FIRST post"))
        WallService.add(Post(text = "This is my SECOND post"))
        WallService.createComment(0x0002,Comment(text ="Comment to the SECOND post"))

        // Act -> Try to report comment which does not exist
         WallService.reportComment(commentId = 0x0007, ReportComment(reason = 4))
    }
/* +++++++++++   End of tasks 1,2,3 for "Домашнее задание к занятию «3.1. Исключения»"  ++++++++++++ */
/* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
}