function travelPassActionList() {

	var travelActionView;
	var travelGrid;

	var travelPassData = document.getElementById("view").value

	/*<![CDATA[*/
	//var data = /*[[${managerEmplist}]]*/'Emp';//

	console.log(travelPassData);


	var data1 = JSON.parse(travelPassData);
     var travelPassList=[];
     for(var i=0; i<data1.length; i++){
	travelPassList.push(data1[i]['application_no']);
     console.log(data1[i]['application_no']);
}
     console.log(travelPassList);

	var nextIdTravel = 1;
	data1.forEach(function(item) {
		// Generate a unique id using the nextId value
		var itemIdTravel = "item_" + nextIdTravel;

		// Set the id property of the item object
		item.id = itemIdTravel;

		// Increment the nextId value
		nextIdTravel++;
	});
	// Use the updated items array list with unique ids
	/* unique id end */

	var columnFilters = {};

	function checkboxFormatter(row, cell, value, columnDef, dataContext) {
        let a = dataContext.application_no;
        return '<input type="checkbox" value="' + a + '" name="checkName" id="checkBox"' + (value ? 'checked="checked"' : '') + '/>';
    }
function travelDateFormatter(row, cell, value, columnDef, dataContext) {
        const date = new Date(value);
        var getDay = date.toLocaleString("default", { day: "2-digit" });
        var getMonth = date.toLocaleString("default", { month: "2-digit" });
        var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
        const formattedDate = getDay + "-" + getMonth + "-" + getYear;// Format the date as a string
        return formattedDate; // Return the formatted date string
    }

//var check= document.getElementById("checkBox");
   var check = document.querySelectorAll('input[type="checkbox"]:checked');
console.log(check);var columns = [ {

        id:"select",
		name:"select",
		field:"select",
		width:50,
		sortable:true,
		formatter:checkboxFormatter
	}, {



			id : "application_no",

			name : "Application Number", //column name
			sortable:true,

			field : "application_no",
			width:120

		}, {

			id : "applicant_name1",

			name : "Applicant Name",
			sortable:true,

			field : "applicant_name1",
			width:120


		}, {

			id : "ticket_no",

			name : "Ticket Number",
			sortable:true,

			field : "ticket_no",
			width:160

		}, {

			id : "aadhar_number",

			name : "Aadhar Number",
			sortable:true,

			field : "aadhar_number",
			width:150,

		}, {

			id : "mobile_number",

			name : "Mobile Number",

			sortable:true,
			field : "mobile_number",
			width:120
		}, {

			id : "source",

			name : "Source",

			sortable:true,
			field : "source",
			width:100
		}, {
			id : "destination",

			name : "Destination",

			sortable:true,
			field : "destination",
			width:100
		}, {

			id : "from_date",

			name : "From Date",

			field : "from_date",
			sortable:true,
			width:120,

			formatter:travelDateFormatter

		}, {

			id : "to_date",

			name : "To Date",

			sortable:true,
			field : "to_date",
			width:120,
     		formatter:travelDateFormatter
		}, {

			id : "reasons",


			field : "reasons",
			name : "Reason",
			sortable:true
		}, {

			id : "number_of_passengers",

			name : "Passengers",

			sortable:true,
			field : "number_of_passengers",
			width:90
		}, {

			id : "vaccine_certificate",


			name : "Vaccination Certificate",
			sortable:true,
			field : "vaccine_certificate",
			width:90
				}, {

			id : "action",
			name : "Status",
			sortable:true,
			field : "action",
			width:90
		}, {

			id : "vaccine_Certificate_image",

			name : "Certificate Image",

			width:100,
			field : "vaccine_Certificate_image",

			formatter:buttonFormatter
		}, ];

function buttonFormatter(row, cell, value, columnDef, dataContext) {

        let a = dataContext.application_no;
        return '<form action="/listTravelPassFormImage" method="post"><button type="submit" name="travelImgView" value="'+a+'">Click</button></form>';
    }

	var travelOptions = {
		editable: true,
		enableAddRow:false,
		enableCellNavigation: true,
		asyncEditorLoading: true,
		forceFitColumns: false,
		showHeaderRow: true,
		headerRowHeight: 30,
		rowHeight:40,
		explicitInitialization: true,
		topPanelHeight: 25,
		suppressCssChangesOnHiddenInit: true
	};


	var sortcol3 = "title";
	var sortdir3 = 1;

	function comparer(a, b) {
		var x = a[sortcol3], y = b[sortcol3];
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
					var column = travelGrid.getColumns()[travelGrid.getColumnIndex(columnId)];

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
		travelActionView = new Slick.Data.DataView();
		travelGrid = new Slick.Grid("#myGrid1", travelActionView, columns, travelOptions);
		travelGrid.setSelectionModel(new Slick.RowSelectionModel());

		// header row start
		travelActionView.onRowCountChanged.subscribe(function(e, args) {
			travelGrid.updateRowCount();
			travelGrid.render();
    });
	travelActionView.onRowsChanged.subscribe(function(e, args) {
			travelGrid.invalidateRows(args.rows);
			travelGrid.render();
		});

		$(travelGrid.getHeaderRow()).delegate(":input", "change keyup",
			function(e) {
				var columnId = $(this).data("columnId");
				if (columnId !== null) {
					columnFilters[columnId] = $.trim($(this).val());
					travelActionView.refresh();
				}
			});

		travelGrid.onHeaderRowCellRendered.subscribe(function(e, args) {
			$(args.node).empty();
			$("<input type='text'>").data("columnId", args.column.id).val(
				columnFilters[args.column.id]).appendTo(args.node);

		});
		// header row end


		// move the filter panel defined in a hidden div into grid top panel
		$("#inlineFilterPanel")
			.appendTo(travelGrid.getTopPanel())
			.show();

		travelGrid.onCellChange.subscribe(function(e, args) {
			travelActionView.updateItem(args.item.id, args.item);
		});

		travelGrid.onAddNewRow.subscribe(function(e, args) {
			var item = { "num": travelPassData.length, "id": "new_" + (Math.round(Math.random() * 10000)), "title": "New task", "duration": "1 day", "percentComplete": 0, "start": "01/01/2009", "finish": "01/01/2009", "effortDriven": false };
			$.extend(item, args.item);
			travelActionView.addItem(item);
		});

		travelGrid.onKeyDown.subscribe(function(e) {
			// select all rows on ctrl-a
			if (e.which !== 65 || !e.ctrlKey) {
				return false;
			}

			var rows = [];
			for (var i = 0; i < travelActionView.getLength(); i++) {
				rows.push(i);
			}

			travelGrid.setSelectedRows(rows);
			e.preventDefault();
		});

		travelGrid.onSort.subscribe(function(e, args) {
			sortdir3 = args.sortAsc;
			sortcol3 = args.sortCol.field;

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
				travelActionView.fastSort((sortcol3 === "percentComplete") ? percentCompleteValueFn : sortcol3, sortdir3);
			} else {
				// using native sort with comparer
				// preferred method but can be very slow in IE with huge datasets
				travelActionView.sort(comparer, sortdir3);
			}
		});

		// wire up model events to drive the grid
		travelActionView.onRowCountChanged.subscribe(function(e, args) {
			travelGrid.updateRowCount();
			travelGrid.render();
		});

		travelActionView.onRowsChanged.subscribe(function(e, args) {
			travelGrid.invalidateRows(args.rows);
			travelGrid.render();
		});

		travelActionView.onPagingInfoChanged.subscribe(function(e, pagingInfo) {
			var isLastPage = pagingInfo.pageNum === pagingInfo.totalPages - 1;
			var enableAddRow = isLastPage || pagingInfo.pageSize === 0;
			var options = travelGrid.getOptions();

			if (options.enableAddRow !== enableAddRow) {
				travelGrid.setOptions({ enableAddRow: enableAddRow });
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
			for (var i = 0; i < 10 && i < travelActionView.getLength(); i++) {
				rows.push(i);
			}

			travelGrid.setSelectedRows(rows);
		});


		// initialize the model after all the events have been hooked up
		travelGrid.init();
		travelActionView.beginUpdate();
		travelActionView.setItems(data1);
		/*
		dataView.setFilterArgs({
			percentCompleteThreshold: percentCompleteThreshold,
			searchString: searchString
		});
		*/
		travelActionView.setFilter(filter);
		travelActionView.endUpdate();

		// if you don't want the items that are not visible (due to being filtered out
		// or being on a different page) to stay selected, pass 'false' to the second arg
		travelActionView.syncGridSelection(travelGrid, true);

		//$("#gridContainer").resizable();
	})

}
