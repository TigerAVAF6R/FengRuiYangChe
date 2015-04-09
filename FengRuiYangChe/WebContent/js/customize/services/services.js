'use strict';

/* Services */

angular.module('mainApp.services', []).service('paginationService',['$scope',
    function($scope){
        
        var page = 1;
       
        this.pageChanged = function(page,pagesData){
            if(!(page ||pagesData) ) return;
            else{
                if(page+10 <= pagesData.length)
                    $scope.pagesData =pagesData.slice((page-1)*10,page*10);
                else
                    $scope.pagesData =pagesData.slice((page-1)*10,pagesData.length);
            }
        }
    }
])
.value('version', '0.1');

$(window).scroll(function(){
		var objTop = $(document).scrollTop();
		 if(objTop < 60){
			$("#headerWrap2").stop(true);
			$("#headerWrap2").fadeOut(500,function(){
				$("#headerWrap").fadeIn(800);
			});					
		} else {
			$("#headerWrap").stop(true);
			$("#headerWrap").fadeOut(800,function(){
				$("#headerWrap2").fadeIn(800);
			}); 
            
		}
	})
