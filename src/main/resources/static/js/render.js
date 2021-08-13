import Navbar from "./views/partials/Navbar.js";

/**
 * Pushes the current URI to the URL bar and sets the HTML of the app div.
 * @param props - the data required for view rendering
 * @param route - the object containing information for the given endpoint
 */
export default function render(props, route) {
    const app = document.querySelector('#app');
    const title = `REST Blog - ${route.title}`;
    history.pushState(props, title, route.uri);
    document.title = title;

    // append the specific view to the html
    app.innerHTML = `${Navbar(null)} ${route.returnView(props)}`;

    //if view event is there add it AFTER the view is appended
    if (route.viewEvent){
        route.viewEvent();
    }
}
