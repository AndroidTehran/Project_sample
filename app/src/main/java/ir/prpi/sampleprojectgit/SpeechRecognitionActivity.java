package ir.prpi.sampleprojectgit;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpeechRecognitionActivity extends AppCompatActivity {

    ImageButton btnSpeak;
    TextView txtSpeak;
    final int REQUEST_CODE_SPEECH = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognition);

        getSupportActionBar().hide();

        txtSpeak = (TextView) findViewById(R.id.txt_speak);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakButtonPressed();
            }
        });
    }

    public void speakButtonPressed(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"fa-IR");
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.say_something));

        try{
            startActivityForResult(i,REQUEST_CODE_SPEECH);
        }catch (Exception e){
            Toast.makeText(this, R.string.not_supported,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("my log", requestCode + "");
        switch (requestCode){
            case REQUEST_CODE_SPEECH:
                Log.w("my log", resultCode + "" + RESULT_OK);
                Log.w("my log", (data == null) + "");
                if(resultCode == RESULT_OK && data != null){
                    Log.w("my log","inja");
                    ArrayList<String> s = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    Log.w("my log", s.size() + "");
                    txtSpeak.setText(s.get(0));


                    Log.w("my log", s.get(0) );
                }

                break;
        }


    }
}










