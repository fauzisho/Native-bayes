package android.uzi.nativebayes.data.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by uzi on 09/06/17.
 * Email : fauzisholichin@gmail.com
 */

public class Word extends RealmObject implements Serializable {

    public static final String TAG = "WORD";

    @PrimaryKey
    private String id;
    private String feature;
    private String category;

    public String getId() {
        return id;
    }

    public String getFeature() {
        return feature;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
