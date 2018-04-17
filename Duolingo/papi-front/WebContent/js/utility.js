function createDynamiTable(groupData,id){
// EXTRACT VALUE FOR HTML HEADER. 
// ('Book ID', 'Book Name', 'Category' and 'Price')
var col = [];
for (var i = 0; i < groupData.length; i++) {
    for (var key in groupData[i]) {
        if (col.indexOf(key) === -1) {
            col.push(key);
        }
    }
}

// CREATE DYNAMIC TABLE.
var table = document.createElement("table")
table.setAttribute("class", "grp-table" );
table.setAttribute("id","data");

// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

var tr = table.insertRow(-1);                   // TABLE ROW.

for (var i = 0; i < col.length; i++) {
    var th = document.createElement("th");      // TABLE HEADER.
    th.innerHTML = col[i].toUpperCase();
    tr.appendChild(th);
}
tr.appendChild(th);
// ADD JSON DATA TO THE TABLE AS ROWS.
for (var i = 0; i < groupData.length; i++) {

    tr = table.insertRow(-1);

    for (var j = 0; j < col.length; j++) {
        var tabCell = tr.insertCell(-1);
        tabCell.innerHTML = groupData[i][col[j]];
        if( j==col.length-1){
          tabCell.innerHTML = '<div onclick=editGroup("'+group[i][col[j]]+'")><i class="fa fa-pencil"></i></div> <a href="'+group[i][col[j]]+'"><i class="fa fa-remove" style="color:red;"></i></a>';
        }
    }
}

// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
var divContainer = document.getElementById(id);
divContainer.innerHTML = "";
divContainer.appendChild(table);
}


function paginateTable(id,rows){
	console.log("paginating table for table #"+id+" and rows "+rows)
	$('#'+id).after('<div id="nav"></div>');
    var rowsShown = rows;
    var rowsTotal = $('#'+id+' tbody tr').length;
    var numPages = rowsTotal/rowsShown;
    for(i = 0;i < numPages;i++) {
        var pageNum = i + 1;
        $('#nav').append('<a href="#" rel="'+i+'">'+pageNum+'</a> ');
    }
    $('#'+id+' tbody tr').hide();
    $('#'+id+' tbody tr').slice(0, rowsShown).show();
    $('#nav a:first').addClass('active');
    $('#nav a').bind('click', function(){

        $('#nav a').removeClass('active');
        $(this).addClass('active');
        var currPage = $(this).attr('rel');
        var startItem = currPage * rowsShown;
        var endItem = startItem + rowsShown;
        $('#'+id+' tbody tr').css('opacity','0.0').hide().slice(startItem, endItem).
        css('display','table-row').animate({opacity:1}, 300);
    });
}

