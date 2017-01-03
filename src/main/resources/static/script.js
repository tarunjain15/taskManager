if(tasks&&tasks.length!==0) {
    $('#task').tmpl(tasks).appendTo('#task_list');
    $.each($(":checkbox"),function(key, checkbox) {
        if($(checkbox).val() === "true") {
            $(checkbox).prop("checked",true);
        }
    })
} else {
    addTaskRow();
}
$("#date-navbar").datepicker();

$('.btn-primary').on("click", addTaskRow);
function addTaskRow() {
	$('#task').tmpl([{id:totalTask+1}]).appendTo('#task_list');
	++totalTask;
	$( "#task_list").find("input[name^='date']").last().datepicker();
    $( "#task_list").find("input[name^='project']" ).last().bind("blur",addProjectToSuggestions);
	$( "#task_list").find("input[name^='project']" ).last().typeahead({
      hint: true,
      highlight: false,
      minLength: 0
    },
    {
      name: 'projectList',
      source: substringMatcher(projectList)
    });
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

 $( function() {
    $( "input[name^='date']" ).datepicker();
  } );

$(document).on('change',(":checkbox"), function() {
    if(this.checked) {
        $(this).val("true");
    }
    else {
        $(this).val("false");
    }
});


function substringMatcher (strs) {
  return function findMatches(q, cb) {
    var matches, substringRegex;

    // an array that will be populated with substring matches
    matches = [];

    // regex used to determine if a string contains the substring `q`
    substrRegex = new RegExp(q, 'i');

    // iterate through the pool of strings and for any string that
    // contains the substring `q`, add it to the `matches` array
    $.each(strs, function(i, str) {
      if (substrRegex.test(str)) {
        matches.push(str);
      }
    });

    cb(matches);
  };
};


$( "input[name^='project']" ).typeahead({
  hint: true,
  highlight: false,
  minLength: 0
},
{
  name: 'projectList',
  source: substringMatcher(projectList)
});

$( "input[name^='project']" ).bind("blur",addProjectToSuggestions);
function addProjectToSuggestions(){
    var value = $(this).val();
    if((value)&&!($.inArray(value, projectList) !== -1)){
        projectList.push($(this).val());
        addProjectToList("",value);
    }
}
$.each(projectList,addProjectToList);
function addProjectToList(key, value) {
   var input = "<li><a href=/project?project="+escape(value)+">"+value+"</a></li>"
   $("#project-dropdown").append(input);
}