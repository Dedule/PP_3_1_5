fetch("/api/users/auth").then(
    res => {
        res.json().then(
            data => {
                document.getElementById("navInfo").innerHTML = data.email + " with roles: " + data.roleString
                let temp = ""
                temp +=
                    `<td>${data.id}</td>
                        <td>${data.firstName}</td>
                        <td>${data.lastName}</td>
                        <td>${data.age}</td>
                        <td>${data.email}</td>
                        <td>${data.roleString}</td>`
                document.getElementById("authUserInfo").innerHTML = temp
            }
        )
    }
)
