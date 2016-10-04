package jp.techacademy.kanta.nakayama.test;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nhk2204 on 2016/10/04.
 */
public class TestApp extends Application{
    @Override
    public void onCreate(){
        super.onCreate();

        //アプリ全体を通して適用されるコンフィグを定義する。
        //コンフィグの作成（？）
        RealmConfiguration realmConfiguration=new RealmConfiguration.Builder(this).build();
        //コンフィグを削除
        //Realm.deleteRealm(realmConfiguration);
        //作成したコンフィグを適用する。
        Realm.setDefaultConfiguration(realmConfiguration);

        //この段階ではデータ（Task)は格納されていない（？）
    }
}
