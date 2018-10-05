var acckind_arr = new Array("Company", "Other Account", "Customer");
var acname_a = new Array();
acname_a[0]="";
acname_a[1]="Manager|Administration|Accounting|Controller|Support|Calling Centre|Warehouse";
acname_a[2]="Agency|Salesman|Operator|Supplier|Charger|Partner";
acname_a[3]="Customer";



function print_accountName(accountName, selectedIndex){
    var option_str = document.getElementById(accountName);
    option_str.length=0;    
    option_str.options[0] = new Option('Select','');
    option_str.selectedIndex = 0;
    var acname_arr = acname_a[selectedIndex].split("|");
    for (var i=0; i<acname_arr.length; i++) {
    option_str.options[option_str.length] = new Option(acname_arr[i],acname_arr[i]);
    
    }
    
}
function print_accountKind(accountKind){

    //given the id of the <select> tag as function argument, it inserts <option> tags
    var option_str = document.getElementById(accountKind);
    option_str.length=0;
    option_str.options[0] = new Option('Select','');
    option_str.selectedIndex = 0;
    for (var i=0; i<acckind_arr.length; i++) {
    option_str.options[option_str.length] = new Option(acckind_arr[i],acckind_arr[i]);
    }
}
