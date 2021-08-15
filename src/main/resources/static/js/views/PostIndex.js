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
             <div class="row mb-2">
             ${props.posts.map(post => ` <div data-id = "${post.id}" class="col-md-6">
                      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                          <strong class="d-inline-block mb-2 text-primary">*New Post*</strong>
                          <h3 data-edibtable class="titleClass mb-0">${post.title}</h3>
                          <p class="contentClass card-text mb-auto">${post.content}.</p>
                          <a data-id="edit-${post.id}" href="#" class="editAnchor">Edit</a>
                          <a data-id="delete-${post.id}" href="#" class="">Delete</a>
                        </div>
                        <div class="col-auto d-none d-lg-block">
                          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
                        </div>
                      </div>
                    </div>`).join('')}
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
}

export function putEvent(prop) {

    console.log(prop);
    $(".editAnchor").each(function(){
        console.log($(this));
        $(this).click(function (e) {
            console.log($(this))
            console.log($(this).parent());
            console.log($(this).siblings());
            var $titleEl = $(this).siblings(".titleClass");
            var $titleInput = $('<input/>').val( $titleEl.text() )
                $titleEl.replaceWith($titleInput);
            console.log($(this).siblings(".titleClass").val());
            console.log($(this).siblings(".contentClass").val());

        })
    })

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
            console.log(readyPost);

            $.ajax({
                url: "http://localhost:8080/api/posts",
                type: "POST",
                headers: {"content-type": "application/json"},
                data: readyPost

            }).success(function (e) {
                console.log(e);
                createView("/posts");
            }).error(function (e) {
                console.log(e);
            })

        })

    })


}