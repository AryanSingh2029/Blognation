document.addEventListener("DOMContentLoaded", () => {
  const container = document.getElementById("post-list");

  const userId = localStorage.getItem("userId"); // ✅ Get user ID from localStorage
  if (!userId) {
    container.innerHTML = "<p>You must be logged in to view your posts.</p>";
    return;
  }

  // ✅ Fetch all posts by the current user
  fetch(`https://blognation-d0rz.onrender.com/api/posts/user/${userId}`)
    .then(res => res.json())
    .then(posts => {
      container.innerHTML = "";

      if (posts.length === 0) {
        container.innerHTML = "<p>No posts found.</p>";
        return;
      }

      posts.forEach(post => {
        const card = document.createElement("div");
        card.className = "post-card";

        card.innerHTML = `
          <h3>${post.title}</h3>
          <p><strong>Author:</strong> ${post.author}</p>
          <p>${truncate(post.content, 150)}</p>
          <button onclick="toggleForm(${post.id})" class="btn-primary">Edit</button>

          <form class="edit-form" id="form-${post.id}" style="display:none;">
            <input type="text" name="title" value="${post.title}" required />
            <input type="text" name="author" value="${post.author}" required />
            <textarea name="content" rows="5" required>${post.content}</textarea>
            <input type="text" name="imageUrl" value="${post.imageUrl || ''}" />
            <div class="btn-group">
              <button type="button" onclick="updatePost(${post.id})" class="btn-primary">Update</button>
              <button type="button" onclick="deletePost(${post.id})" class="btn-danger">Delete</button>
            </div>
          </form>
        `;

        container.appendChild(card);
      });
    })
    .catch(err => {
      container.innerHTML = "<p>Failed to load posts.</p>";
      console.error(err);
    });
});

function toggleForm(postId) {
  const form = document.getElementById(`form-${postId}`);
  form.style.display = form.style.display === "block" ? "none" : "block";
}

function updatePost(postId) {
  const form = document.getElementById(`form-${postId}`);
  const title = form.querySelector("input[name='title']").value;
  const author = form.querySelector("input[name='author']").value;
  const content = form.querySelector("textarea[name='content']").value;
  const imageUrl = form.querySelector("input[name='imageUrl']").value;

  const updatedPost = { title, author, content, imageUrl };

  fetch(`https://blognation-d0rz.onrender.com/api/posts/${postId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(updatedPost)
  })
    .then(res => {
      if (res.ok) {
        alert(" Post updated!");
        location.reload();
      } else {
        alert(" Update failed.");
      }
    })
    .catch(err => {
      alert(" Error occurred while updating.");
      console.error(err);
    });
}

function deletePost(postId) {
  if (confirm("Are you sure you want to delete this post?")) {
    fetch(`https://blognation-d0rz.onrender.com/api/posts/${postId}`, {
      method: "DELETE"
    })
      .then(res => {
        if (res.ok) {
          alert("🗑️ Post deleted.");
          location.reload();
        } else {
          alert(" Delete failed.");
        }
      })
      .catch(err => {
        alert(" Error deleting post.");
        console.error(err);
      });
  }
}

function truncate(text, maxLength) {
  return text.length > maxLength ? text.substring(0, maxLength) + "..." : text;
}
