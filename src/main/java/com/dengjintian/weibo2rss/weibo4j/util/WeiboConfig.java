package com.dengjintian.weibo2rss.weibo4j.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WeiboConfig {
	public WeiboConfig(){}
	private static Properties props = new Properties();
	static{
		try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("weibo.properties"));
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {
            props.setProperty(key, value);
    }
}
