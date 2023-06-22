import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun update() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(1, 11, 30, "T", "Rep", true, Likes(50)))
        service.add(Post(2, 11, 30, "T", "Rep", true, Likes(50)))

        // создаём информацию об обновлении
        val update = Post(1, 55, 30, "T", "Rep", true, Likes(50))

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(result)
    }
}