// 'use strict';
 
// app.controller('UserController', ['$scope', 'User', function($scope, User) {
//           var self = this;
//           self.user= new User();
           
//           self.users=[];
               
//           self.fetchAllUsers = function(){
//               self.users = User.query();
//               alert('hello');
//               var o=self.users.length;
//               console.log(o);
//             //   for(var i=0;i<self.users.length();i++)
//             //   alert(self.users[i].username);
//           };
            
//           self.createUser = function(){
//               self.user.$save(function(){
//                   self.fetchAllUsers();
//               });
//           };
           
//           self.updateUser = function(){
//               self.user.$update(function(){
//                   self.fetchAllUsers();
//               });
//           };
 
//          self.deleteUser = function(identity){
//              var user = User.get({id:identity}, function() {
//                   user.$delete(function(){
//                       console.log('Deleting user with id ', identity);
//                       self.fetchAllUsers();
//                   });
//              });
//           };
 
//           self.fetchAllUsers();
 
//           self.submit = function() {
//               if(self.user.id==null){
//                   console.log('Saving New User', self.user);    
//                   self.createUser();
//               }else{
//                   console.log('Upddating user with id ', self.user.id);
//                   self.updateUser();
//                   console.log('User updated with id ', self.user.id);
//               }
//               self.reset();
//           };
               
//           self.edit = function(id){
//               console.log('id to be edited', id);
//               for(var i = 0; i < self.users.length; i++){
//                   if(self.users[i].id === id) {
//                      self.user = angular.copy(self.users[i]);
//                      break;
//                   }
//               }
//           };
               
//           self.remove = function(id){
//               console.log('id to be deleted', id);
//               if(self.user.id === id) {//If it is the one shown on screen, reset screen
//                  self.reset();
//               }
//               self.deleteUser(id);
//           };
 
           
//           self.reset = function(){
//               self.user= new User();
//               $scope.myForm.$setPristine(); //reset Form
//           };
 
//       }]);




app.controller('AdminController', function($scope,$http,$location) {
		// create a message to display in our view
		$scope.message = 'Everyone come and see how good I look!';

//get method to display the register users
fetchAllUsers=function(){
		    $http.get('http://localhost:8087/Yagnes_collaboration/user').
        success(function(data, status, headers, config) {
            $scope.allusers = data;			
		return $scope.allusers;
        }).error(function(data, status, headers, config) {

			alert(headers);
		});
        }
//	});

fetchAllUsers();

//delete fucntion to delete userby email id
        $scope.deleteUser = function(username){
            alert(username);
      var x=confirm("Are you sure you want to delete ?");
      if(x){
        $http.delete('http://localhost:8087/Yagnes_collaboration/user/'+username).success(function (response) {
fetchAllUsers();
            alert('deleted successfuly');
      })
        }    

};//delete function over

//updating the status of blogs
$scope.updateBlog=function(id){
        $http.put('http://localhost:8087/Yagnes_collaboration/blog/update/'+id).success(function (response) {

            alert('updated successfuly');
      })

};

//fetching all blogs 

fetchblogs=function(){
alert('working');
// getting the blogs 
    $http.get('http://localhost:8087/Yagnes_collaboration/blog/allblogs').
        success(function(data, status, headers, config) {
            $scope.blogs = data;
            console.log($scope.blogs);
		return $scope.blogs;
        }).error(function(data, status, headers, config) {

			alert("Unable to get the blogs");
		});
}
fetchblogs();

	});

