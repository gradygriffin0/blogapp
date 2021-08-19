import createView from "../createView.js";
import fetchData from "../fetchData.js";

var prop;

export default function PostIndex(props) {
    prop = props;
    return `

        <div class="container">
                <div class="p-4 p-md-5 mb-4 text-white rounded bg-dark">
                    <div class="col-md-6 px-0">
                        <h1 class="display-4 fst-italic">Grady's Blog has launched!</h1>
                        <br>
                        <p class="lead my-3">Welcome to the jungle.</p>
                        <a id="createAnchor" href="#parentCreate">Click to create a post</a>
                    </div>
                </div>
                
                <main>
             <div class="row mb-2">01
             ${getPostsComponent(props.posts)}
          </div>  
            <div id="parentCreate" class="container my-5">   
                <div id="createHouse" style="width: 80%; margin: auto;" class="d-flex justify-content-center d-none">
                    <div class="input-group">
                        <div id="createPost" class="col-md-12">
                            <div class="new-post row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                                <div class="col p-4 d-flex flex-column position-static">
                                    <strong class="d-inline-block mb-2 text-primary">Create new post:</strong>
                                    <input id="titleBody" type="text" placeholder="Title.." aria-label="title" aria-describedby="basic-addon1">
                                    <br>
                                    <textarea id="textBody" type="text" placeholder="Body.." aria-label="textBody" aria-describedby="basic-addon2"class="form-control mb-auto"></textarea>
                                </div>
                               <button id="createPostButton" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="putHouse" style="width: 80%; margin: auto;" class="d-flex justify-content-center d-none">
                    <div class="input-group">
                        <div id="putPost" class="col-md-12">
                            <div class="new-post row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                                <div class="col p-4 d-flex flex-column position-static">
                                    <strong class="d-inline-block mb-2 text-primary">Create new post:</strong>
                                    <input id="putTitleBody" type="text" placeholder="Title.." aria-label="title" aria-describedby="basic-addon1">
                                    <br>
                                    <textarea id="putTextBody" type="text" placeholder="Body.." aria-label="textBody" aria-describedby="basic-addon2"class="form-control mb-auto"></textarea>
                                </div>
                               <button id="putPostButton" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>   
        </main>
           
    `;
}

export function loadEvents() {
    postEvent();
    putEvent(prop);
    deleteEvent();
}
function getPostsComponent(posts){
    return posts.map(post => ` <div data-id = "${post.id}" class="col-md-6 overflow-auto">
                      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                          <strong class="d-inline-block mb-2 text-primary">${post.categories[0].name}</strong>
                          <h3 contenteditable="" class="titleClass mb-0">${post.title}</h3>
                          <p contenteditable="" class="contentClass card-text mb-auto">${post.content}.</p>
                          <a data-id="${post.id}" href="#" class="editAnchor">Edit</a>
                          <a data-id="${post.id}" href="#" class="deleteAnchor">Delete</a>
                        </div>
                        <div class="col-auto d-none d-lg-block">
                          <svg class="bd-placeholder-img " width="200" height="250" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
                        </div>
                      </div>
                    </div>`).join('')
}
function deleteEvent(){
    $(".deleteAnchor").each(function(){
        $(this).click(function(){
            let id = $(this).attr("data-id");
            if(confirm("Are you sure you want to delete this post?")){
                $.ajax({
                    url: `http://localhost:8080/api/posts/${id}`,
                    type: "DELETE",
                    headers: {"content-type": "application/json"},
                }).success(function(data){
                    createView("/posts");
                })
            }
        })
    })
}

export function putEvent(prop) {
    $(".editAnchor").each(function(){
        $(this).click(function (e) {
            $('.editAnchor').text("Edit");
            $(this).text("Save");


            $('.titleClass, .contentClass').attr('contenteditable', false);
            var $titleEl = $(this).siblings(".titleClass");
            $titleEl.attr('contenteditable', true);
            // var $titleInput = $('<input/>').val( $titleEl.text() )
            //     $titleEl.replaceWith($titleInput);
            var $contentEl = $(this).siblings(".contentClass");
            $contentEl.attr('contenteditable', true);



            $(this).on('click', submitSavedEdits)


            // var $contentInput = $('<input/>').val( $contentEl.text())
            // $contentEl.replaceWith($contentInput);

            // $titleEl.on('blur', function(){
            //     console.log("test1")
            //     $titleEl.replaceWith($('<h3 data-edibtable class="titleClass mb-0">').val($titleEl.text()))
            // })
            // $contentEl.blur(function(){
            //     console.log("test2")
            //     $contentEl.replaceWith($('<p class="contentClass card-text mb-auto">').val($contentEl.text()))
            // })

        })
    })

}
function submitSavedEdits(){
    let $content = $(this).siblings(".contentClass");
    let $title = $(this).siblings(".titleClass");
    let post = {
        id: $(this).attr('data-id'),
        title: $title.text(),
        content: $content.text()
    }
    $.ajax({
        url: `http://localhost:8080/api/posts/${post.id}`,
        type: "PUT",
        headers: {"content-type": "application/json"},
        data: JSON.stringify(post)

    })
    $(this).text("Edit");
    $(this).off('click', submitSavedEdits);
}
export function postEvent() {
    $("#createAnchor").click(function (e) {

        $("#createHouse").toggleClass("d-none");
        document.getElementById('parentCreate').scrollIntoView(true);

        $("#createPostButton").click(function () {
            let post = {
                "title": `${$("#titleBody").val()}`,
                "content": `${$("#textBody").val()}`
            }
            let readyPost = JSON.stringify(post);

            $.ajax({
                url: "http://localhost:8080/api/posts",
                type: "POST",
                headers: {"content-type": "application/json"},
                data: readyPost

            }).success(function (e) {
                createView("/posts");
            }).error(function (e) {
            })

        })

    })


}