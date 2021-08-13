
function appendPost(jsonPost){
    $("#postHouse").append(`<div class="border my-3 mx-3 bg-light" id="post${jsonPost.id}">
        <h3 class="display-4">${jsonPost.title}</h3>
        <div>${jsonPost.content}</div>
</div>`)
}