package com.nshane.picstore.config;

import android.os.Environment;

/**
 * 常量的定
 * 
 * @author huangjianfeng
 */
public class Constant {

	public static final int APP_ID	       =	900001;
	
	public static final String youtubePluginDurl = "http://client.dl.oppoos.com/android/requestForUpdate.htm?clientAppId=mini&appVsn=200&channel_id=999&udRelease_playerLib=0" ;
	public static String VIDEO_LIB_PATH = "/sdcard/tube/" ;
	
	public static final String SELF_PKG_NAME = "com.ccc.videodownload";

	public static final String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	/**
	 * SD卡文件存储的根路 
	 */
	public static final String TUBEMATE_SDPATH = SDPATH + "/tubecast/";
	/**
	 * 动 路径,默认sd 如果无法访问则存储在data目录 
	 */
	public static String TUBEMATE_PATH = TUBEMATE_SDPATH;
	/**
	 * 图片保存的路 在Gallery相册下可 
	 */
	public static String IMAGE_PATH = TUBEMATE_PATH + "image/";

	public static String VIDEO_PATH = TUBEMATE_PATH + "video/";

	public static String PLUGIN_PATH = TUBEMATE_PATH + "plugin/" ;

	/**
	 * 铃音存放位置
	 */
	public static String RINGTONE_PATH = TUBEMATE_PATH + "ringtone/";
	/**
	 * 图片缓存的路 在Gallery相册下不可见，即隐藏后缀 
	 */
	public static String IMAGECACHE_PATH = TUBEMATE_PATH + "imagecache/";
	public static final String IMAGECACHE = "mytube/imagecache";
	
//	public static final String SELF_APP_NAME;
//	static {
//		SELF_APP_NAME = MyTubeApplication.getInstance().getResources().getString(R.string.app_name);
//	}
	
	/**
	 * 文件下载路径
	 */
	public static String FILE_PATH = TUBEMATE_PATH + "file/";
	
	/**
	 * APP存放位置
	 */
	public static String APP_PATH = TUBEMATE_PATH + "app/";

	public static class FILETYPE {
		public static final int APP = 111;
		public static final int WALLPAPER = 112;
		public static final int RINGTONE = 113;
		public static final int NONE = 114;
		public static final int VIDEO = 115;
		public static final int APP_ADS = 111;//170新加标识广告,为了兼容，避免大量代码修改目前定义变量值和APP相同
		public static final int EBOOK = 116;
		public static final int PLUGIN = 117;
	}

	/**
	 * 暂停下载任务
	 */
	public static final int DOWNLOAD_PAUSE = 13;
	/**
	 * 下载的初始化状 
	 */
	public static final int DOWNLOAD_INIT = 11;
	/**
	 * 下载完成
	 */
	public static final int DOWNLOAD_SUCCESS = 14;
	/**
	 *   下载任务均完 终结Service
	 */
	public static final int DOWNLOAD_FINISH = 15;
	/**
	 * 下载进度条更 
	 */
	public static final int DOWNLOAD_PROGRESS = 17;
	/**
	 * 当前下载进度
	 */
	public static final String DOWNLOAD_PROGRESS_CURRENT = "download_progress_current";

	// public

	public static final String DOWNLOAD_POINT = "download_point";
	public static final String NET_NO_OK = "net_no_ok";

	public static class TABLE_NAME {
		/** 新版下载 */
		public static final String DOWNLOAD = "download";


		
		public static final String SEARCH_HISTORY = "search_history";
		public static final String PLAY_HISTORY = "play_history" ;
		public static final String FAVORITE = "favorite" ;
	}

	public static enum SEARCH_TYPE {
		ALL, APP, GAME, RINGTONE, WALLPAPER, VIDEO, EBOOK
	}

	/* 网络状  -1无网 0 mobile 1wifi */
	public static final int NETWORK_NULL = -1;

	/**
	 * 2G/3G省流量sharedprefrence及类型取 
	 */
	public static final String SP_KEY_TRAFFIC_SAVING_MODE_TYPE = "traffic_saving_type";
	public static final int TRAFFIC_SAVING_MODE_TYPE_NORMAL = 0;
	public static final int TRAFFIC_SAVING_MODE_TYPE_NO_PICTURE = 1;
	public static final int TRAFFIC_SAVING_MODE_TYPE_NO_2G3G = 2;
	
	
	
	public static final int NO_UPDATE = 0x1000001 ;
	
	
	public static final String YOUTUBE_IMAGE = "http://i1.ytimg.com/vi/%1$s/default.jpg";// youtube图片地址
	public static final String WATCHV = "http://www.youtube.com/watch?v=%s";

}
