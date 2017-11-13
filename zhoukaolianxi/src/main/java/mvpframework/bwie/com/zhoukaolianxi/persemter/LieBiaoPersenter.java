package mvpframework.bwie.com.zhoukaolianxi.persemter;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import mvpframework.bwie.com.zhoukaolianxi.bean.MultiBean;
import mvpframework.bwie.com.zhoukaolianxi.model.MyLieBiaoModel;
import mvpframework.bwie.com.zhoukaolianxi.okhttp.OnUiCallback;
import mvpframework.bwie.com.zhoukaolianxi.view.LieBiaoView;
import okhttp3.Call;

/**
 * Created by CZ on 2017/11/13.
 */
//presenter层
public class LieBiaoPersenter {
    LieBiaoView view;
    Context context;
    private final MyLieBiaoModel model;

    public LieBiaoPersenter(LieBiaoView view, Context context) {
        this.view = view;
        this.context = context;
        model = new MyLieBiaoModel();
    }

    public void getdata(String keyword,String page){
        model.getData(keyword, page, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) {
                //进行解析
                Gson gson = new Gson();
                MultiBean bean = gson.fromJson(result, MultiBean.class);
                view.showView(bean);
            }
        });
    }
    //view为空
    public void destory(){
        this.view=null;
    }
}
