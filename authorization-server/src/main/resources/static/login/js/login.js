$(document).ready(function () {
    // 粒子

    var SEPARATION = 100,
        AMOUNTX = 50,
        AMOUNTY = 50;

    var container;
    var camera, scene, renderer;

    var particles,
        particle,
        count = 0;

    var mouseX = -960,
        mouseY = -466;

    var windowHalfX = window.innerWidth / 2;
    var windowHalfY = window.innerHeight / 2;

    init();
    animate();

    function init() {
        container = document.createElement("div");
        document.getElementById("particle-box").appendChild(container);

        camera = new THREE.PerspectiveCamera(
            75,
            window.innerWidth / window.innerHeight,
            1,
            10000
        );
        camera.position.z = 1000;

        scene = new THREE.Scene();

        particles = new Array();

        var PI2 = Math.PI * 2;
        var material = new THREE.ParticleCanvasMaterial({
            color: 0xffffff,
            program: function (context) {
                context.beginPath();
                context.arc(0, 0, 1, 0, PI2, true);
                context.fill();
            }
        });

        var i = 0;

        for (var ix = 0; ix < AMOUNTX; ix++) {
            for (var iy = 0; iy < AMOUNTY; iy++) {
                particle = particles[i++] = new THREE.Particle(material);
                particle.position.x = ix * SEPARATION - (AMOUNTX * SEPARATION) / 2;
                particle.position.z = iy * SEPARATION - (AMOUNTY * SEPARATION) / 2;
                scene.add(particle);
            }
        }
        var rendWidth = $("#particle-box").outerWidth();
        var rendHeight = $("#particle-box").outerHeight();
        renderer = new THREE.CanvasRenderer();
        renderer.setSize(rendWidth, rendHeight);
        container.appendChild(renderer.domElement);

        // document.addEventListener( 'mousemove', onDocumentMouseMove, false );

        window.addEventListener("resize", onWindowResize, false);
    }

    function onWindowResize() {
        windowHalfX = 1200 / 2;
        windowHalfY = 150 / 2;
        var rendWidth = $("#particle-box").outerWidth();
        var rendHeight = $("#particle-box").outerHeight();
        renderer.setSize(rendWidth, rendHeight);
    }

    //

    function onDocumentMouseMove(event) {
        mouseX = event.clientX - windowHalfX;
        mouseY = event.clientY - windowHalfY;
        console.log(mouseX, mouseY);
        // 172 -272
    }

    function animate() {
        requestAnimationFrame(animate);

        render();
    }

    function render() {
        camera.position.x += (mouseX - camera.position.x) * 0.05;
        camera.position.y += (-mouseY - camera.position.y) * 0.05;
        camera.lookAt(scene.position);

        var i = 0;

        for (var ix = 0; ix < AMOUNTX; ix++) {
            for (var iy = 0; iy < AMOUNTY; iy++) {
                particle = particles[i++];
                particle.position.y =
                    Math.sin((ix + count) * 0.3) * 50 +
                    Math.sin((iy + count) * 0.5) * 50;
                particle.scale.x = particle.scale.y =
                    (Math.sin((ix + count) * 0.3) + 1) * 2 +
                    (Math.sin((iy + count) * 0.5) + 1) * 2;
            }
        }

        renderer.render(scene, camera);

        count += 0.1;
    }
});