// Store Finder Logic
document.addEventListener("DOMContentLoaded", function() {
    const regionSelect = document.getElementById("regionSelect");
    const storeList = document.getElementById("storeList");
    const noStoreMsg = document.getElementById("noStoreMsg");

    function loadStores(region) {
        if (!storeList) return;
        fetch("/bin/itransparity/stores?region=" + region)
            .then(res => res.json())
            .then(data => {
                storeList.innerHTML = "";
                if (noStoreMsg) noStoreMsg.style.display = "none";
                if (!data || data.length === 0) {
                    if (noStoreMsg) {
                        noStoreMsg.style.display = "block";
                        noStoreMsg.innerText = "No dealerships found near your location.";
                    }
                    return;
                }
                data.forEach(store => {
                    storeList.innerHTML += "<div style='background:white; border:1px solid #ddd; padding:12px; margin:8px 0; border-radius:8px;'><h4 style='margin:0;'>" + store.name + "</h4><p style='margin:5px 0;'>" + store.address + "</p></div>";
                });
            })
            .catch(() => {
                if (noStoreMsg) {
                    noStoreMsg.style.display = "block";
                    noStoreMsg.innerText = "No dealerships found near your location.";
                }
            });
    }

    if (regionSelect) {
        regionSelect.addEventListener("change", e => loadStores(e.target.value));
        loadStores(regionSelect.value);

        // Auto-detect via IP
        fetch("https://ipapi.co/json/")
            .then(r => r.json())
            .then(j => {
                let city = (j.city || "").toLowerCase();
                if (city.includes("london")) regionSelect.value = "london";
                else if (city.includes("nairobi")) regionSelect.value = "nairobi";
                else regionSelect.value = "delhi";
                loadStores(regionSelect.value);
            }).catch(() => loadStores("delhi"));
    }
});