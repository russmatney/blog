angular.module('game', []);

angular.module('game').factory('checkWin', [function (){
  sameOrDifferent = function(set, property){
    if (set[0][property] == set[1][property] && set[1][property] == set[2][property]){
      return true;
    } else if (set[0][property] == set[1][property] || set[1][property] == set[2][property] || set[0][property] == set[2][property]) {
      return false;
    } else {
      return true;
    }
  };

  return function(set){
    color = sameOrDifferent(set, 'color');
    shade = sameOrDifferent(set, 'shade');
    shape = sameOrDifferent(set, 'shape');
    if (color && shade && shape){
      console.log('win!');
      return true;
    } else if (color || shade || shape) {
      console.log('almost');
      return false;
    } else {
      console.log('no sir');
      return false;
    }
  };
}]);

angular.module('game').factory('cardFactory', [function(){
  return {
    generateCards: function(numberOfCards){
      var cards = [];
      var shape = 0,
          color = 0,
          shade = 0;
      for (var i = 0; i < numberOfCards; i++) {
        shape = Math.floor(Math.random()*3);
        color = Math.floor(Math.random()*3);
        shade = Math.floor(Math.random()*3);
        cards.push({shape: shape, color: color, shade: shade});
      }
      return cards;
    }
  }
}])

angular.module('game').controller('GameCtrl', ['$scope', 'checkWin', 'cardFactory', function ($scope, checkWin, cardFactory){
  console.log('game controller');
  $scope.score = 0;

  $scope.time = 0;

  var update = function(){
    setTimeout(function(){
      $scope.time = $scope.time + 1;
      update();
      $scope.$digest();
    }, 1000);
  };

  update();

  $scope.cards = cardFactory.generateCards(9);
  $scope.set = [];

  $scope.selectCard = function(card){
    card.selected = !card.selected;
    if(card.selected){
      $scope.set.push(card);
    } else {
      var index = $scope.set.indexOf(card);
      $scope.set.splice(index, 1);
    }

    if($scope.set.length > 2){
      if(checkWin($scope.set))
        $scope.handleWin();
    }
  }

  $scope.handleWin = function(){
    console.log($scope.set);
    // remove winning cards if it is a set
    for (var i = 0; i < 3; i++) {
      var index = $scope.cards.indexOf($scope.set[i]);
      console.log(index);
      if (index != -1)
        var removed = $scope.cards.splice(index, 1);
        console.log(removed);
    }
    // empty hand
    $scope.set = [];
    
    // add new cards
    var newCards = cardFactory.generateCards(3);
    for (var i = 0; i < 3; i++) {
      console.log(i);
      $scope.cards.push(newCards[i]);
    }

    // increment score
    $scope.score++;
  }

}]);

angular.module('game').controller('CardCtrl', ['$scope', function ($scope){
  $scope.cardClasses = function(card){
    classes = '';

    if(card.color == 0) {
      classes += 'red ';
    }else if(card.color == 1) {
      classes += 'blue ';
    }else if(card.color == 2) {
      classes += 'green ';
    }

    if(card.shade == 0) {
      classes += 'solid ';
    } else if (card.shade == 1) {
      classes += 'stripped ';
    } else if(card.shade == 2) {
      classes += 'outline ';
    }

    if(card.shape == 0) {
      classes += 'circle ';
    }else if(card.shape == 1) {
      classes += 'diamond ';
    }else if(card.shape == 2) {
      classes += 'square ';
    }

    if(card.selected){
      classes += ' active '
    }
    
    return classes;
  }
}]);
