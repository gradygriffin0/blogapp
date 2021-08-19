export default function Users(props) {
    return `
    
    <div class="container my-5">
    <div class="row row-cols-3"> ${props.users.map(user => `<div data-id = "${user.id}" class="col-md-6 overflow-auto">
                      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                          <strong class="d-inline-block mb-2 text-primary">${user.email}</strong>
                          <h3 class="titleClass mb-0">${user.username}</h3>
                          <a data-id="${user.id}" href="#" class="viewAnchor">View posts(${user.posts.length})</a>
                        </div>
                        <div class="col-auto d-lg-block">
                        <img class="p-1 m-3" src="assets/avatar.svg"  style="width: 100px; height: 100px" alt="Avatar">
                        </div>
                      </div>
                    </div>`).join('')}
    </div>
    </div>`
}


export function loadUserEvents(){
    anchorClick();
}

function anchorClick(){
    $(".viewAnchor").click(function(){

    })
}
