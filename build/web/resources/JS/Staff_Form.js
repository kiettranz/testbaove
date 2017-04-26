function transValue(element){
    var id = element.childNodes[1].childNodes[0].nodeValue;
    var salary = element.childNodes[1].childNodes[1].value;
    var depart = element.childNodes[1].childNodes[3].value;
    var note = element.childNodes[1].childNodes[5].value;
    var photo = element.childNodes[1].childNodes[7].value;
    
    var name = element.childNodes[3].childNodes[0].nodeValue;
    var gender = element.childNodes[5].childNodes[0].nodeValue;
    var birthday = element.childNodes[7].childNodes[0].nodeValue;
    var email = element.childNodes[9].childNodes[0].nodeValue;
    var phone = element.childNodes[11].childNodes[0].nodeValue;

    var PopupId = document.getElementById("PopupId");
    var PopupIdDel = document.getElementById("PopupIdDel");
    PopupId.value=id;
    PopupIdDel.value=id;
    var PopupName = document.getElementById("PopupName");
    PopupName.value=name;
    
    var genderMale = document.getElementById("PopupGenderMale");
    var genderFemale = document.getElementById("PopupGenderFemale");

    if(gender.replace(/\s/g,'') === 'Male'){
        genderMale.checked=true;
        genderFemale.checked=false;
    }else{
        genderFemale.checked=true;
        genderMale.checked=false;
    }
    
    var PopupDate = document.getElementById("PopupDate");
    PopupDate.value=birthday;
    
    var PopupEmail = document.getElementById("PopupEmail");
    PopupEmail.value=email;
    
    var PopupPhone = document.getElementById("PopupPhone");
    PopupPhone.value = phone;
    
    var PopupViewSalary = document.getElementById("PopupViewSalary");
    var PopupSalary=document.getElementById("PopupSalary");
    PopupSalary.value = salary;
    PopupViewSalary.innerHTML="$"+salary;
    
    var PopupDepart=document.getElementById("PopupDepart");
    var PopupViewDepart=document.getElementById("PopupViewDepart");
    PopupViewDepart.innerHTML=depart;
    var length = (PopupDepart.childElementCount*2)-1;
    var result="";
    var i=3;
    while(i<=length){
        if(depart==PopupDepart.childNodes[i].childNodes[0].nodeValue){
            result=PopupDepart.childNodes[i].value;
        }
        i+=2;
    }
    PopupDepart.value=result;

    var PopupNote = document.getElementById("PopupNote");
    var PopupViewNote = document.getElementById("PopupViewNote");
    PopupNote.value=note;
    PopupViewNote.innerHTML=note;
    
    var PopupUrl = document.getElementById("PopupUrl");
    
    var PopupNameImg = document.getElementById("PopupNameImg");
    var PopupPhoto = document.getElementById("PopupImage");
    var PopupViewPhoto = document.getElementById("PopupViewImage");
    PopupPhoto.src=PopupUrl.value+"\\resources\\Photos\\"+photo;
    PopupViewPhoto.src=PopupUrl.value+"\\resources\\Photos\\"+photo;
    PopupNameImg.value=photo;
    
    
}
