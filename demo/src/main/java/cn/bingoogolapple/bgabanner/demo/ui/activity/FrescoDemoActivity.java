package cn.bingoogolapple.bgabanner.demo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.demo.App;
import cn.bingoogolapple.bgabanner.demo.DataUtils;
import cn.bingoogolapple.bgabanner.demo.R;
import cn.bingoogolapple.bgabanner.demo.bean.ImageBean;
import cn.bingoogolapple.bgabanner.demo.model.BannerModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:16/12/12 下午10:37
 * 描述:
 */
public class FrescoDemoActivity extends AppCompatActivity {
    private BGABanner mContentBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_demo);
        setTitle("FrescoDemo");
        mContentBanner = findViewById(R.id.banner_fresco_demo_content);
        //ItemClick
        mContentBanner.setDelegate(new BGABanner.Delegate<View, ImageBean>() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, ImageBean model, int position) {
                Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
            }
        });
        //filldata
        mContentBanner.setAdapter(new BGABanner.Adapter<View, ImageBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, ImageBean model, int position) {
                ImageView simpleDraweeView = itemView.findViewById(R.id.sdv_item_fresco_content);
               // simpleDraweeView.setImageURI(Uri.parse(model));
                Glide.with(FrescoDemoActivity.this).load(model.getUrl()).apply(new RequestOptions().transform(new RoundedCorners(1))).into(simpleDraweeView);

            }
        });
       //set data
        App.getInstance().getEngine().fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
             //  mContentBanner.setData(R.layout.item_fresco, bannerModel==null?DataUtils.getImgs():bannerModel.imgs, bannerModel==null?DataUtils.getTips():bannerModel.tips);
                mContentBanner.setData(R.layout.item_fresco, bannerModel==null?DataUtils.getImageBeanList():bannerModel.imgs, null);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
