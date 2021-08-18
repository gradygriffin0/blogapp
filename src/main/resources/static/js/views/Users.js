


export default function Users(props){
    return `${props.users.map(user => `<div>${user.username}</div>`).join('')}`
}