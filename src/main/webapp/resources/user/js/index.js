jQuery(document).ready(function($) {
	//声明图片集合对象，为全局变量
	var piclist;
	
    
	$(".userImgCenter").click(function(){
		$(".centerList").toggleClass("hidden");
	});
	//举报的异步提交
	$("#report_submit_btn").click(function(){
		//不为空再提交数据
		if($("#desct_input").val()!=null&&$("#desct_input").val()!=""){
			var options={
					target:null,
					url:'/moment/report/doReport.action',
					success:function(data){
						dataobj=JSON.parse(data);
						if(dataobj.status==1){
							alert(dataobj.msg);
							window.location.href="/moment/user/index.action";
						}else{
							alert("举报失败，请稍后再试");
						}
					},
					error:function(responseTxt,statusTxt,XMLHttpRequest){
						alert("举报失败，请稍后再试");
						alert("Error: "+XMLHttpRequest.status+": "+XMLHttpRequest.statusText);
					}
			};
			//使用表单控件，进行异步更新数据
			$('#report_form').ajaxSubmit(options);
			//防止页面进行刷新
			return false;
		}else{
			alert("具体的举报内容可以让我们更好帮助您！");
			return false;
		}
		
	});
	//获取摄影日志
	function getCalendar(){
		var options={
				target:null,
				url:'/moment/calendar/getCalendar.action',
				success:function(data){
					dataobj=JSON.parse(data);
					$(".picCalendar img").attr("src",dataobj.picpath);
					$(".calendar_description").text(dataobj.name);
				}
		};
		//使用表单控件，进行异步更新数据
		$(".picCalendar").ajaxSubmit(options);
		date=new Date();
		$(".cal_time").text(date.getFullYear()+"."+(date.getMonth()+1)+"."+date.getDate());
		var week=['周日','周一','周二','周三','周四','周五','周六'];
		$(".cal_weekday").text(week[date.getDay()]);
		$(".day").text(date.getDate());
		//防止页面进行刷新
		return false;
	}
	//获取图片展示,准备数据
	function getPicList(){
		var options={
				target:null,
				url:'/moment/pic/getPicList.action',
				success:function(data){
					piclist=JSON.parse(data);
					console.log(piclist);
				},
			    error:function(){
			    	alert('更新出错');
			    }
		};
		//使用表单控件，进行异步更新数据
		$(window).ajaxSubmit(options);
		//防止页面进行刷新
		return false;
	}

	
	$("#key").next("button").click(function(){
		var type = $("#key").val();
		console.log(type) ;
		$.ajax({
			data:{'pickey':type},
			url:'/moment/pic/getPicListByCondition.action',
			dataType:"json",
			success:function(data){
				
				$("#waterfall_ul").children("li").not("#main_window").remove() ;
				piclist = data;
				imgLocation();
				loadPicList() ;
				
			}
		});
		//防止页面进行刷新
		return false;
	});
	
	
	
	
	
	//以下部分实现图片瀑布流
	$(window).on("load",function(){
        imgLocation();
        loadPicList() ;
         window.onscroll=function(){
        /*
            	通过屏幕的滚动来加载图片
         */
            if(scrollside()){
            	loadPicList() ;
        }    
         
     }
 });
function loadPicList(){
    var waterfallUl = document.getElementById('waterfall_ul');
    for(i in piclist){
    	
        //创建li
        var pic_card= document.createElement('li');
        pic_card.className = 'pic_card';
        waterfallUl.appendChild(pic_card);
        //创建div
        var piccardDiv= document.createElement('div');
        piccardDiv.className = 'piccardDiv';
        pic_card.appendChild(piccardDiv);
        //创建img
        var img = document.createElement('img');
        piccardDiv.appendChild(img);
         img.src = piclist[i].picpath;
        img.style.height = 'auto';
        //创建picDetail
        var picDetail = document.createElement('div');
        picDetail.className = 'pic_detail';
        piccardDiv.appendChild(picDetail);
        //创建picdetail中的标题
        var picname = document.createElement('h4');
        picname.className = 'picname';
        picname.innerHTML=piclist[i].name;
        picDetail.appendChild(picname);
        //创建图片描述
        var picdesc = document.createElement('p');
        picdesc.className = 'picdesc';
        picdesc.innerHTML=piclist[i].description;
        picDetail.appendChild(picdesc);
        //创建详细信息
        var ul1=document.createElement('ul');
        picDetail.appendChild(ul1);
        //创建点赞模块
        var like=document.createElement('li');
        like.className = 'like';
        ul1.appendChild(like);
        //创建点赞数
        var piclike=document.createElement('span');
        piclike.className = 'piclike';
        piclike.innerHTML=piclist[i].piclike;
        like.appendChild(piclike);
        //创建收藏模块
        var collect=document.createElement('li');
        collect.className = 'collect';
        ul1.appendChild(collect);
        //创建收藏数
        var piccollect=document.createElement('span');
        piccollect.className = 'piccollect';
        piccollect.innerHTML=piclist[i].collect;
        collect.appendChild(piccollect);
        //创建评论模块
        var comment=document.createElement('li');
        comment.className = 'comment';
        ul1.appendChild(comment);
        //创建评论数
        var piccomment=document.createElement('span');
        piccomment.className = 'piccomment';
        piccomment.innerHTML=piclist[i].comment;
        comment.appendChild(piccomment);
        //创建举报模块
        var report=document.createElement('li');
        report.className = 'report';
        report.innerHTML='举报';
        $(".report").attr({'data-toggle':'modal','data-target':'#reportModal','data-toggle':'modal','data-target':'#reportModal'});
        ul1.appendChild(report);
         //创建ownerDetail
        var ownerDetail = document.createElement('div');
        ownerDetail.className = 'owner_detail';
        piccardDiv.appendChild(ownerDetail);
        //创建用户头像
        var userimg = document.createElement('img');
        ownerDetail.appendChild(userimg);
        userimg.src = piclist[i].user.img;
        userimg.className = 'ownerImg';
        //创建新的div
        var div=document.createElement('div');
        ownerDetail.appendChild(div);
        //创建ownername
        var ownername=document.createElement('span');
        div.appendChild(ownername);
        ownername.innerHTML=piclist[i].user.name;
        //创建等级
        var ownergrade=document.createElement('span');
        div.appendChild(ownergrade);
        ownergrade.innerHTML="&nbsp;&nbsp;&nbsp;"+piclist[i].user.grade.grade;
        //发表图片
        var publish=document.createElement('p');
        div.appendChild(publish);
        publish.innerHTML="发表图片";
        //时间
         var time=document.createElement('p');
         ownerDetail.appendChild(time);
        time.className='time';
        time.innerHTML=piclist[i].time.split(" ")[0];
        
        
    }
    imgLocation();
}
/**
* 瀑布流主函数
* @param  wrap  [Str] 外层元素的ID
* @param  box   [Str] 每一个box的类名
*/
function imgLocation(){
    //  1.获得外层以及每一个box
    var pic_card =$('.pic_card');
    console.log(pic_card.eq(0));
    var boxWidth=pic_card.eq(1).width();
        /*
            通过qu(0)来获取第一个盒子的宽度
            宽度都相同，因此获取那个box的宽度都可以。
         */
    var num=Math.floor(1200/boxWidth);
    console.log(num);
        /*
            计算一排能放几个图片 num
            取整数。
         */
    var boxArr=[];
    pic_card.each(function(index,value){
        /*
            index:确定从哪个图片开始
            value：确定当前是哪个对象
         */
        // console.log(index+"  "+value);
        var boxHeight = pic_card.eq(index).height();
        console.log(boxHeight);
            /*
            获取每个盒子的高度
            其中第一排直接放在数组中。
             */
        if(index<num){
            boxArr[index] = boxHeight;
        }else{
            var minBoxHeight=Math.min.apply(null,boxArr);
            var minBoxIndex=$.inArray(minBoxHeight,boxArr);
            /*
                当放置第二排时，需要考虑第一排的高度
                获取最小盒子的高度
                从数组中获取最小盒子的位置，从而为了放置下一排的盒子。
             */
             $(value).css({
                 'position':'absolute',
                 'top':minBoxHeight,
                 'left':pic_card.eq(minBoxIndex).position().left
             });
             /*
                获取图片的对象，然后对图像进行操作。
                实际上操作的是box，位置的放置通过CSS来控制。
              */
             boxArr[minBoxIndex]+=pic_card.eq(index).height();
      
   
        }
    });
}

/**
* 数据请求检验
*/
function scrollside(){
    var pic_card=$(".pic_card");
    var lastboxHeight = pic_card.last().get(0).offsetTop+Math.floor(pic_card.last().height()/2);
        /*
            获取最后一个盒子的高度也就是最高的盒子的高度。
         */
    var documentHeight=$(document).width();
        //获取当前屏幕的高度
    var scrollHeight=$(window).scrollTop();
        //获取窗口的滚动高度 滚动到距顶端的位置

    return (lastboxHeight<scrollHeight+documentHeight)?true:false;
    /*
        当前文档的高度+滚动的高度大于最高的图片的高度的时候，进行图片的加载。
     */
}
	

	
	//在首页更新时刷新页面
	getPicList();
	//获取摄影日志
	getCalendar();
	
});