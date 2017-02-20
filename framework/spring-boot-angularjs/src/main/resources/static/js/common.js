/**
 * Created by Chenjw on 2016/8/24.
 */

/** 请求状态常量 */
Success = "200"; // 业务处理成功，付款与退款结果表示处理完成
Waiting_For_Payment = "210"; // 等待支付中
Refunding = "220"; // 退款处理中
Failure = "300"; // 业务处理失败
ERROR = "500"; // 系统异常
Session_Expire = "1100"; // 会话超时

/** HttpStatus */
var HttpStatus = {
	OK : 200,
	Forbidden : 403,
	Not_Found : 404,
	Method_Not_Allowed : 405,
	Request_Timeout : 408,
	Unauthorized : 401,
	Not_Acceptable : 406,
	Request_Entity_Too_Large : 413,
	Request_URI_Too_Long : 414,
	Internal_Server_Error : 500,
	Service_Unavailable : 503,
	Gateway_Timeout : 504,
	HTTP_Version_Not_Supported : 505
}


/** 字典缓存 */
isEmpty = function (str) {
	return typeof(str) == "undefined" || str == null || str == "null" || str == '';
}
isNotEmpty = function (str) {
	return !isEmpty(str)
}
/**
 * 日期格式化方法
 * @param fmt
 * @returns {*}
 */
