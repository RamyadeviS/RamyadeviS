function epassHistory() {

	var dataViewHistory;
	var epassHistoryGrid;
   var epassHistoryData = document.getElementById("viewRequest").value

	/*<![CDATA[*/
	//var data = /*[[${managerEmplist}]]*/'Emp';//
	console.log(epassHistoryData);
	var data1 = JSON.parse(epassHistoryData);
    var leaveIdList=[];
     for(var i=0; i<data1.length; i++){
	leaveIdList.push(data1[i]['application_no']);
     console.log(data1[i]['application_no']);
}
     console.log(leaveIdList);
	var epassHistoryId = 1;
	data1.forEach(function(item) {
	// Generate a unique id using the nextId value
		var itemId = "item_" + epassHistoryId;

		// Set the id property of the item object
		item.id = itemId;

		// Increment the nextId value
		epassHistoryId++;
	});

	// Use the updated items array list with unique ids
	/* unique id end */
var columnFiltersHistory = {};

function epassHistoryDateFormatter(row, cell, value, columnDef, dataContext) {
        const date = new Date(value);
        var getDay = date.toLocaleString("default", { day: "2-digit" });
        var getMonth = date.toLocaleString("default", { month: "2-digit" });
        var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
        const formattedDate = getDay + "-" + getMonth + "-" + getYear;// Format the date as a string
        return formattedDate; // Return the formatted date string
    }
//var check= document.getElementById("checkBox");

	var columnsEpass = [ {
			id : "application_no",

			field : "application_no",
			name : "Application Number", //column name

			sortable:true,
			width:100
		}, {
			id : "applicant_name",

			field : "applicant_name",
			name : "Applicant Name",

			sortable:true,
			width:100

		}, {
			id : "aadhar_no",

			field : "aadhar_no",
			name : "Aadhar Number",

			sortable:true,
			width:150

		}, {
			id : "reason",

			field : "reason",
			name : "Reason",

			sortable:true
				}, {
			id : "from_district",

			field : "from_district",
			name : "From District",

			sortable:true,
			width:100
		}, {
			id : "to_district",

			field : "to_district",
			name : "To District",

			sortable:true,
			width:100
		}, {
			id : "travel_date",

			field : "travel_date",
			name : "Travel Date",

			sortable:true,
			width:100,

			formatter:epassHistoryDateFormatter
		}, {
		    id : "return_date",

			field : "return_date",
			name : "Return Date",

             width:100,
             sortable:true,
			formatter:epassHistoryDateFormatter

		}, {
	     	id : "no_of_passengers",
			field : "no_of_passengers",
			name : "Passengers",

			sortable:true,
			width:70
		}, {
			id : "vehicle_no",

			field : "vehicle_no",
			name : "Vehicle Number",

			sortable:true,
			width:100
		}, {
			id : "mobile_no",

			field : "mobile_no",
			name : "Mobile Number",

			sortable:true,
			width:100
		}, ];

	var optionsEpass = {
		editable: true,
		enableAddRow:false,
		enableCellNavigation: true,
		asyncEditorLoading: true,
		forceFitColumns: false,
		showHeaderRow: true,
		headerRowHeight: 45,
		explicitInitialization: true,
		topPanelHeight: 30,
		suppressCssChangesOnHiddenInit: true
	};


	var sortcol1 = "title";
	var sortdir1 = 1;

	function comparer(a, b) {
		var x = a[sortcol1], y = b[sortcol1];
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
			for (var columnId in columnFiltersHistory) {
				if (columnId !== undefined && columnFiltersHistory[columnId] !== "") {
					var column = epassHistoryGrid.getColumns()[epassHistoryGrid.getColumnIndex(columnId)];

					if (item[column.field] !== undefined) {
						var filterResult = typeof item[column.field].indexOf === 'function'
							? (item[column.field].indexOf(columnFiltersHistory[columnId]) === -1)
							: (item[column.field] !== columnFiltersHistory[columnId]);

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
		dataViewHistory = new Slick.Data.DataView();
		epassHistoryGrid = new Slick.Grid("#myGrid", dataViewHistory, columnsEpass, optionsEpass);
		epassHistoryGrid.setSelectionModel(new Slick.RowSelectionModel());

		// header row start
		dataViewHistory.onRowCountChanged.subscribe(function(e, args) {
			epassHistoryGrid.updateRowCount();
			epassHistoryGrid.render();
		});

		dataViewHistory.onRowsChanged.subscribe(function(e, args) {
			epassHistoryGrid.invalidateRows(args.rows);
			epassHistoryGrid.render();
		});

		$(epassHistoryGrid.getHeaderRow()).delegate(":input", "change keyup",
			function(e) {
				var columnId = $(this).data("columnId");
				if (columnId !== null) {
					columnFiltersHistory[columnId] = $.trim($(this).val());
					dataViewHistory.refresh();
				}
			});

		epassHistoryGrid.onHeaderRowCellRendered.subscribe(function(e, args) {
			$(args.node).empty();
			$("<input type='text'>").data("columnId", args.column.id).val(
				columnFiltersHistory[args.column.id]).appendTo(args.node);

		});
		// header row end


		// move the filter panel defined in a hidden div into grid top panel
		$("#inlineFilterPanel")
			.appendTo(epassHistoryGrid.getTopPanel())
			.show();

		epassHistoryGrid.onCellChange.subscribe(function(e, args) {
			dataViewHistory.updateItem(args.item.id, args.item);
		});

		epassHistoryGrid.onAddNewRow.subscribe(function(e, args) {
			var item = { "num": epassHistoryData.length, "id": "new_" + (Math.round(Math.random() * 10000)), "title": "New task", "duration": "1 day", "percentComplete": 0, "start": "01/01/2009", "finish": "01/01/2009", "effortDriven": false };
			$.extend(item, args.item);
			dataViewHistory.addItem(item);
		});

		epassHistoryGrid.onKeyDown.subscribe(function(e) {
			// select all rows on ctrl-a
			if (e.which !== 65 || !e.ctrlKey) {
				return false;
			}

			var rows = [];
			for (var i = 0; i < dataViewHistory.getLength(); i++) {
				rows.push(i);
			}

			epassHistoryGrid.setSelectedRows(rows);
			e.preventDefault();
		});

		epassHistoryGrid.onSort.subscribe(function(e, args) {
			sortdir1 = args.sortAsc;
			sortcol1 = args.sortCol.field;

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
				dataViewHistory.fastSort((sortcol1 === "percentComplete") ? percentCompleteValueFn : sortcol1, sortdir1);
			} else {
				// using native sort with comparer
				// preferred method but can be very slow in IE with huge datasets
				dataViewHistory.sort(comparer, sortdir1);
			}
		});

		// wire up model events to drive the grid
		dataViewHistory.onRowCountChanged.subscribe(function(e, args) {
			epassHistoryGrid.updateRowCount();
			epassHistoryGrid.render();
		});

		dataViewHistory.onRowsChanged.subscribe(function(e, args) {
			epassHistoryGrid.invalidateRows(args.rows);
			epassHistoryGrid.render();
		});

		dataViewHistory.onPagingInfoChanged.subscribe(function(e, pagingInfo) {
			var isLastPage = pagingInfo.pageNum === pagingInfo.totalPages - 1;
			var enableAddRow = isLastPage || pagingInfo.pageSize === 0;
			var options = epassHistoryGrid.getOptions();

			if (options.enableAddRow !== enableAddRow) {
				epassHistoryGrid.setOptions({ enableAddRow: enableAddRow });
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
			for (var i = 0; i < 10 && i < dataViewHistory.getLength(); i++) {
				rows.push(i);
			}

			epassHistoryGrid.setSelectedRows(rows);
		});


		// initialize the model after all the events have been hooked up
		epassHistoryGrid.init();
		dataViewHistory.beginUpdate();
		dataViewHistory.setItems(data1);
		/*
		dataView.setFilterArgs({
			percentCompleteThreshold: percentCompleteThreshold,
			searchString: searchString
		});
		*/
		dataViewHistory.setFilter(filter);
		dataViewHistory.endUpdate();

		// if you don't want the items that are not visible (due to being filtered out
		// or being on a different page) to stay selected, pass 'false' to the second arg
		dataViewHistory.syncGridSelection(epassHistoryGrid, true);

		//$("#gridContainer").resizable();
	})

}
