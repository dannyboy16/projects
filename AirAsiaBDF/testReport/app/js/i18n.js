'use strict';

var i18n = angular.module('i18n', ['pascalprecht.translate']);


i18n.config(function($translateProvider) {
  $translateProvider.translations('es', {
    PATH_FILE: 'Escribir ruta de carga de fichero:',
    LOAD_FILE: 'Cargar Fichero',
    DEFAULT_FILE: 'Fichero por defecto',
    SELECT_SUITE: 'Seleccionar suite: ',
    ALL: 'Todos',
    SEARCH_BY_NAME: 'Buscar por nombre:',
    ORDER_BY: 'Ordenar por:',
    ALPHABETICAL: 'Alfabetico',
    STATUS: 'Estado',
    SHOW_ONLY: 'Mostrar solo:',
    PASSED_MANY: 'Pasados',
    FAILED_MANY: 'Fallados',
    BLOCKED_MANY: 'Bloqueados',
    PASSED: 'Pasado',
    FAILED: 'Fallado',
    BLOCKED: 'Bloqueado',
    SUMMARY: 'Resumen de la ejecución',
    RAN_TEST: 'Tests ejecutados',
    PASSED_TEST: 'Tests pasados',
    FAILED_TEST: 'Tests fallados',
    BLOCKED_TEST: 'Tests bloqueados',
    NAME: 'Caso',
    DESCRIPTION: 'Descripción:',
    GLOBAL_STATUS: 'Resultado del caso:',
    STEP_NUMBER:'Paso:',
    STATUS_EXPLAIN:'Resultado:',
    STEP_BY_STEP: 'Paso a paso',
    CLOSE:'Cerrar',
    BACK:'Volver',
    ALERT_OK :'Fichero de datos cargado correctamente',
    ALERT_FAIL :'Fichero de datos no encontrado',
    TITTLE:'REPORTE DE PRUEBAS',
    PREVIOUS_EXECUTION:'Ejecución anterior',
    NEXT_EXECUTION: 'Ejecución siguiente',
    EXECUTION_NUMBER:'Ejecución número',
    LAST_EXECUTION: 'última ejecución',
    VIEW_REPORT: 'Ver reporte',
    RETURN_EXECUTION: 'Volver a ejecuciones',
    RETURN_TEST: 'Volver a tests'
  });


  $translateProvider.translations('en', {
    PATH_FILE: 'Write load path file:',
    LOAD_FILE: 'Load file',
    DEFAULT_FILE: 'Default file',
    SELECT_SUITE: 'Choose a suite',
    ALL: 'All',
    SEARCH_BY_NAME: 'Search by name:',
    ORDER_BY: 'Order by:',
    ALPHABETICAL: 'Alphabetical',
    STATUS: 'Status',
    SHOW_ONLY: 'Show only',
    PASSED_MANY: 'Passed',
    FAILED_MANY: 'Failed',
    BLOCKED_MANY: 'Blocked',
    PASSED: 'Passed',
    FAILED: 'Failed',
    BLOCKED: 'Blocked',
    SUMMARY: 'Run summary',
    RAN_TEST: 'Run tests',
    PASSED_TEST: 'Passed tests',
    FAILED_TEST: 'Failed tests',
    BLOCKED_TEST: 'Blocked tests',
    NAME: 'Test',
    DESCRIPTION: 'Description:',
    GLOBAL_STATUS: 'Status of the test:',
    STEP_NUMBER:'Step:',
    STATUS_EXPLAIN:'Status explain:',
    STEP_BY_STEP: 'Step by Step',
    CLOSE:'Close',
    ALERT_OK :'data file loaded correctly',
    ALERT_FAIL :'data file not found',
    BACK:'Back',
    TITTLE:'TEST REPORT',
    PREVIOUS_EXECUTION:'Previous Execution',
    NEXT_EXECUTION: 'Ejecución siguiente',
    EXECUTION_NUMBER:'Execution number',
    LAST_EXECUTION: 'Last execution',
    VIEW_REPORT: 'View Report',
    RETURN_EXECUTION: 'Return to executions',
    RETURN_TEST: 'Return to tests'	
  });

  $translateProvider.preferredLanguage('en');

});

