(function() {
"use strict";

document.addEventListener("DOMContentLoaded", function() {
 const instaEle = document.querySelector('.instagram-counter');
const twitterEle = document.querySelector('.twitter-counter');
const youtubeEle = document.querySelector('.youtube-counter');

let times = 0;

let id = setInterval(function(){
  instaEle.textContent = times*300;
  youtubeEle.textContent = times * 150;
  twitterEle.textContent = times * 15;
  times++;
  if(times==101){
    window.clearInterval(id)
  }
},20);

});
})();