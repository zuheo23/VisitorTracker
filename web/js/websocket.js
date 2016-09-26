//var socket = new WebSocket("ws://125.63.60.48:8080/VisitorTracker/actions");
//var socket = new WebSocket("ws://192.168.0.5:8080/VisitorTracker/actions");
var loc = window.location, new_uri;
if (loc.protocol === "https:") {
    new_uri = "wss:";
} else {
    new_uri = "ws:";
}
new_uri += "//" + loc.host;
new_uri += loc.pathname + "actions";
var socket = new WebSocket(new_uri);


socket.onmessage = onMessage;
window.onunload = function() {
    //removeVisitor(mi);
    socket.close();
};

function onMessage(event) {
    console.log("IMHERE");
    console.log(event.data);
    var visitor = JSON.parse(event.data);
    if (visitor.action === "add") {
        createVisitorMarker(visitor);
        visitors[visitor.name] = visitor;
    }
    if (visitor.action === "remove") {
        console.log("REMOVE action");
        removeVisitorMarker(visitor);
        delete visitors[visitor['name']];
    }
   
}

function addVisitor(visitor) {
    visitor['action'] = "add";
    console.log(JSON.stringify(visitor));
    socket.send(JSON.stringify(visitor));
}

function removeVisitor(visitor) {
    visitor['action'] = "remove";
    socket.send(JSON.stringify(visitor));
}

