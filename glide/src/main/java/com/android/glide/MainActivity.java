package com.android.glide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    String path = "http://192.168.1.41:9999/liqin.jpg";
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = ((ImageView) findViewById(R.id.image));
    }

    public void loadClick(View view) {
        Toast.makeText(this, "dianji", Toast.LENGTH_SHORT).show();
        RequestManager with = Glide.with(this); //创建加载图片的实例
        with.load(path).into(imageView);
//        //使用Glide加载本地图片
//        File file = new File(getCacheDir() + "/image.jpg");
//        with.load(file).into(imageView);
//        //使用加载Resource 资源
//        int ic_launcher_background = R.drawable.ic_launcher_background;
//        with.load(ic_launcher_background).into(imageView);

        //加载二进制流文件



    }
}
