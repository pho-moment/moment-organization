<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Moment首页</title>
    <link rel="stylesheet" href="${path}/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${path}/resources/css/index.css" />
    <link href="${path}/resources/css/cropper.min.css" rel="stylesheet">
    <link href="${path}/resources/css/main.css" rel="stylesheet">
    <link rel="shortcut icon"  href="img/icon.jpg">
    <style type="text/css">
        .search {
           background: url(${path}/resources/fonts/search.svg) no-repeat 7px 6px;
            background-size: 25px 23px;
        }
        .setting {
            background: url(${path}/resources/fonts/shezhi.svg) no-repeat 2px 13px;
            background-size: 29px 28px;
            width: 35px;
            height: 55px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .like{
            background: url(${path}/resources/fonts/xihuan.svg) no-repeat 1px 3px;
            background-size:  16px 16px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .like1{
            background: url(${path}/resources/fonts/like1.svg) no-repeat 1px 3px;
            background-size:  16px 16px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .collect{
           background: url(${path}/resources/fonts/shoucang.svg) no-repeat 0px -1px;
            background-size: 18px 26px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .comment{
            background: url(${path}/resources/fonts/pinglun.svg) no-repeat -1px 1px;
            background-size: 19px 24px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .exit{
            background: url(${path}/resources/fonts/tuichu.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
        .inform{
            background: url(${path}/resources/fonts/inform.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
        .user{
            background: url(${path}/resources/fonts/user.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
        .setting1{
            background: url(${path}/resources/fonts/setting1.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
    </style>
</head>
<body>
<div class="top_content">
    <header>
        <div>
             <ul>
            <li>
                <a href="index.html"><img src="${path}/resources/img/logo.png" class="logo"></a>
            </li>
            <li>
                <a href="">首页</a>
            </li>
            <li>
                <a href="">外拍</a>
            </li>
            <li>
                <a href="">论坛</a>
            </li>
            <li>
                <a href="">干货</a>
            </li>
            <li class="userCenter">
                <img src="${path}/resources/img/user.jpg" class="userImg userImgCenter">
                    <ul class="centerList hidden">
                        <li>
                            <a href class="user">个人中心</a>
                        </li>
                        <li>
                            <a href class="inform">重要通知</a>
                        </li>
                        <li>
                            <a href class="setting1">账号设置</a>
                        </li>
                        <li>
                            <a href class="exit">退出账号</a>
                        </li>
                    </ul>
            </li>
        </ul>
        </div>
       
    </header>
    <div class="search_block">
        <form>
            <input type="text" placeholder="输入关键词" >
            <button class="search_btn search"></button>
             <p>
            <a href="">黑白</a>&nbsp;|&nbsp;
            <a href="">风光</a>&nbsp;|&nbsp;
            <a href="">夜景</a>&nbsp;|&nbsp;
            <a href="">古风</a>&nbsp;|&nbsp;
            <a href="">静物</a>&nbsp;|&nbsp;
            <a href="">旅拍</a>&nbsp;|&nbsp;
            <a href="">星空</a>&nbsp;|&nbsp;
            <a href="">其他</a>
        </p>
        </form>
    </div>
</div>
<div class="container">
    <div class="pic_waterfall">
        <ul>
            <li class="user_card">
                <div class="picCalendar">
                   <img src="${path}/resources/img/calendar.jpg">
                   <div class="calendar">
                        <p class="calendar_description">只能七个字以内</p>
                        <p class="cla_today">
                            <span class="cal_time"> 2017.5.02&nbsp;&nbsp;&nbsp;</span>
                            <span class="cal_weekday">周三</span>
                        </p>
                        <p class="lunar_today">甲午年丁寅日</p>
                   </div>  
                   <div class="date">
                        <h2 class="day">2</h2>
                        <p class="forecast">宜摄影</p>
                   </div>     
                </div>
                <div>
                    <div class="userSetting">
                        <a href=""><img src="${path}/resources/img/user.jpg" class="userImg"></a>
                        <a href=""><button class="setting"> </button></a>
                    </div>
                    <div class="user_detail">
                        <ul>
                            <li>
                                <h3>12</h3>
                                <p>收藏</p>
                            </li>
                            <li>
                                <h3>13</h3>
                                <p>关注</p>
                            </li>
                            <li>
                                <h3>13</h3>
                                <p>粉丝</p>
                            </li>
                        </ul>
                    </div>
                   <div class="container" id="crop-avatar">
                        <!-- 生成模态窗口的按钮-->
                        <button class="upload_btn avatar-view" data-toggle="modal" data-target="#myModal">上传图片</button> 
                        <!-- 模态窗口 -->
                        <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
                            <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <form class="avatar-form" action="crop.php" enctype="multipart/form-data" method="post">
            <div class="modal-header">
              <button class="close" data-dismiss="modal" type="button">&times;</button>
              <h4 class="modal-title" id="avatar-modal-label">上传照片</h4>
            </div>
            <div class="modal-body">
              <div class="avatar-body">

                <!-- 上传图片 -->
                <div class="avatar-upload">
                  <input class="avatar-src" name="avatar_src" type="hidden">
                  <input class="avatar-data" name="avatar_data" type="hidden">
                  <label for="avatarInput">选择照片上传</label>
                  <input class="avatar-input" id="avatarInput" name="avatar_file" type="file">
                </div>

                <!-- 图片的剪裁预览窗口 -->
                <div class="row">
                  <div class="col-md-9">
                    <div class="avatar-wrapper"></div>
                  </div>
                  <div class="col-md-3">
                    <div class="avatar-preview preview-lg"></div>
                  </div>
                </div>
                <!--图片相关信息输入-->
                <div class="pic_detail_input">
                <hr>
                <div class="form-group">
                    <label for="picNameInput">图片名称</label>
                    <input class="avatar-input" id="picNameInput" name="name" type="text" placeholder="给你的作品取个名字吧(七个字以内)">
                </div>
                <hr>
                <div class="form-group">
                    <label for="picDescriptionInput">图片故是</label>
                    <textarea class="avatar-input" id="picDescriptionInput" name="desct" type="text" placeholder="因故而是，向大家说说照片背后的故事吧" rows="3" cols="20"></textarea>
                </div>
                <hr>
                </div>
                <!--控制图片的旋转-->
                <div class="row avatar-btns">
                    <button class="btn  btn-block avatar-save" type="submit">上传照片</button>
                    <button class="btn  btn-block avatar-save" type="submit">生成日志</button>
                </div>
              </div>
            </div>
            <!-- <div class="modal-footer">
              <button class="btn btn-default" data-dismiss="modal" type="button">Close</button>
            </div> -->
          </form>
        </div>
      </div>
                        </div><!-- /.modal -->

  </div>

                </div>
            </li>
            <li>
                <img src="${path}/resources/img/test.jpg">
                <div class="pic_detail">
                    <h4 class="picname">图片名称孩图片名称孩</h4>
                    <p class="picdesc">图片描述图片描述</p>
                    <ul>
                        <li class="like "><span class="piclike">12</span></li>
                        <li class="collect"><span class="piccollect">12</span></li>
                        <li class="comment"> <span class="piccollect">12</span></li>
                        <li class="report"><a href="">举报</a></li>
                    </ul>
                </div>
                <div class="owner_detail">
                        <img src="${path}/resources/img/user.jpg" class="ownerImg">
                        <div>
                            <span class="ownername">XXX</span>
                            <span class="ownergrade">LV0</span>
                            <p>发表图片<p>
                        </div>
                        <p class="time">2017-04-30</p>
                </div>
            </li>
            <li>
                <img src="${path}/resources/img/test2.jpg">
                <div class="pic_detail">
                    <h4 class="picname">图片名称孩图片名称孩</h4>
                    <p class="picdesc">图片描述图片描述</p>
                    <ul>
                        <li class="like "><span class="piclike">12</span></li>
                        <li class="collect"><span class="piccollect">12</span></li>
                        <li class="comment"> <span class="piccollect">12</span></li>
                        <li class="report"><a href="">举报</a></li>
                    </ul>
                </div>
                <div class="owner_detail">
                        <img src="${path}/resources/img/user.jpg" class="ownerImg">
                        <div>
                            <span class="ownername">XXX</span>
                            <span class="ownergrade">LV0</span>
                            <p>发表图片<p>
                        </div>
                        <p class="time">2017-04-30</p>
                </div>
            </li>
             <li>
            </li>
            <li>
            </li>
            <li>
            </li>
            <li>
            </li>
            <li>
            </li>
            <li>
            </li>
        </ul>       
    </div>
</div>
<footer>

</footer>
<script src="${path}/resources/js/jquery-2.2.3.min.js"></script>
<script src="${path}/resources/js/bootstrap.min.js"></script>
<script src="${path}/resources/js/jquery.form.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script src="${path}/resources/js/cropper.min.js"></script>
<script src="${path}/resources/js/main.js"></script>
</body>
</html>