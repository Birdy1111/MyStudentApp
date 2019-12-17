package com.example.z.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Info_Search extends AppCompatActivity implements View.OnClickListener  {
    private Button btn_return;
    private ListView lv_searchlist;
    private TextView tv_searchText;
    private TextView tv_searchSum;
    IBop dbOperate=new IBop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_search);

        lv_searchlist=(ListView)findViewById(R.id.lv_searchlist);
        tv_searchText=(TextView)findViewById(R.id.tv_searchText);
        tv_searchSum=(TextView)findViewById(R.id.tv_searchSum);
        btn_return=(Button)findViewById(R.id.btn_return);
        btn_return.setOnClickListener(this);
        dbOperate.test(this);
        Bundle bundle=getIntent().getExtras();
        String searchKey=bundle.getString("searchKey");
        List<Info> searchList= dbOperate.searchByAll(searchKey);

        tv_searchText.setText(searchKey);
        tv_searchSum.setText(String.valueOf(searchList.size()+"条信息"));
        if (!searchList.isEmpty()){
            Info_Adapter myAdapter=new Info_Adapter(this,searchList);
            lv_searchlist.setAdapter(myAdapter);
        }
        else
        {
            Toast.makeText(this,"无结果",Toast.LENGTH_SHORT);
        }
    }

    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(this,Info_List.class);
        startActivity(intent);
    }
}
