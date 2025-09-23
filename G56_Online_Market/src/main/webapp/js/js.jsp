<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 20.09.2025
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
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
</script>

<button onclick="scrollToTop()" id="scrollTopBtn" class="btn btn-primary"
        style="position: fixed; bottom: 40px; right: 40px; display: none; z-index: 9999;">
    ↑ Top
</button>


<div class="modal fade" id="imageModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body p-0">
                <img id="modalImg" src="" class="img-fluid w-100" alt="Product Image">
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>

