import java.lang.RuntimeException

fun main(args: Array<String>) {
    println("Hello World,Kotlin!")
    val myLike = Likes(10)
    val myDonut = Donut()
    val myComments = Comments(1, 1, 1, "MyComm1", null, 1, 1, null)
    val myExComments = Comments(2, 2, 2, "MyComm2", null, 1, 1, null)
    val copyright = Copyright()
    val geo = Geo("One", "Two")
    val reposts = Reposts()
    val views = Views()
    val attachments = arrayOf(
        PhotoAttachment(Photo(1, 1)),
        VideoAttachment(Video(2, 2)),
        AudioAttachment(Audio(3, 3)),
        GraffitiAttachment(Graffiti(4, 4)),
        StickerAttachment(Sticker(5, 5))
    )

    val firstPost =
        Post(
            id = 1,
            ownerId = 11,
            fromId = 30,
            text = "TEXT",
            postType = "REPLY",
            canDelete = true,
            likes = myLike,
            donut = myDonut,
            comments = myExComments,
            copyright = copyright,
            geo = geo,
            reposts = reposts,
            views = views,
            attachments = attachments
        )
    WallService.add(firstPost)
    WallService.add(
        Post(
            id = 2,
            ownerId = 11,
            fromId = 30,
            text = "T",
            postType = "REP",
            canDelete = true,
            likes = myLike,
            donut = myDonut,
            comments = myComments,
            copyright = copyright,
            geo = geo,
            reposts = reposts,
            views = views,
            attachments = attachments
        )
    )
    WallService.print()
    println(
        WallService.update(
            Post(
                id = 1,
                ownerId = 11,
                fromId = 30,
                text = "T",
                postType = "REP",
                canDelete = true,
                likes = myLike,
                donut = myDonut,
                comments = myComments,
                copyright = copyright,
                geo = geo,
                reposts = reposts,
                views = views,
                attachments = attachments
            )
        )
    )
    println(
        WallService.update(
            Post(
                id = 3,
                ownerId = 33,
                fromId = 30,
                text = "T",
                postType = "REP",
                canDelete = true,
                likes = myLike,
                donut = myDonut,
                comments = myComments,
                copyright = copyright,
                geo = geo,
                reposts = reposts,
                views = views,
                attachments = attachments
            )
        )
    )
    WallService.createComment(1, myExComments)   //4
    WallService.print()

}

data class Post(
    val id: Int = 0,                    //Идентификатор записи.
    val ownerId: Int = 0,               //Идентификатор владельца стены
    val fromId: Int = 0,                //Идентификатор автора записи
    val createdBy: Int = 0,             //Идентификатор администратора
    val date: Int = 0,                  //Время публикации записи
    val text: String = "Default text",               //Текст записи
    val replyOwnerId: Int = 0,               //Идентификатор владельца записи, в ответ на которую была оставлена текущая.
    val replyPostId: Int = 0,                //Идентификатор записи, в ответ на которую была оставлена текущая.
    val views: Views?,                   //Информация о просмотрах записи. Объект с единственным полем
    val postType: String = "Default postType",                //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val signerId: Int = 0,                   //Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем;
    val canPin: Boolean = true,                 //Информация о том, может ли текущий пользователь закрепить запись
    val canDelete: Boolean = true,                 //Информация о том, может ли текущий пользователь удалить запись
    val canEdit: Boolean = true,                   //Информация о том, может ли текущий пользователь редактировать запись
    val isPinned: Boolean = true,              //Информация о том, что запись закреплена
    val markedAsAds: Boolean = true,           //Информация о том, содержит ли запись отметку "реклама"
    val isFavorite: Boolean = true,            //true, если объект добавлен в закладки у текущего пользователя.
    val donut: Donut?,                  //Информация о записи VK Donut:
    val comments: Comments?,            //Информация о комментариях к записи, объект с полями
    val copyright: Copyright?,          //Источник материала, объект с полями
    val geo: Geo?,                      //Информация о местоположении, содержит поля
    val likes: Likes = Likes(),          //Информация о лайках к записи, объект с полями
    val reposts: Reposts?,              //Информация о репостах записи («Рассказать друзьям»), объект с полями
    val postponedId: Int = 0,               //Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере.
    val attachments: Array<Attachment> = arrayOf(
        PhotoAttachment(Photo(1, 1)),
        VideoAttachment(Video(2, 2)),
        AudioAttachment(Audio(3, 3)),
        GraffitiAttachment(Graffiti(4, 4)),
        StickerAttachment(Sticker(5, 5))
    )
)

