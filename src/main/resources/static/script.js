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
removedTaskIds =[];
 $(document).on('click', '.glyphicon-remove', function () {
     alert("Are you sure you want to delete this task?");
     removedTaskIds.push($(this).closest('li').find('input').attr('name').split('_')[1]);

     $(this).closest('li').remove();
     return false;
 });

 $( "#task-form" ).submit(function( event ) {
    var input1 = $("<input>")
                   .attr("type", "hidden")
                   .attr("name", "taskCount").val(totalTask);
    var input2 = $("<input>")
                   .attr("type", "hidden")
                   .attr("name", "removedTasks").val(removedTaskIds);
    $('#task-form').append($(input1)).append($(input2));
});