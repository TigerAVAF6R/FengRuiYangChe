/**
 * Created by LT on 13-12-6.
 */
(function (window, angular) {
    angular.module('service.httpService',[]).service('httpService', ['$http',
        function ($http) {
            var httpOptionsDefault = {
                method: "get",
                url: "#/index.html",
                params: {},
                data: {},
                headers:{},
                cache:false,
                timeout:3000,
                withCredentials: false
            };
           
            this.service = function (httpOptions){
                
                if(!httpOptions) httpOptions = httpOptionsDefault;
                
                return $http(httpOptions);
            }
            this.valided = function(httpOptions,url){

                this.service(httpOptions).success(function(data,status,headers,config){

                    if(data.result){
                        window.location.href="/index.html";
                    }
                    if(!data.result){
                        alert(data.msg)
                    }
                }).error(function(data,status,headers,config){
                    alert("error")
                });
            }
            this.send = function (httpOptions,url){
                
            }
            
        }]);
})(window, window.angular);
 