function addWeightsToEncodeForm(){
    var weightsText = document.getElementById("weights");
    if(weightsText.value == "") return;
    var decodedAera = document.getElementById("decoded-area");
    if(decodedAera.value == "") return;
    var encodeForm = document.getElementById("Encode");
    var useDefaultBox = document.getElementById("use-default");
    var input = document.createElement("input");
    input.type = "text";
    input.name = "encode-weights";
    input.value = weightsText.value;
    encodeForm.appendChild(input);

    var check = document.createElement("input");
    check.type = "checkbox";
    check.name = "use-default";
    check.checked = useDefaultBox.checked;
    encodeForm.appendChild(check);
    encodeForm.submit();
}
function addWeightsToDecodeForm(){
    var weightsText = document.getElementById("weights");
    if(weightsText.value == "") return;
    var encodedAera = document.getElementById("encoded-area");
    if(encodedAera.value == "") return;
    var decodeForm = document.getElementById("Decode");
    var useDefaultBox = document.getElementById("use-default");
    var input = document.createElement("input");
    input.type = "text";
    input.name = "decode-weights";
    input.value = weightsText.value;
    //input.attr("value",weightsText.value);
    decodeForm.appendChild(input);

    var check = document.createElement("input");
    check.type = "checkbox";
    check.name = "use-default";
    check.checked = useDefaultBox.checked;
    decodeForm.appendChild(check);
    decodeForm.submit();
}
function useDefault(){
    var useDefaultBox = document.getElementById("use-default");
    var weightsText = document.getElementById("weights");
    if(useDefaultBox.checked == true){
        weightsText.value = "";
        weightsText.value = "(a,8.2),(b,1.5),(c,2.8),(d,4.3),(e,13),(f,2.2),(g,2),(h,6.1),(i,7),(j,0.15),(k,0.77),(l,4),(m,2.4),(n,6.7),(o,7.5),(p,1.9),(q,0.095),(r,6),(s,6.3),(t,9.1),(u,2.8),(v,0.98),(w,2.4),(x,0.15),(y,2),(z,0.074)";
    }
    else{
        weightsText.value = "";
    }
}
function changeText(){
    var useDefaultBox = document.getElementById("use-default");
    if(useDefaultBox.checked == true){
        useDefaultBox.checked = false;
    }
}