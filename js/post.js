document.addEventListener("DOMContentLoaded", function () {
  const postId = new URLSearchParams(window.location.search).get("id");

  const titleEl = document.getElementById("post-title");
  const contentEl = document.getElementById("post-content");
  const authorEl = document.getElementById("post-author");
  const imageEl = document.getElementById("post-image");

  const demoPosts = {
    demo1: {
      title: "SOAKING IN THE COLORS",
      content: "After me I dragged six lengths of leather from an equal number of my warriors...",
      author: "Demo Author",
      image: "assets/pic-2.jpg"
    },
    demo2: {
      title: "AS WE FALL INTO THE EARTH",
      content: "In this way, with ten warriors, I built a series of three steps from the ground...",
      author: "Demo Author",
      image: "assets/pic-3.jpg"
    },
    demo3: {
      title: "REACHING MAJESTY",
      content: "The scent of hay was in the air through the lush meadows beyond Pyrford...",
      author: "Demo Author",
      image: "assets/pic-4.jpg"
    }
  };

  if (postId.startsWith("demo") && demoPosts[postId]) {
    const post = demoPosts[postId];
    titleEl.textContent = post.title;
    contentEl.textContent = post.content;
    authorEl.textContent = `By ${post.author}`;
    imageEl.src = post.image;
  } else {
    fetch(`https://blognation-d0rz.onrender.com/api/posts/${postId}`)
      .then(response => {
        if (!response.ok) throw new Error("Post not found");
        return response.json();
      })
      .then(post => {
        titleEl.textContent = post.title;
        contentEl.textContent = post.content;
        authorEl.textContent = `By ${post.author}`;
        imageEl.src = "assets/default.jpg"; // or use post.imageUrl if available
      })
      .catch(error => {
        titleEl.textContent = "Post not found";
        contentEl.textContent = "This blog post does not exist or has been deleted.";
        authorEl.textContent = "";
        imageEl.src = "assets/not-found.jpg"; // fallback image
      });
  }
});
