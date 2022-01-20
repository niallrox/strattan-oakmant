const burger_state = {state: true}
const functions_block = document.querySelector('.function_open')
const burger = document.querySelector('.burger')
const exit = document.querySelector('.exit')
const table = document.querySelector('.table')
const name = document.querySelector('.name')


//http://localhost:17627/api/seller/courses
fetch('http://localhost:17627/api/seller/courses', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': sessionStorage.getItem('token')
    }
}).then((response) => {
    if (response.ok){
        return response.json()
    } else {
        window.location.replace('https://se.ifmo.ru/~s286535/html/login.html')
    }
}).then((body) => {
    add_row(body)
})


function add_row(body) {
    let row
    let text_row
    body.forEach(elem => {
        row = document.createElement('div')
        text_row = '<span>' + elem['name'] + '</span>' + '<span>' + 'Цена: ' + elem['price'] + '$' + '</span>'
        row.className = 'row'
        row.innerHTML = text_row
        table.append(row)
    })
}

function set_burger(){
    burger_state.state = !burger_state.state
    if (burger_state.state){
        functions_block.style.display = 'none'
    } else {
        functions_block.style.display = 'flex'
    }
}

burger.addEventListener('click', set_burger)
exit.addEventListener('click', set_burger)
