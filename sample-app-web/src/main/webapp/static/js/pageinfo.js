jQuery(function(){
	jQuery('.pageLink').click(function(){
		var targetForm = $(this).parents('form');
		if(targetForm.size()>0){
//			jQuery(this).parents(".page-boot").children('.pageSIBLING').val($(this).attr('pageNumber'));
			var pageNumber = $(this).attr('pageNumber');
			if(pageNumber){
				jQuery(".pageSIBLING",jQuery(this).parents(".pageinfo")).val(pageNumber);
			}
			targetForm.submit();
		}
	});
});