app.controller('PasswordCtrl',
	['$scope','$state', 'Rest',
		function ($scope, $state, Rest ) {
			$scope.update = function () {
				layer.confirm("确定要修改此账号密码吗？", function () {
					Message.info("修改中。。。");
					var oldPassword = $scope.param.oldPassword;
					var newPassword = $scope.param.newPassword;
					var date =  {
						oldPassword:hex_md5(oldPassword),
						newPassword:hex_md5(newPassword)
					};
					Rest.post({
						url: "user_manage/update_password",
						params: date,
						success: function (result) {
							Message.info(result.message);
						}
					});
				});
			};
		}
	]
);