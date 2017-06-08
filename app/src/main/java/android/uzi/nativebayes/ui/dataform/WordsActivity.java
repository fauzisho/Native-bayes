package android.uzi.nativebayes.ui.dataform;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.uzi.nativebayes.R;
import android.uzi.nativebayes.data.DataManager;
import android.uzi.nativebayes.data.model.Word;
import android.uzi.nativebayes.ui.main.MainActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by uzi on 09/06/17.
 * Email : fauzisholichin@gmail.com
 */

public class WordsActivity extends AppCompatActivity implements WordsAdapter.WordsAdapterListener {
    private ListView lvData;
    private Button btnAdd;
    private WordsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        initView();
    }

    private void initView() {
        lvData = (ListView) findViewById(R.id.lvData);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WordsActivity.this, WordsFormActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mAdapter = new WordsAdapter(this, DataManager.getInstance().dataAll());
        mAdapter.setListener(this);
        lvData.setAdapter(mAdapter);
    }

    @Override
    public void onLongClick(final Word item) {
        new AlertDialog.Builder(this)
                .setTitle("Remove")
                .setMessage("Remove Feature " + item.getFeature() + " ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataManager.getInstance().dataRemove(item);
                        mAdapter.remove(item);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onClick(Word item) {
        Intent intent = new Intent(WordsActivity.this, WordsFormActivity.class);
        intent.putExtra(Word.TAG, item);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(WordsActivity.this, MainActivity.class));
        finish();
    }
}
