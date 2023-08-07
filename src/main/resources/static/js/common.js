/*layer-pop*/
function pLayer(i){
	if (i == 0) {
		$('div[id^=pLayer]').fadeOut(300);
	} else {
		$('#pLayer'+i).fadeIn(300);
	}
}

//ie 6 png 투명
function setPng24(obj) {
	obj.width=obj.height=1;
	obj.className=obj.className.replace(/\bpng24\b/i,'');
	obj.style.filter =
	"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');"
	obj.src=''; 
	return '';
}

/*gnb*/
$(function(){
	var gnb = $('.navi-area');
	gnb.find('>ul>li>a')
		.mouseover(function(){
			gnb.find('>ul>li>ul:visible').hide().parent('li').find('>a');
			$(this).next('ul:hidden').show().parent('li').find('>a');
		})
		//.focus(function(){
			//$(this).mouseover();
		//})
		.end()
		.mouseleave(function(){
			gnb.find('>ul>li>ul').hide().prev('a');
		});
	gnb.find('>ul>li>ul>li')
		.mouseover(function(){
			$(this).addClass('on');
		})
		.mouseleave(function(){
			$(this).removeClass('on');
		});
	$('.allmenu-btn a').click(function() {
		 if($('.allmenu').css('display')=='block'){
		  $('.allmenu').hide();
		 }else{
		  $('.allmenu').show();
		 }
	});
	$('.allmenu .close a').click(function(){
		$('.allmenu').hide();
	});
});

/*tab change*/
function tabChg(tabId,num){
	var tabCnt = document.getElementById(tabId).getElementsByTagName('li');
	var content;
	for ( i=1;i<=tabCnt.length;i++ ){
		if ( i==num) {
			content=tabCnt[num-1].childNodes[0];
			document.getElementById(content.href.split("#")[1]).style.display="block";
			//tabCnt[num-1].getElementsByTagName('img')[0].src=tabCnt[num-1].getElementsByTagName('img')[0].src.replace("_off.gif","_on.gif");
			tabCnt[num-1].className="on";

		}
		else {
			content=tabCnt[i-1].childNodes[0];
			document.getElementById(content.href.split("#")[1]).style.display="none";
			//tabCnt[i-1].getElementsByTagName('img')[0].src=tabCnt[i-1].getElementsByTagName('img')[0].src.replace("_on.gif","_off.gif");
			tabCnt[i-1].className="";
		}
	}
}
