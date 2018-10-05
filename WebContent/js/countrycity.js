var country_arr = new Array("Saudi Arabia", "United Arab Emirates", "Qatar", "Kuwait", "Bahrain", "Oman", "Egypt", "India");
var city_a = new Array();
city_a[0]="";
city_a[1]="Al Makkah|Al Riyadh|Al Bahah|Al Hudud|Shamaliyah|Al Jawf|Al Madinah|Al Qasim|Ar Riyad|Ash Sharqiyah (Eastern Province)|Ha'il|Jizan|Makkah|Najran|Tabuk";
city_a[2]="Ajman|Abu Dhabi|Al Fujayrah|Sharjah|Dubai|Khaymah|Qaywayn";
city_a[3]="Abu az Zuluf|Abu Thaylah|Ad Dawhah al Jadidah|Al Arish|Al Bida ash Sharqiyah";
city_a[4]="Al Ahmadi|Hawalli |Al Salimiyah|Kuwait City|Sabah as Salim|Salwa|Al Fahahil";
city_a[5]="Manama|Muharraq |Riffa|Hamad Town|Isa Town|Jidaf|Seef|Saar|Jufair|Hoora";
city_a[6]="Muskat|Salalah|Sohar|Nizwa|Sur|Seeb|Muttrah|Bahla|Ibra";	
city_a[7]="Cairo|Giza|Aswan|alexendria|Luxor|Aswan|Asyut|Giza|Fayium";
city_a[8]="Delhi|Mumbai|Banglore|Chennai|Hyderbad|kolkata|Ahmedabad|Lucknow";



function print_city(city, selectedIndex){
    var option_str = document.getElementById(city);
    option_str.length=0;    
    option_str.options[0] = new Option('Select','');
    option_str.selectedIndex = 0;
    var city_arr = city_a[selectedIndex].split("|");
    for (var i=0; i<city_arr.length; i++) {
    option_str.options[option_str.length] = new Option(city_arr[i],city_arr[i]);
    
    }
    
}
function print_country(country){

    //given the id of the <select> tag as function argument, it inserts <option> tags
    var option_str = document.getElementById(country);
    option_str.length=0;
    option_str.options[0] = new Option('Select','');
    option_str.selectedIndex = 0;
    for (var i=0; i<country_arr.length; i++) {
    option_str.options[option_str.length] = new Option(country_arr[i],country_arr[i]);
    }
}
