//// web/js/locations.js
//
//const locations = {
//    "Maharashtra": {
//        "Mumbai": ["Andheri", "Bandra", "Dadar", "Malad", "Colaba"],
//        "Pune": ["Shivajinagar", "Kothrud", "Hadapsar", "Aundh", "Baner"],
//        "Nagpur": ["Sitabuldi", "Mahal", "Sadar", "Hingna", "Ramdaspeth"],
//        "Thane": ["Vashi", "Kopri", "Ghodbunder", "Mumbra", "Kalwa"],
//        "Nashik": ["Deolali", "Dwarka", "Panchavati", "Sinnar", "Trimbak"],
//        "Aurangabad": ["Cidco", "Jalna Road", "Paithan", "Kanchanwadi", "Chandrapur"],
//        "Ahilyanagar": [
//            "Ahilyanagar" ,"Akole", "Ambad", "Ambikanagar", "Ambevangaon",
//            "Rahuri Khurd","Rahuri Budruk", "Udadavane", "Unchakhadak Khurd", "Unchakhadak Budruk",
//            "Umbarewadi", "Jamagaon", "Takali", "Vambori", "Ghoti", "Tisgaon", "Chanda"
//        ]
//    },
//    "Karnataka": {
//        "Bangalore": ["Whitefield", "Koramangala", "Jayanagar", "Indiranagar"],
//        "Mysore": ["Chamundi Hill", "Vijayanagar", "Saraswathipuram"]
//    },
//    "Tamil Nadu": {
//        "Chennai": ["T Nagar", "Velachery", "Anna Nagar", "Adyar"],
//        "Coimbatore": ["Peelamedu", "RS Puram", "Saibaba Colony"]
//    },
//    "Delhi": {
//        "New Delhi": ["Connaught Place", "Chanakyapuri", "Karol Bagh"],
//        "South Delhi": ["Saket", "Hauz Khas", "Defence Colony"]
//    }
//};
//
//// Populate states and districts for cascading dropdowns
//function populateDistricts() {
//    const sections = [
//        { state: "perm_state", district: "perm_district", city: "perm_city" },
//        { state: "curr_state", district: "curr_district", city: "curr_city" },
//        { state: "bank_state", district: "bank_district", city: "bank_city" },
//        { state: "nominee_state", district: "nominee_district", city: "nominee_city" }
//    ];
//
//    sections.forEach(sec => {
//        const stateSelect = document.getElementById(sec.state);
//        const districtSelect = document.getElementById(sec.district);
//        const citySelect = document.getElementById(sec.city);
//
//        // Populate states
//        stateSelect.innerHTML = '<option value="" disabled selected>--Select State--</option>';
//        for (const state in locations) {
//            const opt = document.createElement("option");
//            opt.value = state;
//            opt.textContent = state;
//            stateSelect.appendChild(opt);
//        }
//
//        // Clear district and city
//        districtSelect.innerHTML = '<option value="" disabled selected>--Select District--</option>';
//        citySelect.innerHTML = '<option value="" disabled selected>--Select City--</option>';
//
//        // On state change → populate districts
//        stateSelect.addEventListener("change", () => {
//            districtSelect.innerHTML = '<option value="" disabled selected>--Select District--</option>';
//            citySelect.innerHTML = '<option value="" disabled selected>--Select City--</option>';
//
//            const selectedState = stateSelect.value;
//            if (selectedState && locations[selectedState]) {
//                for (const district in locations[selectedState]) {
//                    const opt = document.createElement("option");
//                    opt.value = district;
//                    opt.textContent = district;
//                    districtSelect.appendChild(opt);
//                }
//            }
//        });
//
//        // On district change → populate cities
//        districtSelect.addEventListener("change", () => {
//            citySelect.innerHTML = '<option value="" disabled selected>--Select City--</option>';
//            const selectedState = stateSelect.value;
//            const selectedDistrict = districtSelect.value;
//            if (selectedState && selectedDistrict && locations[selectedState][selectedDistrict]) {
//                locations[selectedState][selectedDistrict].forEach(city => {
//                    const opt = document.createElement("option");
//                    opt.value = city;
//                    opt.textContent = city;
//                    citySelect.appendChild(opt);
//                });
//            }
//        });
//    });
//}
//
//// Copy Permanent ↔ Current address
//function setupAddressCopy() {
//    const checkbox = document.getElementById("same_address");
//    checkbox.addEventListener("change", function() {
//        const permFields = ["perm_house", "perm_street", "perm_city", "perm_district", "perm_state", "perm_pincode"];
//        const currFields = ["curr_house", "curr_street", "curr_city", "curr_district", "curr_state", "curr_pincode"];
//        if (this.checked) {
//            permFields.forEach((f, i) => {
//                document.getElementById(currFields[i]).value = document.getElementById(f).value;
//            });
//        } else {
//            currFields.forEach(f => document.getElementById(f).value = "");
//        }
//    });
//}
//
//// Run on page load
//document.addEventListener("DOMContentLoaded", function() {
//    populateDistricts();
//    setupAddressCopy();
//});
//
const locations = {
    "Maharashtra": {
        "Mumbai": ["Andheri", "Bandra", "Dadar", "Malad", "Colaba"],
        "Pune": ["Shivajinagar", "Kothrud", "Hadapsar", "Aundh", "Baner"],
        "Nagpur": ["Sitabuldi", "Mahal", "Sadar", "Hingna", "Ramdaspeth"],
        "Thane": ["Vashi", "Kopri", "Ghodbunder", "Mumbra", "Kalwa"],
        "Nashik": ["Deolali", "Dwarka", "Panchavati", "Sinnar", "Trimbak"],
        "Aurangabad": ["Cidco", "Jalna Road", "Paithan", "Kanchanwadi", "Chandrapur"],
        "Ahilyanagar": ["Akole", "Ambad", "Ambikanagar", "Rahuri Khurd", "Tisgaon"]
    },
    "Karnataka": {
        "Bangalore": ["Whitefield", "Koramangala", "Jayanagar", "Indiranagar"],
        "Mysore": ["Chamundi Hill", "Vijayanagar", "Saraswathipuram"]
    },
    "Tamil Nadu": {
        "Chennai": ["T Nagar", "Velachery", "Anna Nagar", "Adyar"],
        "Coimbatore": ["Peelamedu", "RS Puram", "Saibaba Colony"]
    },
    "Delhi": {
        "New Delhi": ["Connaught Place", "Chanakyapuri", "Karol Bagh"],
        "South Delhi": ["Saket", "Hauz Khas", "Defence Colony"]
    }
};

