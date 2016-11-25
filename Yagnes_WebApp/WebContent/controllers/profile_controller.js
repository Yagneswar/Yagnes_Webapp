app.controller('ProfileController', function($scope,$http,$location) {

$scope.insertBlog=function(){
    
alert($scope.category);

if($scope.title!==null||$scope.ownerid!==null||$scope.discription!==null){
      $http.post('http://localhost:8087/Collaboration/blog/insert/'+$scope.title+'/'+$scope.ownerid+'/'+$scope.discription+'/'+$scope.category).success(function(data, status, headers, config) {

alert('Blog inserted Waiting for For Admin Approval');
    $location.path('/profile');          

    }).error(function(data, status, headers, config,statusText) {
        
        alert('Error '+statusText);
        
    });
    }
    else{
        alert('Feilds Can\'t be blank');
    }
};
});
