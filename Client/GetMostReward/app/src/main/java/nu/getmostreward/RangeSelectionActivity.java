package nu.getmostreward;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by mengchaowang on 5/17/16.
 */
public class RangeSelectionActivity extends Activity {

    private RadioGroup rangeGroup;
    private RadioButton rangeButton;
    private Button ok;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.range_selection);
        rangeGroup = (RadioGroup) findViewById(R.id.range_selection_groups);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        ok = (Button) findViewById(R.id.range_selection_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rangeGroup.getCheckedRadioButtonId();
                if (id == -1) {
                    //no radio buttons are checked
                } else {
                    //one of the radio buttons is
                    RadioButton selected = (RadioButton) findViewById(id);
                    System.out.println(selected.getText().toString());
                }
            }
        });


    }
}
