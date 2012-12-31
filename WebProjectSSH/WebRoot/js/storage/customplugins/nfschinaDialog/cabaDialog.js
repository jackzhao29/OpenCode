/**
 * @author Administrator
 */
(function($){
    /*默认参数*/
    $.fn.nfschinaDialog = function(options){
    	//alert("Dialog.js nfschinaDialog[]-->> options =="+ options);
		var _thisDomId=$(this).attr("id");/*获取对象ID*/
		
		if(options=='close')
		{
			destroyDialog($("#nfschinaDialog_"+_thisDomId),$(this),_thisDomId);
			return;
		}
		/*如果已出现了同样的弹出框，则不继续进行。*/
		if($("#nfschinaDialog_"+_thisDomId).size()>0)
		{return;}
		/*默认值*/
		$.fn.nfschinaDialog.defaultOptions = {
	        position: "absolute",/*如果浏览器支持position:fixed属性*/
	        top: "",/*带单位*/
	        left: "",/*带单位*/
	        width: 350,/*如果带单位必须是字符串的形式，否则只能写数字*/
	        height: "",/*如果带单位必须是字符串的形式，否则只能写数字*/
	        title: "默认对话框",
			delay:-1,
			drag:true,/*能否拖动*/
			bgframe:true,
			buttonAlign:"",
	        buttons: {}
    	}
		
		//$('#id', window.parent.document)
		// var c = $(window.parent.document.body) //麻烦的方法： var c = $($(window).eq(0)[0].parent.document).find('body'); ，忘了可以用前面的方法了
            
       
		
		var _jbody=$("body");
		//var _jbody=$(window.parent.document.body);//$("body");//zyl
		
		var _jthis=$(this).clone(true);/*备份，对话框取消时还原对象*/
		$(this).remove();/*移除页面上填充内容的元素*/
        var _finalOptions = $.extend({}, $.fn.nfschinaDialog.defaultOptions, options);
		var _isIE6=true;
		if(window.XMLHttpRequest)
			_isIE6=false;
		if(_finalOptions.position=="fixed" && _isIE6)
			_finalOptions.position="absolute";
		
		/*往body中添加弹出框*/
		_jbody.append(initNfschinaDialog(_thisDomId,_finalOptions));
		var _nfschinaDialog=$("#nfschinaDialog_"+_thisDomId);
		var _nfschinaDialogBody=$("#nfschinaDialogBody_"+_thisDomId);
		var _nfschinaDialogBodyIframe=$("#nfschinaDialogBodyIframe"+_thisDomId);
		
		/*在弹出框中添加操作元素*/
		_nfschinaDialogBody.append(_jthis);
		
		/*
		 * 下面的操作是对弹出框根据参数进行设置
		 */
		var documentWH={w:$(document).width(),h:$(document).height()};
		var windowWH={w:$(window).width(),h:$(window).height()};
		
	
		
		/*设置弹出框的宽度*/
		_nfschinaDialog.width(_finalOptions.width);
		_nfschinaDialog.find(".nfschinaDialogTop").width(_finalOptions.width-6);
		_nfschinaDialog.find(".nfschinaDialogBottom").width(_finalOptions.width-6);
		
		if(_finalOptions.height)
		{
			_nfschinaDialog.height(_finalOptions.height);
		}
		
		/*先将清空按钮所在的div*/
		$('.nfschinaDialogButton',_nfschinaDialog).empty().css("text-align",_finalOptions.buttonAlign);
		
		/*为弹出框添加按钮*/
		hasButtons=false;
		/*判断是否要添加按钮*/
		(typeof(_finalOptions.buttons)=="object"&&_finalOptions.buttons!=null&&$.each(_finalOptions.buttons,function(){return !(hasButtons=true);}));
		
		if(hasButtons)
		{
			$.each(_finalOptions.buttons,function(name,callBack){
				$('<button>'+name+'</button>')
				.click(function(){callBack.apply(_jthis[0]);
				}).appendTo($("#nfschinaDialogButton_"+_thisDomId));
			});
		}
		else{
			$("#nfschinaDialogButton_"+_thisDomId).remove();
		}
		
		/*判断是否显示遮盖*/
		if(_finalOptions.bgframe)
		{
			var _bgWidth=0;
			var _bgHeight=0;
			if(document.body.clientHeight>windowWH.h){_bgHeight=document.body.clientHeight;}else{_bgHeight=windowWH.h;}
			if(document.body.clientWidth>windowWH.w){_bgWidth=document.body.clientWidth;}else{_bgWidth=windowWH.w;}
			if(!_isIE6)
			{
				$('<div id="nfschinaDialogBg_'+_thisDomId+'" class="nfschinaDialogBg" style="width:'+_bgWidth+'px;height:'+_bgHeight+'px;"></div>').css("opacity", 0.1).appendTo(_jbody);
			}
			else
			{
				$('<div id="nfschinaDialogBg_'+_thisDomId+'" class="nfschinaDialogBg" style="width:'+_bgWidth+'px;height:'+_bgHeight+'px;"><iframe class="nfschinaDialogBgIframe" style="width:'+_bgWidth+'px;height:'+_bgHeight+'px;">'+
				'</iframe></div>').css("opacity", 0.1).appendTo(_jbody).find("iframe").css("opacity", 0.1);
			}
		}
		
		//alert("cabaDialog.js  nfschinaDialog()--->> 22  _jthis.html() == "+_jthis.html());
		
		_jthis.show();
		_nfschinaDialog.show();
		_nfschinaDialogBody.width($('#nfschinaDialogHead_'+_thisDomId).width());
		
		//alert("cabaDialog.js  nfschinaDialog()--->> 33  _nfschinaDialog.html() == "+_nfschinaDialog.html());
		
		/*为了准确的添加iframe，这里要设置width和height*/
		_nfschinaDialogBodyIframe.css("width",_nfschinaDialog.width()).css("height",_nfschinaDialog.height());
		
		/*注意，这个地方把弹出框位置的设定放在弹出框显示之后
		 * 因为如果隐藏时获取高和宽，可能不准确
		 */
		var _documentScroll={l:document.documentElement.scrollLeft,t:document.documentElement.scrollTop};
		//设置距离document顶端的高度
		if(_finalOptions.top)
		{
			_nfschinaDialog.css("top",top);		
		}
		else{
			var top=(windowWH.h)/2-(_nfschinaDialog.height())/2;
			top=top*2/3;
			top=top>5?top:5;
			top=top+_documentScroll.t
			_nfschinaDialog.css("top",top);
		}
		/*设置距离document最左边的位移*/
		if(_finalOptions.left)
		{
			var left=(windowWH.w)/2-(_nfschinaDialog.width())/2+_finalOptions.left;
			left=left>0?left:0;
			_nfschinaDialog.css("left",left);
		}
		else{
			var left=(windowWH.w)/2-(_nfschinaDialog.width())/2+_documentScroll.l;
			_nfschinaDialog.css("left",left);
		}
		
		/*调节内部显示，当对话框的nfschinaDialogBody部分太长或者太宽是，应该出现滚动条*/
		if(_nfschinaDialog.height()+5>windowWH.h)
		{
			_nfschinaDialogBody.height(windowWH.h-10-(_nfschinaDialog.height()-_nfschinaDialogBody.height()));
			_nfschinaDialogBodyIframe.height(windowWH.h-(10));
			_nfschinaDialog.height(windowWH.h-(10));
		}
		/*弹出框自动延迟消失*/
		if(_finalOptions.delay>0)
		{
			setTimeout(function(){
				_nfschinaDialog.hide();
				destroyDialog(_nfschinaDialog,_jthis,_thisDomId);
			}, _finalOptions.delay);
		}
		
		/*为右上角关闭添加onclick事件*/
		$('#nfschinaDialogClose_'+_thisDomId).click(function(){
			destroyDialog(_nfschinaDialog,_jthis,_thisDomId);
		});
		
		/*拖动*/
		if(_finalOptions.drag)
		{
			_nfschinaDialog.draggable({handle:'#nfschinaDialogHead_'+_thisDomId});
		}
		
		
		//zyl:caba 20111213===================================================\
		/*
		var windowParentWH;
		var windowParent = $(window.parent);
		//var _jbody=$(window.parent.document.body);//$("body");
		
		if($(window.parent)){
			windowParentWH = {w:$(window.parent).width(),h:$(window.parent).height()};//zyl:caba 20111213
			alert("cabaDialog.js  nfschinaDialog()--->> 99 11 "
					
				
				);
		}
		
		alert("cabaDialog.js  nfschinaDialog()--->> 99 22 "
				+ "    windowWH.w == " + windowWH.w
				+ "    windowWH.h == " + windowWH.h
				+ "    windowParent == " + windowParent
				+ "    windowParent.width() == " + windowParent.width()
				+ "    windowParent.height() == " + windowParent.height()
				+ "    windowParentWH.w == " + windowParentWH.w
				+ "    windowParentWH.h == " + windowParentWH.h
				+ "     width  = " +$(window.parent.document).width()
				+ "     height  = " +$(window.parent.document).height()				
				+ "    document.height == " + $(window.parent.document).height()
				+ "    width == " + $(window.parent.document.body).width()
				+ "    height == " + $(window.parent.document.body).height()
				+ "    outerHeight == " + $(window.parent.document.body).outerHeight(true)
				
			);
		 //alert("cabaDialog.js  nfschinaDialog()--->>   _jbody.html() == "+_jbody.html());
		
		
		alert("cabaDialog.js  nfschinaDialog()--->> 88 "				
				+"     _nfschinaDialog.html() == "+_nfschinaDialog.html());
		
		
		//<div id="divProvince" style="display:none; position:absolute;width:260px;background-color:#BFEBEE; border:1px solid #BEC0BF;padding:5px;font-size:12px;">
		_nfschinaDialog.css("top","100px");
		_nfschinaDialog.css("position","absolute");
		// z-index: 12001;
		_nfschinaDialog.css("z-index","65535");
		//<div id="mask" style="top:0;left:0;z-index:65535; display:none; POSITION: absolute;"></div>
		//_nfschinaDialog.css("display","none");
		
		alert("cabaDialog.js  nfschinaDialog()--->> 99 "
				+ "    windowWH.w == " + windowWH.w
				+ "    windowWH.h == " + windowWH.h
				+ "    windowParentWH.w == " + windowParentWH.w
				+ "    windowParentWH.h == " + windowParentWH.h
				
				+ "    _nfschinaDialog.height() == " + _nfschinaDialog.height()
				+ "    _nfschinaDialog.width() == " + _nfschinaDialog.width()
				+ "    left == " + _nfschinaDialog.css("left")
				+ "    top == " + _nfschinaDialog.css("top")
				
			);
		
		//_nfschinaDialog.css("left",left);
		*/
		//zyl:caba 20111213===================================================/
		
		
    };
	
	 var initNfschinaDialog = function(_thisDomId,_finalOptions){
	 	
	 return '<div class="nfschinaDialog" style="margin:0;z-index:1002;display:none;position: '+_finalOptions.position+';left: -1000px;top: -1000px;" id="nfschinaDialog_'+_thisDomId+'">'+
		   		'<div class="nfschinaDialogTop"></div>'+
		   		'<div class="nfschinaDialogHead" id="nfschinaDialogHead_'+_thisDomId+'">'+
		     		'<h2 class="nfschinaDialogH2"><span class="nfschinaDialogSpan">'+_finalOptions.title+'</span></h2>'+
		     		'<div class="nfschinaDialogClose"><a id="nfschinaDialogClose_'+_thisDomId+'" href="javascript:void(0)">关闭</a></div>'+
		   		'</div>'+
		   		'<div class="nfschinaDialogBody" id="nfschinaDialogBody_'+_thisDomId+'"></div>'+
		   		'<div class="nfschinaDialogButton" id="nfschinaDialogButton_'+_thisDomId+'"></div>'+
	   			'<div id="nfschinaDialogBodyIframe'+_thisDomId+'" style="position:absolute;visibility:inherit;z-index:-1;top:0;left:0"><iframe frameborder="no" style="border:none;width:100%;height:100%"></iframe></div>'+
	 			'<div class="nfschinaDialogBottom"></div>'+
	 		'</div>';
	 }
	/*销毁对话框，用originally替换dialogBody，并隐藏
	  * 
	  *parameters 
	  *dialogBody:对话框div的jquery对象
	  *originally:对话框中用户处理内的jquery对象
	  */
	 var destroyDialog=function(jo_dialogBody,jo_originally,_thisDomId){
	 	/*
	 	 * 如果存在对话框才销毁，这个地方不能写成这样：if(jo_dialogBody.attr('id'))
		 *因为jo_dialogBody在对话框已经销毁之后还存在值，所以必须用$("#nfschinaDialogBody_"+_thisDomId)
		 *才能取到实际的对话框到底有没有。
		 */
		if($("#nfschinaDialogBody_"+_thisDomId).attr('id'))
		{
			jo_dialogBody.replaceWith(jo_originally);
			jo_originally.hide();
			$("#nfschinaDialogBg_"+_thisDomId).remove();
		}
	 }
})(jQuery);

