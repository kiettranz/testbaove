function transValueRecord(element) {
    var id = element.childNodes[1].childNodes[0].nodeValue;
    var email = element.childNodes[7].childNodes[0].nodeValue;
    var bonusId = document.getElementById("bonusId");
    var violateId = document.getElementById("violateId");
    var bonusEmail = document.getElementById("bonusEmail");
    var violateEmail = document.getElementById("violateEmail");
    bonusId.value = id;
    violateId.value = id;
    bonusEmail.value=email;
    violateEmail.value=email;
}
