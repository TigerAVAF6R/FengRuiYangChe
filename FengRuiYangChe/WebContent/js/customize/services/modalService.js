/**
 * Created by LT on 13-12-6.
 */
(function (window, angular) {
    angular.module('service.modalService',[]).service('modalService', ['$modal',
        function ($modal) {
            var modalDefaults = {
                backdrop: true,
                keyboard: true,
                modalFade: true,
                templateUrl: 'tpls/dirtModalInfo.html'
            };

            var modalOptions = {
                closeButtonText: 'Close',
                actionButtonText: 'OK',
                headerText: 'Proceed?',
                bodyText: 'Perform this action?',
                date: '2013-12-6'
            };

            this.showModal = function (customModalDefaults, customModalOptions) {
                if (!customModalDefaults) customModalDefaults = {};
                customModalDefaults.backdrop = 'static';
                return this.show(customModalDefaults, customModalOptions);
            };

            this.show = function (customModalDefaults, customModalOptions) {
                var tempModalDefaults = {};
                var tempModalOptions = {};
                angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);
                angular.extend(tempModalOptions, modalOptions, customModalOptions);
                if (!tempModalDefaults.controller) {
                    tempModalDefaults.controller = ["$scope", "$modalInstance",function ($scope, $modalInstance) {
                        $scope.modalOptions = tempModalOptions;
                        $scope.modalOptions.ok = function (result) {
                            $scope.$emit("dy");
                            $modalInstance.close(result);
                        };
                        $scope.modalOptions.close = function (result) {
                            $modalInstance.dismiss('cancel');
                        };
                    }]
                }
                return $modal.open(tempModalDefaults).result;
            };
        }]);
})(window, window.angular);