function createDynamiForm(group,id){
	// EXTRACT VALUE FOR HTML HEADER. 
	// ('Book ID', 'Book Name', 'Category' and 'Price')
	var col = [];
	for (var i = 0; i < group.length; i++) {
	    for (var key in group[i]) {
	        if (col.indexOf(key) === -1) {
	            col.push(key);
	        }
	    }
	}

	// CREATE DYNAMIC FORM.
	var form = document.createElement("form")
	form.setAttribute("class", "grp-table" );
	form.setAttribute("id","form-data");
//
	var fbq_outer_div = document.createElement("div");
	fbq_outer_div.setAttribute("class", "fbq_outer_div" );
	var fbq_list_div = document.createElement("div");
	fbq_list_div.setAttribute("class", "fbq_list_div" );
	
	for(var k =1;k<4;k++){
		let hr = document.createElement("hr");
		fbq_list_div.appendChild(hr);
	for (var j = 1; j<3; j++) {
		let fb_question = document.createElement("div");
		fb_question.setAttribute("class", "fbq_q" );
		
		//create paragraph element
		let p = document.createElement("p");
		p.textContent=j+") This is first Question";
		
		//create input element
		let iText = document.createElement("input");
		iText.type = "text";
		iText.name = "user_name";
		iText.id = "user_name1";
		iText.setAttribute("class", "fbq-text-box" );
		
		fb_question.appendChild(p)
		fb_question.appendChild(iText);
		
		let  rating_div = document.createElement("div");
		rating_div.setAttribute("class", "rating" );
		
		for(var r=5;r>=1;r--){
			console.log("adding star")
			let  rating_span = document.createElement("span");
			let iRadio = document.createElement("input");
			iRadio.type = "radio";
			iRadio.name = "rating";
			iRadio.id = "start"+r;
			iRadio.setAttribute("for", "start"+r );
			
			let label = document.createElement("label");
			label.for = "text";
			label.name = "user_name";
			//label.id = "user_name1";
			label.setAttribute("class", "fbq-text-box" );
			
			rating_span.appendChild(iRadio);
			rating_span.appendChild(label);
			rating_div.appendChild(rating_span);
			
		}
		let  rating_edit_btn = document.createElement("button");
		rating_edit_btn.setAttribute("for", "q-edit" );
		rating_edit_btn.innerHTML='<div onclick=editFBQuestion('+true+')>Edit</div>'
			//			fb_question.setAttribute("class", "fbq_q" );
//			
//			//create paragraph element
//			var p1 = document.createElement("p");
//			p1.textContent="This is first Question";
//			
//			//create input element
//			var i1 = document.createElement("input");
//			i1.type = "text";
//			i1.name = "user_name";
//			i1.id = "user_name1";
//			i1.setAttribute("class", "fbq-text-box" );
//			
//			fb_question.appendChild(p1)
//			fb_question.appendChild(i1);
		fb_question.appendChild(rating_div)
		fb_question.appendChild(rating_edit_btn)
		fbq_list_div.appendChild(fb_question);
		
	}
	let hr1 = document.createElement("hr");
	//fbq_list_div.appendChild(hr);
	}
	
	fbq_outer_div.appendChild(fbq_list_div);
	//var i = document.createElement("input");
	//i.type = "text";
	//i.name = "user_name";
	//i.id = "user_name1";

	//create a checkbox
	//var c = document.createElement("input");
	//c.type = "radio";
	//c.id = "checkbox1";
	//c.name = "check1";

	//create a button
	var s = document.createElement("input");
	s.type = "submit";
	s.value = "Submit";

	// add all elements to the form
	form.appendChild(fbq_outer_div);
	form.appendChild(s);

	// add the form inside the body
	//$(id).append(form);   //using jQuery or
	//document.getElementsByTagName('body')[0].appendChild(form); //pure javascript

	// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

	

	// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	var divContainer = document.getElementById(id);
	divContainer.innerHTML = "";
	divContainer.appendChild(form);
	}


function createChatList(chats, user_id, group_id, div_id){
	// CREATE DYNAMIC Chat Page.
	var outerdiv = document.createElement("div")
	
	for(var k =0;k<chats.length;k++){
		let container_box = document.createElement("div");
		container_box.setAttribute("class", "container-box" );
		
		let container = document.createElement("div");
		if(user_id== chats[k].user_id){
			container.setAttribute("class", "container darker" );
		}else{
			container.setAttribute("class", "container" );
		}
		
		let img_div = document.createElement("img");
		img_div.setAttribute("src", "images/image1.jpg" );
		if(user_id== chats[k].user_id){
			img_div.setAttribute("class", "right" );
		}else{
			img_div.setAttribute("class", "" );
		}
		
		let p_tag = document.createElement("p");
		p_tag.textContent=chats[k].description;
		
		let span_tag = document.createElement("span");
		if(user_id== chats[k].user_id){
			span_tag.setAttribute("class", "time-right" );
		}else{
			span_tag.setAttribute("class", "time-left" );
		}
		span_tag.textContent=chats[k].scheduleDate;
		
		container.appendChild(img_div);
		container.appendChild(p_tag);
		container.appendChild(span_tag);
		container_box.appendChild(container);
		outerdiv.appendChild(container_box);
	}
	// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	var divContainer = document.getElementById(div_id);
	divContainer.innerHTML = "";
	divContainer.appendChild(outerdiv);
}


function pushChat(data){
		$.ajax({
			method : "POST",
			url : "http://localhost:9090/papi/schedule/sharemessage",
			dataType : "json",
			contentType : "application/json",
			data : data
		}).done(function() {
		}).always(function() {
			showSnackBar("Refreshing Messages");
		    getChatList();
		  });
}
		

function getChatList(){
	$.ajax({
		method : "GET",
		url : "http://localhost:9090/papi/schedule/getmessagelist/1",
		dataType : "json",
		contentType : "application/json"
		//data : data //'{"name":"' + $("#pw_grp_name").val() + '"}'
	}).done(function(chatList) {
		//alert("Data Saved: " + chatList);
		
		createChatList(chatList, 1, 1, "chatList")
	});
	
}

function showSnackBar(msg) {
    //var x = document.getElementById("snackbar");
    $('#snackbar').html(msg);
    $('#snackbar').addClass('show');
    setTimeout(function(){ $('#snackbar').removeClass('show') }, 3000);
}