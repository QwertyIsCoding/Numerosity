// Theme Toggle
const toggle = document.getElementById('theme-toggle');
if (toggle) {
  toggle.addEventListener('change', () => {
    document.body.classList.toggle('dark');
  });
}

// Blob subtle movement on mouse move
const blobs = document.querySelectorAll('.blob');
document.addEventListener('mousemove', (e) => {
  const x = e.clientX / window.innerWidth - 0.5;
  const y = e.clientY / window.innerHeight - 0.5;
  blobs.forEach((blob, i) => {
    const factor = (i + 1) * 10;
    blob.style.transform = `translate(${x * factor}px, ${y * factor}px)`;
  });
});
