const burger = document.querySelector('.burger')
const functions_block = document.querySelector('.function_open')
const content = document.querySelector('.content')
const reports = document.querySelector('.reports')
const modal_window = document.querySelector('.modal_window')
const exit = document.querySelector('.exit')
const burger_state = {state: true}
const modal_window_state = {state: false}
const name = document.querySelector('.name')
const button_ok = document.querySelector('.button_ok')
const body = document.querySelector('body')
const coks = document.querySelector('.coks')
const modal_ok = document.querySelector('.modal_ok')
let symbols_count = reports.value



button_ok.addEventListener('click', change_modal_window_state)
reports.addEventListener('input', check_report_symbols)
burger.addEventListener('click', set_burger)
exit.addEventListener('click', set_burger)
coks.addEventListener('click', send_report)




fetch('http://localhost:17627/api/seller/courses', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': sessionStorage.getItem('token')
    }
}).then((response) => {
    if (response.ok){
        return response.text()
    } else {
        window.location.replace('https://se.ifmo.ru/~s286535/html/login.html')
    }
}).then((body) => {
    name.innerHTML = body
})

function change_modal_window_state() {
    modal_window_state.state = !modal_window_state.state
    if (modal_window_state.state){
        modal_window.style.opacity = 1
        modal_window.style.pointerEvents = 'auto'
        content.style.pointerEvents = 'none'
        content.style.overflowY = 'hidden'
        body.style.overflowY = 'hidden'
        reports.blur()
    } else {
        modal_window.style.opacity = 0
        modal_window.style.pointerEvents = 'none'
        content.style.pointerEvents = 'auto'
        body.style.overflowY = 'scroll'
    }
}

function check_report_symbols() {
    symbols_count = reports.value.length
    if (symbols_count > 10){
        change_modal_window_state()
    }
}

function send_report() {
    fetch('http://localhost:63342/only_js_kurs_bd/html/xyi', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: reports
    }).then((response) => {
        if (response.ok){
            coks.style.pointerEvents = 'none'
            modal_ok.style.left = '50%'
        }
        setTimeout(() => {
            modal_ok.style.left = '-50%'
        }, 2000)
        setTimeout(() => {
            coks.style.pointerEvents = 'auto'
        }, 3000)
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
