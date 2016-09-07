package andreasgift.decision_maker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MultichoiceActivity extends AppCompatActivity {

    private static final Random random = new Random();
    private static final ArrayList <String> mArrayList = new ArrayList<String>();
    private ArrayAdapter mAdapterMultichoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multichoice);

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


        //In the event the user click Multichoice Button
        final Button MultichoiceDecision = (Button) findViewById(R.id.MultichoiceDecision);
        MultichoiceDecision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultichoiceDecision.setText(randomYesNo());
            }
        });

        //In the event the user click AddChoice Button
        final Button AddChoice = (Button) findViewById(R.id.AddButton);
        AddChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MultichoiceActivity.this);
                LayoutInflater inflater = MultichoiceActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog, null);
                alertDialog.setView(dialogView);
                alertDialog.setTitle("ADD NEW CHOICE");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText dob=(EditText)dialogView.findViewById(R.id.dialogText);
                        String  str=dob.getText().toString();
                        mArrayList.add(str);
                        mAdapterMultichoice.notifyDataSetChanged();
                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

        //In the event the user first Clear Button
        final Button ClearAll = (Button) findViewById(R.id.ClearButton);
        ClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (mAdapterMultichoice != null)
            { mArrayList.clear();
                mAdapterMultichoice.notifyDataSetChanged();
            }
            }
        });

        //Show multichoice item on the listView
        mAdapterMultichoice = new ArrayAdapter(
                getApplicationContext(),R.layout.item_listview, R.id.item_listview,mArrayList);
        ListView mListView = (ListView)findViewById(R.id.MultichoiceList);
        mListView.setAdapter(mAdapterMultichoice);
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
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //This method to call random item inside MultichoiceDecision after the button is clicked
    private String randomYesNo() {
        // Select a random choice option.
        String decision = "Please add your choice";
        if (mArrayList.size()>0){
        String [] MultichoiceArray = new String[mArrayList.size()];
        MultichoiceArray = mArrayList.toArray(MultichoiceArray);
        decision = MultichoiceArray[random.nextInt(2)];}
            return decision;
    }


}