<!DOCTYPE html>
<html>
<head>
<title>Spell Checker</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

</head>
<body>

	<div class="col-sm-6 col-sm-offset-3">
		<h1>Spell Checking and Correction</h1>

		<form action="check" method="POST">
			<div id="name-group" class="form-group">
				<label for="name">Enter Paragraph</label>
				<textarea class="form-control" id="inputText" name="inputText"
					rows="5"></textarea>
			</div>

			<div class="col-md-12 text-center">
				<button id="subm" type="submit" class="btn btn-success">Run
					Check</button>
			</div>
			<div id="results"></div>
			<div id="table" class="col-lg-6 text-center" hidden="hidden">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Type</th>
							<th>Misspelled Words</th>
							<th>Start Index</th>
							<th>End Index</th>
							<th>Replacement Words</th>
						</tr>
					</thead>
					<tbody id="issuesList">
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<script>

$(document).ready(function () {
	
	  $("form").submit(function (event) {
	    var formData = {
	    	inputText: $("#inputText").val(),
	    };

	    $.ajax({
	      type: "POST",
	      url: "check",
	      data: formData,
	      dataType: "json",
	      encode: true,
	    }).done(function (data) {
	    	$("#results").empty();
	    	$('#issuesList').empty();
	    	$("#results").append("Check Id : "+data.id+
	    			"<br>Words : "+data.info.words+
	    			"<br>Time : "+data.info.time+
	    			"<br>Applied Suggestion : "+data.outputText+"<br>"
	    	);
	    	$.each(data.issues, function (key, value) {
				$('#issuesList').append("<tr>\
							<td>"+value.type+"</td>\
							<td>"+value.match.surface+"</td>\
							<td>"+value.match.beginOffset+"</td>\
							<td>"+value.match.endOffset+"</td>\
							<td>"+value.match.replacement+"</td>\
							</tr>");
	    });
	    	//$("#subm").hide();
	    	$("#table").show();
	    	//$("#inputText").val(data.outputText);
	    });

	    event.preventDefault();
	  });
	});
</script>

</body>
</html>