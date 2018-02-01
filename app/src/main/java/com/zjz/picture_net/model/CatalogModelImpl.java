package com.zjz.picture_net.model;

import android.util.Log;

import com.zjz.picture_net.constant.Constant;
import com.zjz.picture_net.contract.IComicCatalogContract;
import com.zjz.picture_net.contract.IMainContract;
import com.zjz.picture_net.entity.ComicCatalogAndUrl;
import com.zjz.picture_net.entity.ComicDescription;
import com.zjz.picture_net.entity.ComicInfo;
import com.zjz.picture_net.utils.HttpUtils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public class CatalogModelImpl implements IComicCatalogContract.Model {
    @Override
    public void getComicInformation(final String url, final IComicCatalogContract.OnModelResultCallBack onModelResultCallBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc;
                try {
                    ArrayList<ComicCatalogAndUrl> arrayList = new ArrayList<>();
                    Connection conn = Jsoup.connect(url);
                    conn.header("User-Agent", HttpUtils.getUserAgentString());

                    doc = conn.get();
                    Elements elementComicDescription = doc.select("html body>table").get(4).select(">tbody>tr>td").get(1).select(">table>tbody>tr>td>table>tbody>tr");
                    Elements elements = doc.select("#ComicInfo");
                    Elements elements1 = doc.select("#comiclistn");//漫画列表

                    String description  =  elements.first().text();//漫画简介


                    ComicDescription comicDescription = new ComicDescription();
                    String title = elementComicDescription.get(0).text();
                    if(title.lastIndexOf("漫画") == (title.length() - 2)){//截取漫画名
                      title =  title.substring(0,title.length() - 2);
                    }
                    String des = elementComicDescription.get(4).text();
                    String[] desS = des.split(" | ");
                    comicDescription.setTitle(title);
                    comicDescription.setAuthor(desS[0]);
                    comicDescription.setStatus(desS[2]);
                    comicDescription.setUpdateDate(desS[4]);
                    comicDescription.setDescription(description);


                   Elements elements2   =  elements1.select("dd");//列表

                    for(int i = 0 ; i<elements2.size() ; i++){
                        ComicCatalogAndUrl comicCatalogAndUrl = new ComicCatalogAndUrl();
                        comicCatalogAndUrl.setTitle(elements2.get(i).select("a").first().text());
                        comicCatalogAndUrl.setUrl(Constant.KUKU_URL + elements2.get(i).select("a").first().attr("href"));
                        arrayList.add(comicCatalogAndUrl);
                    }


                    onModelResultCallBack.succeedDescription(comicDescription);
                    onModelResultCallBack.succeedCatalog(arrayList);
//                    Elements elementPictures = elements.get(1).select("dd");
//
//                    for (int i = 0 ; i<elementPictures.size();i++ ){
//                        String imgUrl =  elementPictures.get(i).select("a img[src]").first().attr("src");//获取图片url
//                        String title =  elementPictures.get(i).select("a[href]").get(1).text();//获取标题
//                        String contentUrl = elementPictures.get(i).select("a[href]").get(1).attr("href");//获取链接url
//
//                        ComicInfo comicInfo = new ComicInfo();
//                        comicInfo.setName(title);
//                        comicInfo.setImgUrl(imgUrl);
//                        comicInfo.setContentUrl("http://comic.kukudm.com/"+ contentUrl);
//                        comicInfoArrayList.add(comicInfo);
//                    }
//
//                    onModelResultCallBack.succeed(comicInfoArrayList);

                } catch (IOException e) {
                    onModelResultCallBack.failedCatalog("请检查网络连接");
                    onModelResultCallBack.failedDescription("请检查网络连接");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
