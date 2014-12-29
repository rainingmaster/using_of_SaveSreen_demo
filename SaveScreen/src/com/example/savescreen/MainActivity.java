package com.example.savescreen;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button save = (Button)findViewById(R.id.save_screen);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				GetandSaveCurrentImage();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * 获取和保存当前屏幕的截图
	 */
	private void GetandSaveCurrentImage()  
	{  
	    //1.构建Bitmap  
	    WindowManager windowManager = getWindowManager();  
	    Display display = windowManager.getDefaultDisplay();  
	    int w = display.getWidth();  
	    int h = display.getHeight();  
	      
	    Bitmap Bmp = Bitmap.createBitmap( w, h, Config.ARGB_8888 );      
	      
	    //2.获取屏幕  
	    View decorview = this.getWindow().getDecorView();   
	    decorview.setDrawingCacheEnabled(true);   
	    Bmp = decorview.getDrawingCache();   
	    
	    String SavePath = getSDCardPath()+"/AndyDemo/ScreenImage";
	  
	    //3.保存Bitmap   
	    try {  
	        File path = new File(SavePath);  
	        //文件  
	        String filepath = SavePath + "/Screen_1.png";  
	        File file = new File(filepath);  
	        if(!path.exists()){  
	            path.mkdirs();  
	        }  
	        if (!file.exists()) {  
	            file.createNewFile();  
	        }  
	          
	        FileOutputStream fos = null;  
	        fos = new FileOutputStream(file);  
	        if (null != fos) {  
	            Bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
	            fos.flush();  
	            fos.close();    
	              
	            Toast.makeText(this, "截屏文件已保存至SDCard/AndyDemo/ScreenImage/下", Toast.LENGTH_LONG).show();
	        }  
	  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  
	
    /**
     * 获取SDCard的目录路径功能
     * @return
     */
	private String getSDCardPath(){
		File sdcardDir = null;
		//判断SDCard是否存在
		boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if(sdcardExist){
			sdcardDir = Environment.getExternalStorageDirectory();
		}
		return sdcardDir.toString();
	}

}
