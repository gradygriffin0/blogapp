let loadedPosts = [];

function getPosts() {
    $.ajax({
        url: "http://localhost:8080/api/posts",
        type: "GET"
    }).success(function (data) {
        data.forEach(function (obj){
            massagePosts(obj);
            appendPost(obj);
        })

    }).error(function (error) {
        console.log(error);
    })
}

getPosts();

function massagePosts(data) {
    let post = {
        id: data.id,
        title: data.title,
        content: data.content
    }

    loadedPosts.push(post);
}