import createView from "../createView.js";

export default function Register(props){
    return `
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Register</title>
</head>
<body>
<section class="vh-100" style="background-color: #eee;">
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px;">
          <div class="card-body p-md-5">
            <div class="row justify-content-center">
              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Register An Account</p>

                <form class="mx-1 mx-md-4">

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input id="usernameInput" type="text" id="form3Example1c" class="form-control" />
                      <label class="form-label" for="form3Example1c">Username</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input id="emailInput" type="email" id="form3Example3c" class="form-control" />
                      <label class="form-label" for="form3Example3c">Email</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input id="passwordInput" type="password" id="form3Example4c" class="form-control" />
                      <label class="form-label" for="form3Example4c">Password</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input id="passwordConfirmInput" type="password" id="form3Example4cd" class="form-control" />
                      <label class="form-label" for="form3Example4cd">Confirm Password</label>
                    </div>
                  </div>

                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                    <button id="registerButton" type="button" class="btn btn-primary btn-lg">Register</button>
                  </div>

                </form>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>`;
}

export function LoadRegisterEvents(){
    submitForm();

}

function submitForm(){
    $("#registerButton").click(function(){
        let flag = false;
        if (!($("#usernameInput").val().length >= 5)){
            alert("Username needs to be at-least 5 characters");
            flag = true;
        }
        if (!($("#emailInput").val().length >= 5)){
            alert("Email needs to be at-least 5 characters");
            flag = true;
        }

        // if (!($("#emailInput").val().contains('@'))){
        //     alert("Does not match standard email format");
        //     flag = true;
        // }

        // if (!($("#passwordInput").val().length >= 5)){
        //     alert("Password needs to be at-least 5 characters");
        //     flag = true;
        // }
        if (!($("#passwordInput").val() === $("#passwordConfirmInput").val())){
            alert("Passwords do not match");
            flag = true;
        }
        if (flag === true){
            return;
        } else {
            // createdAt: new Date().getTime(),
            let user = {
                username: $("#usernameInput").val(),
                email: $("#emailInput").val(),
                password: $("#passwordInput").val(),
                role: 'USER'
            }
            $.ajax({
                url: "http://localhost:8080/api/users/create",
                method: "POST",
                headers: {
                    "accept" : "application/json",
                    "content-type" : "application/json"},
                data: JSON.stringify(user),
            }).success(function(data){
                console.log(data)
                createView('/')
            }).error(function(e){
                console.log(e);
            })
        }
    })
}