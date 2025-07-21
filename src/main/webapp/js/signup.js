document.addEventListener('DOMContentLoaded', function () {
  const signupBtn = document.getElementById('signupBtn');

  signupBtn.addEventListener('click', async function () {
    const name = document.getElementById('username').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!username || !email || !password) {
      alert('Please fill in all fields.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/v1/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email, password }),
      });

      const result = await response.json();

      if (result.status === 'success' && result.userType === 'Admin'){
        alert(`Admin Signup successful! ${result.adminUid}`);
        window.location.href = '/dashboard#/admin';
      }else if (result.status === 'success') {
        alert(`Signup successful! User ID: ${result.userUid}`);
        window.location.href = '/dashboard#/movies';
      }
      else {
        alert(`Signup failed: ${result.message}`);
      }
    } catch (error) {
      console.error('Signup failed:', error);
      alert('Something went wrong. Please try again later.');
    }
  });
});
