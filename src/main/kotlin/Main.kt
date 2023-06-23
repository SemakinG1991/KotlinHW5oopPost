fun main(args: Array<String>) {
    println("Hello World,Kotlin!")
    val loveLike = Likes(10)
    val firstPost = Post(1, 11, 30, "TEXT", "REPLY", true, loveLike)
    WallService.add(firstPost)
    WallService.add(Post(2, 23, 23, "Hi!", "Type", true, loveLike))
    WallService.print()
    println(WallService.update(Post(1, 11, 30, "T", "Rep", true, Likes(50))))
    println(WallService.update(Post(2, 11, 30, "T", "Rep", true, Likes(55))))
    println(WallService.update(Post(3, 11, 30, "T", "Rep", true, Likes(99))))
    WallService.print()
}

data class Post(
    val id: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val postType: String,
    val canPin: Boolean,
    val likes: Likes = Likes()
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false
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