data class Donut(
    val is_donut: Boolean = true,   //запись доступна только платным подписчикам VK Donut
    val PaidDuration: Int = 0,      //Время, в течение которого запись будет доступна только платным подписчикам VK Donut
//val placeholder (object) — заглушка для пользователей, которые не оформили подписку VK Donut. Отображается вместо содержимого записи.
    val canPublishFreeCopy: Boolean = true, //можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: String = " " // Информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
    //all — всю информацию о VK Donut.
    //duration — время, в течение которого запись будет доступна только платным подписчикам VK Donut.
)

data class Geo(
    val type: String,       //тип места
    val coordinates: String     //координаты места

)

data class Views(
    val count: Int = 0                  //число просмотров записи.
)

data class Reposts(
    val count: Int = 0,                 //число пользователей, скопировавших запись;
    val userReposted: Boolean = true       //наличие репоста от текущего пользователя
)

data class Comments(
    val id: Int = 0,                         // Идентификатор комментария.
    val fromId: Int = 0,                        // Идентификатор автора комментария.
    val date: Int = 0,                       //Дата создания комментария в формате Unixtime.
    val text: String = "Default text comment",  //Текст комментария.
    val donut: Donut?,                           // является ли комментатор подписчиком VK Donut.
    val reply_to_user: Int = 0,      //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
    val reply_to_comment: Int = 0,  //Идентификатор комментария, в ответ на который оставлен текущий (если применимо).
    val attachments: Attachment?     //Медиавложения комментария (фотографии, ссылки и т.п.). Описание массива attachments находится на отдельной странице.
)

data class Copyright(
    val id: Int = 0,
    val link: String = "",
    val name: String = "",
    val type: String = ""
)

data class Likes(
    val count: Int = 0,                 //число пользователей, которым понравилась запись;
    val userLikes: Boolean = false,     //наличие отметки «Мне нравится» от текущего пользователя
    val canLike: Boolean = false,       //информация о том, может ли текущий пользователь поставить отметку «Мне нравится»
    val canPublish: Boolean = false     //информация о том, может ли текущий пользователь сделать репост записи
)

interface Attachment {
    val type: String
}

data class Photo(
    val id: Int = 1,
    val ownerId: Int = 1
)

data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type = "photo"
}

data class Video(
    val id: Int = 2,
    val ownerId: Int = 2
)

data class VideoAttachment(val video: Video) : Attachment {
    override val type = "video"
}

data class Audio(
    val id: Int = 3,
    val ownerId: Int = 3
)

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type = "audio"
}

data class Graffiti(
    val id: Int = 4,
    val ownerId: Int = 4
)

data class GraffitiAttachment(val graffiti: Graffiti) : Attachment {
    override val type = "graffiti"
}

data class Sticker(
    val id: Int = 5,
    val ownerId: Int = 5
)

data class StickerAttachment(val sticker: Sticker) : Attachment {
    override val type = "sticker"
}

class PostNotFoundException(message: String) :
    RuntimeException(message) //3: throw PostNotFoundException("Нет Поста с таким $postId")

object WallService {

    private var posts = emptyArray<Post>()
    private var lastID: Int = 0
    private var lastIDComm: Int = 0
    private var comments = emptyArray<Comments>()            //1


    fun add(post: Post): Post {
        posts += post.copy(id = ++lastID, likes = post.likes.copy())
        return posts.last()
    }

    fun print() {
        for (post in posts) {
            print(post)
            println()
        }
    }

    fun clear() {
        comments = emptyArray()
        posts = emptyArray()
        lastID = 0
    }


    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(likes = newPost.likes.copy())
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, comment: Comments): Comments {     //2
        for (post in posts) {
            if (post.id == postId) {
                comments += comment.copy(id=++lastIDComm)
                println("Я добавил коммент в массив!")
                return comments.last()
            }
        }
        throw PostNotFoundException("Нет Поста с таким id: $postId")
    }
}






