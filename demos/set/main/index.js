angular.module('main', []);

angular.module('main').controller('MainCtrl', [function (){
  console.log('main controller');
}]);

angular.module('main').config(function($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.otherwise("");

  // Now set up the states
  $stateProvider
    .state('prospect', {
      url: "",
      templateUrl: "main/_prospect.html",
      controller: function($scope) {
        console.log('prospect');
        $scope.greeting = "Let's set up an appointment!";
      }
    })
    .state('prospect.getStarted', {
      url: "",
      templateUrl: "main/_getStarted.html",
      controller: function($scope) {
        console.log('getStarted');
      }
    })
    .state('reviewed', {
      url: "",
      templateUrl: "main/_reviewed.html",
      controller: function($scope) {
        console.log('reviewed');
        $scope.farewell = "You done, son";
      }
    });
});