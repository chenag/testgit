(function (jQuery) {
    /**
     * 初始化微信验证
     */
    jQuery.initWx = function (callback) {
        var url = '/kyb/weChat/sign?url=' + encodeURIComponent(location.href.split('#')[0]);
        $.post(url, {}, function (json) {
            wx.config({
                debug: false,
                appId: json.appId,
                timestamp: json.timestamp,
                nonceStr: json.nonceStr,
                signature: json.signature,
                jsApiList: [
                    'checkJsApi',
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                    'onMenuShareQQ',
                    'onMenuShareWeibo',
                    'onMenuShareQZone',
                    'hideMenuItems',
                    'showMenuItems',
                    'hideAllNonBaseMenuItem',
                    'showAllNonBaseMenuItem',
                    'translateVoice',
                    'startRecord',
                    'stopRecord',
                    'onVoiceRecordEnd',
                    'playVoice',
                    'onVoicePlayEnd',
                    'pauseVoice',
                    'stopVoice',
                    'uploadVoice',
                    'downloadVoice',
                    'chooseImage',
                    'previewImage',
                    'uploadImage',
                    'downloadImage',
                    'getNetworkType',
                    'openLocation',
                    'getLocation',
                    'hideOptionMenu',
                    'showOptionMenu',
                    'closeWindow',
                    'scanQRCode',
                    'chooseWXPay',
                    'openProductSpecificView',
                    'addCard',
                    'chooseCard',
                    'openCard'
                ]
            });
            wx.ready(function () {
                if (callback) callback();
            });
        }, 'json');
    };

    //--------------------------------------------------------------------------------------

    /**
     * 获取当前日期时间
     * type: 不传则显示时分秒，传date则只显示日期
     */
    jQuery.nowTime = function (type) {
        var d = new Date();
        var result = d.toJSON().split('T')[0];
        if (type != 'date') result += ' ' + d.toTimeString().split(' ')[0];
        return result;
    };

    //--------------------------------------------------------------------------------------

    /**
     * 浏览器验证
     */
    jQuery.browser = {
        mozilla: /firefox/.test(navigator.userAgent.toLowerCase()),
        webkit: /webkit/.test(navigator.userAgent.toLowerCase()),
        opera: /opera/.test(navigator.userAgent.toLowerCase()),
        msie: /msie/.test(navigator.userAgent.toLowerCase())
    };

    //--------------------------------------------------------------------------------------

    /**
     * 显示加载中状态
     * 基于【fontawesome】
     */
    jQuery.fn.showLoading = function (options) {
        return this.each(function () {
            var target = this;
            var text;
            if (options == 'oldWx') {
                text = '<div id="loadingToast" class="weui_loading_toast miniLoading"><div class="weui_mask_transparent"></div><div class="weui_toast"><div class="weui_loading"><div class="weui_loading_leaf weui_loading_leaf_0"></div><div class="weui_loading_leaf weui_loading_leaf_1"></div><div class="weui_loading_leaf weui_loading_leaf_2"></div><div class="weui_loading_leaf weui_loading_leaf_3"></div><div class="weui_loading_leaf weui_loading_leaf_4"></div><div class="weui_loading_leaf weui_loading_leaf_5"></div><div class="weui_loading_leaf weui_loading_leaf_6"></div><div class="weui_loading_leaf weui_loading_leaf_7"></div><div class="weui_loading_leaf weui_loading_leaf_8"></div><div class="weui_loading_leaf weui_loading_leaf_9"></div><div class="weui_loading_leaf weui_loading_leaf_10"></div><div class="weui_loading_leaf weui_loading_leaf_11"></div></div><p class="weui_toast_content">数据加载中</p></div></div>';
                jQuery(target).append(text);
                return;
            }
            if (options == 'wx') {
                text = '<div id="loadingToast" class="miniLoading"><div class="weui-mask_transparent"></div><div class="weui-toast"><i class="weui-loading weui-icon_toast"></i><p class="weui-toast__content">数据加载中</p></div></div>';
                jQuery(target).append(text);
                return;
            }
            var defaults = {
                showMask: true,
                inline: false
            };
            options = jQuery.extend(defaults, options);
            text = '<div class="miniLoading" style="margin-top: 20%; width: 100%; text-align: center; font-size: 28px; font-weight: bold; color: #000;"><i class="fa fa-spinner fa-spin"></i>&nbsp;加载中。。。</div>';
            var loadingMask = '<div class="miniLoading" style="position: fixed; width: 100%; height: 100%; top: 0; left: 0; z-index: 99999; background: rgba(188, 188, 188, 0.3);">' + text + '</div>';
            if (options.showMask) text = loadingMask;
            if (options.inline) text = '<div class="miniLoading" style="margin: 20px auto; width: 100%; text-align: center; font-size: 28px; font-weight: bold; color: #000;"><i class="fa fa-spinner fa-spin"></i></div>';
            jQuery(target).find('div.miniLoading').remove();
            jQuery(target).append(text);
        });
    };

    /**
     * 取消加载中状态
     * 基于【fontawesome】
     */
    jQuery.fn.hideLoading = function () {
        return this.each(function () {
            var target = this;
            jQuery(target).find('div.miniLoading').remove();
        });
    };

    //--------------------------------------------------------------------------------------

    /**
     * form自动填充
     * @param {Object} options {
	 * 	url: '',
	 * 	data: {},
	 * 	callback: function(data) {}
	 * }
     */
    jQuery.fn.fitFormValues = function (options) {
        return this.each(function () {
            var target = this;
            if (typeof options == 'string') {
                getDataByUrl(options, function (data) {
                    options = {
                        formTarget: target,
                        data: data
                    };
                    fitFormByData(options);
                });
            } else if (typeof options == 'object') {
                if (options.url && options.callback) {
                    getDataByUrl(options.url, function (data) {
                        options['data'] = data;
                        options['formTarget'] = target;
                        fitFormByData(options);
                    });
                } else if (options.data && options.callback) {
                    options['formTarget'] = target;
                    fitFormByData(options);
                } else {
                    options = {
                        formTarget: target,
                        data: options
                    };
                    fitFormByData(options);
                }
            }
        });

        function getDataByUrl(url, callback) {
            var data = {};
            callback(data);
        }

        function fitFormByData(options) {
            var formTarget = options.formTarget;
            var data = options.data;
            for (var key in data) {
                var value = data[key];
                if (typeof value == 'object') {
                    value = JSON.stringify(value);
                }
                if (value == 'null') value = '';
                var target = jQuery(formTarget).find('[fieldname="' + key + '"]')[0];
                if (!target) continue;
                var nodeName = target.nodeName;
                if (nodeName == 'INPUT') {
                    var inputType = target.type;
                    if (inputType == 'radio') {
                        jQuery(formTarget).find('[fieldname="' + key + '"][value="' + value + '"]').prop('checked', true);
                    } else if (inputType == 'checkbox') {
                        if (typeof value == 'string' && value.indexOf(',') >= 0) {
                            value = value.split(',');
                        } else if (typeof value != 'object') {
                            var temp = [];
                            temp.push(value + '');
                            value = temp;
                        }
                        jQuery(formTarget).find('[fieldname="' + key + '"][value]').prop('checked', false);//取消所有勾选
                        jQuery(value).each(function (i) {
                            jQuery(formTarget).find('[fieldname="' + key + '"][value="' + value[i] + '"]').prop('checked', true);
                        });
                    } else {
                        jQuery(target).val(value);
                    }
                } else if (nodeName == 'BUTTON') {
                    jQuery(formTarget).find('button[fieldname="' + key + '"][value="' + value + '"]').click();
                } else if (nodeName == 'IMG') {
                    jQuery(target).attr('src', value);
                    if (jQuery(target).next().attr('type') == 'file') {
                        jQuery(target).imgUploader('clearData');
                        jQuery(target).next().val('');
                    }
                } else if (nodeName == 'TD' || nodeName == 'SPAN' || nodeName == 'DIV' || nodeName == 'H1') {
                    jQuery(target).html(value);
                } else {
                    jQuery(target).val(value);
                }
                //easyui特殊处理
                var easyuiType = ['combo', 'combobox', 'combotree', 'combogrid', 'numberbox', 'datebox', 'datetimebox'];
                jQuery(easyuiType).each(function (i) {
                    if (jQuery(target).hasClass('easyui-' + easyuiType[i])) {
                        jQuery(target)[easyuiType[i]]('setValue', value);
                        return false;
                    }
                });
            }
            if (options.callback) {
                options.callback(options.data);
            }
        }
    };

    //--------------------------------------------------------------------------------------

    /**
     * form自动取值
     */
    jQuery.fn.getFormValues = function () {
        var formValues = {};
        var formTarget = this;
        jQuery(formTarget).find('input[fieldname], textarea[fieldname], select[fieldname], img[fieldname]').each(function () {
            var target = this;
            var value = '';
            var key = jQuery(target).attr('fieldname');
            var nodeName = target.nodeName;
            if (nodeName == 'INPUT') {
                var inputType = target.type;
                if (inputType == 'radio') {
                    var selected = jQuery(formTarget).find('[fieldname="' + key + '"]:checked').val();
                    if (selected) {
                        value = selected;
                    }
                } else if (inputType == 'checkbox') {
                    var checkbox = jQuery(formTarget).find('[fieldname="' + key + '"]');//取出同名勾选框数量
                    if (checkbox.length == 1) {//只有一个勾选框的情况下（一般用于启用禁用）
                        value = jQuery(checkbox).val();
                        if (jQuery(checkbox).prop('checked') == false) {//如果并没有选中，设置相反的值
                            if (value == '0') {
                                value = '1';
                            } else if (value == '1') {
                                value = '0';
                            } else if (value == 'true') {
                                value = 'false';
                            } else if (value == 'false') {
                                value = 'true';
                            }
                        }
                    } else {//多个勾选框的情况下
                        checkbox = jQuery(formTarget).find('[fieldname="' + key + '"]:checked');//取出选中的数量
                        value = '.';
                        jQuery(checkbox).each(function () {
                            value += ',' + jQuery(this).val();
                        });
                        value = value.replace('.,', '').replace('.', '');
                    }
                } else {
                    value = jQuery(target).val();
                }
            } else if (nodeName == 'IMG') {
                value = jQuery(target).attr('src');
                if (value != '' && jQuery(target).next().attr('type') == 'file') {
                    value = jQuery(target).imgUploader('getData') || value;
                }
            } else {
                value = jQuery(target).val();
            }
            //easyui特殊处理
            var easyuiType = ['combo', 'combobox', 'combotree', 'combogrid', 'numberbox', 'datebox', 'datetimebox'];
            jQuery(easyuiType).each(function (i) {
                if (jQuery(target).hasClass('easyui-' + easyuiType[i])) {
                    value = jQuery(target)[easyuiType[i]]('getValue');
                    return false;
                }
            });
            if (!value || value == '' || value == 'null') {
                return;
            }//如果没有值，就忽略
            formValues[key] = value;
        });
        return formValues;
    };

    //--------------------------------------------------------------------------------------

    /**
     * 清空form
     */
    jQuery.fn.clearFormValues = function () {
        return this.each(function () {
            var target = this;
            jQuery(target).find('input[type="text"][fieldname], input[type="hidden"][fieldname], input[type="password"][fieldname], input[type="file"][fieldname], textarea[fieldname], input[type="file"]').val('');
            jQuery(target).find('input[type="checkbox"][fieldname], input[type="radio"][fieldname]').prop('checked', false);//取消所有勾选
            jQuery(target).find('select[fieldname]').each(function () {
                this.selectedIndex = 0;
            });
            jQuery(target).find('img[fieldname]').attr('src', '');//取消图片显示
            //easyui特殊处理
            var easyuiType = ['combo', 'combobox', 'combotree', 'combogrid', 'numberbox', 'datebox', 'datetimebox'];
            jQuery(target).find('input[fieldname]').each(function () {
                target = this;
                if (jQuery(target).attr('checked')) jQuery(target).prop('checked', true);
                jQuery(easyuiType).each(function (i) {
                    if (jQuery(target).hasClass('easyui-' + easyuiType[i])) {
                        jQuery(target)[easyuiType[i]]('setValue', '');
                        return false;
                    }
                });
            });
        });
    };

    //--------------------------------------------------------------------------------------

    /**
     * 根据json的value返回text的值
     */
    jQuery.fn.getJsonTextByValue = function (value) {
        var text = "";
        this.each(function () {// 遍历所有的json数据
            if (value == this.value) {// 匹配、赋值
                text = this.text;
            }
        });
        return text;
    };
    /**
     * 根据json的text返回value的值
     */
    jQuery.fn.getJsonValueByText = function (text) {
        var value = "";
        this.each(function () {// 遍历所有的json数据
            if (text == this.text) {// 匹配、赋值
                value = this.value;
            }
        });
        return value;
    };

    //--------------------------------------------------------------------------------------
    /**
     * 遍历json值给下拉列表框赋值
     */
    jQuery.fn.initSelect = function (json) {
        var target = jQuery(this);
        if (toString.apply(json) === '[object Array]') {//如果是数组
            target.each(function () {
                var t = jQuery(this);
                jQuery(json).each(function () {
                    t.append('<option value="' + this.value + '">' + this.text + '</option>');
                });
                t.data('data', json);
            });
        } else if (typeof json == 'string') {//如果是字符串（传入的是url）
            jQuery.post(json, {}, function (data) {
                data.splice(0, 0, {"value": "", "text": "请选择"});
                target.initSelect(data).data('data', data);
            }, 'json');
        } else {
            if (json.url != null) {
                if (json.valueField == null || json.textField == null) {
                    json['valueField'] = 'value';
                    json['textField'] = 'text';
                    target.initSelect(json);
                } else {
                    var defaultTxt = json.defaultTxt || '请选择';
                    jQuery.post(json.url, {}, function (data) {
                        var temp = [{"value": "", "text": defaultTxt}];
                        jQuery(data).each(function (i) {
                            temp.push({"value": data[i][json.valueField], "text": data[i][json.textField]});
                        });
                        target.initSelect(temp).data('data', data);
                        if (json.onLoadSuccess) {
                            json.onLoadSuccess(temp, target);
                        }
                    }, 'json');
                }
            } else if (json.data != null) {
                if (json.valueField == null || json.textField == null) {
                    target.initSelect(json.data);
                } else {
                    var temp = [];
                    if (json.defaultTxt) temp = [{"value": "", "text": json.defaultTxt}];
                    jQuery(json.data).each(function (i) {
                        temp.push({"value": json.data[i][json.valueField], "text": json.data[i][json.textField]});
                    });
                    target.initSelect(temp);
                }
                target.data('data', json.data);
            }
        }
        return target;
    };

    //--------------------------------------------------------------------------------------

    /**
     * 图片上传控件
     */
    var loadExif = false;
    jQuery.fn.imgUploader = function (options) {
        //加载exif用来解析图片
        if (!loadExif) {
            $.getScript("/assets/plugins/exif.js");
            loadExif = true;
        }

        var target = jQuery(this);
        var fileTg = target.next();
        if (target.length > 1) return false;
        if (target.attr('type') == 'file') fileTg = target;
        if (fileTg.attr('type') != 'file') return false;

        if (typeof options == 'string') {
            switch (options) {
                case 'getData':
                    return target.data('base64Data');
                case 'clearData':
                    return clearData(target);
            }
        }

        var defaults = {
            allowTypes: ['image/jpg', 'image/jpeg', 'image/png', 'image/gif'],//允许上传的图片类型
            maxSize: 1024 * 1024 * 20,//20MB
            maxWidth: 1000,//图片最大宽度
            imgTarget: target,
            fileTarget: fileTg,
            onLoadSuccess: function (data) {
                target.attr('src', data).data('base64Data', data).removeClass('loading');
            }
        };
        options = $.extend({}, defaults, options);

        if (target != fileTg) {
            target.on('click', function () {
                fileTg.click();
            });
        }
        fileTg.on('change', function () {
            ImgUploader.selectImgBtnFun(options);
        });

        function clearData(target) {
            target.data('base64Data', null);
            return target;
        }
    };

    //--------------------------------------------------------------------------------------

    /**
     * 图片上传控件
     */
    var ImgUploader = {
        orientation: 1,
        //获取照片方向角属性
        getOrientation: function (file) {
            EXIF.getData(file, function () {
                EXIF.getAllTags(this);
                ImgUploader.orientation = EXIF.getTag(this, 'Orientation');
                console.log('图片方向', ImgUploader.orientation);
            });
        },
        //选择图片按钮change事件
        selectImgBtnFun: function (options) {
            var files = options.fileTarget[0].files;
            //如果没有选中文件，直接返回
            if (files.length === 0) return;
            for (var i = 0, len = files.length; i < len; i++) {
                var file = files[i];
                var reader = new FileReader();
                //如果类型不在允许的类型范围内
                if (options.allowTypes.indexOf(file.type) === -1) {
                    $.alert('该类型不允许上传');
                    continue;
                }
                if (file.size > options.maxSize) {
                    $.alert('图片太大，不允许上传');
                    continue;
                }
                if (options.imgTarget) options.imgTarget.attr('src', '').addClass('loading');
                reader.onload = function (e) {
                    ImgUploader.loadReader(e, options);
                };
                ImgUploader.getOrientation(file);//选好照片获取方向角属性
                reader.readAsDataURL(file);
            }
        },
        //canvas生成图片，显示到img
        loadReader: function (reader, options) {
            var img = new Image();
            img.onload = function () {
                //不要超出最大宽度
                var w = Math.min(options.maxWidth, img.width);
                //高度按比例计算
                var h = img.height * (w / img.width);
                var canvas = document.createElement('canvas');
                var ctx = canvas.getContext('2d');
                //设置 canvas 的宽度和高度
                canvas.width = w;
                canvas.height = h;
                ctx.drawImage(img, 0, 0, w, h);
                var base64 = null;
                var orientation = ImgUploader.orientation || '';
                //图片旋转修复
                if (navigator.userAgent.match(/Android/i)) {//修复android
                    // var encoder = new JPEGEncoder();
                    // base64 = encoder.encode(ctx.getImageData(0, 0, w, h), 80);
                    base64 = canvas.toDataURL('image/png', 0.8);
                } else {//修复ios
                    //如果方向角不为1，都需要进行旋转
                    if (orientation != '' && orientation != 1) {
                        switch (orientation) {
                            case 6:
                                console.log('需要顺时针（向左）90度旋转');
                                ImgUploader.rotateImg(this, 'left', canvas, options.maxWidth);
                                break;
                            case 8:
                                console.log('需要顺时针（向右）90度旋转');
                                ImgUploader.rotateImg(this, 'right', canvas, options.maxWidth);
                                break;
                            case 3:
                                console.log('需要180度旋转');
                                ImgUploader.rotateImg(this, 'right', canvas, options.maxWidth);//转两次
                                ImgUploader.rotateImg(this, 'right', canvas, options.maxWidth);
                                break;
                        }
                    }
                    base64 = canvas.toDataURL('image/png', 0.8);
                }

                //显示预览
                options.onLoadSuccess(base64);
            };
            img.src = reader.target.result;
        },
        //转换图片方向
        rotateImg: function (img, direction, canvas, maxWidth) {
            //最小与最大旋转方向，图片旋转4次后回到原方向
            var min_step = 0;
            var max_step = 3;
            if (img == null)return;
            //不要超出最大宽度
            var width = Math.min(maxWidth, img.width);
            //高度按比例计算
            var height = img.height * (width / img.width);

            var step = 2;
            if (step == null) {
                step = min_step;
            }
            if (direction == 'right') {
                step++;
                //旋转到原位置，即超过最大值
                step > max_step && (step = min_step);
            } else {
                step--;
                step < min_step && (step = max_step);
            }
            //旋转角度以弧度值为参数
            var degree = step * 90 * Math.PI / 180;
            var ctx = canvas.getContext('2d');
            switch (step) {
                case 0:
                    ctx.drawImage(img, 0, 0);
                    break;
                case 1:
                    //设置 canvas 的宽度和高度
                    canvas.width = height;
                    canvas.height = width;
                    ctx.rotate(degree);
                    ctx.drawImage(img, 0, -height, width, height);
                    break;
                case 2:
                    canvas.width = width;
                    canvas.height = height;
                    ctx.rotate(degree);
                    ctx.drawImage(img, -width, -height, width, height);
                    break;
                case 3:
                    canvas.width = height;
                    canvas.height = width;
                    ctx.rotate(degree);
                    ctx.drawImage(img, -width, 0, height, width);
                    break;
            }
        }
    };
})(jQuery);

