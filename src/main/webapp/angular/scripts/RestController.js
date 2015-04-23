app.controller("restController", function ($scope, restService) {

  $scope.list  = function() {
    var promise = restService.list();
    promise.then(function(payload) {
      $scope.list = payload.data;
    });
  };

});