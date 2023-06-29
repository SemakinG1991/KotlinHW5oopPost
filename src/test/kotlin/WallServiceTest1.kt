import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addId() {
        val service = WallService
        val post = service.add(
            Post(
                1, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut = null, comments = null,
                copyright = null, geo = null, Likes(50), reposts = null, 0
            )
        )
        val result = post.id
        assertEquals(1, result)
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(
            Post(
                1, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut = null, comments = null,
                copyright = null, geo = null, Likes(50), reposts = null, 0
            )
        )

        service.add(
            Post(
                2, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut = null, comments = null,
                copyright = null, geo = null, Likes(50), reposts = null, 0
            )
        )

        val update = Post(
            2, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut = null, comments = null,
            copyright = null, geo = null, Likes(50), reposts = null, 0
        )
        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(
            Post(
                1, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut = null, comments = null,
                copyright = null, geo = null, Likes(50), reposts = null, 0
            )
        )
        service.add(
            Post(
                2, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut = null, comments = null,
                copyright = null, geo = null, Likes(50), reposts = null, 0
            )
        )

        val update = Post(
            3, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut = null, comments = null,
            copyright = null, geo = null, Likes(50), reposts = null, 0
        )
        val result = service.update(update)

        assertFalse(result)
    }

    @Test
    fun createCommentId() {
        val service = WallService
        val commOrig = Comments(3, 2, 2, "MyCommentaryOr", null, 1, 1, null)
        val commTest = Comments(5, 2, 2, "MyCommentaryTst", null, 1, 1, null)
        val post = service.add(
            Post(
                1, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut = null, comments = commOrig,
                copyright = null, geo = null, Likes(50), reposts = null, 0
            )
        )
        val commentar = service.createComment(1, commTest)
        val result = commentar.id
        assertEquals(1, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val service = WallService
        val commOrig = Comments(3, 2, 2, "MyCommentaryOr", null, 1, 1, null)
        val commTest = Comments(5, 2, 2, "MyCommentaryTst", null, 1, 1, null)
        service.add(
            Post(
                10, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut = null, comments = commOrig,
                copyright = null, geo = null, Likes(50), reposts = null, 0
            )
        )
        service.createComment(10, commTest)
    }

}