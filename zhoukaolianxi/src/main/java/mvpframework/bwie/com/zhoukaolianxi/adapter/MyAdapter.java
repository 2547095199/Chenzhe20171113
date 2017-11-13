package mvpframework.bwie.com.zhoukaolianxi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mvpframework.bwie.com.zhoukaolianxi.R;
import mvpframework.bwie.com.zhoukaolianxi.bean.MultiBean;
/**
 *适配器
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myholder> {
    //两个参数  上下文，和数据源
    Context context;
    MultiBean bean;
    public MyAdapter(Context context, MultiBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局返回到holder

        View view=View.inflate(context, R.layout.lin_layout,null);
        myholder myholder=new myholder(view);

        return myholder;
    }

    @Override
    public void onBindViewHolder(myholder holder, int position) {
        //通过数据源内的图片字符串 通过split截取成数组
         String[] imgdata=bean.getData().get(position).getImages().split("\\|");
         Glide.with(context)
                 .load(imgdata[0])
                 .into(holder.item_li_iv);
        //设置控件 属性
         holder.item_li_tv1.setText(bean.getData().get(position).getTitle());
         holder.item_li_tv2.setText(bean.getData().get(position).getPrice()+"￥");
    }
    @Override
    public int getItemCount() {
        return bean.getData()==null?0:bean.getData().size();
    }
    class myholder extends RecyclerView.ViewHolder{
        ImageView item_li_iv;
//        ImageView item_gri_iv;
        TextView item_li_tv1;
        TextView item_li_tv2;
//        TextView item_gri_tv1;
//        TextView item_gri_tv2;
        public myholder(View itemView) {
            super(itemView);
            item_li_iv=itemView.findViewById(R.id.item_li_iv);
//            item_gri_iv=itemView.findViewById(R.id.item_gri_iv);
            item_li_tv1=itemView.findViewById(R.id.item_li_tv1);
            item_li_tv2=itemView.findViewById(R.id.item_li_tv2);
//            item_gri_tv1=itemView.findViewById(R.id.item_gri_tv1);
//            item_gri_tv2=itemView.findViewById(R.id.item_gri_tv2);
        }
    }

}
