package com.zjz.picture_net.model;

import com.zjz.picture_net.contract.IMainContract;
import com.zjz.picture_net.entity.ComicInfo;
import com.zjz.picture_net.utils.HttpUtils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public class MainModelImpl implements IMainContract.Model {
    @Override
    public void getComicList(final String url, final IMainContract.OnModelResultCallBack onModelResultCallBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc;
                try {
                    ArrayList<ComicInfo> comicInfoArrayList = new ArrayList<>();
                    Connection conn = Jsoup.connect(url);
                    conn.header("User-Agent", HttpUtils.getUserAgentString());

                    doc = conn.get();
                    Elements elements = doc.select("tbody tr td dl");

                    Elements elementPictures = elements.get(1).select("dd");

                    for (int i = 0 ; i<elementPictures.size();i++ ){
                        String imgUrl =  elementPictures.get(i).select("a img[src]").first().attr("src");//获取图片url
                        String title =  elementPictures.get(i).select("a[href]").get(1).text();//获取标题
                        String contentUrl = elementPictures.get(i).select("a[href]").get(1).attr("href");//获取链接url

                        ComicInfo comicInfo = new ComicInfo();
                        comicInfo.setName(title);
                        comicInfo.setImgUrl(imgUrl);
                        comicInfo.setContentUrl("http://comic.kukudm.com/"+ contentUrl);
                        comicInfoArrayList.add(comicInfo);
                    }

                    onModelResultCallBack.succeed(comicInfoArrayList);

                } catch (IOException e) {
                    onModelResultCallBack.failed("未知错误");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
