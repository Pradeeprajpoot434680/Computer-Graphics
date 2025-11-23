const canvas = document.getElementById('myCanvas');
console.log(canvas);

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

let ctx = canvas.getContext('2d');

ctx.fillRect(50, 50, 100, 100); // x: 50, y: 50, width: 100, height: 100

ctx.clearRect(60, 60, 80, 80); // Clear a rectangle area

ctx.fillRect(500, 500, 100, 100); // x: 500, y: 500, width: 100, height: 100


//put a point (pixel)
ctx.fillRect(100, 100, 1, 1);
