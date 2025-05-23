document.getElementById("create-form").addEventListener("submit", function (e) {
  e.preventDefault();

  const title = document.getElementById("title").value.trim();
  const author = document.getElementById("author").value.trim();
  const content = document.getElementById("content").value.trim();
  const imageUrl = document.getElementById("imageUrl").value.trim(); // 🔹 New field

  const postData = { title, author, content, imageUrl };

  fetch("http://localhost:8081/api/posts", {
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
      window.location.href = "feed.html"; // Redirect or update as needed
    })
    .catch(error => {
      alert(error.message);
    });
});