//--------------------------------------------------------------------------------------
/**
 * Url信息操作
 */
var Url = function () {//url参数
    var data,
        index;
    (function init() {
        data = [];
        index = {};
        var u = window.location.search.substr(1);
        if (u != '') {
            var params = decodeURIComponent(u).split('&');
            for (var i = 0, len = params.length; i < len; i++) {
                if (params[i] != '') {
                    var p = params[i].split("=");
                    if (p.length == 1 || (p.length == 2 && p[1] == '')) {// p | p=
                        data.push(['']);
                        index[p[0]] = data.length - 1;
                    } else if (typeof(p[0]) == 'undefined' || p[0] == '') {// =c | =
                        data[0] = [p[1]];
                    } else if (typeof(index[p[0]]) == 'undefined') {// c=aaa
                        data.push([p[1]]);
                        index[p[0]] = data.length - 1;
                    } else {// c=aaa
                        data[index[p[0]]].push(p[1]);
                    }
                }
            }
        }
    })();
    return {
        //获得参数,类似request.getParameter()
        getParam: function (o) {//o: 参数名或者参数次序
            try {
                return (typeof(o) == 'number' ? data[o][0] : data[index[o]][0]);
            } catch (e) {
            }
        },
        //获得参数组, 类似request.getParameterValues()
        getParams: function (o) {//o: 参数名或者参数次序
            try {
                return (typeof(o) == 'number' ? data[o] : data[index[o]]);
            } catch (e) {
            }
        },
        getAllParams: function () {
            var result = {};
            for (var key in index) {
                var i = index[key];
                result[key] = data[i][0];
            }
            return result;
        },
        //是否含有paramName参数
        hasParam: function (paramName) {
            return typeof(paramName) == 'string' ? typeof(index[paramName]) != 'undefined' : false;
        },
        //获得参数Map ,类似request.getParameterMap()
        getParamMap: function () {
            var map = {};
            try {
                for (var p in index) {
                    map[p] = data[index[p]];
                }
            } catch (e) {
            }
            return map;
        }
    }
}();

//--------------------------------------------------------------------------------------
/**
 * Cookie工具类
 */
var Cookie = {
    get: function (key) {
        if (document.cookie.length > 0) {
            var start = document.cookie.indexOf(key + '=');
            if (start != -1) {
                start = start + key.length + 1;
                var end = document.cookie.indexOf(';', start);
                if (end == -1) end = document.cookie.length;
                return unescape(document.cookie.substring(start, end));
            }
        }
        return '';
    },
    set: function (key, value) {
        var cookie = key + '=' + escape(value) + ';';
        cookie += 'path=/;';
        document.cookie = cookie;
    }
};

//--------------------------------------------------------------------------------------
/**
 * 判断是否是移动端
 */
function isMobile() {
    var sUserAgent = navigator.userAgent.toLowerCase();
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsAndroid = sUserAgent.match(/android/i) == "android";
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    return (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM);
}
