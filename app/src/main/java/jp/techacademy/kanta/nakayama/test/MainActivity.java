package jp.techacademy.kanta.nakayama.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {
    //Realmの設定
    //===========================================================================================
    //Realmを定義
    private Realm mRealm;
    //Realmデータベースから取得した結果を保持する変数<>内は保持するデータの型（？）
    private RealmResults<Task> mTaskRealmResults;
    //Realmデータベースに追加や削除など変化があった際に呼ばれるメソッド
    private RealmChangeListener mRealmListener=new RealmChangeListener(){
        @Override
        public void onChange() {
            reloadListView();
        }
    };
    //===========================================================================================

    //MainActivityのListView
    private ListView mListView;
    private TaskAdapter mTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Realmの設定（画面内）
        //===========================================================================================
        //アプリ内のRealmの設定を取得（？）（TaskAppクラスで設定した奴（？））
        mRealm=Realm.getDefaultInstance();
        //Realmデータベース内にあるTaskクラスのものをすべて探し出してmTaskRealmResultsに格納
        mTaskRealmResults=mRealm.where(Task.class).findAll();
        //mTaskResultsに格納されたデータをidでソートする。
        mTaskRealmResults.sort("id", Sort.DESCENDING);
        //Realmにデータが入ったことを確認する（？）
        mRealm.addChangeListener(mRealmListener);
        //===========================================================================================

        //ListViewの設定
        mTaskAdapter=new TaskAdapter(MainActivity.this);
        mListView=(ListView)findViewById(R.id.listView1);

        //ListViewをタップしたときの処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //入力・編集画面に推移
            }
        });

        //ListViewを長押ししたときの処理
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //タスクの削除
                return false;
            }
        });

        if(mTaskRealmResults.size()==0){
            addTaskForTest();
        }

        //ListViewの更新。
        reloadListView();
    }

    private void reloadListView(){

        //Taskを格納するArrayList
        ArrayList<Task> taskArrayList=new ArrayList<>();

        for(int i=0;i<mTaskRealmResults.size();i++){
            Task task=new Task();

            //taskの中にmTaskRealmResultsから拾い出したデータを格納する
            task.setId(mTaskRealmResults.get(i).getId());
            task.setTaskName1(mTaskRealmResults.get(i).getTaskName1());
            task.setTaskName2(mTaskRealmResults.get(i).getTaskName2());

            //完成したtaskをtaskArrayListに格納する
            taskArrayList.add(task);
        }

        mTaskAdapter.setTaskArrayList(taskArrayList);
        mListView.setAdapter(mTaskAdapter);
        mTaskAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        //Realmを閉じる
        mRealm.close();
    }

    private void addTaskForTest(){
        Task task=new Task();
        task.setId(0);
        task.setTaskName1("NAKAYAMA");
        task.setTaskName2("KANTA");

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(task);
        mRealm.commitTransaction();

        task.setId(1);
        task.setTaskName1("NAKAYAMA");
        task.setTaskName2("HANAKO");

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(task);
        mRealm.commitTransaction();
    }
}
