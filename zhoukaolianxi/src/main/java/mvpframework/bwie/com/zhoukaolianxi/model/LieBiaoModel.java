package mvpframework.bwie.com.zhoukaolianxi.model;

import okhttp3.Callback;

/**
 * Created by CZ on 2017/11/13.
 */
public interface LieBiaoModel {
    public void getData(String keywords, String page, Callback callback);
}
