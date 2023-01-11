const form_new = document.getElementById('formForNewUser');
const role_new = document.querySelector('#roles').selectedOptions;




form_new.addEventListener('submit', addNewUser);


async function addNewUser(event) {
    event.preventDefault();
    const urlNew = 'api/admins/newAddUser';
    let listOfRole = [];
    for (let i = 0; i < role_new.length; i++) {
        listOfRole.push({
            id:role_new[i].value
        });
    }
    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            firstName: form_new.firstname.value,
            lastName: form_new.lastname.value,
            age: form_new.age.value,
            email: form_new.email.value,
            password: form_new.password.value,
            roles: listOfRole
        })
    }
    await fetch(urlNew,method).then(() => {
        form_new.reset();
        getAdminPage();
    });

}




