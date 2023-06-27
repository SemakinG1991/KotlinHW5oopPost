fun main(args: Array<String>) {
    println("Hello World,Kotlin!")
    val loveLike = Likes(10)
    val donut = Donut()
    val comments = Comments()
    val copyright = Copyright()
    val geo = Geo("One","Two")
    val reposts = Reposts()
    val views = Views()

    val firstPost =
        Post(
            id = 1,
            ownerId = 11,
            fromId = 30,
            text = "TEXT",
            postType = "REPLY",
            canDelete = true,
            likes = loveLike,
            donut = donut,
            comments = comments,
            copyright = copyright,
            geo = geo,
            reposts = reposts,
            views = views
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
            likes = loveLike,
            donut = donut,
            comments = comments,
            copyright = copyright,
            geo = geo,
            reposts = reposts,
            views = views
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
                likes = loveLike,
                donut = donut,
                comments = comments,
                copyright = copyright,
                geo = geo,
                reposts = reposts,
                views = views
            )
        )
    )
    println(
        WallService.update(
            Post(
                id = 2,
                ownerId = 22,
                fromId = 30,
                text = "T",
                postType = "REP",
                canDelete = true,
                likes = loveLike,
                donut = donut,
                comments = comments,
                copyright = copyright,
                geo = geo,
                reposts = reposts,
                views = views
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
                likes = loveLike,
                donut = donut,
                comments = comments,
                copyright = copyright,
                geo = geo,
                reposts = reposts,
                views = views
            )
        )
    )
    WallService.print()
}

data class Post(
    val id: Int = 0,                    //Идентификатор записи.
    val ownerId: Int = 0,               //Идентификатор владельца стены
    val fromId: Int = 0,                //Идентификатор автора записи
    val createdBy: Int = 0,             //Идентификатор администратора
    val date: Int = 0,                  //Время публикации записи
    val text: String = "",               //Текст записи
    val replyOwnerId: Int = 0,               //Идентификатор владельца записи, в ответ на которую была оставлена текущая.
    val replyPostId: Int = 0,                //Идентификатор записи, в ответ на которую была оставлена текущая.
    val views: Views?,                   //Информация о просмотрах записи. Объект с единственным полем
    val postType: String = " ",                //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
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
    val likes: Likes=Likes(),          //Информация о лайках к записи, объект с полями
    val reposts: Reposts?,              //Информация о репостах записи («Рассказать друзьям»), объект с полями
    val postponedId: Int = 0               //Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере.
)

data class Donut(
    val is_donut: Boolean = true,   //запись доступна только платным подписчикам VK Donut;
    val PaidDuration: Int = 0,      //время, в течение которого запись будет доступна только платным подписчикам VK Donut;
//val placeholder (object) — заглушка для пользователей, которые не оформили подписку VK Donut. Отображается вместо содержимого записи.
    val canPublishFreeCopy: Boolean = true, //можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: String = "", //информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
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
    val count: Int = 0,             // количество комментариев
    val canPost: Boolean = false,   //информация о том, может ли текущий пользователь комментировать запись
    val groupsCanPost: Boolean = false,   //информация о том, могут ли сообщества комментировать запись
    val canClose: Boolean = false,  //может ли текущий пользователь закрыть комментарии к записи
    val canOpen: Boolean = false  // может ли текущий пользователь открыть комментарии к записи
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

object WallService {

    private var posts = emptyArray<Post>()
    private var lastID: Int = 0
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
}





