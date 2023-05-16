function travelPassHistory() {

	var dataViews;
	var historyGrid;

	var dataHistory = document.getElementById("view").value

	/*<![CDATA[*/
	//var data = /*[[${managerEmplist}]]*/'Emp';//

	console.log(dataHistory);


	var data1 = JSON.parse(dataHistory);
     var leaveIdList=[];
     for(var i=0; i<data1.length; i++){
	leaveIdList.push(data1[i]['application_no']);
     console.log(data1[i]['application_no']);
}
     console.log(leaveIdList);

	var nextIdHistory = 1;
	data1.forEach(function(item) {
		// Generate a unique id using the nextId value
		var itemsId = "item_" + nextIdHistory;

		// Set the id property of the item object
		item.id = itemsId;

		// Increment the nextId value
		nextIdHistory++;
	});

	// Use the updated items array list with unique ids
	/* unique id end */

	var columnFilter = {};
function historyDateFormatter(row, cell, value, columnDef, dataContext) {
        const date = new Date(value);
        var getDay = date.toLocaleString("default", { day: "2-digit" });
        var getMonth = date.toLocaleString("default", { month: "2-digit" });
        var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
        const formattedDate = getDay + "-" + getMonth + "-" + getYear;// Format the date as a string
        return formattedDate; // Return the formatted date string
    }

var column = [ {


			name : "Application Number", //column name
			id : "application_no",

			field : "application_no",
			sortable:true,
			width:120

		}, {


			name : "Applicant Name",
			id : "applicant_name",

			field : "applicant_name",
			sortable:true,
			width:120


		}, {


			name : "Ticket Number",
			id : "ticket_no",

			field : "ticket_no",
			sortable:true,
			width:160

		}, {


			name : "Aadhar Number",
			id : "aadhar_no",

			field : "aadhar_no",
			sortable:true,
			width:150,

		}, {


			name : "Mobile Number",
			id : "mobile_no",

			field : "mobile_no",
			sortable:true,
			width:120
		}, {


			name : "Source",
			id : "source",

			field : "source",
			sortable:true,
			width:100
		}, {

			name : "Destination",
			id : "destination",

			field : "destination",
			sortable:true,
			width:100
		}, {


			name : "From Date",
			id : "travel_date",

			field : "travel_date",
			width:120,
			sortable:true,

			formatter:historyDateFormatter

		}, {


			name : "To Date",
			id : "return_date",

			field : "return_date",
			width:120,
			sortable:true,
     		formatter:historyDateFormatter
		}, {


			name : "Reason",
			id : "reason",

			field : "reason",
			sortable:true
		}, {


			name : "Passengers",
			id : "no_of_passengers",

			field : "no_of_passengers",
			sortable:true,
			width:90
		}, ];

	var option = {
		editable: true,
		enableAddRow:false,
		enableCellNavigation: true,
		asyncEditorLoading: true,
		forceFitColumns: false,
		showHeaderRow: true,
		headerRowHeight: 30,
		explicitInitialization: true,
		topPanelHeight: 25,
		suppressCssChangesOnHiddenInit: true
	};

	var sortcol2 = "title";
	var sortdir2 = 1;

	function comparer(a, b) {
		var x = a[sortcol2], y = b[sortcol2];
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
			for (var columnId in columnFilter) {
				if (columnId !== undefined && columnFilter[columnId] !== "") {
					var column = historyGrid.getColumns()[historyGrid.getColumnIndex(columnId)];

					if (item[column.field] !== undefined) {
						var filterResult = typeof item[column.field].indexOf === 'function'
							? (item[column.field].indexOf(columnFilter[columnId]) === -1)
							: (item[column.field] !== columnFilter[columnId]);

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
		dataViews = new Slick.Data.DataView();
		historyGrid = new Slick.Grid("#myGrid1", dataViews, column, option);
		historyGrid.setSelectionModel(new Slick.RowSelectionModel());

		// header row start
		dataViews.onRowCountChanged.subscribe(function(e, args) {
			historyGrid.updateRowCount();
			historyGrid.render();
    });
	dataViews.onRowsChanged.subscribe(function(e, args) {
			historyGrid.invalidateRows(args.rows);
			historyGrid.render();
		});

		$(historyGrid.getHeaderRow()).delegate(":input", "change keyup",
			function(e) {
				var columnId = $(this).data("columnId");
				if (columnId !== null) {
					columnFilter[columnId] = $.trim($(this).val());
					dataViews.refresh();
				}
			});

		historyGrid.onHeaderRowCellRendered.subscribe(function(e, args) {
			$(args.node).empty();
			$("<input type='text'>").data("columnId", args.column.id).val(
				columnFilter[args.column.id]).appendTo(args.node);

		});
		// header row end


		// move the filter panel defined in a hidden div into grid top panel
		$("#inlineFilterPanel")
			.appendTo(historyGrid.getTopPanel())
			.show();

		historyGrid.onCellChange.subscribe(function(e, args) {
			dataViews.updateItem(args.item.id, args.item);
		});

		historyGrid.onAddNewRow.subscribe(function(e, args) {
			var item = { "num": dataHistory.length, "id": "new_" + (Math.round(Math.random() * 10000)), "title": "New task", "duration": "1 day", "percentComplete": 0, "start": "01/01/2009", "finish": "01/01/2009", "effortDriven": false };
			$.extend(item, args.item);
			dataViews.addItem(item);
		});

		historyGrid.onKeyDown.subscribe(function(e) {
			// select all rows on ctrl-a
			if (e.which !== 65 || !e.ctrlKey) {
				return false;
			}

			var rows = [];
			for (var i = 0; i < dataViews.getLength(); i++) {
				rows.push(i);
			}

			historyGrid.setSelectedRows(rows);
			e.preventDefault();
		});

		historyGrid.onSort.subscribe(function(e, args) {
			sortdir2 = args.sortAsc;
			sortcol2 = args.sortCol.field;

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
				dataViews.fastSort((sortcol2 === "percentComplete") ? percentCompleteValueFn : sortcol2, sortdir2);
			} else {
				// using native sort with comparer
				// preferred method but can be very slow in IE with huge datasets
				dataViews.sort(comparer, sortdir2);
			}
		});

		// wire up model events to drive the grid
		dataViews.onRowCountChanged.subscribe(function(e, args) {
			historyGrid.updateRowCount();
			historyGrid.render();
		});

		dataViews.onRowsChanged.subscribe(function(e, args) {
			historyGrid.invalidateRows(args.rows);
			historyGrid.render();
		});

		dataViews.onPagingInfoChanged.subscribe(function(e, pagingInfo) {
			var isLastPage = pagingInfo.pageNum === pagingInfo.totalPages - 1;
			var enableAddRow = isLastPage || pagingInfo.pageSize === 0;
			var options = historyGrid.getOptions();

			if (options.enableAddRow !== enableAddRow) {
				historyGrid.setOptions({ enableAddRow: enableAddRow });
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
			for (var i = 0; i < 10 && i < dataViews.getLength(); i++) {
				rows.push(i);
			}

			historyGrid.setSelectedRows(rows);
		});


		// initialize the model after all the events have been hooked up
		historyGrid.init();
		dataViews.beginUpdate();
		dataViews.setItems(data1);
		/*
		dataView.setFilterArgs({
			percentCompleteThreshold: percentCompleteThreshold,
			searchString: searchString
		});
		*/
		dataViews.setFilter(filter);
		dataViews.endUpdate();

		// if you don't want the items that are not visible (due to being filtered out
		// or being on a different page) to stay selected, pass 'false' to the second arg
		dataViews.syncGridSelection(historyGrid, true);

		//$("#gridContainer").resizable();
	})

}
