//1.发送请求
//2.修改dom树
function renderAuthor(currentUser){
    document.querySelector(".author-avatar").src=currentUser.avatar
    document.querySelector(".author-username").textContent=currentUser.username
    document.querySelector(".author-git").href=currentUser.gitRepo
}
function renderCount(articleCount,typeCount) {
    document.querySelector(".article-count").textContent=articleCount
    document.querySelector(".type-count").textContent=typeCount
}
function renderArticleList(articleList) {
    var container = document.querySelector(".articleList");

    for (var i in articleList) {
        var article=articleList[i]
        var html= `<div class="blog">`+
            `<div class="title">${article.title}</div>`+
            `<div class="date">${article.publishedAt}</div>`+
            `<div class="desc">${article.summary}</div>`+
            `<a href="blog_content.html?aid=${article.aid}" class="detail">查看全文 &gt;&gt;</a>`+
        `</div>`
        container.innerHTML+=html
    }
}
var xhr = new XMLHttpRequest();
xhr.open("get","/article-list.json")
xhr.onload=function (){
    alert(this.responseText)
    var data=JSON.parse(this.responseText)
    if(!data.currentUser){
        //如果没有登录，则重定向到登录页面
        location.assign("/login.html")
        return
    }
    renderAuthor(data.currentUser)
    renderCount(data.articleCount,data.typeCount)
    renderArticleList(data.articleList)
}
xhr.send()