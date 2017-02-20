app.controller('userManageCtrl',
	['$rootScope', '$scope', '$http', 'Rest',"$modal",
		function ($rootScope, $scope, $http , Rest, $modal) {
			$scope.init = function () {
				Rest.get({
					url:"user_manage/",
					success:function (data) {
						$scope.items = data.infobean;
						angular.forEach($scope.items, function (item) {
							item.saveText="未更改";
							item.gameSelectedArray = item.gameList.split(",") ;
							$scope.selectItem($scope.items[0]);
						});
					}
				});
			};

			$scope.init();

			$scope.getGameList = function () {
				Rest.get({
					url: "game",
					success: function (data) {
						$scope.gameList = data.infobean;
					}
				});
			};

			$scope.getGameList();

			$scope.selectItem = function (item) {
				angular.forEach($scope.items, function (item) {
					item.selected = false;
				});
				$scope.item = item;
				$scope.item.selected = true;
			};

			$scope.deleteItem = function (item) {
				layer.confirm("删除后不可恢复，确定要删除此账号吗？", function () {
					Message.info("删除中。。。");
					Rest.delete({
						url: "user_manage/"+item.userID,
						success: function (result) {
							Message.info(result.message);
							if(result.status == 200){
								$scope.items.splice($scope.items.indexOf(item), 1);
							}
						}
					});
				});
				angular.forEach($scope.items, function (item) {
					item.selected = false;
				});
				$scope.item = $scope.items[0];
				$scope.item.selected = true;
			};

			//新增账号
			$scope.createItem = function () {
				var addScope = $rootScope.$new(false);
				addScope.parentScope = $scope;
				var modalInstance =  $modal.open({
					templateUrl: 'view/user_manage/user_add_modal.html',
					size: 'md',
					scope: addScope
				});
				addScope.modalInstance = modalInstance;
			};
			//
			//检查是否选中
			$scope.isChecked = function(gameID, item){
				if(item != null){
					return item.gameSelectedArray.indexOf(gameID) != -1 ;
				}
			};
			//选中的点击事件
			$scope.updateSelection = function($event, gameID, item){
				var checkbox = $event.target ;
				var checked = checkbox.checked ;
				if(checked){
					item.gameSelectedArray.push(gameID) ;
				}else{
					var idx = item.gameSelectedArray.indexOf(gameID) ;
					item.gameSelectedArray.splice(idx,1) ;
				}
				item.saveText = "提交修改";
			};

			$scope.resetPsw = function (item) {
				var param = {
					userID:item.userID,
					password:hex_md5("123456")
				};
				layer.confirm("确定要将此账号密码重置为123456吗？", function () {
					Rest.post({
						url: "user_manage/",
						params: param,
						success: function (result) {
							if(result.status == 200){
								Message.info("重置成功");
							}
						}
					});
				});

			}

			$scope.save = function (item) {
				var param = {
					userID:item.userID,
					gameList:""
				};
				angular.forEach(item.gameSelectedArray, function (i) {
					if(param.gameList == ""){
						param.gameList = i;
					}else{
						param.gameList += ","+i;
					}
				});
				Message.warn("提交中");
				Rest.post({
					url: "user_manage/",
					params: param,
					success: function (result) {
						Message.info(result.message);
						if(result.status == 200){
							item.saveText = "未更改";
						}
					}
				});
			};
		}
	]
);