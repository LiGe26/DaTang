// JavaScript Document
function changenewsbg(k) {
	for(var j=1;j<=13;j++){
		if(j==k){				
			getObject('p'+j).className='bg02 f14b_0334';
		}
		else{				
			getObject('p'+j).className='bg01 f14b_0334';
		}
	}
}
function getObject(objectId) {
    if(document.getElementById && document.getElementById(objectId)) {
	// W3C DOM
	return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {
	// MSIE 4 DOM
	return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {
	// NN 4 DOM.. note: this won't find nested layers
	return document.layers[objectId];
    } else {
	return false;
    }
}
