package com.zjz.picture_net.model;

import android.view.View;

import com.zjz.picture_net.constant.Constant;
import com.zjz.picture_net.contract.IComicContentContract;
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

public class ComicContentModelImpl implements IComicContentContract.Model {
    @Override
    public void getComic(final String url, final IComicContentContract.OnModelResultCallBack onModelResultCallBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String nextUrl = url;
                try {

                    ArrayList<String> imgUrlList = new ArrayList<>();

                    while( !nextUrl.equals("")) {

                        Connection conn = Jsoup.connect(nextUrl);
                        conn.header("User-Agent", HttpUtils.getUserAgentString());

                        Document doc = conn.get();
                        Elements elements = doc.select("tbody tr");

                        String imgString;

                        Element element = elements.get(1);
                        imgString = element.select("script:not([src])").toString();
                        if (element.select("a[href]:has([src])").size() == 1) {
                            nextUrl = Constant.KUKU_URL+element.select("a[href]:has([src])").first().attr("href");
                        } else {
                            //previousString = element.select("a[href]:has([src])").get(0).toString();
                            nextUrl = Constant.KUKU_URL+ element.select("a[href]:has([src])").get(1).attr("href");
                            if (nextUrl.contains("exit")) {
                                nextUrl = "";//结束的标志
                            }
                        }

                        int begin = imgString.indexOf("+\"");
                        int end = imgString.indexOf("jpg");


                        final String httpImgUrl = "http://n.1whour.com/" + imgString.substring(begin + 2, end + 3);//图片的地址

                        imgUrlList.add(httpImgUrl);
                    }

                    onModelResultCallBack.succeed(imgUrlList);
//                        if (!previousString.equals("")) {
//                            mPreviousUrl = "http://comic.kukudm.com" + previousString.substring(begin1 + 1, end1 + 3);//上一页的地址
//                        }
//                        mNextUrl = "http://comic.kukudm.com" + nextString.substring(begin1 + 1, end1 + 3);//下一页的地址
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (mPreviousUrl.equals("")) {
//                                    mBtnPrevious.setVisibility(View.GONE);
//                                } else {
//                                    mBtnPrevious.setVisibility(View.VISIBLE);
//                                }
                                //           Glide.with(getBaseContext()).load(httpImgUrl).into(mIvMain);//显示图片
                                //    mTvShow.setText(httpImgUrl + "\n" + mNextUrl + "\n");

                } catch (IOException e) {
                    onModelResultCallBack.failed("未知错误");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
