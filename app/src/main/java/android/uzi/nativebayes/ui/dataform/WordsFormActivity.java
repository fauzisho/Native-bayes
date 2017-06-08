package android.uzi.nativebayes.ui.dataform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.uzi.nativebayes.R;
import android.uzi.nativebayes.data.DataManager;
import android.uzi.nativebayes.data.model.Word;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by uzi on 09/06/17.
 * Email : fauzisholichin@gmail.com
 */

public class WordsFormActivity extends AppCompatActivity {
    private EditText etAddFeature;
    private EditText etAddDataCategory;
    private Button btnAddDataSave;
    private Word currentData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        initView();
        initData();
    }

    private void initData() {
        currentData = (Word) getIntent().getSerializableExtra(Word.TAG);
        if (currentData != null) {
            etAddFeature.setText(currentData.getFeature());
            etAddDataCategory.setText(currentData.getCategory());
        }
    }

    private void initView() {
        etAddFeature = (EditText) findViewById(R.id.etAddFeature);
        etAddDataCategory = (EditText) findViewById(R.id.etAddDataCategory);
        btnAddDataSave = (Button) findViewById(R.id.btnAddDataSave);
        btnAddDataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentData == null) {
                    saveData();
                } else {
                    updateData();
                }

            }
        });
    }

    private void updateData() {
        if (isValid()) {
            currentData.setFeature(etAddFeature.getText().toString());
            currentData.setCategory(etAddDataCategory.getText().toString());
            DataManager.getInstance().dataSave(currentData);
            Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void saveData() {
        if (isValid()) {
            Word data = new Word();
            data.setId(UUID.randomUUID().toString());
            data.setFeature(etAddFeature.getText().toString());
            data.setCategory(etAddDataCategory.getText().toString());
            DataManager.getInstance().dataSave(data);
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean isValid() {

        boolean valid = true;

        if (TextUtils.isEmpty(etAddFeature.getText().toString())) {
            Toast.makeText(this, "Required", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
}
