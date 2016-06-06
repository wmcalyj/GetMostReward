package nu.getmostreward;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class PoiSelectionActivity extends Activity {
    private List<String> selectedOptions = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_selection);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            switch(((CheckBox) view).getId()){
                case R.id.checkbox_movie_theater:
                    selectedOptions.add("movie_theater");
                    break;
                case R.id.checkbox_Restaurant:
                    selectedOptions.add("restaurant");
                    break;
                case R.id.checkbox_car_rental:
                    selectedOptions.add("car_rental");
                    break;
                case R.id.checkbox_department_store:
                    selectedOptions.add("department_store");
                    break;
                case R.id.checkbox_gas_station:
                    selectedOptions.add("gas_station");
                    break;
                case R.id.checkbox_grocery_or_supermarket:
                    selectedOptions.add("grocery_or_supermarket");
                    break;
                default:
                    break;

            }

        }
    }

    public void saveOptions(View v) {
        ArrayList<String> options = new ArrayList<String>();
        for (String s : selectedOptions) {
            options.add(s);
        }
        Intent intent = new Intent(this, MasterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putStringArrayListExtra("TYPES", options);
        startActivity(intent);
    }

}