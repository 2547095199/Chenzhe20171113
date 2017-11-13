package mvpframework.bwie.com.zhoukaolianxi.model;

import mvpframework.bwie.com.zhoukaolianxi.okhttp.OkHttp3Utils;
import okhttp3.Callback;

/**
 * Created by CZ on 2017/11/13.
 */
public class MyLieBiaoModel implements LieBiaoModel {

    @Override
    public void getData(String keywords, String page, Callback callback) {
        OkHttp3Utils.doGet("http://120.27.23.105/product/searchProducts?keywords="+keywords+"&page="+page+"&source=android",callback);
    }
}
