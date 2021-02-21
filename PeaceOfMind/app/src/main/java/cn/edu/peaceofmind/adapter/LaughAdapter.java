package cn.edu.peaceofmind.adapter;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.entity.LaughInfo;
import cn.edu.peaceofmind.utils.Utils;
import cn.edu.peaceofmind.utils.XToastUtils;

public class LaughAdapter extends  RecyclerView.Adapter<LaughAdapter.ViewHolder>  {


    private List<LaughInfo> laughInfos;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String result = msg.obj+"";
            switch (msg.what){
                case 0:
                    if(result.equals("true")){
                        XToastUtils.info("点赞成功");
                    }else{
                        XToastUtils.error("点赞失败");
                    }
                    break;
                case 1:
                    if(result.equals("true")){
                        XToastUtils.info("取消点赞成功");
                    }else{
                        XToastUtils.info("取消点赞失败");
                    }
                    break;
            }

        }
    };

    static final class ViewHolder extends RecyclerView.ViewHolder{//定义用于缓存item布局中子控件对象的类
        ImageView ivPraise;
        TextView tvSummary;
        TextView tvPraise;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPraise = itemView.findViewById(R.id.iv_praise);
            tvSummary = itemView.findViewById(R.id.tv_summary);
            tvPraise = itemView.findViewById(R.id.tv_praise);
        }
    }


    public LaughAdapter(List<LaughInfo> laughInfos){
        this.laughInfos = laughInfos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_laugh_card_view_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LaughInfo laughInfo = laughInfos.get(position);
        holder.tvSummary.setText(laughInfo.getSummary());
        holder.tvPraise.setText(laughInfo.getPraise()+"");
        int userId = 10;//TODO 获取userId
        holder.ivPraise.setTag(false);
        holder.ivPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("iv_like.getTag()",holder.ivPraise.getTag()+"");
                if((boolean)holder.ivPraise.getTag()){
                    holder.ivPraise.setColorFilter(Color.parseColor("#aaaaaa"));
                    int zanNumBefore = laughInfo.getPraise();
                    int zanNumAfter = zanNumBefore-1;
//                    decZanNumByComment(laughInfo.getLaughId(),userId,zanNumAfter);
                    laughInfo.setPraise(zanNumAfter);
                    holder.tvPraise.setText(zanNumAfter+"");
                    holder.ivPraise.setTag(false);
                }else {
                    holder.ivPraise.setColorFilter(Color.parseColor("#FF5C5C"));
                    int zanNumBefore = laughInfo.getPraise();
                    int zanNumAfter = zanNumBefore+1;
//                    addZanNumByComment(laughInfo.getLaughId(),userId,zanNumAfter);
                    laughInfo.setPraise(zanNumAfter);
                    holder.tvPraise.setText(zanNumAfter+"");
                    holder.ivPraise.setTag(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return laughInfos.size();
    }

    //对评论点赞
    private void addZanNumByComment(int jokeId, int userId, int zanNumAfter) {
        new Thread(){
            @Override
            public void run() {
                Log.e("笑话的id",jokeId+",点赞数"+zanNumAfter);
                String result = new Utils().getConnectionResult("joke","addZanNum","jokeId="+jokeId
                        +"&&userId="+userId+"&&zanNumAfter="+zanNumAfter);
                Message message = new Message();
                message.obj = result;
                message.what=0;
                handler.sendMessage(message);
            }
        }.start();
    }
    //对评论取消点赞
    private void decZanNumByComment(int jokeId, int userId, int zanNumAfter) {
        new Thread(){
            @Override
            public void run() {
                Log.e("取消点赞笑话的id",jokeId+",点赞数"+zanNumAfter);
                String result = new Utils().getConnectionResult("joke","decZanNum","jokeId="+jokeId
                        +"&&userId="+userId+"&&zanNumAfter="+zanNumAfter);
                Message message = new Message();
                message.obj = result;
                message.what=1;
                handler.sendMessage(message);
            }
        }.start();
    }
}
