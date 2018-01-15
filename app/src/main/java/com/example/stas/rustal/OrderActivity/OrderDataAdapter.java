package com.example.stas.rustal.OrderActivity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stas.rustal.OrderModel.OrderListModel;
import com.example.stas.rustal.R;

import java.util.ArrayList;
import java.util.Locale;

public class OrderDataAdapter extends RecyclerView.Adapter<OrderDataAdapter.ViewHolder> {

    private OrderListModel orderList;
    private ArrayList<String> alValue;
    private ArrayList<String> alProp;

    OrderDataAdapter(OrderListModel orderList) {
        this.orderList = orderList;

        alValue = new ArrayList<>();
        alProp = new ArrayList<>();

        if(orderList.getMessage().getVALUE()!=null) {
            alValue.add((String) orderList.getMessage().getVALUE());
            alProp.add(orderList.getMessage().getNAME());}

        if(orderList.getAddress().getVALUE()!=null) {
            alValue.add(orderList.getAddressLoading().getVALUE());
            alProp.add(orderList.getAddressLoading().getNAME());}

        if(orderList.getAddress().getVALUE()!=null) {
            alValue.add(orderList.getAddress().getVALUE());
            alProp.add(orderList.getAddress().getNAME());}

        if(orderList.getUserContactPhone().getVALUE()!=null) {
            alValue.add(orderList.getUserContactPhone().getVALUE().toString());
            alProp.add(orderList.getUserContactPhone().getNAME());}

        if(orderList.getUserContactInfo().getVALUE()!=null) {
            alValue.add(orderList.getUserContactInfo().getVALUE().toString());
            alProp.add(orderList.getUserContactInfo().getNAME());}

        if(orderList.getTime().getVALUE()!=null) {
            alValue.add(orderList.getTime().getVALUE());
            alProp.add(orderList.getTime().getNAME());}

        if(orderList.getTimeEnd().getVALUE()!=null) {
            alValue.add(orderList.getTimeEnd().getVALUE());
            alProp.add(orderList.getTimeEnd().getNAME());}

        if(orderList.getLiftUp().getVALUE()!=null) {
            alValue.add((String) orderList.getLiftUp().getVALUE());
            alProp.add(orderList.getLiftUp().getNAME());}

        if(orderList.getGetPrice().getVALUE()!=null) {
            alValue.add((String) orderList.getGetPrice().getVALUE());
            alProp.add(orderList.getGetPrice().getNAME());}

        if(orderList.getPaymomentForm().getVALUE()!=null) {
            alValue.add((String) orderList.getPaymomentForm().getVALUE());
            alProp.add(orderList.getPaymomentForm().getNAME());}

        if(orderList.getShippingPriority().getVALUE()!=null) {
            alValue.add(orderList.getShippingPriority().getVALUE());
            alProp.add(orderList.getShippingPriority().getNAME());}
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_properties_card, viewGroup, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

        if (alValue.get(i) != null) viewHolder.tv_status.setText(alValue.get(i));
        if (alValue.get(i) != null) viewHolder.tv_number.setText(alProp.get(i));


        if(orderList.getUserContactPhone().getVALUE()!=null){
        if(orderList.getUserContactPhone().getVALUE().toString().equals(alValue.get(i))) {

            viewHolder.tv_status.setLinksClickable(true);
            viewHolder.tv_status.setClickable(true);
            viewHolder.tv_status.setLinkTextColor(Color.BLUE);
            viewHolder.tv_status.setAutoLinkMask(Linkify.PHONE_NUMBERS);
            viewHolder.tv_status.setMovementMethod(LinkMovementMethod.getInstance());


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.tv_status.setText(PhoneNumberUtils.formatNumber(alValue.get(i), Locale.getDefault().getCountry()));
            } else {
                viewHolder.tv_status.setText(PhoneNumberUtils.formatNumber(alValue.get(i))); //Deprecated method
            }
            //System.out.println("FORMATTED " + PhoneNumberUtils.formatNumber(viewHolder.tv_status.getText().toString()));
            //viewHolder.tv_status.setText(PhoneNumberUtils.formatNumber(viewHolder.tv_status.getText().toString()));

        }}
    }


    @Override
    public int getItemCount() {

        //System.out.println("COUNT IS   " + alValue.size());
        return alValue.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_number,tv_status;
        ViewHolder(View view) {
            super(view);

            tv_number = view.findViewById(R.id.order_property_name_tv);
            tv_status = view.findViewById(R.id.order_property_tv);

        }
    }

}
