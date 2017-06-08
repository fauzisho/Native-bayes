package android.uzi.nativebayes;

import android.app.Application;
import android.uzi.nativebayes.data.DataManager;
import android.uzi.nativebayes.data.model.Word;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by uzi on 09/06/17.
 * Email : fauzisholichin@gmail.com
 */

public class NativeBayesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initData();
    }

    private void initData() {
        //example data
        if (DataManager.getInstance().dataAll().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                Word data = new Word();
                data.setId(UUID.randomUUID().toString());
                switch (i) {
                    case 0:
                        data.setFeature("normal news");
                        data.setCategory("normal");
                        break;
                    case 1:
                        data.setFeature("good news");
                        data.setCategory("happy cheerful cheery merry joyful jovial jolly jocular gleeful carefree untroubled delighted smiling beaming grinning in good spirits in a good mood lighthearted pleased contented content satisfied gratified buoyant radiant sunny blithe joyous beatific thrilled elated exhilarated ecstatic blissful euphoric overjoyed exultant rapturous");
                        break;
                    case 2:
                        data.setFeature("bad news");
                        data.setCategory("sad sorrowful dejected depressed downcast miserable down despondent despairing disconsolate desolate wretched glum gloomy doleful dismal melancholy mournful woebegone forlorn crestfallen heartbroken inconsolable");
                        break;
                }
                DataManager.getInstance().dataSave(data);
            }
        }
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

}