var nfschinaDialogConfirm=function(content ,callBack){
	if($("#toolConfirm").attr("id")=="toolConfirm")
	{
		//alert("Dialog.js-->>11  content =="+ content);
		$("#toolConfirm").html(content);
	}
	else
	{
		//alert("Dialog.js-->>22  content =="+ content);
		content='<div id="toolConfirm" style="display: none;padding:10px;height:50px;">'+content+'</div>';
		$("body").append(content);
	}
	//alert("Dialog.js-->>33   content =="+ content);
	 var dialogOption = {
                        title: "确认对话框",
                        delay: -1,
						buttons:{
							'确定':callBack,
							'取消':function(){$(this).nfschinaDialog("close")}
						},
						buttonAlign:"right"                     
                    };
	// alert("Dialog.js  nfschinaDialogConfirm()-->>44  content =="+ content);
	 
     $("#toolConfirm").nfschinaDialog(dialogOption);
};

var nfschinaDialogAlert=function(content,delay,title,width){
	if($("#toolAlert").attr("id")=="toolAlert")
	{
		$("#toolAlert").html(content);
	}
	else
	{
		content='<div id="toolAlert" style="display: none;padding:10px;height:50px;">'+content+'</div>';
		$("body").append(content);
	}
	 var dialogOption = {
                        title: title||"提示对话框",
                        delay: delay||3000,
                        bgframe: false,
                        buttons:{
							'确定':function(){$(this).nfschinaDialog("close")}
						},
						buttonAlign:"center"
                    };
     $("#toolAlert").nfschinaDialog(dialogOption);
};