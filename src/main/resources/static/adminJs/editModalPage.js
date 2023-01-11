const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('edit-id');
const name_ed = document.getElementById('edit-first_name');
const lastname_ed = document.getElementById('edit-last_name');
const age_ed = document.getElementById('edit-age');
const email_ed = document.getElementById('edit-email');


async function loadDataForEditModal(id) {
    const  urlDataEd = 'api/admins/users/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        // let userData =
            await usersPageEd.json().then(user => {
                id_ed.value = `${user.id}`;
                name_ed.value = `${user.firstName}`;
                lastname_ed.value = `${user.lastName}`;
                age_ed.value = `${user.age}`;
                email_ed.value = `${user.email}`;
            })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}
async function editUser() {
    let urlEdit = 'api/admin/users/' + id_ed.value;
    let listOfRole = [];
    for (let i=0; i<form_ed.rolesForEditing.options.length; i++) {
        if (form_ed.rolesForEditing.options[i].selected) {
            listOfRole.push("ROLE_" + form_ed.rolesForEditing.options[i].value);
        }
    }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: form_ed.name.value,
            lastname: form_ed.lastname.value,
            age: form_ed.age.value,
            email: form_ed.email.value,
            password: form_ed.password.value,
            roles: listOfRole
        })
    }
    await fetch(urlEdit,method).then(() => {
        $('#editCloseBtn').click();
        getAdminPage();
    })
}
