// import createView from "../createView";

export default function PostIndex(props) {
    return `
        <div class="container">
        <div class="p-4 p-md-5 mb-4 text-white rounded bg-dark">
            <div class="col-md-6 px-0">
                <h1 class="display-4 fst-italic">Grady's Blog has launched!</h1>
                <br>
                <p class="lead my-3">Welcome to the jungle.</p>
                <a id="createAnchor" href="#createNewPost">Click to create a post</a>
            </div>
        </div>
        </div>
        <main>
     <div class="row mb-2">
     ${props.posts.map(post => ` <div id ="post-${post.id}" class="col-md-6">
              <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                  <strong class="d-inline-block mb-2 text-primary">*New Post*</strong>
                  <h3 class="mb-0">${post.title}</h3>
                  <p class="card-text mb-auto">${post.content}.</p>
                  <a href="#" class="stretched-link">Continue reading</a>
                </div>
                <div class="col-auto d-none d-lg-block">
                  <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
                </div>
              </div>
            </div>`).join('')}
  </div>  
  <div id="parentCreate" class="container my-5">   
        <div id="createHouse" class="d-flex justify-content-center d-none">
            <div class="input-group">
                <div id="createPost" class="col-md-6">
                    <div class="new-post row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary">Create new post:</strong>
                            <input id="titleBody" type="text" placeholder="Title.." aria-label="title" aria-describedby="basic-addon1">
                            <br>
                            <textarea id="textBody" type="text" placeholder="Body.." aria-label="textBody" aria-describedby="basic-addon2"class="form-control mb-auto"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>   
        </main>
           
    `;
}


export function postEvent() {
    $("#createAnchor").click(function (e) {

        document.getElementById('#parentCreate').scrollIntoView(true);

        $("#createPost").toggleClass("d-none");

    })


    createView('/posts');
}