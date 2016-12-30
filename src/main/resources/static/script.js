if(tasks&&tasks.length!==0) {
    $('#task').tmpl(tasks).appendTo('#task_list');
} else {
    addTaskRow();
}

$('.btn-primary').on("click", addTaskRow);
function addTaskRow() {
	$('#task').tmpl([{id:totalTask+1}]).appendTo('#task_list');
	++totalTask;
};

 $(document).on('click', '.glyphicon-remove', function () {
     alert("Are you sure you want to delete this task?");
     $(this).closest('li').remove();
     return false;
 });

 $( "#task-form" ).submit(function( event ) {
    var input = $("<input>")
                   .attr("type", "hidden")
                   .attr("name", "taskCount").val(totalTask);
    $('#task-form').append($(input));
});