Date.prototype.format = function (fmt) {
	var o = {
		"M+": this.getMonth() + 1,                 //月份
		"d+": this.getDate(),                    //日
		"h+": this.getHours(),                   //小时
		"m+": this.getMinutes(),                 //分
		"s+": this.getSeconds(),                 //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds()             //毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
/**
 * Date日期类转换处理， 统一格式yyyy/MM/dd hh:mm:ss
 * @returns {*}
 */
Date.prototype.toJSON = function () {
	return this.format("yyyy/MM/dd hh:mm:ss");
}
Date.prototype.toISOString = function () {
	return this.format("yyyy/MM/dd hh:mm:ss");
}


/**
 * JSON转url参数
 * @param param
 * @param key
 * @returns {string}
 */
parseQueryString = function (param, key) {
	var paramStr = "";
	if (param instanceof String || param instanceof Number || param instanceof Boolean) {
		paramStr += "&" + key + "=" + encodeURIComponent(param);
	} else {
		$.each(param, function (i) {
			var k = key == null ? i : key + (param instanceof Array ? "[" + i + "]" : "." + i);
			paramStr += '&' + parseQueryString(this, k);
		});
	}
	return paramStr.substr(1);
}

angular.module('app')
// 字典
	.factory("Dict", function () {
		var getDict = function (dictName) {
			var dict = sessionStorage.getItem(dictName);
			if (isNotEmpty(dict)) {
				return JSON.parse(dict);
			}
			// 发送请求
			$.ajax({
				type: "GET",
				url: "dict/" + dictName,
				dataType: "json",
				async: false,
				success: function (result) {
					if (result.status == 200) {
						sessionStorage.setItem(dictName, JSON.stringify(result.infobean));
						return result.infobean;
					}
				}
			});
			return null;
		}
		return {
			getDict : function (dictName) {
				return getDict(dictName);
			}
		}
	})

	// HTTP Rest服务
	.factory("Rest", ['$http', function ($http) {
		/**
		 * 表单参数组织
		 * @param obj
		 * @returns {string}
		 */
		var param = function (obj) {
			var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

			for (name in obj) {
				value = obj[name];

				if (value instanceof Array) {
					for (i = 0; i < value.length; ++i) {
						subValue = value[i];
						fullSubName = name + '[' + i + ']';
						innerObj = {};
						innerObj[fullSubName] = subValue;
						query += param(innerObj) + '&';
					}
				} else if (value instanceof Object) {
					for (subName in value) {
						subValue = value[subName];
						fullSubName = name + '.' + subName;
						innerObj = {};
						innerObj[fullSubName] = subValue;
						query += param(innerObj) + '&';
					}
				} else if (value !== undefined && value !== null) {
					query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
				}
			}
			return query.length ? query.substr(0, query.length - 1) : query;
		};

		/**
		 * 表单内容默认请求头
		 * @type {{headers: {Content-Type: string}}}
		 */
		var defaultConfig = {
			headers:{
				"Content-Type":"application/x-www-form-urlencoded;charset=UTF-8",
			}
		};

		/**
		 * 通用异常处理方法
		 * @param response
		 * @param option
		 */
		var errorHandle = function(response, option) {
			if(option.error){
				option.error(response)
			}else{
				if (response.status == HttpStatus.Unauthorized) {
					window.location.href = "login.html";
				} else if (response.status == HttpStatus.Forbidden) {
					Message.error("您无权访问该接口，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Not_Found) {
					Message.error("服务不存在，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Request_Timeout) {
					Message.error("请求超时，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Method_Not_Allowed) {
					Message.error("请求方式不正确，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Not_Acceptable) {
					Message.error("返回数据格式不可用，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Request_Entity_Too_Large) {
					Message.error("请求体过大，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Request_URI_Too_Long) {
					Message.error("请求连接过长，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Service_Unavailable) {
					Message.error("服务不可达，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Gateway_Timeout) {
					Message.error("网管超时，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.HTTP_Version_Not_Supported) {
					Message.error("http协议版本不支持，请稍后再试或联系研发技术人员！");
				} else if (response.status == HttpStatus.Internal_Server_Error) {
					Message.error("服务异常，请稍后再试或联系研发技术人员！");
				} else {
					Message.error("网络请求失败，请稍后再试或联系研发技术人员！");
				}
			}
		}
		/**
		 * 通用正常处理方法
		 * @param response
		 * @param option
		 */
		var successHandle = function(response,option) {
			if (HttpStatus.OK == response.data.status || isEmpty(response.data.status)) {
				if (option.success) {
					option.success(response.data);
				}
			} else {
				Message.warn(response.data.message);
			}
		}

		/**
		 * Http Get请求方法
		 * @param option 对象参数
		 *    @param: url                必填
		 *    @param: params 请求参数    选填
		 *    @param: success 成功回调   选填
		 *    @param: error 错误回调     选填
		 *    @param: config 请求头配置  选填
		 */
		var get = function (option) {
			var requestParam = {params: option.params}
			$http.get(option.url, requestParam)
				.then(function successCallback(response) {
					successHandle(response, option);
				}, function errorCallback(response) {
					errorHandle(response, option);
				});
		}

		/**
		 * Http Post请求方法
		 * @param option 对象参数
		 *    @param: url                必填
		 *    @param: params 请求参数    选填
		 *    @param: success 成功回调   选填
		 *    @param: error 错误回调     选填
		 *    @param: config 请求头配置  选填
		 */
		var post = function (option) {
			var config = $.extend({}, defaultConfig, option.config);
			$http.post(option.url, param(option.params), config)
				.then(function successCallback(response) {
					successHandle(response, option);
				}, function errorCallback(response) {
					errorHandle(response, option);
				});
		}

		/**
		 * Http Put请求方法
		 * @param option 对象参数
		 *    @param: url                必填
		 *    @param: params 请求参数    选填
		 *    @param: success 成功回调   选填
		 *    @param: error 错误回调     选填
		 *    @param: config 请求头配置  选填
		 */
		var put = function (option) {
			var config = $.extend({}, defaultConfig, option.config);
			$http.put(option.url, param(option.params), config)
				.then(function successCallback(response) {
					successHandle(response, option);
				}, function errorCallback(response) {
					errorHandle(response, option);
				});
		}

		/**
		 * Http Post请求方法
		 * @param option 对象参数
		 *    @param: url                必填
		 *    @param: params 请求参数    选填
		 *    @param: success 成功回调   选填
		 *    @param: error 错误回调     选填
		 *    @param: config 请求头配置  选填
		 */
		var patch = function (option) {
			var config = $.extend({}, defaultConfig, option.config);
			$http.patch(option.url, param(option.params), config)
				.then(function successCallback(response) {
					successHandle(response, option);
				}, function errorCallback(response) {
					errorHandle(response, option);
				});
		}

		/**
		 * Http Delete请求方法
		 * @param option 对象参数
		 *    @param: url                必填
		 *    @param: params 请求参数    选填
		 *    @param: success 成功回调   选填
		 *    @param: error 错误回调     选填
		 *    @param: config 请求头配置  选填
		 */
		var deleteReq = function (option) {
			var config = $.extend({}, defaultConfig, option.config);
			$http.delete(option.url, param(option.params), config)
				.then(function successCallback(response) {
					successHandle(response, option);
				}, function errorCallback(response) {
					errorHandle(response, option);
				});
		}
		return {
			/**
			 * Http Get Post Patch Put Delete请求方法，所有Http请求调用参数结构都一致，只需要区分方法名即可访问后台接口
			 * option 对象参数
			 *    @param: url                必填 接口请求地址
			 *    @param: params 请求参数    选填 接口请求参数
			 *    @param: success 成功回调   选填 回调参数为接口返回数据
			 *    @param: error 错误回调     选填 一般情况网络请求只需处理正常的业务响应，即HttpStatus=200，
			 *                                     Result.status=200的业务处理，
			 *                                     如果需要定制异常或错误的响应方式则声明error function参数
			 *    @param: config 请求头配置  选填 请求头配置，如特地网络请求使用此参数配置，默认为表单请求
			 */
			get : function (option) {
				get(option);
			},
			post : function (option) {
				post(option);
			},
			put : function (option) {
				put(option);
			},
			patch : function (option) {
				patch(option);
			},
			delete : function (option) {
				deleteReq(option);
			}
		}
	}])


	.run(['$rootScope', 'Dict',
		function($rootScope, Dict){
			$rootScope.convert = function (dictName, code) {
				var dict = Dict.getDict(dictName);
				if (isEmpty(dict)) {
					return code;
				}
				if (isEmpty(dict[code])) {
					return code;
				} else {
					return dict[code];
				}
			}

			/**
			 * 日期格式转换为参数format格式
			 * @param dateTime
			 * @param format yyyy-MM-dd hh:mm | yyyy-MM-dd hh:mm:ss
			 * @returns {*|string}
			 */
			$rootScope.dateFormat_ = function (dateTime) {
				if (isEmpty(dateTime)) {
					return "";
				} else {
					return new Date(dateTime * 1000).format("yyyy-MM-dd hh:mm:ss");
				}
			}
			/**
			 * 日期格式转换为yyyy-MM-dd
			 * @param dateTime
			 * @returns {*|string}
			 */
			$rootScope.dateFormat = function (dateTime) {
				if (isEmpty(dateTime)) {
					return "";
				} else {
					return new Date(dateTime * 1000).format("yyyy-MM-dd");
				}
			}
			/**
			 * 日期格式转换为long类型
			 * @param dateTime
			 * @returns {*|Long}
			 */
			$rootScope.dateToLong = function (dateTime) {
				if (isEmpty(dateTime)) {
					return "";
				} else {
					var dateToLong = new Date(dateTime);
					var dateLongFormat = dateToLong.valueOf();
					return dateLongFormat / 1000;
				}
			}

			$rootScope.money = function(number) {
				var places = 2;
				symbol = "￥";
				thousand =",";
				decimal =".";
				var negative = number < 0 ? "-" : "",
					i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
					j = (j = i.length) > 3 ? j % 3 : 0;
				return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
			}

			$rootScope.goToVenueSystem = function (venueID) {
				window.open ("user/access-token/" + venueID,
					'newwindow',
					'width='+(window.screen.availWidth-10) +
					',height='+(window.screen.availHeight-30) +
					',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
			}

			$rootScope.getAge = function (date) {
				var r = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
				if (r == null){
					return "无";
				}
				var d = new Date(r[1], r[3] - 1, r[4]);
				if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]) {
					var Y = new Date().getFullYear();
					return  (Y - r[1]);
				}else{
					return ("输入的日期格式错误！");
				}
			}

			}]);

var Message = {
	info: function (msg) {
		layer.msg(msg);
	},
	warn: function (msg) {
		layer.msg(msg);
	},
	error: function (msg) {
		layer.alert(msg);
	}
}