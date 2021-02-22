package cn.edu.peaceofmind.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.activity.NewsWebActivity;
import cn.edu.peaceofmind.entity.NewsInfo;

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.ViewHolder>  {

    private Context context;
    private List<NewsInfo> newsInfos;
    static final class ViewHolder extends RecyclerView.ViewHolder {//定义用于缓存item布局中子控件对象的类
        CardView cardView;
        TextView tvUserName;
        TextView tvTitle;

        ImageView ivPraise;

//        TextView tvSummary;
//        ImageView ivImage;
        TextView tvPraise;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            ivPraise = itemView.findViewById(R.id.iv_praise);
//            tvSummary = itemView.findViewById(R.id.tv_summary);
//            ivImage = itemView.findViewById(R.id.iv_image);
            tvPraise = itemView.findViewById(R.id.tv_praise);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    public NewsAdapter(Context context, List<NewsInfo> newsInfos){
        this.context = context;
        this.newsInfos = newsInfos;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_card_view_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsInfo newsInfo = newsInfos.get(position);
        holder.tvUserName.setText(newsInfo.getAuther_name());
        holder.tvTitle.setText(newsInfo.getTitle());
        holder.tvPraise.setText(newsInfo.getRead_zan()+"");
        //点击事件
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsWebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("newsInfo",newsInfo);
                intent.putExtras(bundle);
                context.startActivity(intent);
//                Uri uri = Uri.parse("https://mini.eastday.com//mobile//210218200939611795237.html");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(uri);
//                context.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return newsInfos.size();
    }
}
