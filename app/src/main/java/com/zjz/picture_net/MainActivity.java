package com.zjz.picture_net;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjz.picture_net.base.BaseActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tv_show)
    TextView mTvShow;
    @BindView(R.id.btn_get)
    Button mBtnGet;
    @BindView(R.id.iv_main)
    ImageView mIvMain;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.btn_previous)
    Button mBtnPrevious;

    private String mNextUrl;
    private String mPreviousUrl;

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_get, R.id.btn_next, R.id.btn_previous})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                String url1 = "http://comic.kukudm.com/comiclist/5/3443/1.htm";
                String url2 = "http://comic.kukudm.com/comiclist/3/56260/7.htm";
                showPicture(url1);
                break;
            case R.id.btn_previous:
                showPicture(mPreviousUrl);
                break;
            case R.id.btn_next:
                showPicture(mNextUrl);
                break;

        }
    }


    private void showPicture(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc;
                try {



                    Connection conn = Jsoup.connect(url);
                    conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");

                    doc = conn.get();
                    Elements elements = doc.select("tbody tr");

                    String imgString ;
                    String previousString ="";
                    String nextString ;


                    Element element = elements.get(1);
                    imgString = element.select("script:not([src])").toString();
                    if (element.select("a[href]:has([src])").size() == 1) {
                        nextString = element.select("a[href]:has([src])").toString();
                       mPreviousUrl = "";
                    } else {
                        previousString =element.select("a[href]:has([src])").get(0).toString();
                        nextString = element.select("a[href]:has([src])").get(1).toString();
                    }



                    int begin = imgString.indexOf("+\"");
                    int end = imgString.indexOf("jpg");

                    int begin1 = nextString.indexOf("\"");
                    int end1 = nextString.indexOf("htm");

                    final String httpImgUrl = "http://n.1whour.com/" + imgString.substring(begin + 2, end + 3);//图片的地址

                    if(!previousString.equals("")){
                        mPreviousUrl = "http://comic.kukudm.com" + previousString.substring(begin1 + 1, end1 + 3);//上一页的地址
                    }
                    mNextUrl = "http://comic.kukudm.com" + nextString.substring(begin1 + 1, end1 + 3);//下一页的地址

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(mPreviousUrl.equals("")){
                                mBtnPrevious.setVisibility(View.GONE);
                            }
                            else{
                                mBtnPrevious.setVisibility(View.VISIBLE);
                            }
                            Glide.with(getBaseContext()).load(httpImgUrl).into(mIvMain);//显示图片
                        //    mTvShow.setText(httpImgUrl + "\n" + mNextUrl + "\n");
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
