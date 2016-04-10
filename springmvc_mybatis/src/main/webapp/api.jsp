<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<html>
<head>
<%
    String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>API Test</title>
<script src="<%=path%>/js/md5.js"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

</head>
<body>
  <p>
    <label for="location">location</label> <input type="text"
      style="width: 300px;" id="location" name="location"
      value="http://localhost:8080/app/dict/map" />
  </p>
  <p>
    <label for="sessionToken">sessionToken</label> <input type="text" id="sessionToken"
      value="" />
  </p>
  <p>
    <label for="dataMode">dataMode</label> <input type="text" id="dataMode"
      value="0" />
  </p>
  <p>
    <label for="userID">userID</label> <input type="text" id="userID"
      value="" />
  </p>
<!--   <p> -->
<!--     <label for="tel">tel</label> <input type="text" id="tel" -->
<!--       value="15111111111" /> -->
<!--   </p> -->
  <p>
    <label for="params">params</label>
    <textarea id="params" name="params" rows="10" cols="40">{dict:["common:sportType"]}</textarea>
  </p>
  <input type="button" id="btnSubmit" value="submit"
    onclick="submitinfo()" />
  <p id="result"></p>
</body>
</html>
<script type="text/javascript">
	$(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				submitinfo();
			}
		}
	});

	function Base64() {

		// private property  
		_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

		// public method for encoding  
		this.encode = function(input) {
			var output = "";
			var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
			var i = 0;
			input = _utf8_encode(input);
			while (i < input.length) {
				chr1 = input.charCodeAt(i++);
				chr2 = input.charCodeAt(i++);
				chr3 = input.charCodeAt(i++);
				enc1 = chr1 >> 2;
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = chr3 & 63;
				if (isNaN(chr2)) {
					enc3 = enc4 = 64;
				} else if (isNaN(chr3)) {
					enc4 = 64;
				}
				output = output + _keyStr.charAt(enc1) + _keyStr.charAt(enc2)
						+ _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
			}
			return output;
		}

		// public method for decoding  
		this.decode = function(input) {
			var output = "";
			var chr1, chr2, chr3;
			var enc1, enc2, enc3, enc4;
			var i = 0;
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
			while (i < input.length) {
				enc1 = _keyStr.indexOf(input.charAt(i++));
				enc2 = _keyStr.indexOf(input.charAt(i++));
				enc3 = _keyStr.indexOf(input.charAt(i++));
				enc4 = _keyStr.indexOf(input.charAt(i++));
				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;
				output = output + String.fromCharCode(chr1);
				if (enc3 != 64) {
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64) {
					output = output + String.fromCharCode(chr3);
				}
			}
			output = _utf8_decode(output);
			return output;
		}

		// private method for UTF-8 encoding  
		_utf8_encode = function(string) {
			string = string.replace(/\r\n/g, "\n");
			var utftext = "";
			for (var n = 0; n < string.length; n++) {
				var c = string.charCodeAt(n);
				if (c < 128) {
					utftext += String.fromCharCode(c);
				} else if ((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				} else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}

			}
			return utftext;
		}

		// private method for UTF-8 decoding  
		_utf8_decode = function(utftext) {
			var string = "";
			var i = 0;
			var c = c1 = c2 = 0;
			while (i < utftext.length) {
				c = utftext.charCodeAt(i);
				if (c < 128) {
					string += String.fromCharCode(c);
					i++;
				} else if ((c > 191) && (c < 224)) {
					c2 = utftext.charCodeAt(i + 1);
					string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
					i += 2;
				} else {
					c2 = utftext.charCodeAt(i + 1);
					c3 = utftext.charCodeAt(i + 2);
					string += String.fromCharCode(((c & 15) << 12)
							| ((c2 & 63) << 6) | (c3 & 63));
					i += 3;
				}
			}
			return string;
		}
	}

	function submitinfo() {
		var base64 = new Base64();

		var requestParam = {
			dataMode : $("#dataMode").val()
		};

		var json = {
// 			tel : $("#tel").val(),
			sessionToken : $("#sessionToken").val(),
			userID : $("#userID").val()
		};

		//    json.version = $("#version").val();
		//    json.type = $("#type").val();
		//    json.devmode = $("#devmode").val();
		//    json.model = $("#model").val();
		//    json.logintype = $("#logintype").val();
		//    json.tel = $("#tel").val();
		//    json.token = $("#token").val();
		//    var param = $("#param").val();

		var params = $("#params").val();
		eval("params=" + params);
		$.extend(json, params);

		var data = JSON.stringify(json);
		console.debug(data);

		var base64Param = base64.encode(data);
		requestParam.params = base64Param;
		requestParam.encryptToken = hex_md5(base64Param);

		var strParam = base64.encode(JSON.stringify(requestParam));

		$.ajax({
			type : "POST",
			url : $("#location").val(),
// 			data : requestParam,
			data : {
				"data" : strParam,
				"dataMode" : $("#dataMode").val()
			},
			success : function(msg) {
				$("#result").html(JSON.stringify(msg));
			},
			beforeSend : function() {
				$("#result").html("loading...");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#result").html(textStatus);
			}
		});

	}
</script>