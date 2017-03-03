accessid = '';
accesskey = '';
host = '';
policyBase64 = '';
signature = '';
callbackbody = '';
filename = '';
key = '';
expire = 0;
g_object_name = '';
g_object_name_type = '';
now = timestamp = Date.parse(new Date()) / 1000;
var uploaders = [];
var clickEvent = false;
var imgID = '';
function send_request() {
    var xmlhttp = null;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xmlhttp != null) {
        serverUrl = 'http://localhost:27090';
        xmlhttp.open("GET", serverUrl, false);
        xmlhttp.send(null);
        return xmlhttp.responseText
    }
    else {
        alert("Your browser does not support XMLHTTP.");
    }
}

function check_object_radio() {
    g_object_name_type = 'random_name';
}

function get_signature() {
    //可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
    now = timestamp = Date.parse(new Date()) / 1000;
    if (expire < now + 3) {
        body = send_request();
        var obj = eval("(" + body + ")");
        host = obj['host'];
        policyBase64 = obj['policy'];
        accessid = obj['accessid'];
        signature = obj['signature'];
        expire = parseInt(obj['expire']);
        callbackbody = obj['callback'];
        key = obj['dir'];
        return true;
    }
    return false;
};

function random_string(len) {
    len = len || 32;
    var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    var maxPos = chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function get_suffix(filename) {
    pos = filename.lastIndexOf('.');
    suffix = '';
    if (pos != -1) {
        suffix = filename.substring(pos)
    }
    return suffix;
}

function calculate_object_name(filename) {
    if (g_object_name_type == 'local_name') {
        g_object_name += "${filename}"
    }
    else if (g_object_name_type == 'random_name') {
        suffix = get_suffix(filename);
        g_object_name = key + random_string(10) + suffix
    }
    return ''
}

function get_uploaded_object_name(filename) {
    if (g_object_name_type == 'local_name') {
        tmp_name = g_object_name;
        tmp_name = tmp_name.replace("${filename}", filename);
        return tmp_name
    }
    else if (g_object_name_type == 'random_name') {
        return g_object_name
    }
}

function set_upload_param(up, filename, ret) {
    if (ret == false) {
        ret = get_signature()
    }
    g_object_name = key;
    if (filename != '') {
        suffix = get_suffix(filename);
        calculate_object_name(filename)
    }
    new_multipart_params = {
        'key': g_object_name,
        'policy': policyBase64,
        'OSSAccessKeyId': accessid,
        'success_action_status': '200', //让服务端返回200,不然，默认会返回204
        'callback': callbackbody,
        'signature': signature
    };

    up.setOption({
        'url': host,
        'multipart_params': new_multipart_params
    });

    up.start();
}

function setImgUrl(imgID,file) {
    var fileName = get_uploaded_object_name(file.name);
    var index = fileName.indexOf("/");
    $("#"+imgID).attr("src", "http://enjoyorsport.oss-cn-hangzhou.aliyuncs.com/" + fileName);
    var scope = $("#"+imgID).scope();
    if(imgID == "matchPhoto"){
        scope.matchPhoto = fileName.substr(index + 1);
        scope.$apply();
    }
    if(imgID == "healthPhotoOne"){
        scope.healthPhotoOne = fileName.substr(index + 1);
        scope.$apply();
    }
    if(imgID == "healthPhotoSecond"){
        scope.healthPhotoSecond = fileName.substr(index + 1);
        scope.$apply();
    }
    if(imgID == "healthPhotoThree"){
        scope.healthPhotoThree = fileName.substr(index + 1);
        scope.$apply();
    }
    if(imgID == "healthPhotoFour"){
        scope.healthPhotoFour = fileName.substr(index + 1);
        scope.$apply();
    }
    if(imgID == "healthPhotoFive"){
        scope.healthPhotoFive = fileName.substr(index + 1);
        scope.$apply();
    }
}
    var initUploaders = function (btn) {
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: btn,
        flash_swf_url: 'js/lib/plupload-2.1.2/js/Moxie.swf',
        silverlight_xap_url: 'js/lib/plupload-2.1.2/js/Moxie.xap',
        url: 'http://oss.aliyuncs.com',

        filters: {
            mime_types: [ //只允许上传图片
                {title: "Image files", extensions: "jpg,jpeg,png,gif"}
            ],
            max_file_size: '2mb', //最大只能上传2mb的文件
            prevent_duplicates: false //允许选取重复文件
        },

        init: {
            FileFiltered: function (up, files) {
                //提示正在加载
                layer.load(0, {shade: false});
            },
            BeforeUpload: function (up, file) {
                check_object_radio();
                set_upload_param(up, file.name, true);
            },
            FileUploaded: function (up, file, info) {
                layer.closeAll('loading');
                if (info.status == 200) {
                    setImgUrl(imgID,file);
                }
                else {
                    layer.msg(info.response);
                }
            },
            Error: function (up, err) {
                layer.closeAll('loading');
                if (err.code == -600) {
                    layer.msg("选择的文件太大了");
                }
                else if (err.code == -601) {
                    layer.msg("选择的文件类型不对");
                }
                else if (err.code == -602) {
                    layer.msg("这个文件已经上传过一遍了");
                }
                else {
                    layer.msg("服务出错，请联系开发人员");
                }
            }
        }
    });
    uploader.bind('FilesAdded', function (up, files) {
        //上传图片
        set_upload_param(uploader, '', false);
    });
    if(clickEvent == false){
        uploader.init();
        uploaders.push(uploader);
    }
};

var upload = function (btn,ImgID,uploaders) {
    clickEvent = true;
    imgID = ImgID;
    initUploaders(btn);
};


