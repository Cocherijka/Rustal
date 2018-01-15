package com.example.stas.rustal.OrderListActivity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stas.rustal.OrderActivity.OrderActivity;
import com.example.stas.rustal.OrderModel.OrderListModel;
import com.example.stas.rustal.R;

import java.util.ArrayList;

public class OrderListDataAdapter extends RecyclerView.Adapter<OrderListDataAdapter.ViewHolder> {
    private ArrayList<OrderListModel> orderList;


    OrderListDataAdapter(ArrayList<OrderListModel> orderList) {
        this.orderList = orderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final OrderListDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_number.setText(orderList.get(i).getID());
        viewHolder.tv_status.setText(orderList.get(i).getStatus().getVALUEENUM());
        viewHolder.tv_address.setText(orderList.get(i).getAddress().getVALUE());


        viewHolder.itemView.setOnClickListener(view -> view.getContext().startActivity(new Intent(view.getContext(), OrderActivity.class).putExtra("id", viewHolder.tv_number.getText())));
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_number,tv_status,tv_address;
        ViewHolder(View view) {
            super(view);

            tv_number = view.findViewById(R.id.cardview_tv_number);
            tv_status = view.findViewById(R.id.cardview_tv_status);
            tv_address = view.findViewById(R.id.cardview_tv_address);

        }
    }
}
