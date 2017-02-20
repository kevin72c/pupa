
var dojoy={
	msgwin:function (){//弹出窗口
		var self=this;
		this.hiddenTime=10;
		this.timeout=0;
		this.timestatus=true;
		this.title="订单消息";
		var timer=null;
		/**参数
		 * params={
		 * 	hiddenTime:10,//自动隐藏时间
		 *  receive:function(){//接收按钮事件
		 *  
		 *  },
		 *  cancel:function(){//取消按钮事件
		 *  
		 *  },
		 *  title:function(){//默认标题
		 *  
		 *  }
		 * }
		 * 方法
		 * setTitle("自定义标题");
		 * 
		 * open();//打开窗口
		 * 
		 * close();//关闭窗口
		 * 
		 * auto();//自动判断，如果打开状态则关闭，如果关闭状态则打开
		 * 
		 * setContent(message)//设置消息内容
		 * 
		 */	
		this.createWin=function(params){//创建WINDOW
			if(params!=null){
				if(params.hiddenTime!=null){//初始化隐藏时间默认10秒
					this.hiddenTime=params.hiddenTime;
				}
				if(params.receive!=null){//绑定接收事件
					this.receive=params.receive;
				}
				if(params.cancel!=null){//绑定取消事件
					this.cancel=params.cancel;
				}
				if(params.title!=null){
					this.title=params.title;
				}
			};
			
			var winhtml='<div id="chat-container" class="">'+
			'	<span class="chat-list-open-close">'+
			'		<i class="glyphicon glyphicon-circle-arrow-left"></i>'+
			'	</span>'+
			'	<div class="chat-list-body custom-scroll">'+
			'		<ul id="chat-users">'+
			'			<div class="message-title">'+(self.title)+'</div>'+
			'			<div class="message-content"></div>'+
			'		</ul>'+
			'		<div class="message-controls">'+
			'			<button class="btn btn-sm btn-primary pull-right message-receive">查看</button>'+
			'			<button class="btn btn-sm btn-default pull-right message-ignore" style="margin-right:5px;">忽略</button> '+
			'		</div>'+
			'	</div>'+
			'</div>';
			$("body").append(winhtml);
			
			$(".chat-list-open-close,.message-ignore").on("click",function(){//绑定事件
				self.auto();
				self.cancel();
			});
			$("#chat-container").on("mouseover",function(){
				self.timeout=5;
				self.timestatus=false;
			});
			$("#chat-container").on("mouseout",function(){
				self.timestatus=true
				self.timeout=5;
			});
			$("#chat-container .message-receive").on("click",self.receive);
		};
		this.cancel=function(){//默认取消方法
			
		};
		this.receive=function(){

		};
		this.autoHidden=function(){
			 if(self.timeout<=0){
				 self.timeout=self.hiddenTime;
				 self.open();
				 self.time();
			 }else{
				 self.timeout=self.hiddenTime;
			 }
		};
		this.time=function(){
		  if(self.timeout<=0){
			  self.close();
			  clearTimeout(self.timer);
			  return;  
		  }
		  if(self.timestatus){
			  self.timeout--;
		  }
		  self.timer=setTimeout(self.time,500); //time是指本身,延时递归调用自己,500为间隔调用时间,单位毫秒
		};
		this.open=function(){
			$("#chat-container").addClass("open");
			$(".chat-list-open-close").children(".glyphicon").addClass("glyphicon-circle-arrow-right");
			$(".chat-list-open-close").children(".glyphicon").removeClass("glyphicon-circle-arrow-left");
		};
		this.close=function(){
			$("#chat-container").removeClass("open");
			$(".chat-list-open-close").children(".glyphicon").removeClass("glyphicon-circle-arrow-right");
			$(".chat-list-open-close").children(".glyphicon").addClass("glyphicon-circle-arrow-left");
		};
		this.auto=function(){
			if($("#chat-container").is(".open")){
				self.close();
			}else{
				self.open();
			}
		};
		this.setTitle=function(title){
			$("#chat-container .message-title").html(title);
		};
		this.setContent=function(message){
			$("#chat-container .message-content").html(message);
		}
	},
	/**
	 * WEBSOCKET 连接服务器工具 
	 * params={
	 * 	url:"ws://xxx.xx.xx" ,//请求URL
	 *	onOpen:function(){//连接完成触发
	 *	
	 *	},
	 *	onClose:function(){//连接关闭触发
	 *
	 *	},
	 *	onError:function(){//出现异常触发
	 *
	 *	},
	 *	onMessage:function(message){//接收到消息触发
	 *
	 *	}
	 * }
	 *
	**/
	websocket:function(){
		var initParams=null;
		var self=this;
		var time=null;
		this.socket={},
		this.initialize=function(params) {
			if(params==null){
				alert("缺少重要参数，详情见注释");
			}else{
				self.connect(params);
			}
        };
        this.connect=function(params) {
        	initParams=params;
        	var url="";
        	if (window.location.protocol == 'http:') {
            	url="ws://"+params.url;
            } else {
            	url='wss://' + params.url;
            }
			if ('WebSocket' in window) {
				self.socket = new WebSocket(url);
            } else if ('MozWebSocket' in window) {
            	self.socket = new MozWebSocket(url);
            } else {
                console.log('Error: WebSocket is not supported by this browser.');
                return;
            }
			if(params.onOpen!=null){
				self.onopen=params.onOpen;
			}
			if(params.onClose!=null){
				self.onclose=params.onClose;
			}
			if(params.onError!=null){
				self.onerror=params.onError;
			}
			if(params.onMessage!=null){
				self.socket.onmessage=function(message){
					var obj=$.parseJSON(message.data);
					if(obj.messageType==-1){//这个是心跳包
						self.socket.send("{'type':-1}");
					}else{//这个不是心跳包
						params.onMessage(message);
					}
				}
			}
			self.socket.onopen=this.onopen;
			self.socket.onclose=this.onclose;
			self.socket.onerror=this.onerror;
			//self.socket.onmessage=this.onmessage;
        };
        this.onopen=function(){//连接回调
        	console.log("链接成功");
        	clearTimeout(time);
	 		self.socket.send("{'type':0}");//查询未读处理订单
        };
        this.onclose=function(){//关闭回调
        	self.heartbeat();
        };
        this.onerror=function(){//异常回调
        	
        };
        this.sendMessage=function(message){
        	self.socket.send(message);
        };
        this.heartbeat=function(){
        	//重新连接
        	console.log("重新连接...");
        	self.connect(initParams);
        	time=setTimeout(time,10000); //time是指本身,延时递归调用自己,500为间隔调用时间,单位毫秒
        };
        
	}
}
