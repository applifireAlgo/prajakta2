Ext.define('Prajakta2.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Prajakta2.view.appreportui.ReportViewController',
	            'Prajakta2.view.appreportui.datagrid.DataGridPanel',
	            'Prajakta2.view.appreportui.datagrid.DataGridView',
	            'Prajakta2.view.appreportui.querycriteria.QueryCriteriaView',
	            'Prajakta2.view.appreportui.chart.ChartView',
	            'Prajakta2.view.appreportui.datapoint.DataPointView',
	            'Prajakta2.view.googlemaps.map.MapPanel',
	            'Prajakta2.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
