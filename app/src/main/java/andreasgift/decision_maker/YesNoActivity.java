package andreasgift.decision_maker;

        import android.app.Activity;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.Random;

        import android.app.Activity;
        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.app.TimePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.TimePicker;


public class YesNoActivity extends AppCompatActivity {

    private static final Random random = new Random();
    private static final String[] YesNoString = {"YES","NO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yes_no);

        //set toolbar to the UI
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
        }

        // to set the status bar color (on Android Lolipop and above) as per app theme
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        //In the event the user first click
        final Button YesNoDecision = (Button) findViewById(R.id.YesNoDecision);
        YesNoDecision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YesNoDecision.setText(randomYesNo());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.homepage:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                super.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //This method to call random YES/NO decision after the button is clicked
    private String randomYesNo() {
        // Select a random Yes/No.
        String decision = YesNoString[random.nextInt(2)];
    return decision;
    }

}