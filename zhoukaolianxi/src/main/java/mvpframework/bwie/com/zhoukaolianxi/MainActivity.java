package mvpframework.bwie.com.zhoukaolianxi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import mvpframework.bwie.com.zhoukaolianxi.adapter.MyAdapter;
import mvpframework.bwie.com.zhoukaolianxi.bean.MultiBean;
import mvpframework.bwie.com.zhoukaolianxi.persemter.LieBiaoPersenter;
import mvpframework.bwie.com.zhoukaolianxi.view.LieBiaoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LieBiaoView {

    LieBiaoPersenter presenter = new LieBiaoPersenter(this, this);
    private Button mChangeBt;
    private RelativeLayout mHead;
    private EditText mGoodsEt;
    private Button mSearchBt;
    private RelativeLayout mBody;
    private XRecyclerView mXrv;
    private  int num=1;
    private  int  aa=1;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        mChangeBt = (Button) findViewById(R.id.bt_change);
        mChangeBt.setOnClickListener(this);
        mHead = (RelativeLayout) findViewById(R.id.head);
        mGoodsEt = (EditText) findViewById(R.id.et_goods);
        mSearchBt = (Button) findViewById(R.id.bt_search);
        mSearchBt.setOnClickListener(this);
        mBody = (RelativeLayout) findViewById(R.id.body);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
    }

    @Override
    public void onClick(View v) {
        //点击事件
        switch (v.getId()) {
            case R.id.bt_change:
                //根据aa变量是奇数还是偶数来判断加载那种布局返回那张图片
                aa++;
                if(aa % 2==0){
                    GridLayoutManager manager= new GridLayoutManager(MainActivity.this,2);
                    mXrv.setLayoutManager(manager);
                    mChangeBt.setBackgroundResource(R.drawable.lv_icon);

                }if(aa % 2 ==1){
                LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
                mXrv.setLayoutManager(manager);
                mChangeBt.setBackgroundResource(R.drawable.grid_icon);
            }
                break;
            case R.id.bt_search:
                //点击搜索按钮时触发presenter的获取数据方法
                presenter.getdata(mGoodsEt.getText().toString(),"1");
                break;
            default:
                break;
        }
    }
    @Override
    public void showView(MultiBean bean) {
        Toast.makeText(MainActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
        mXrv.setLayoutManager(manager);
        final MyAdapter adapter=new MyAdapter(MainActivity.this,bean);
        mXrv.setAdapter(adapter);
        //XRecyclerview的上拉下拉方法
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        presenter.getdata(mGoodsEt.getText().toString(),"1");
                        adapter.notifyDataSetChanged();
                        mXrv.refreshComplete();
                    }
                },900);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载
                        num++;
                        presenter.getdata(mGoodsEt.getText().toString(),num+"");
                        adapter.notifyDataSetChanged();
                        mXrv.loadMoreComplete();

                    }
                },900);
            }
        });
    }
    //实现presenter内部的防止内存溢出方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destory();
    }


}
