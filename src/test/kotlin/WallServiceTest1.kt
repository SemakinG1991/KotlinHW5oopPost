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
        val postK = serviceK.add(Post(1, 11, 30, "T", "Rep", true, Likes(50)))
        val result = postK.id
        assertEquals(1, result)
    }
    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(1, 11, 30, "T", "Rep", true, Likes(50)))
        service.add(Post(2, 11, 30, "T", "Rep", true, Likes(50)))

        val update = Post(2, 55, 30, "T", "Rep", true, Likes(50))
        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(Post(1, 11, 30, "T", "Rep", true, Likes(50)))
        service.add(Post(2, 11, 30, "T", "Rep", true, Likes(50)))

        val update = Post(3, 55, 30, "T", "Rep", true, Likes(50))
        val result = service.update(update)

        assertFalse(result)
    }


}