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
        val serviceK = WallService
        val postK = serviceK.add(
            Post(1, 11, 30, 5, 5, "T",
                5, 5, views = null, "Rep", 5, true, true,
                true, true, true, true, donut=null,comments = null,
                copyright = null,geo = null, Likes(50),reposts = null,0)
        )
        val result = postK.id
        assertEquals(1, result)
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(1, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut=null,comments = null,
            copyright = null,geo = null, Likes(50),reposts = null,0))

        service.add(Post(2, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut=null,comments = null,
            copyright = null,geo = null, Likes(50),reposts = null,0))

        val update = Post(2, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut=null,comments = null,
            copyright = null,geo = null, Likes(50),reposts = null,0)
        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(Post(1, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut=null,comments = null,
            copyright = null,geo = null, Likes(50),reposts = null,0))
        service.add(Post(2, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut=null,comments = null,
            copyright = null,geo = null, Likes(50),reposts = null,0))

        val update = Post(3, 11, 30, 5, 5, "T",
            5, 5, views = null, "Rep", 5, true, true,
            true, true, true, true, donut=null,comments = null,
            copyright = null,geo = null, Likes(50),reposts = null,0)
        val result = service.update(update)

        assertFalse(result)
    }


}