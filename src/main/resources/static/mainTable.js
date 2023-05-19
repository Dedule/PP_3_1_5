////////////////// MAIN TABLE/////////////////////
const tableId = document.getElementById('table-body')
fetch("/api/users").then(res => {
    res.json().then(data => {
        if (data.length > 0) {
            let temp = ""
            data.forEach(user => {
                temp += `
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                            <td>${user.roleString}</td>                            
                            <td>
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal" onclick="editModal(${user.id})">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal" onclick="delModal(${user.id})">Delete</button>
                            </td>
                        </tr>`
            })
            tableId.innerHTML = temp
        }

    })
})
////////////////// EDIT USER/////////////////////
const edit_id = document.getElementById('idEdit')
const edit_fn = document.getElementById('firstNameEdit')
const edit_ln = document.getElementById('lastNameEdit')
const edit_age = document.getElementById('ageEdit')
const edit_email = document.getElementById('emailEdit')
const edit_pass = document.getElementById('passwordEdit')
const edit_form = document.getElementById('edit_form')

function editModal(id) {
    const editUserUrl = `/api/users/${id}`
    fetch(editUserUrl).then(temp => {
        if (temp.ok) {
            temp.json().then(user => {
                edit_id.value = user.id
                edit_fn.value = user.firstName
                edit_ln.value = user.lastName
                edit_age.value = user.age
                edit_email.value = user.email
                edit_pass.value = user.password
            })
        } else {
            alert('error')
        }
    })
}

edit_form.addEventListener('submit', e => {
    e.preventDefault()
    const editUrl = `/api/users/${edit_id.value}`
    let rolesEdit = []
    for (let role of edit_form.roles) {
        if (role.selected) {
            let tmp = {}
            tmp["id"] = role.value
            rolesEdit.push(tmp)
        }
    }
    const method = {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: document.getElementById('idEdit').value,
            firstName: document.getElementById('firstNameEdit').value,
            lastName: document.getElementById('lastNameEdit').value,
            age: document.getElementById('ageEdit').value,
            email: document.getElementById('emailEdit').value,
            password: document.getElementById('passwordEdit').value,
            roles: rolesEdit
        })
    }
    fetch(editUrl, method).then(res => res.json()).then(() => {
        location.reload();
    }).catch(err => console.log(err))
})


////////////////// DELETE USER/////////////////////
const del_id = document.getElementById('idDelete')
const del_fn = document.getElementById('firstNameDelete')
const del_ln = document.getElementById('lastNameDelete')
const del_age = document.getElementById('ageDelete')
const del_email = document.getElementById('emailDelete')
const del_roles = document.getElementById('rolesDelete')
const del_form = document.getElementById('del_form')

function delModal(id) {
    const delUserUrl = `/api/users/${id}`
    fetch(delUserUrl).then(temp => {
        if (temp.ok) {
            temp.json().then(user => {
                del_id.value = user.id
                del_fn.value = user.firstName
                del_ln.value = user.lastName
                del_age.value = user.age
                del_email.value = user.email
                del_roles.value = user.roleString

            })
        } else {
            alert('error')
        }
    })
}

del_form.addEventListener('submit', e => {
    e.preventDefault()
    const delUrl = `/api/users/${del_id.value}`
    const method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }
    fetch(delUrl, method).then(res => res.json()).then(() => {
        location.reload();
    }).catch(err => console.log(err))
})


