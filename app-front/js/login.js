const login_form = document.querySelector('.login')
const password_form = document.querySelector('.password')
const send_login = document.querySelector('.send_login')
let login = login_form.value
let password = password_form.value



send_login.addEventListener('click', send_login_function)
login_form.addEventListener('input', update_form)
password_form.addEventListener('input', update_form)

function update_form() {
    login = login_form.value
    password = password_form.value
}



function send_login_function() {
    fetch('http://localhost:17627/api/login',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({"login": login, "password": password})
        }).then((response) => {
        if (response.ok) {
            return response.json()
        } else {
            throw new Error('Error')
        }
    }).then((body) => {
        let array_body = body['role'].split('')
        switch (array_body[0]) {
            case 'H':
                replace_user('', body)
                break
            case 'S':
                replace_user('https://se.ifmo.ru/~s286535/html/reports.html', body)
                break
            case 'A':
                replace_user()
                break
            case 'J':
                replace_user()
                break
        }
    }).catch(

    )
}

function replace_user(url, body) {
    sessionStorage.setItem('token', body['token'])
    sessionStorage.setItem('role', body['role'])
    window.location.replace(url)
}

