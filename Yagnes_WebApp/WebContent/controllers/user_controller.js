app.controller('UserController', function($scope,$http,$location) {

$scope.createUser=function(){
    
 $scope.role="ROLE_USER";
 $scope.enable=1;


if($scope.name!==null||$scope.password!==null||$scope.username!==null){
      $http.post('http://localhost:8989/arun_online_collaboration/user/'+$scope.username+'/'+$scope.name+'/'+$scope.password).success(function(data, status, headers, config) {

alert('Welcome to MyBlog.com');
    $location.path('/login');          

    }).error(function(data, status, headers, config) {
        if(status===409)
        alert('User already available');
        
    });
    }
    else{
        alert('Feilds Can\'t be blank');
    }
};
});
