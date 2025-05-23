document.addEventListener("DOMContentLoaded", () => {
  fetch("http://localhost:8081/api/posts")
    .then(res => res.json())
    .then(posts => {
      const container = document.getElementById("dashboard-posts");
      container.innerHTML = "";

      posts.forEach(post => {
        const card = document.createElement("div");
        card.className = "post-card";

        card.innerHTML = `
          <h3>${post.title}</h3>
          <p>${truncate(post.content, 150)}</p>
          <p><strong>Author:</strong> ${post.author}</p>
          <div class="stats">
            <span>❤️ Likes: <strong id="like-count-${post.id}">0</strong></span>
            <span>💬 Comments: <strong id="comment-count-${post.id}">0</strong></span>
          </div>
          <button onclick="viewComments(${post.id})">View Comments</button>
          <div class="comment-box" id="comments-${post.id}" style="display:none; margin-top:10px;"></div>
        `;

        container.appendChild(card);

        // Fetch like and comment counts
        fetch(`http://localhost:8081/api/likes/${post.id}`)
          .then(res => res.json())
          .then(count => {
            document.getElementById(`like-count-${post.id}`).innerText = count;
          });

        fetch(`http://localhost:8081/api/comments/${post.id}`)
          .then(res => res.json())
          .then(comments => {
            document.getElementById(`comment-count-${post.id}`).innerText = comments.length;
          });
      });
    })
    .catch(err => console.error("Failed to load dashboard:", err));
});

function truncate(text, length) {
  return text.length > length ? text.substring(0, length) + "..." : text;
}

function viewComments(postId) {
  const box = document.getElementById(`comments-${postId}`);
  const visible = box.style.display === "block";
  box.style.display = visible ? "none" : "block";

  if (!visible) {
    fetch(`http://localhost:8081/api/comments/${postId}`)
      .then(res => res.json())
      .then(comments => {
        box.innerHTML = comments.length === 0
          ? "<p>No comments yet.</p>"
          : comments.map(c => `<p><strong>${c.author}</strong>: ${c.content}</p>`).join("");
      });
  }
}
