---
title: Elements
parent: API
nav_order: 1
has_toc: false
---

# Elements

All elements have 4 basic properties. It's position, size, anchor point, and offset. These properties are as explained below.

----

## Position

This is the number of scaled pixels[^1] from the anchor's position. This is an integer, without any upper or lower limits.

{: .important }
Omitting the position will default to `0, 0`.

----

## Size

This is the size of the element in scaled pixels[^1]. This is an integer, without any upper or lower limits.

{: .important }
Negative values _may_ cause the element to not appear on the screen.

{: .important }
Omitting the size will default to `0, 0` and cause the element to not appear on the screen.

----

## Anchor

This is the anchor point where the Position starts from in relation to the screen size. This is a floating point percentage (0.0-1.0), without any upper or lower limits.

{: .important }
Omitting the anchor will default to `0.0, 0.0`.

{: .examples }
`0.0, 0.0` will anchor to the top left corner of the screen.<br>
`0.5, 0.5` will anchor to the center of the screen.<br>
`1.0, 1.0` will anchor to the bottom right of the screen.

----

## Offset

This is the offset point where the position aligns on the element in relation to it's size. This is a floating point percentage (0.0-1.0), without any upper or lower limits.

{: .important }
Omitting the offset will default to `0.0, 0.0`.

{: .examples }
`0.0, 0.0` results in the top left corner at the anchor + position.<br>
`0.5, 0.5` results in the center at the anchor + position.<br>
`1.0, 1.0` results in the bottom right corner at the anchor + position.

----

## Try It Out!

Sometimes words just aren't enough, and a more hands-on example speaks volumes more.

<canvas id="canvas" width="512" height="288" style="border:1px solid"></canvas>

<div id="tryit"><table cellpadding="0" cellspacing="0"><tr>
<td style="background-color:#ff4040;color:black;font-weight:700"><label id="posx">Position X</label></td>
<td><input type="range" id="posx1" min="-512" max="512" value="0" oninput="sync(this,'posx2')" aria-labelledby="posx" /></td>
<td><input type="number" id="posx2" value="0" oninput="sync(this,'posx1')" aria-labelledby="posx" /></td>
</tr><tr>
<td style="background-color:#ff4040;color:black;font-weight:700"><label id="posy">Position Y</label></td>
<td><input type="range" id="posy1" min="-288" max="288" value="0" oninput="sync(this,'posy2')" aria-labelledby="posy" /></td>
<td><input type="number" id="posy2" value="0" oninput="sync(this,'posy1')" aria-labelledby="posy" /></td>
</tr><tr>
<td style="background-color:#ffff40;color:black;font-weight:700"><label id="sizex">Size X</label></td>
<td><input type="range" id="sizex1" min="0" max="100" value="50" oninput="sync(this,'sizex2')" aria-labelledby="sizex" /></td>
<td><input type="number" id="sizex2" value="50" oninput="sync(this,'sizex1')" aria-labelledby="sizex" /></td>
</tr><tr>
<td style="background-color:#ffff40;color:black;font-weight:700"><label id="sizey">Size Y</label></td>
<td><input type="range" id="sizey1" min="0" max="100" value="25" oninput="sync(this,'sizey2')" aria-labelledby="sizey" /></td>
<td><input type="number" id="sizey2" value="25" oninput="sync(this,'sizey1')" aria-labelledby="sizey" /></td>
</tr><tr>
<td style="background-color:#6060ff;color:black;font-weight:700"><label id="anchorx">Anchor X</label></td>
<td><input type="range" id="anchorx1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'anchorx2',2)" aria-labelledby="anchorx" /></td>
<td><input type="number" id="anchorx2" value="0.50" step="0.01" oninput="sync(this,'anchorx1',2)" aria-labelledby="anchorx" /></td>
</tr><tr>
<td style="background-color:#6060ff;color:black;font-weight:700"><label id="anchory">Anchor Y</label></td>
<td><input type="range" id="anchory1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'anchory2',2)" aria-labelledby="anchory" /></td>
<td><input type="number" id="anchory2" value="0.50" step="0.01" oninput="sync(this,'anchory1',2)" aria-labelledby="anchory" /></td>
</tr><tr>
<td style="background-color:#d0d0d0;color:black;font-weight:700"><label id="offsetx">Offset X</label></td>
<td><input type="range" id="offsetx1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'offsetx2',2)" aria-labelledby="offsetx" /></td>
<td><input type="number" id="offsetx2" value="0.50" step="0.01" oninput="sync(this,'offsetx1',2)" aria-labelledby="offsetx" /></td>
</tr><tr>
<td style="background-color:#d0d0d0;color:black;font-weight:700"><label id="offsety">Offset Y</label></td>
<td><input type="range" id="offsety1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'offsety2',2);" aria-labelledby="offsety" /></td>
<td><input type="number" id="offsety2" value="0.50" step="0.01" oninput="sync(this,'offsety1',2);" aria-labelledby="offsety"  /></td>
</tr></table></div>

<script type="text/javascript">
const width=512;
const height=288;
const posxElement=document.getElementById("posx2");
const posyElement=document.getElementById("posy2");
const sizexElement=document.getElementById("sizex2");
const sizeyElement=document.getElementById("sizey2");
const anchorxElement=document.getElementById("anchorx2");
const anchoryElement=document.getElementById("anchory2");
const offsetxElement=document.getElementById("offsetx2");
const offsetyElement=document.getElementById("offsety2");
const ctx=document.getElementById("canvas").getContext("2d");
function sync(input,output,x=0) {
  document.getElementById(output).value=parseFloat(input.value).toFixed(x);
  draw();
}
function draw() {
  const sizex=parseInt(sizexElement.value);
  const sizey=parseInt(sizeyElement.value);
  const offsetx=parseFloat(offsetxElement.value);
  const offsety=parseFloat(offsetyElement.value);
  const anchorX=width*parseFloat(anchorxElement.value);
  const anchorY=height*parseFloat(anchoryElement.value);
  const anchorPosX=anchorX+parseInt(posxElement.value);
  const anchorPosY=anchorY+parseInt(posyElement.value);
  ctx.lineWidth=1;
  ctx.clearRect(0,0,width,height);
  ctx.strokeStyle="#ff4040";
  ctx.beginPath();ctx.moveTo(anchorPosX,0);ctx.lineTo(anchorPosX,height);ctx.stroke();
  ctx.beginPath();ctx.moveTo(0,anchorPosY);ctx.lineTo(width,anchorPosY);ctx.stroke();
  ctx.strokeStyle="#6060ff";
  ctx.beginPath();ctx.moveTo(anchorX,0);ctx.lineTo(anchorX,height);ctx.stroke();
  ctx.beginPath();ctx.moveTo(0,anchorY);ctx.lineTo(width,anchorY);ctx.stroke();
  ctx.strokeStyle="#ff80ff";
  ctx.beginPath();ctx.moveTo(anchorPosX,anchorPosY);ctx.lineTo(anchorX,anchorY);ctx.stroke();
  ctx.lineWidth=2;
  ctx.strokeStyle="#d0d0d0";
  ctx.fillStyle="#ffff0060";
  ctx.beginPath();ctx.rect(anchorPosX-(sizex*offsetx),anchorPosY-(sizey*offsety),sizex,sizey);ctx.stroke();ctx.fill();
}
draw();
</script>

----

[^1]: Scaled pixels are the number of real pixels multiplied by the client's `GUI Scale`. For more information see the [Video Settings](https://minecraft.fandom.com/wiki/Options#Video_Settings) section of the Minecraft wiki.
