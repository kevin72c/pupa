app.controller('AddUserModalCtrl',
	['$scope','$state', 'Rest',
		function ($scope, $state, Rest ) {
			$scope.publish = function () {
				layer.confirm("创建后账号不可修改，确认要创建此账号？", function () {
					Message.info("提交中。。。");
					var password = $scope.param.password;
					var date =  {
						nickName:$scope.param.nickName,
						userName:$scope.param.userName
					};
					date.password = hex_md5(password);
					Rest.put({
						url: "user_manage/",
						params: date,
						success: function (result) {
							Message.info(result.message);
							if(result.status == 200){
								$scope.parentScope.init();
								$scope.modalInstance.close();
							}
						}
					});
				});

			};
		}
	]
);