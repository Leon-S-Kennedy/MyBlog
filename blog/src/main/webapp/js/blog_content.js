function renderAuthor(currentUser) {
    document.querySelector(".author-avatar").src=currentUser.avatar
    document.querySelector(".author-name").textContent=currentUser.username
    document.querySelector(".author-git").href=currentUser.gitRepo
}

function renderCount(articleCount,typeCount) {
    document.querySelector(".article-count").textContent=articleCount
    document.querySelector(".type-count").textContent=typeCount
}

function renderArticle(article) {
    document.querySelector(".blog-title").textContent=article.title
    document.querySelector(".date").textContent=article.publishedAt
    document.querySelector(".content").textContent=article.content
}

var xhr = new XMLHttpRequest();
xhr.open("get","/article-detail.json"+location.search)
xhr.onload=function () {
    alert(this.responseText)
    var data = JSON.parse(this.responseText);
    if(!data.currentUser){
        location.assign("/login.html")
        return
    }
    renderAuthor(data.currentUser)
    renderCount(data.articleCount,data.typeCount)
    renderArticle(data.article)

}
xhr.send()