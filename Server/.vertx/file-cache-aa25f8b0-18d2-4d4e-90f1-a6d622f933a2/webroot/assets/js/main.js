"use strict";
document.addEventListener('DOMContentLoaded',init);
let x= 7; //155
let speed = 7;
let xcoords = [6,31,56,81,106,131,156,181,206,229,254];
let heigth = [150,150,150,150,150,150,150,150,150,150,150,150];
let ctx;
let block = new Image();
block.src = "/API/assets/media/block.png";
function init() {
    LoadCanvas();
    summonblock();
    document.addEventListener('keydown',ControlBlock);
    document.addEventListener('keyup',slowspeed);
}
function slowspeed(e) {
    if(e.keyCode === 40 && x<= 275){speed= 7;}
}
function ControlBlock(e) {
    console.log(e.keyCode);
    if(e.keyCode === 37 && x> 0){x--} //12 -25
    if(e.keyCode === 39 && x< 10){x++;} //230 + 25
    if(e.keyCode === 40){speed = 14;}
    //if(e.keyCode === 38){rotateBlok();}
}
function rotateBlok() {
    ctx.save();
    ctx.rotate(90*Math.PI/180);
    ctx.drawImage(block, 10, x,12,42);
    ctx.restore();
}
function LoadCanvas(){
    let background = new Image;
    background.src = "/API/assets/media/background.png";
    let canvas = document.querySelectorAll("canvas");
    ctx = canvas[0].getContext("2d")
    // for(let i=1;i<canvas.length;i++) {
    //     ctx = canvas[i].getContext("2d");
    //     ctx.drawImage(background, 0, 0,300,150);
    // }
}
function summonblock() {
    ctx.drawImage(block, xcoords[x], 3,42,12);  //42
    setTimeout(function(){moveDown(3)},500)
}
function moveDown(y){
    let xstart = (xcoords[x]-25);
    let xstop = (67);
    let ystart = (y-7);
    let ystop = (19);
    if(xstart < 0){xstart = 0;}
    if(xstop > 230){xstop = 230;}
    console.log("x:"+xcoords[x]+" y:"+y+" x-:"+xstart+" y:"+ystart+" x+:"+xstop+" y+:"+ystop);

    ctx.clearRect(xstart,ystart,xstop,ystop);
    y+=speed;
    ctx.drawImage(block, xcoords[x], y,42,12);
    if(y+14<heigth[x]) {setTimeout(function(){moveDown(y)},500)}
    else {
        heigth[x] -=14;
        summonblock();
    }
}