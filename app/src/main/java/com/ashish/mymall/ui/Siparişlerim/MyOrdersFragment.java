package com.ashish.mymall.ui.Siparişlerim;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.mymall.DBquerries;
import com.ashish.mymall.MyOrderAdapter;
import com.ashish.mymall.R;

public class MyOrdersFragment extends Fragment {
    public MyOrdersFragment() {
    }

    private Dialog loadingDialog;
    public static MyOrderAdapter myOrderAdapter;
    private RecyclerView myordersRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_orders, container, false);
        myordersRecyclerView=root.findViewById(R.id.my_orders_recyclerview);

        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        myordersRecyclerView.setLayoutManager(linearLayoutManager);

        myOrderAdapter=new MyOrderAdapter(DBquerries.myOrderItemModelList,loadingDialog);
        myordersRecyclerView.setAdapter(myOrderAdapter);

        DBquerries.loadOrders(getContext(),myOrderAdapter,loadingDialog);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        myOrderAdapter.notifyDataSetChanged();
    }
}