app.controller('BlogController', function($scope,$http) {
		// create a message to display in our view
		$scope.message = 'Everyone come and see how good I look!';

		    $http.get('http://localhost:8087/Collaboration/blog/allblogs').
        success(function(data, status, headers, config) {
            $scope.blogs = data;
			$scope.stat=status;
			var x=0;
			for(x=0;x<$scope.blogs.length;x++)
			alert($scope.blogs[x].blogDiscription);

		return $scope.blogs;
        }).error(function(data, status, headers, config) {

			alert("Unable to get the values");
		});

	});
