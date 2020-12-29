<%
    //put left
    String decodedText = (String)request.getAttribute("decoded-text");
    //out.println(decodedText);
    String textToEncode = (String)request.getAttribute("text-to-encode");
    //out.println(textToEncode);
    String left = null;
    if(decodedText != null)left = decodedText;
    if(textToEncode != null)left = textToEncode;
    //out.println(left);
    //put right
    String encodedText = (String)request.getAttribute("encoded-text");
    //out.println(encodedText);
    String textToDecode = (String)request.getAttribute("text-to-decode");
    //out.println(textToDecode);
    String right = null;
    if(encodedText != null)right = encodedText;
    if(textToDecode != null)right = textToDecode;
    //out.println(right);
    //put down
    String charWeightsPairs = (String)request.getAttribute("char-weights-pairs");
    //out.println(charWeightsPairs);

    String error = (String)request.getAttribute("error");

    String haffInfo = (String)request.getAttribute("haffInfo");

    String useDefault = (String)request.getAttribute("use-default");
    if(useDefault == null) {useDefault = "off";}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Haffman Encoder&Decoder</title>
    <link rel="stylesheet" type="text/css" href="./style/index.css" />
    <style>
    div.tree {
      display: flex;
      flex-wrap: wrap;
      align-items: flex-start;
    }

    .tree > .tree {
      width: 50%;
    }


    div.tree > span {
      width: 100%;
      text-align: center;
      padding-bottom: 3em;
      background-image: url(both.svg);
      background-repeat: no-repeat;
      background-size: 100% calc(100% - 0);
      background-position: 0 0;
    }

    div.tree > span:only-child {
      background-image: none;
      padding-bottom: 0;
    }

    div.left {
      margin-right: auto;
    }
    div.right {
      margin-left: auto;
    }

    div.only-has-left > div.tree {
      width: 90%;
      margin-right: auto;
    }
    div.only-has-right > div.tree {
      width: 90%;
      margin-left: auto;
    }

    div.only-has-left > span {
      background-image: url(left.svg);
    }
    div.only-has-right > span {
      background-image: url(right.svg);
    }
    </style>
</head>
<body>
    <header>
        <h2>Haffman Encoder&Decoder</h2>
    </header>
    <form action="decode" id="Decode" method="POST"></form>
    <form action="encode" id="Encode" method="POST"></form>
    <script src="src/index.js"></script>
    <div class="content">
        <textarea id="decoded-area" placeholder="Decoded Text Here" form="Encode" name="text-to-encode"></textarea>
        <div class="buttons">
            
                <!--<button id='encode-btn' type="submit" form="Encode">Encode &gt;&gt;</button>-->
                <button id='encode-btn' onclick="addWeightsToEncodeForm()">Encode &gt;&gt;</button>
          
            
                <!--<button id='decode-btn' type="submit" form="Decode">&lt;&lt; Decode</button>-->
                <button id='decode-btn' onclick="addWeightsToDecodeForm()">&lt;&lt; Decode</button>
            
            
        </div>
        <textarea id="encoded-area" placeholder="Encoded Text Here" form="Decode" name="text-to-decode"></textarea>
        <!--<input type="textarea" placeholder="Encoded Text Here" form="Encode" name="text-to-decode" width="303m" height="12em" margin="1em"></textarea>-->
    </div>
    <!--<div class="default">
        <label>use default weights <input type="checkbox" id="use-default" onclick="useDefault()"/></label>
    </div>-->
    <label><% if(error!=null) out.print("ERROR!!!" + error); %></label>
    <div class="weights">
        <textarea id="weights" placeholder="enter the char weight pairs here eg. (a,1),(b,2),(c.3),(d,4)" oninput="changeText()"></textarea>
        <!--<input type="checkbox" id="use-default" onclick="useDefault()"/><label for="use-default">use default weights</label>-->
        <label>use default weights <input type="checkbox" id="use-default" onclick="useDefault()"/></label>
    </div>

    <section id="display_tree"></section>
    <footer>
        <p>by inHann @2020</p>
</body>
    <script language="javascript">
        function fill(){
            //fill left
            var left = document.getElementById("decoded-area");
            left.innerHTML = <% if(left == null)out.print(left);else out.print("\"" + left + "\"");%>;
            //fill right
            var right = document.getElementById("encoded-area");
            right.innerHTML = <% if(right == null)out.print(right);else out.print("\"" + right + "\"");%>;
            //fill down
            var down = document.getElementById("weights");
            down.innerHTML = <% if(charWeightsPairs == null)out.print(left);else out.print("\"" + charWeightsPairs + "\"");%>;
            //fill checkbox
            var check = document.getElementById("use-default");
            check.checked = <% if(useDefault.equals("on"))out.print("true");else out.print("false");%>;
        }
        fill();
    </script>
    <script language="javascript">
      function ary2tree(ary, root = 0) {
        if (ary[root] != null) {
          var rt = {
            val: ary[root]
          }
          rt.left = ary2tree(ary, root * 2 + 1)
          rt.right = ary2tree(ary, root * 2 + 2)
          return rt
        } else {
          return null
        }
      }

      function tree2html(root, side='') {
        if (root) {
          let leaf = !root.left && !root.right
          let both = root.left && root.right
          let onlyLeft = root.left && !root.right
          let onlyRight = !root.left && root.right
          return `
            <div class="tree ${side} ${both?'both':''} ${leaf?'leaf':''} ${onlyLeft?'only-has-left':''} ${onlyRight?'only-has-right':''}">
              <span>${root.val}</span>
              ${tree2html(root.left, 'left')}
              ${tree2html(root.right, 'right')}
            </div>
          `
        } else {
          return ''
        }
      }

      function showTree(el, ary) {
        el.innerHTML = tree2html(ary2tree(ary))
      }

      function onInput() {
        var aryStr = this.value
        try {
          var ary = eval(aryStr)
        } catch(e){
          return
        }
        showTree(this.nextElementSibling, ary)
      }

      showTree(document.getElementById("display_tree"),eval(<% if(haffInfo == null);else out.print("\"" + haffInfo + "\"");%>))

    </script>
</html>
