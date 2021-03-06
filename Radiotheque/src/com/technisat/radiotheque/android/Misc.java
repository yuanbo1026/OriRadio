package com.technisat.radiotheque.android;

import java.util.Random;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.technisat.radiothek.R;

public class Misc {
	
	public static String getLanguageCode(Context ctx){
		return ctx.getResources().getConfiguration().locale.getLanguage();
	}
	
	public static boolean isValidMetaData(String meta){
		if (meta == null)
			return false;
		
		if (meta.length() < 5)
			return false;
		
		if (meta.contains("[ICY 401 Service"))
			return false;
		
		if (meta.contains("(leer)"))
				return false;
		
		if (!meta.contains("-"))
			return false;
		
		return true;
	}
	
	/**
	 * This Method cuts out all non-relevant information of a given meta-data String
	 * and returns a String StreamTitle (Artist - Title in most cases)
	 * 
	 * @param s String with meta-data information
	 * @return String with Artist and Title
	 */
	public static String cutOutTitleAndArtist(String s){
		
		// Strings always got this form:		"StreamTitle='Artist - Title';StreamUrl='Url';"
		// seperate String at '='
		String[] splitResult = s.split("=",3);
		if(splitResult[0].equalsIgnoreCase("StreamTitle")){
			
			// Cut our everything behind Interpreten und Titel'
			String[]resultWithQuotation=splitResult[1].split("[;]", 2);
			
			// Remove quotation
			int length = resultWithQuotation[0].length();
			String resultWithoutQuotation = resultWithQuotation[0].substring(1,length-1);
						
			return resultWithoutQuotation;
		}else{
			return "";
		}
	}
	
	public static int getRandomNumber(int max){
		Random r = new Random();
		return r.nextInt(max);
	}
	
	public static String getRandomShareText(Context ctx){
		switch (getRandomNumber(5)) {
		case 0:
			return ctx.getString(R.string.share_text_1);
		case 1:
			return ctx.getString(R.string.share_text_2);
		case 2:
			return ctx.getString(R.string.share_text_3);
		case 3:
			return ctx.getString(R.string.share_text_4);
		case 4:
			return ctx.getString(R.string.share_text_5);
		default:
			return ctx.getString(R.string.share_text_1);
		}
	}
	
	public static final int FONT_NORMAL = 0;
	public static final int FONT_BOLD = 1;
	public static final int FONT_LIGHT = 2;
	
	public static Typeface getCustomFont(Context ctx, int style){		
		switch (style) {
		case FONT_NORMAL:
			return Typeface.createFromAsset(ctx.getAssets(), "Kelson Sans Regular.otf");
		case FONT_BOLD:
			return Typeface.createFromAsset(ctx.getAssets(), "Kelson Sans Bold.otf");
		case FONT_LIGHT:
			return Typeface.createFromAsset(ctx.getAssets(), "Kelson Sans Light.otf");
		}

		return null;
	}
	
	public static boolean isOnline(Context ctx) {
	    ConnectivityManager cm =
	        (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	/**
	 * initialize Image Loader->?
	 * @param ctx
	 */
	public static void initUIL(Context ctx){
//		Log.d("Nexxoo", "Init called for UIL");
		if (!ImageLoader.getInstance().isInited()){
			DisplayImageOptions options = new DisplayImageOptions.Builder()
	    	.cacheInMemory(true)
	    	//.cacheOnDisc(true)
	    	.build();
			
			ImageLoaderConfiguration imgCfg = new ImageLoaderConfiguration.Builder(ctx)
			.defaultDisplayImageOptions(options)
			.build();
			ImageLoader.getInstance().init(imgCfg);
//			Log.d("Nexxoo", "Init done for UIL");
		}
	}

}
