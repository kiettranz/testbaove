function transValueToTextBox(element){
    var txtusername = element.childNodes[1].childNodes[0].nodeValue;
    var txtpassword = element.childNodes[3].childNodes[0].nodeValue;
    var txtfullname = element.childNodes[5].childNodes[0].nodeValue;
    var txtrole = element.childNodes[7].childNodes[0].nodeValue;
    
    var password = document.getElementById("txtPassword");
    var fullname = document.getElementById("txtFullname");
    var role = document.getElementById("txtRole");
    var username = document.getElementById("txtUsername");
    
    username.value=txtusername;
    password.value=txtpassword;
    fullname.value=txtfullname;
    role.value=txtrole;
}
function transValueToPopup(element){
    var txtusername = element.childNodes[1].childNodes[0].nodeValue;
    var txtpassword = element.childNodes[3].childNodes[0].nodeValue;
    var txtfullname = element.childNodes[5].childNodes[0].nodeValue;
    var txtrole = element.childNodes[7].childNodes[0].nodeValue;
    
    var alertPw = document.getElementById("alertPopupPassword");
    var alertFn = document.getElementById("alertPopupFullname");
    
    alertPw.innerHTML="";
    alertFn.innerHTML="";
    
    var password = document.getElementById("PopupPassword");
    var fullname = document.getElementById("PopupFullname");
    var role = document.getElementById("PopupRole");
    var username = document.getElementById("PopupUsername");
    var usernameDel = document.getElementById("PopupUsernameDel");
    username.value=txtusername;
    password.value=txtpassword;
    fullname.value=txtfullname;
    role.value=txtrole;
    usernameDel.value=txtusername;
    
}
function transValueSelect(){
    var role = document.getElementById("PopupRole");
    var role1 = document.getElementById("PopupRole1");
    role1.value=role.value;
}

    
    
function validatePassword(){
    var password = document.getElementById("PopupPassword");
    var alert = document.getElementById("alertPopupPassword");
    if(password.value.length<=8){
        alert.innerHTML="The password must be longer than 7 characters";
    }else{alert.innerHTML="";}
}
function validateFullname(){
    var regexVI =/^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ_\s]+$/;
    var fullname = document.getElementById("PopupFullname");
    var alert = document.getElementById("alertPopupFullname");
    if(fullname.value.match(regexVI)!=fullname.value){
        alert.innerHTML="Fullname is incorrect";
    } else{
        alert.innerHTML="";
    }
}
function validateForm(){
    var alertPw = document.getElementById("alertPopupPassword");
    var alertFn = document.getElementById("alertPopupFullname");
    if(alertPw.innerHTML=="" && alertFn.innerHTML==""){
        return true;
    }
    return false;
}
