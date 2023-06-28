const search=()=>{
	 //console.log("Searching...")
	let query = $("#search-input").val()
	
	if(query==''){
		$(".search-result").hide();
	}
	else{
		console.log(query);
		
		let url=`http://localhost:8080/search/${query}`;
		fetch(url).then(response => {
			return response.json();
		}).then((data) => {
			console.log(data);
			let text=`<div class='list-group'>`
			
			data.forEach(book=> {
				text+=`<a href='#' class='list-group-item list-group-action'>${book.name}</a> `
			})
			text+=`</div>`
			
			$(".search-result").html(text);
			$(".search-result").show();
		});
		
	}
}