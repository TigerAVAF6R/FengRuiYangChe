'use strict';

/* Directives */

angular.module('mainApp.directives', []).
  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }])
 .directive('navigation', function(){
    return {
        restrict: 'EA',
        replace: true,
        templateUrl:"/tpls/navigation.html"
    }
 })
 .directive('footer', function(){
    return {
        restrict: 'EA',
        replace: true,
        templateUrl:"/tpls/footer.html"
    }
 })
 .directive('datePicker', function(){
    return {
        restrict: 'EA',
        replace: true,
        templateUrl:"/tpls/datePicker.html"
    }
 })
 .directive('paginations', function(){
    return {
        restrict: 'EA',
        replace: true,
        templateUrl:"/tpls/paginations.html",
        controller:"PaginationDemoCtrl"
    }
 })
 .directive('pageGroup', function(){
    return {
        restrict: 'EA',
        replace: true,
        templateUrl:"/tpls/pageGroup.html"
    }
 })
 ;