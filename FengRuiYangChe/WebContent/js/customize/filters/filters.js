'use strict';

/* Filters */

 angular.module('mainApp.filters', []).
  filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    }
  }])
  .filter('frontChar',function(){
    return function(strs){
        if(strs.length >30)
            return strs.substr(0,30)+" ...";
        else
            return strs;
    }
});
