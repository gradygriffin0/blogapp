export default function Navbar(props) {
    return `<nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-5">
    <a class="navbar-brand mx-4" href="/" data-link >  Grady's Blog</a>
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarsExample03"
            aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse collapse" id="navbarsExample03" style="">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/" data-link >Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/posts" data-link >Posts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/about" data-link >About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login" data-link >Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register" data-link >Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/users" data-link >Users</a>
            </li>
        </ul>
       
    </div>
</nav>
        
    `;
}
// <nav class="d-flex justify-content-around">
//     <a href="/" data-link>Home</a>
//     <a href="/posts" data-link>Posts</a>
//     <a href="/about" data-link>About</a>
//     <a href="/login" data-link>Login</a>
//
//     <a href="/register" data-link>Register a new account</a>
//     <a href="/users" data-link>View Users</a>
// </nav>

