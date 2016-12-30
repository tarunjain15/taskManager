
totalTask = 0;

$('#task').tmpl(tasks).appendTo('#task_list');
totalTask = totalTask+tasks.length;

$('.btn-primary').on("click", addTaskRow);
function addTaskRow() {
	$('#task').tmpl([{id:totalTask+1}]).appendTo('#task_list');
	++totalTask;
};

 $(document).on('click', '.glyphicon-remove', function () { // <-- changes
     alert("Are you sure you want to delete this task?");
     $(this).closest('li').remove();
     return false;
 });

 $( "#task-form" ).submit(function( event ) {
    var url = "saveTasks"; // the script where you handle the form input.
    $.ajax({
           type: "POST",
           url: url,
           data: $("#idForm").serialize(), // serializes the form's elements.
           success: function(data)
           {
               alert(data); // show response from the php script.
           }
         });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});