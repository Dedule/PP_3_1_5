const form_n = document.getElementById('newUserForm')
const roles_n = document.querySelector('#roleNew').selectedOptions

form_n.addEventListener('submit', newUser)

async function newUser(ev) {
    ev.preventDefault()
    let newRoles = []
    for (let i = 0; i < roles_n.length; i++) {
        newRoles.push({
            id: roles_n[i].value
        })
    }

    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            firstName: document.getElementById('newFirstName').value,
            lastName: document.getElementById('newLastName').value,
            age: document.getElementById('newAge').value,
            email: document.getElementById('newEmail').value,
            password: document.getElementById('newPassword').value,
            roles: newRoles
        })
    }
    fetch('/api/users', method).then(res => res.json())
        .then(data => {
            console.log(data);
            location.reload();
        });
}