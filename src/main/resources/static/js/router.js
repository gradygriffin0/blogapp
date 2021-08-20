import Home from "./views/Home.js";
import PostIndex from "./views/PostIndex.js";
import About from "./views/About.js";
import Error404 from "./views/Error404.js";
import Loading from "./views/Loading.js";
import Login from "./views/Login.js";
import Register from "./views/Register.js"
import LoginEvent from "./auth.js";
import {loadEvents} from "./views/PostIndex.js";
import {LoadRegisterEvents} from "./views/Register.js"
import Users, {loadUserEvents} from "./views/users.js"
/**
 * Returns the route object for a specific route based on the given URI
 * @param URI
 * @returns {*}
 */
export default function router(URI) {
    const routes = {
        '/': {
            returnView: Home,
            state: {},
            uri: '/',
            title: 'Home',
        },
        '/login': {
            returnView: Login,
            state: {},
            uri: '/login',
            title: "Login",
            viewEvent: LoginEvent
        },
        '/users':{
          returnView: Users,
            state: {
              users: '/api/users'
            },
            uri: '/users',
            title: 'Users',
            viewEvent: loadUserEvents
        },
        '/register': {
            returnView: Register,
            state: {
                users: '/api/users'
            },
            uri: '/register',
            title: 'Register',
            viewEvent: LoadRegisterEvents
        },
        '/posts': {
            returnView: PostIndex,
            state: {
                posts: '/api/posts',
                users: '/api/users',
                categories: '/api/categories'
            },
            uri: '/posts',
            title: 'All Posts',
            viewEvent: loadEvents
        },
        '/about': {
            returnView: About,
            state: {},
            uri: '/about',
            title: 'About',
        },
        '/error': {
            returnView: Error404,
            state: {},
            uri: location.pathname,
            title: ' ERROR',
        },
        '/loading': {
            returnView: Loading,
            state: {},
            uri: location.pathname,
            title: 'Loading...',
        }

    };

    return routes[URI];
}

