app.controller('LoginController', function($scope,$http,$location) {

$scope.loginUser=function(){
    
alert($scope.username+''+$scope.password);
      $http.post('http://localhost:8087/Collaboration/login/'+$scope.username+'/'+$scope.password).success(function(data, status, headers, config,statusText) {

          if(status===200 ){
              var user=data;
              if(user.role==='ROLE_ADMIN')
          $location.path('/admin');
            else
            $location.path('/');
            }
    }).error(function(data, status, headers, config,statusText) {

        if(statusText==='NOT_FOUND')
        alert('User Not Found Try Again');
        
    });

};
});
