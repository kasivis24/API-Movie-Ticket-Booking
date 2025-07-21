document.addEventListener('DOMContentLoaded', function () {

const url = window.location.href;
const relativeUrl = window.location.pathname + window.location.hash;
if (relativeUrl!=='/html/login.html') {
console.log(relativeUrl);
    window.location.href = '/html/login.html';
}
console.log(relativeUrl);
  const loginBtn = document.getElementById('loginBtn');

  loginBtn.addEventListener('click', async function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!email || !password) {
      alert('Please enter email and password.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/v1/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });

      const result = await response.json();

      console.log(result);

      if (result.status === 'success' && result.userType === 'Admin'){
         console.log('success login Admin');
         alert(`Admin login successful! ${result.adminUid}`);
         window.location.href = '/dashboard#/admin';
      }
      else if (result.status === 'success') {
        console.log('success login user');
        document.cookie = `userToken=${result.userUid}; path=/`;
        alert(`Login successful!\nUser ID: ${result.userUid}`);
        window.location.href = '/dashboard#/movies';
      } else {
        alert(`Login failed: ${result.message}`);
      }
    } catch (error) {
      console.error('Login failed:', error);
      alert('Something went wrong. Please try again later.');
    }
  });
});
