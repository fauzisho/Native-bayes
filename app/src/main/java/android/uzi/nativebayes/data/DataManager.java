package android.uzi.nativebayes.data;

import android.uzi.nativebayes.data.model.Word;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by uzi on 09/06/17.
 * Email : fauzisholichin@gmail.com
 */

public class DataManager {
    private static final DataManager ourInstance = new DataManager();

    public DataManager() {
    }

    public static DataManager getInstance() {
        return ourInstance;
    }


    public List<Word> dataAll() {
        RealmResults<Word> realmQuery = Realm.getDefaultInstance()
                .where(Word.class)
                .findAllSorted("feature");
        return Realm.getDefaultInstance().copyFromRealm(realmQuery);
    }

    public List<Word> dataFilterFeature(String penyakit) {
        RealmResults<Word> realmQuery = Realm.getDefaultInstance()
                .where(Word.class)
                .equalTo("feature", penyakit)
                .findAll();
        return Realm.getDefaultInstance().copyFromRealm(realmQuery);

    }

    public List<Word> dataFilterCategory(String word) {
        RealmResults<Word> realmQuery = Realm.getDefaultInstance()
                .where(Word.class)
                .equalTo("category", word)
                .findAll();
        return Realm.getDefaultInstance().copyFromRealm(realmQuery);

    }

    public void dataSave(final Word word) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(word);
            }
        });
    }

    public void dataRemove(final Word item) {
        final Word contactQuery = Realm.getDefaultInstance()
                .where(Word.class)
                .equalTo("id", item.getId())
                .findFirst();
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                contactQuery.deleteFromRealm();
            }
        });
    }
}
