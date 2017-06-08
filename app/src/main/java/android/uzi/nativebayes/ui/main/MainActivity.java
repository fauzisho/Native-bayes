package android.uzi.nativebayes.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.uzi.nativebayes.R;
import android.uzi.nativebayes.bayes.BayesClassifier;
import android.uzi.nativebayes.bayes.Classifier;
import android.uzi.nativebayes.data.DataManager;
import android.uzi.nativebayes.data.model.Word;
import android.uzi.nativebayes.ui.dataform.WordsActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etWordsInput;
    private Button btnOk;
    private Button btnFormWords;
    private TextView tvResult;
    private ArrayList<Word> words;
    private List<Word> data;
    final Classifier<String, String> bayes = new BayesClassifier<String, String>();
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btnFormWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etWordsInput.getText().equals("")) {
                    String[] text = etWordsInput.getText().toString().split("//s");
                    result = bayes.classify(Arrays.asList(text)).getCategory();
                    tvResult.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "Input your sentence..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        etWordsInput = (EditText) findViewById(R.id.etWordsInput);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnFormWords = (Button) findViewById(R.id.btnFormWords);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        words = new ArrayList<>();
        data = DataManager.getInstance().dataAll();
        for (int i = 0; i < data.size(); i++) {
            String text0 = data.get(i).getCategory();
            String[] text = text0.split("\\s");

            bayes.learn(data.get(i).getFeature(), Arrays.asList(text));
        }
    }
}
