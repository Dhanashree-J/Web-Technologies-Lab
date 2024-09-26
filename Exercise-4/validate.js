function validateForm() 
    {
        var name = document.getElementById("name").value;
        if (name === "") {
            alert("Name must not be empty");
            return false;
        }
        var age = document.getElementById("age").value;
        if (age === "") {
            alert("Age must not be empty");
            return false;
       }
       if (age < 1 || age > 100) {
           alert("Please enter a valid age");
           return false;
       }
       var radios = document.getElementsByName("gender");
       var filled = false;
       for (var i = 0; i < radios.length; i++) {
           if (radios[i].checked) {
               filled = true;
           }
       }
       if(!filled)
       {
           alert("Select gender");
           return false;
       }
       var mail=document.getElementById("mail").value;
       if(mail==="")
       {
           alert("Mail id required");
           return false;
       }
       var atindex=mail.indexOf("@");
       var dotindex=mail.indexOf(".",atindex);
       if(atindex < 1 || dotindex < 2 || dotindex < atindex+2)
       {
           alert("Enter valid email address");
           return false;
       }
       var phone = document.getElementById("phone").value;
       if (phone === "") 
       {
           alert("Fill the phone number");
           return false;
       }
       var date= document.getElementById("tdate").value;
       if (date === "") {
           alert("Select the travel date");
           return false;
       }
       const tdate=new Date(date);
       const today=new Date();
       if(tdate.getDate() < today.getDate() || tdate.getFullYear() < today.getFullYear() || tdate.getMonth() < today.getMonth())
       {
           alert("Enter the valid date");
           return false;
       }
       var stay = document.getElementById("stay").value;
       if (stay === "") {
           alert("Enter the number of days staying");
           return false;
       }
       var location=document.getElementById("location").value;
       if(location==="")
       {
            alert("Enter pickup location");
            return false;
       }
       var places = document.getElementsByName("tourist-places");
       var selected= false;
       for (var i = 0; i < places.length; i++) 
       {
           if (places[i].checked) {
               selected = true;
               break;
           }
       }
       if(!selected)
       {
           alert("Select your preferred spots");
           return false;
       }
       var files=document.querySelector("input[type='file']")
       if (files.files.length === 0) {
           alert("Upload verification document");
           return false;
       }
       alert("Submitted Successfully");
       return true;
   }