package com.example.khutsomatlala.hackaton_user11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class imageListAdapter extends ArrayAdapter<Place> {

    private Activity context;
    private int resource;
    private List<Place> listImage;


    public imageListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Place> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.listImage = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(resource, null);

        //referencing item_list
        final ImageView img = view.findViewById(R.id.imgPlacePic);
        TextView txtPlaceName = view.findViewById(R.id.tvPlaceName);
        TextView txt = view.findViewById(R.id.tvLike);
        View ViewName = view.findViewById(R.id.ViewName);
        TextView price = view.findViewById(R.id.tvPrice);
        ImageView ivBook = view.findViewById(R.id.ivBook);


        //Assigning data
        txtPlaceName.setText(listImage.get(position).getPlaceName());
        txt.setText(listImage.get(position).getPlaceWebsite());
        price.setText("R" + listImage.get(position).getPlaceAddress());


        Glide.with(context).load(listImage.get(position).getUrI()).into(img);


        ivBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, book_new.class);


                String price = listImage.get(position).getPlaceAddress();
                String name = listImage.get(position).getPlaceName();
                String pic = listImage.get(position).getUrI();
                String email = listImage.get(position).getEmail();

                i.putExtra("pic", pic);
                i.putExtra("name", name);
                i.putExtra("price", price);
                i.putExtra("email",email);

                context.startActivity(i);
            }
        });


        ViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getContext(), PlaceDetailsActivity.class);

                String lat = listImage.get(position).getPlaceLatitude();
                String lon = listImage.get(position).getPlaceLongitude();
                String name = listImage.get(position).getPlaceName();
                String call = listImage.get(position).getPlaceCell();
                String infor = listImage.get(position).getPlaceInfo();

                String hours = listImage.get(position).getPlaceHours();
                String pic = listImage.get(position).getUrI();
                String price = listImage.get(position).getPlaceAddress();
                String location = listImage.get(position).getPlaceWebsite();

//                String feat_1 = listImage.get(position).getFeat1();
//                String feat_2 = listImage.get(position).getFeat2();
//                String feat_3 = listImage.get(position).getFeat3();
//
//                String icon_1 = listImage.get(position).getIcon1();
//                String icon_2 = listImage.get(position).getIcon2();
//                String icon_3 = listImage.get(position).getIcon3();



                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("name", name);
                intent.putExtra("call", call);
                intent.putExtra("infor", infor);
                intent.putExtra("location", location);
                intent.putExtra("hours", hours);
                intent.putExtra("pic", pic);
                intent.putExtra("price", price);

//
//                intent.putExtra("feat_1",feat_1);
//                intent.putExtra("feat_2",feat_2);
//                intent.putExtra("feat_3",feat_3);
//
//                intent.putExtra("icon_1",icon_1);
//                intent.putExtra("icon_2",icon_2);
//                intent.putExtra("icon_3",icon_3);

                context.startActivity(intent);

            }
        });

//tabbing on the image
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                final LayoutInflater inflater = context.getLayoutInflater();

                final View dialogView = inflater.inflate(R.layout.custom_dialog, null);

                ImageView dia_pic = dialogView.findViewById(R.id.dia_pic);
                TextView dia_name = dialogView.findViewById(R.id.dia_name);
                //changed
                ImageView dia_call = dialogView.findViewById(R.id.dia_call);
              //  final ImageView dia_direction = dialogView.findViewById(R.id.dia_direction);


                Button dia_infor = dialogView.findViewById(R.id.dia_infor);
                Button dia_book = dialogView.findViewById(R.id.dia_book);

                Glide.with(context).load(listImage.get(position).getUrI()).centerCrop().into(dia_pic);

                dia_name.setText(listImage.get(position).getPlaceName());

                builder.setView(dialogView);

                final AlertDialog alertDialog = builder.create();

                dialogView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), MaxPicActivity.class);

                        String pic = listImage.get(position).getUrI();

                        intent.putExtra("max_pic", pic);

                        context.startActivity(intent);

                        alertDialog.dismiss();
                        //  dialogView.setVisibility(View.GONE);
                    }


                });
                // final AlertDialog alertDialog = builder.create();


                dia_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String call = listImage.get(position).getPlaceCell();

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + call));
                        context.startActivity(intent);

                        alertDialog.dismiss();
                    }
                });

//      dia_direction.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View view) {
//
//                        Intent intent = new Intent(getContext(), MapsActivity.class);
//
//                        String lat = listImage.get(position).getPlaceLatitude();
//                        String lon = listImage.get(position).getPlaceLongitude();
//                        String name = listImage.get(position).getPlaceName();
//
//                        intent.putExtra("lat", lat);
//                        intent.putExtra("lon", lon);
//                        intent.putExtra("name", name);
//                        context.startActivity(intent);
//
//                        alertDialog.dismiss();
//
//                    }
//                });


                dia_book.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), book_new.class);

                        String name = listImage.get(position).getPlaceName();
                        String pic = listImage.get(position).getUrI();
                        String price = listImage.get(position).getPlaceAddress();

                        intent.putExtra("pic", pic);
                        intent.putExtra("price", price);
                        context.startActivity(intent);
                        alertDialog.dismiss();
                    }
                });

//                dia_infor.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        Intent intent = new Intent(getContext(), DetailActivity.class);
//
//                        String lat = listImage.get(position).getPlaceLatitude();
//                        String lon = listImage.get(position).getPlaceLongitude();
//                        String name = listImage.get(position).getPlaceName();
//                        String call = listImage.get(position).getPlaceCell();
//                        String infor = listImage.get(position).getPlaceInfo();
//                        String address = listImage.get(position).getPlaceAddress();
//                        String hours = listImage.get(position).getPlaceHours();
//                        String pic = listImage.get(position).getUrI();
//
//                        intent.putExtra("lat", lat);
//                        intent.putExtra("lon", lon);
//                        intent.putExtra("name", name);
//                        intent.putExtra("call", call);
//                        intent.putExtra("infor", infor);
//                        intent.putExtra("address", address);
//                        intent.putExtra("hours", hours);
//                        intent.putExtra("pic", pic);
//
//                        context.startActivity(intent);
//                        alertDialog.dismiss();
//
//                    }
//                });

                alertDialog.show();

            }

        });

        return view;
    }
}
