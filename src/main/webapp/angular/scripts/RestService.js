app.factory('restService', function($http) {
  return {

    list: function() {
      return $http({
        url: host + '/employee/list',
        dataType: 'json',
        method: 'GET',
        headers: {"Content-Type": "application/json"}
      })
    }
  }
});