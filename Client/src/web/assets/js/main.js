"use strict";
document.addEventListener('DOMContentLoaded',init);
function init() {
    document.querySelector("button").addEventListener("click",reboot)
}
function reboot() {java.reboot();}

