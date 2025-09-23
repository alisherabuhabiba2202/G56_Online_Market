
window.addEventListener('scroll', function() {
    let scrollBtn = document.getElementById('scrollTopBtn');
    if (!scrollBtn) return;
    if (window.scrollY > 200) {
        scrollBtn.style.display = "block";
    } else {
        scrollBtn.style.display = "none";
    }
});

function scrollToTop() {
    window.scrollTo({top: 0, behavior: 'smooth'});
}


document.addEventListener('DOMContentLoaded', () => {
    let cards = document.querySelectorAll('.card');
    cards.forEach(card => {
        card.addEventListener('mouseenter', () => card.classList.add('shadow-lg'));
        card.addEventListener('mouseleave', () => card.classList.remove('shadow-lg'));
    });
});


document.addEventListener('click', function(e) {
    if(e.target.classList.contains('lightbox')) {
        let modalImg = document.getElementById('modalImg');
        let modal = new bootstrap.Modal(document.getElementById('imageModal'));
        modalImg.src = e.target.src;
        modal.show();
    }
});

(function() {
    'use strict';
    const forms = document.querySelectorAll('.needs-validation');
    Array.prototype.slice.call(forms)
        .forEach(function(form) {
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
})();
