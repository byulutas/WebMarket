package com.ashish.mymall.ui.İstek_Listem;

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
import com.ashish.mymall.R;
import com.ashish.mymall.WishlistAdapter;

public class MyWishlistFragment extends Fragment {
    public MyWishlistFragment() {
    }

    private RecyclerView wishlistRecyclerView;
    private Dialog loadingDialog;
    public static WishlistAdapter wishlistAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_wishlist, container, false);
        wishlistRecyclerView=root.findViewById(R.id.my_wishlist_recyclerview);

        loadingDialog=new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);

        if(DBquerries.wishlistModelList.size() == 0){
            DBquerries.wishlist.clear();
            DBquerries.loadWishlist(getContext(),loadingDialog,true);
        }else {
            loadingDialog.dismiss();
        }

        wishlistAdapter=new WishlistAdapter(DBquerries.wishlistModelList,true);
        wishlistAdapter.notifyDataSetChanged();
        wishlistRecyclerView.setAdapter(wishlistAdapter);
        return root;
    }
}