package com.zjz.picture_net;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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


    @OnClick(R.id.btn_get)
    public void onViewClicked() {
        showToast("haha");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {

                    Connection conn = Jsoup.connect("http://comic.kukudm.com/comiclist/3/56260/7.htm");
                    conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");

                    doc = conn.get();
                    Elements elements = doc.select("tbody tr");
                    final StringBuilder imgUrl = new StringBuilder();

                    String url;
                    for (Element element : elements) {
                        imgUrl.append(element.select("script:not([src])").toString());

                    }

                    url = imgUrl.toString();
                    int begin = url.indexOf("new");
                    int end = url.indexOf("jpg");

                    final String text1 = url.substring(begin, end + 3);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvShow.setText(text1);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
