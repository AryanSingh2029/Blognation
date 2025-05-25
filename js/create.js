document.getElementById("create-form").addEventListener("submit", function (e) {
  e.preventDefault();

  const title = document.getElementById("title").value.trim();
  const author = document.getElementById("author").value.trim();
  const content = document.getElementById("content").value.trim();
  const imageUrl = document.getElementById("imageUrl").value.trim();

  const userId = localStorage.getItem("userId"); // ✅ Get logged-in user ID

  if (!userId) {
    alert("❌ You must be logged in to create a post.");
    return;
  }

  const postData = { title, author, content, imageUrl };

  fetch(`https://blognation-d0rz.onrender.com/api/posts/user/${userId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(postData)
  })
    .then(response => {
      if (!response.ok) throw new Error("❌ Failed to create post.");
      return response.json();
    })
    .then(data => {
      alert("✅ Post created successfully!");
      window.location.href = "feed.html";
    })
    .catch(error => {
      alert(error.message);
    });
});
