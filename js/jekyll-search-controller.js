/**
 * Setup Module with `highlight` filter
 */

var JekyllApp = angular.module('JekyllApp', [], function () {
});

JekyllApp.filter('highlight', function () {
    return function (text, filter) {
        if (text) {
            if (filter === '') {
                return text;
            }
            else {
                return text.replace(new RegExp(filter, 'gi'), '<span style="color:red">$&</span>');
            }
        }
    };
});

/**
 * Inject required $objects into our Controller
 */

JekyllSearchController.$inject = ['$scope'];

function JekyllSearchController($scope) {
    $scope.searchText = '';
    $scope.posts = JekyllApp.posts;
    $scope.tech_posts = JekyllApp.tech_posts;
    $scope.getitwrite_posts = JekyllApp.getitwrite_posts;
    $scope.hundred_worders_posts = JekyllApp['100_worders_posts'];
    $scope.demos_posts = JekyllApp.demos_posts;
}



var JekyllDemoApp = angular.module('JekyllDemoApp', [], function () {
});

// JekyllDemoApp.filter('highlight', function () {
//     return function (text, filter) {
//         if (text) {
//             if (filter === '') {
//                 return text;
//             }
//             else {
//                 if (typeof text == 'object') {
//                     if (text[0]) {
                        
//                         console.log(text[0].title);
//                         return text[0].title.replace(new RegExp(filter, 'gi'), '<span style="color:red">$&</span>');
//                     }
//                 } else {
//                     return text;
//                 }
//             }
//         }
//     };
// });

JekyllDemoCtrl.$inject = ['$scope'];

function JekyllDemoCtrl($scope) {
    $scope.sections = [{"name": "Posts", "posts": [{"title" : "Post About Dinosaurs"}, {"title" : "Post About Killer Whales"}, {"title" : "Post About Power Rangers"}]}, {"name": "Ramblings", "posts": [{"title": "Angry Ramblings"},{"title": "Muttering Ramblings"},{"title": "Ingenious Ramblings"}]}];
    console.log($scope.sections);
    $scope.searchText = '';
}