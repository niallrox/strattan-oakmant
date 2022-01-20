const content = document.querySelector('.content')
const table = document.querySelector('.table')
const ok = document.querySelector('.ok')
const krest = document.querySelector('.krest')
const ok_img = 'http://localhost:63342/only_js_kurs_bd/content/ok.png'
const krest_img = 'http://localhost:63342/only_js_kurs_bd/content/krest2.png'




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
        text_row = '<span>' + elem[0] + '<span/>' + '<span>' + elem[1] + '<span/>' + '<span>' + elem[2] + '<span/>' + '<span>' + elem[3] + '<span/>' + '<span>' + elem[4] + '<span/>'
        row.className = 'row'
        row.innerHTML = text_row
        table.append(row)
    })
}
document.onclick = function( e ) {
    let name = e.target.parentElement.parentElement.querySelector('.name').innerHTML
    let surname = e.target.parentElement.parentElement.querySelector('.surname').innerHTML
    if (e.target.className === 'ok') {
        send_result(name, surname)
        animation_remove(e)
    }
    if (e.target.className === 'krest'){
        send_result(name, surname)
        animation_remove(e)
    }
}
function animation_remove(e) {
    e.target.parentElement.parentElement.style.pointerEvents = 'none'
    e.target.style.transform = 'scale(1.5)'
    setTimeout(() => {
        e.target.style.transform = 'scale(1)'
    }, 300)
    setTimeout(() => {
        e.target.parentElement.parentElement.style.transform = 'scale(0)'
    }, 400)
    setTimeout(() => {
        e.target.parentElement.parentElement.remove()
    }, 800)
}

function send_result(name, surname) {
    fetch('http://localhost:17627/api/seller/courses', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        }
    }).then((response) => {
        if (response.ok){
            return response.json()
        } else {
            window.location.replace('https://se.ifmo.ru/~s286535/html/login.html')
        }
    }).then((body) => {

    })
}
