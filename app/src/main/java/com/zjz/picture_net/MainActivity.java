package com.zjz.picture_net;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zjz.picture_net.adapter.MainComicRvAdapter;
import com.zjz.picture_net.base.BaseActivity;
import com.zjz.picture_net.contract.IMainContract;
import com.zjz.picture_net.entity.ComicInfo;
import com.zjz.picture_net.listener.OnClickRecyclerViewListener;
import com.zjz.picture_net.presenter.MainPresenterImpl;
import com.zjz.picture_net.ui.ComicCatalogActivity;
import com.zjz.picture_net.utils.GridSpacingItemDecoration;
import com.zjz.picture_net.utils.ProgressDialogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnClickRecyclerViewListener, IMainContract.View {


    @BindView(R.id.rv_main_comic)
    RecyclerView mRvMainComic;
    @BindView(R.id.tv_main_title)
    TextView mTvMainTitle;

    private MainComicRvAdapter mMainComicRvAdapter;

    private ArrayList<ComicInfo> mComicInfoArrayList = new ArrayList<>();//漫画目录

    private MainPresenterImpl mMainPresenter;

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        getSwipeBackLayout().setEnableGesture(false);//关闭右滑返回上一级
        mMainPresenter = new MainPresenterImpl(this, this);

        mTvMainTitle.setText("看看漫画");

        mMainComicRvAdapter = new MainComicRvAdapter();
        mMainComicRvAdapter.setOnRecyclerViewListener(this);

        mRvMainComic.setLayoutManager(new GridLayoutManager(this, 3,GridLayoutManager.VERTICAL, false));
        mRvMainComic.addItemDecoration(new GridSpacingItemDecoration(3, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
        mRvMainComic.setHasFixedSize(true);
        mRvMainComic.setAdapter(mMainComicRvAdapter);

        ProgressDialogUtil.showProgressDialog(this,"内容加载中...");
        mMainPresenter.showComicList("http://comic.kukudm.com/");
    }


    @Override
    protected void initView() {

    }

    @Override
    public void onShowComicListSucceed(ArrayList<ComicInfo> comicInfoArrayList) {//成功后回调
        mComicInfoArrayList = comicInfoArrayList;
        mMainComicRvAdapter.updateData(mComicInfoArrayList);
        ProgressDialogUtil.dismiss();
    }

    @Override
    public void onShowComicListFailed(String reason) {
        showToast(reason);
        ProgressDialogUtil.dismiss();
    }


    @Override
    public void onItemClick(int position) {
        ComicCatalogActivity.gotoActivityByUrl(this, mComicInfoArrayList.get(position).getContentUrl(), mComicInfoArrayList.get(position).getImgUrl()); //点击跳转
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }


    @OnClick()
    public void onViewClicked() {
    }

}
