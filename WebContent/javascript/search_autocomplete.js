function lookup(text){
	
	if (text.length == 0) 
	{
		$('#autocompleteDropdown').fadeOut();
	} 
	else 
	{
		$.get("FabFlixSearchBar", {searchText: text}, function(list) {
			
			$('#autocompleteDropdown').html(list);
			$('#autocompleteDropdown').fadeIn();
		});
	}
};

$(document).ready(function() {
	
	var dropdown = $('<div id="autocompleteDropdown" style="position: absolute"></div>');
	
	$('body').append(dropdown);
	
	$('#autocompleteDropdown').hide();
	
	var pos = $('#searchBar').offset();
    var height = $('#searchBar').height();
    var width = $('#searchBar').width();

    $('#autocompleteDropdown').css({
    	
    	minWidth: width  + 'px',
        top: pos.top + height + 10+ 'px',
        left: pos.left  + 'px',
       });
    $('#bar_category').css({
    	minWidth: width + 5 + 'px'
    	
    });
	
	$('#searchBar').focus(function() {
		$('#autocompleteDropdown').fadeIn();
	});
	
	$('#searchBar').blur(function() {
		$('#autocompleteDropdown').fadeOut();
	});
	
});


