function EpassActionList() {

	var dataView;
	var grid;
	var data = document.getElementById("viewRequest").value

	/*<![CDATA[*/
	//var data = /*[[${managerEmplist}]]*/'Emp';//
	console.log(data);
	var data1 = JSON.parse(data);
    var leaveIdList=[];
     for(var i=0; i<data1.length; i++){
	leaveIdList.push(data1[i]['application_no']);
     console.log(data1[i]['application_no']);
}
     console.log(leaveIdList);
	var nextIdAction = 1;
	data1.forEach(function(item) {
	// Generate a unique id using the nextId value
		var itemIdAction= "item_" + nextIdAction;

		// Set the id property of the item object
		item.id = itemIdAction;

		// Increment the nextId value
		nextIdAction++;
	});
	// Use the updated items array list with unique ids
	/* unique id end */
var columnFilters = {};
	function checkboxFormatter(row, cell, value, columnDef, dataContext) {
        let a = dataContext.application_no;
        return '<input type="checkbox" value="' + a + '" name="checkName" id="checkBox"' + (value ? 'checked="checked"' : '') + '/>';
    }
function dateFormatter(row, cell, value, columnDef, dataContext) {
        const date = new Date(value);
        var getDay = date.toLocaleString("default", { day: "2-digit" });
        var getMonth = date.toLocaleString("default", { month: "2-digit" });
        var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
        const formattedDate = getDay + "-" + getMonth + "-" + getYear;// Format the date as a string
        return formattedDate; // Return the formatted date string
    }
//var check= document.getElementById("checkBox");
   var check = document.querySelectorAll('input[type="checkbox"]:checked');
console.log(check);
	var columns = [ {
		id:"select",
		name:"select",
		field:"select",
		width:60,
		formatter:checkboxFormatter
	}, {
			id : "application_no",

			name : "Application Number", //column name

			field : "application_no",
			sortable:true,
			width:100
		}, {
			id : "applicant_name",

			name : "Applicant Name",

			field : "applicant_name",
			sortable:true,
			width:100

		}, {
			id : "aadhar_no",

			name : "Aadhar Number",

			field : "aadhar_no",
			sortable:true,
			width:150

		}, {
			id : "reason",

			name : "Reason",

			field : "reason",
			sortable:true
				}, {
			id : "from_district",

			name : "From District",

			field : "from_district",
			sortable:true,
			width:100
		}, {
			id : "to_district",

			name : "To District",

			field : "to_district",
			sortable:true,
			width:100
		}, {
			id : "travel_date",

			name : "Travel Date",

			field : "travel_date",
			sortable:true,
			width:100,

			formatter:dateFormatter
		}, {
		    id : "return_date",

			name : "Return Date",

			field : "return_date",
             width:100,
             sortable:true,
			formatter:dateFormatter

		}, {
	     	id : "no_of_passengers",
			name : "Passengers",

			field : "no_of_passengers",
			sortable:true,
			width:70
		}, {
			id : "vehicle_no",

			name : "Vehicle Number",

			field : "vehicle_no",
			sortable:true,
			width:100
		}, {
			id : "mobile_no",

			name : "Mobile Number",

			field : "mobile_no",
			sortable:true,
     		width:100
	}, {
			id : "status",

			name : "Status",

			field : "status",
			sortable:true,
			width:100
		}, {
			id : "vaccination_certificate",

			name : "Vaccination Certificate",

			field : "vaccination_certificate",
			sortable:true,
			width:70
		}, {

			id : "Certificate_image",

			name : "Certificate Image",

			field : "Certificate_image",
			width:100,
		formatter:buttonFormatter
		}, ];
function buttonFormatter(row, cell, value, columnDef, dataContext) {

        let a = dataContext.application_no;
        return '<form action="/listEpassFormImage" method="post"><button type="submit" name="imageView" value="'+a+'">Click</button></form>';
    }


	var options = {
		editable: true,
		enableAddRow:false,
		enableCellNavigation: true,
		asyncEditorLoading: true,
		forceFitColumns: false,
		showHeaderRow: true,
		headerRowHeight: 45,
		rowHeight:40,
		explicitInitialization: true,
		topPanelHeight: 30,
		suppressCssChangesOnHiddenInit: true
	};


	var sortcol = "title";
	var sortdir = 1;

	function comparer(a, b) {
		var x = a[sortcol], y = b[sortcol];
		return (x === y ? 0 : (x > y ? 1 : -1));
	}

	$(".grid-header .ui-icon")
		.addClass("ui-state-default ui-corner-all")
		.mouseover(function(e) {
			$(e.target).addClass("ui-state-hover")
		})
		.mouseout(function(e) {
			$(e.target).removeClass("ui-state-hover")
		});

	$(function() {
		/* filter start */
		function filter(item) {
			for (var columnId in columnFilters) {
				if (columnId !== undefined && columnFilters[columnId] !== "") {
					var column = grid.getColumns()[grid.getColumnIndex(columnId)];

					if (item[column.field] !== undefined) {
						var filterResult = typeof item[column.field].indexOf === 'function'
							? (item[column.field].indexOf(columnFilters[columnId]) === -1)
							: (item[column.field] !== columnFilters[columnId]);

						if (filterResult) {
							return false;
						}
					}
				}
			}
			return true;
		}
		/* filter end */


		//		dataView = new Slick.Data.DataView({ inlineFilters: true });
		dataView = new Slick.Data.DataView();
		grid = new Slick.Grid("#myGrid", dataView, columns, options);
		grid.setSelectionModel(new Slick.RowSelectionModel());

		// header row start
		dataView.onRowCountChanged.subscribe(function(e, args) {
			grid.updateRowCount();
			grid.render();
		});

		dataView.onRowsChanged.subscribe(function(e, args) {
			grid.invalidateRows(args.rows);
			grid.render();
		});

		$(grid.getHeaderRow()).delegate(":input", "change keyup",
			function(e) {
				var columnId = $(this).data("columnId");
				if (columnId !== null) {
					columnFilters[columnId] = $.trim($(this).val());
					dataView.refresh();
				}
			});

		grid.onHeaderRowCellRendered.subscribe(function(e, args) {
			$(args.node).empty();
			$("<input type='text'>").data("columnId", args.column.id).val(
				columnFilters[args.column.id]).appendTo(args.node);

		});
		// header row end

		// move the filter panel defined in a hidden div into grid top panel
		$("#inlineFilterPanel")
			.appendTo(grid.getTopPanel())
			.show();

		grid.onCellChange.subscribe(function(e, args) {
			dataView.updateItem(args.item.id, args.item);
		});

		grid.onAddNewRow.subscribe(function(e, args) {
			var item = { "num": data.length, "id": "new_" + (Math.round(Math.random() * 10000)), "title": "New task", "duration": "1 day", "percentComplete": 0, "start": "01/01/2009", "finish": "01/01/2009", "effortDriven": false };
			$.extend(item, args.item);
			dataView.addItem(item);
		});

		grid.onKeyDown.subscribe(function(e) {
			// select all rows on ctrl-a
			if (e.which !== 65 || !e.ctrlKey) {
				return false;
			}

			var rows = [];
			for (var i = 0; i < dataView.getLength(); i++) {
				rows.push(i);
			}

			grid.setSelectedRows(rows);
			e.preventDefault();
		});

		grid.onSort.subscribe(function(e, args) {
			sortdir = args.sortAsc;
			sortcol = args.sortCol.field;

			if ($.browser.msie && $.browser.version <= 8) {
				// using temporary Object.prototype.toString override
				// more limited and does lexicographic sort only by default, but can be much faster

				var percentCompleteValueFn = function() {
					var val = this["percentComplete"];
					if (val < 10) {
						return "00" + val;
					} else if (val < 100) {
						return "0" + val;
					} else {
						return val;
					}
				};

				// use numeric sort of % and lexicographic for everything else
				dataView.fastSort((sortcol === "percentComplete") ? percentCompleteValueFn : sortcol,sortdir);
			} else {
				// using native sort with comparer
				// preferred method but can be very slow in IE with huge datasets
				dataView.sort(comparer, sortdir);
			}
		});

		// wire up model events to drive the grid
		dataView.onRowCountChanged.subscribe(function(e, args) {
			grid.updateRowCount();
			grid.render();
		});

		dataView.onRowsChanged.subscribe(function(e, args) {
			grid.invalidateRows(args.rows);
			grid.render();
		});

		dataView.onPagingInfoChanged.subscribe(function(e, pagingInfo) {
			var isLastPage = pagingInfo.pageNum === pagingInfo.totalPages - 1;
			var enableAddRow = isLastPage || pagingInfo.pageSize === 0;
			var options = grid.getOptions();

			if (options.enableAddRow !== enableAddRow) {
				grid.setOptions({ enableAddRow: enableAddRow });
			}
		});

		/*
				// wire up the slider to apply the filter to the model
				$("#pcSlider,#pcSlider2").slider({
					"range": "min",
					"slide": function(event, ui) {
						Slick.GlobalEditorLock.cancelCurrentEdit();

						if (percentCompleteThreshold != ui.value) {
							window.clearTimeout(h_runfilters);
							h_runfilters = window.setTimeout(updateFilter, 10);
							percentCompleteThreshold = ui.value;
						}
					}
				});

				// wire up the search textbox to apply the filter to the model
				$("#txtSearch,#txtSearch2").keyup(function(e) {
				Slick.GlobalEditorLock.cancelCurrentEdit();
				// clear on Esc
					if (e.which == 27) {
						this.value = "";
					}

					searchString = this.value;
					updateFilter();
				});
		*/

		$("#btnSelectRows").click(function() {
			if (!Slick.GlobalEditorLock.commitCurrentEdit()) {
				return;
			}

			var rows = [];
			for (var i = 0; i < 10 && i < dataView.getLength(); i++) {
				rows.push(i);
			}

			grid.setSelectedRows(rows);
		});


		// initialize the model after all the events have been hooked up
		grid.init();
		dataView.beginUpdate();
		dataView.setItems(data1);
		/*
		dataView.setFilterArgs({
			percentCompleteThreshold: percentCompleteThreshold,
			searchString: searchString
		});
		*/
		dataView.setFilter(filter);
		dataView.endUpdate();

		// if you don't want the items that are not visible (due to being filtered out
		// or being on a different page) to stay selected, pass 'false' to the second arg
		dataView.syncGridSelection(grid, true);

		//$("#gridContainer").resizable();
	})

}
