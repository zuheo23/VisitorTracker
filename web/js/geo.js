var map;
var marker;
var markers = {};
var visitors = {};
var mi = {id: "", action: "", name: "", longitude: "", latitude: "", mobile: "", email: "", ipAddress: "", marker: ""};
var options = {enableHighAccuracy: true};
var breakEarly = false;
//var options = {enableHighAccuracy: true, timeout: 5000, maximumAge: 0};

function createVisitorMarker(visitor) {
    if (markers[visitor['name']] === undefined) {
        marker = new google.maps.Marker({
            map: map,
            title: visitor['name']
        });
        markers[visitor['name']] = marker;
    };
}

function removeVisitorMarker(visitor) {
    if (visitors[visitor['name']] !== undefined) {
        console.log("DELETING " + visitor['name'])
        delete visitors[visitor['name']];
    }
    if (markers[visitor['name']] !== undefined) {
        console.log("DELETING MARKER" + visitor['name'])
        markers[visitor['name']].setMap(null);
        delete markers[visitor['name']];
    };
}


function setMyIp() {
    $.ajax({
        url: "/VisitorTracker/api/whatsmyip",
        async: true,
        success: function (data) {
            mi['name'] = data.ipAddress + ":" + data.port;
            //mi['name'] = data.ipAddress;
            mi['ipAddress'] = data.ipAddress;
            mi['host'] = data.host;
            mi['port'] = data.port;

            createVisitorMarker(mi);
            visitors[mi['name']] = mi;
        }
    });

}

function setMyLatLong(position) {
    mi['longitude'] = position.coords.longitude;
    mi['latitude'] = position.coords.latitude;
}


function updateMarkers() {
    console.log("Visitors: " + Object.keys(visitors).length);
    for (var key in visitors) {
        if (visitors.hasOwnProperty(key)) {
            //console.log(JSON.stringify(visitors[key]));
            markers[key].setPosition(new google.maps.LatLng(visitors[key].latitude, visitors[key].longitude));
        }
    }
}

function createMe() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(setMyLatLong, showError, options);
    } else {
        $("#error").html( "Geolocation is not supported by this browser." );
        
        breakEarly = true;
        socket.close();
    }
    setMyIp();
}

function showMyInfo() {

    $("#geo").html("Latitude: " + mi.latitude +
            "<br>Longitude: " + mi.longitude);
    $("#whatsmyip").html("IP ADDRESS: <strong>" + mi.ipAddress + "</strong><br />" +
            "HOST: <strong>" + mi.host + "</strong><br />" +
            "PORT: <strong>" + mi.port + "</strong><br />" +
            "NAME: <strong>" + mi.name + "</strong><br />");
}



function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            //x.innerHTML = "User denied the request for Geolocation."
            $("#error").html( "Please allow this site to use your location." );
            break;
        case error.POSITION_UNAVAILABLE:
            $("#error").html( "Location information is unavailable." );
            break;
        case error.TIMEOUT:
            $("#error").html( "The request to get user location timed out." );
            break;
        case error.UNKNOWN_ERROR:
            $("#error").html( "An unknown error occurred." );
            break;
    }
    
    breakEarly = true;
    socket.close();
}

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -33.833, lng: 151.120},
        zoom: 10
    });
}

function updateMyLatLong() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(setMyLatLong, showError, options);
    } else {
        $("error").html( "Geolocation is not supported by this browser." );
        
        breakEarly = true;
        socket.close();
    }
}

var loop;
function exitEarly() {
    clearInterval(loop);
}

function initAll() {
    initMap();
    createMe();
    showMyInfo();
    loop = setInterval(function () {
        updateMyLatLong();
        addVisitor(mi);
        updateMarkers();
        showMyInfo();
        console.log(breakEarly);
        if (breakEarly) { exitEarly() };
    }, 5000);
}
