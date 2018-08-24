package com.list_crudoperation.harsharaple.listview_crudoperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    EditText nametxt;
    Button addbtn,upbtn,delbtn,clearbtn;
    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = (ListView)findViewById(R.id.listview1);
        nametxt = (EditText) findViewById(R.id.txt1);
        addbtn = (Button) findViewById(R.id.btninsert);
        upbtn = (Button) findViewById(R.id.btnupdate);
        delbtn = (Button) findViewById(R.id.btndelete);
        clearbtn = (Button) findViewById(R.id.btnclear);

        //Adapter
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,names);
        lv.setAdapter(adapter);

        //Set Selected Item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    nametxt.setText(names.get(position));
            }
        });

        //Handle events

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                add();
            }
        });
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                update();
            }
        });
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                delete();
            }
        });
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                clear();
            }
        });

    }

    //Add
    private void add()
    {
        String name= nametxt.getText().toString();
        if(!name.isEmpty() && name.length()>0)
        {
            //Add
            adapter.add(name);

            //Refresh
            adapter.notifyDataSetChanged();
            nametxt.setText(" ");
            Toast.makeText(getApplicationContext(),"Added"+name,Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Nothing To Add"+name,Toast.LENGTH_SHORT).show();
        }
    }

    //Update
    private void update()
    {
        String name= nametxt.getText().toString();
        int pos = lv.getCheckedItemPosition();
        if(!name.isEmpty() && name.length()>0)
        {
            //Remove Item
            adapter.remove(names.get(pos));

            //Insert
            adapter.insert(name,pos);

            //Refresh
            adapter.notifyDataSetChanged();


            Toast.makeText(getApplicationContext(),"Updated"+name,Toast.LENGTH_SHORT).show();

        }
        else
            {
            Toast.makeText(getApplicationContext(),"Nothing To Updated"+name,Toast.LENGTH_SHORT).show();
        }
    }

    //Delete
    private void delete()
    {
        int pos = lv.getCheckedItemPosition();
        if(pos > -1)
        {
            //Remove
            adapter.remove(names.get(pos));

            //Refresh
            adapter.notifyDataSetChanged();

            nametxt.setText(" ");
            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Nothing To Delete",Toast.LENGTH_SHORT).show();
        }


    }


    //Clear
    private void clear()
    {
       adapter.clear();

    }
}
