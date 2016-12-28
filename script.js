var tasks = [
	{
		id: 1,
		desc: "hello",
		time: "1",
		date: "11/12",
		excuse: "idk"
	}
];

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

 $('#btn-success').on("click", saveTask);
function saveTask(){        
    $.post("process.php", $("#task-form").serialize(), function(data) {
        alert(data);
    });
};