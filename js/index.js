document.addEventListener("DOMContentLoaded", function () {
  fetch("https://blognation-d0rz.onrender.com/api/posts")
    .then(response => response.json())
    .then(posts => {
      const container = document.getElementById("post-container");
      container.innerHTML = "";

      posts.forEach(post => {
        const card = document.createElement("div");
        card.className = "post-card";

        card.innerHTML = `
          <h2>${post.title}</h2>
          <p>${truncate(post.content, 200)}</p>
          <p><strong>Author:</strong> ${post.author}</p>
          <a href="post.html?id=${post.id}" class="read-more">Read More</a>
        `;

        container.appendChild(card);
      });
    })
    .catch(error => {
      console.error("Failed to load posts:", error);
    });
});

function truncate(text, maxLength) {
  return text.length > maxLength ? text.substring(0, maxLength) + "..." : text;
}