function populateLocations(stateId, districtId, cityId) {
    const stateSelect = document.getElementById(stateId);
    const districtSelect = document.getElementById(districtId);
    const citySelect = cityId ? document.getElementById(cityId) : null;

    for (let state in locations) {
        let option = document.createElement("option");
        option.value = state;
        option.text = state;
        stateSelect.add(option);
    }

    stateSelect.onchange = function () {
        districtSelect.innerHTML = "<option value=''>--Select District--</option>";
        if (citySelect) citySelect.innerHTML = "<option value=''>--Select City--</option>";
        
        if (this.value in locations) {
            for (let district in locations[this.value]) {
                let option = document.createElement("option");
                option.value = district;
                option.text = district;
                districtSelect.add(option);
            }
        }
    };

    if (citySelect) {
        districtSelect.onchange = function () {
            citySelect.innerHTML = "<option value=''>--Select City--</option>";
            const selectedState = stateSelect.value;
            const selectedDistrict = this.value;
            if (locations[selectedState] && locations[selectedState][selectedDistrict]) {
                locations[selectedState][selectedDistrict].forEach(city => {
                    let option = document.createElement("option");
                    option.value = city;
                    option.text = city;
                    citySelect.add(option);
                });
            }
        };
    }
}

document.addEventListener("DOMContentLoaded", function () {
    populateLocations("bank_state", "bank_district", null);
    populateLocations("perm_state", "perm_district", "perm_city");
    populateLocations("curr_state", "curr_district", "curr_city");

    document.getElementById('same_address').onclick = function () {
        if (this.checked) {
            const fields = ['house', 'street', 'state', 'district', 'city', 'pincode'];
            fields.forEach(f => {
                const source = document.getElementById('perm_' + f);
                const target = document.getElementById('curr_' + f);
                if (source && target) {
                    target.value = source.value;
                    target.dispatchEvent(new Event('change'));
                    target.dispatchEvent(new Event('input'));
                }
            });
        }
    };
});