package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.CommentBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class PingLunAdapter extends RecyclerView.Adapter<PingLunAdapter.ViewHolder> {

private List<CommentBean.ResultBean> lists;
private Context context;
private onItemClickLitener monItemClickLitener;

public PingLunAdapter(List<CommentBean.ResultBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pinglun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
        }

public void setonItemClickLitener(onItemClickLitener monItemClickLitener) {
        this.monItemClickLitener = monItemClickLitener;
        }

public interface onItemClickLitener {
    void onItemClick(View view, int position);
}

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        Glide.with(context).load(lists.get(position).comment_author_url).error(R.mipmap.head_portrait).into(holder.rv_touxiang);
        holder.tv_name.setText(lists.get(position).comment_author);
        holder.tv_time.setText("发表于"+lists.get(position).comment_date);
        holder.tv_content.setText(lists.get(position).comment_content);

        //委员-0     中共党员-1       预备党员-2      发展对象-3       团员-4       群众-5   其它为空
        if (lists.get(position).rudangzhibu != null) {
            if (lists.get(position).rudangzhibu.equals("0")) {

                holder.rv_touxiang.setBackgroundResource(R.mipmap.wei);
            } else if (lists.get(position).rudangzhibu.equals("1")) {
                holder.rv_touxiang.setBackgroundResource(R.mipmap.dang);
            } else if (lists.get(position).rudangzhibu.equals("2")) {
                holder.rv_touxiang.setBackgroundResource(R.mipmap.dang);
            } else if (lists.get(position).rudangzhibu.equals("3")) {
                holder.rv_touxiang.setBackgroundResource(R.mipmap.fazhangduixiang);
//                holder.tv_position.setText("发展对象");
            } else if (lists.get(position).rudangzhibu.equals("4")) {
                holder.rv_touxiang.setBackgroundResource(R.mipmap.tuan);
//                holder.tv_position.setText("团员");
            } else if (lists.get(position).rudangzhibu.equals("6")) {
                holder.rv_touxiang.setBackgroundResource(R.mipmap.shuji);
//                holder.tv_position.setText("书记");
            } else {
                holder.rv_touxiang.setBackgroundResource(R.mipmap.publics);
//                holder.tv_position.setText("群众");
            }
        } else {
            holder.rv_touxiang.setBackgroundResource(R.mipmap.publics);
//            holder.tv_position.setText("群众");

        }






        if (monItemClickLitener != null) {
            holder.rlGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monItemClickLitener.onItemClick(holder.itemView, holder.getPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rl_goods)
    RelativeLayout rlGoods;
    @BindView(R.id.rv_touxiang)
    RoundedImageView rv_touxiang;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_huifu)
    TextView tv_huifu;
    @BindView(R.id.tv_content)
    TextView tv_content;

    ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
}
