package com.example.widget_7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupDrink;
    private RadioButton radioButtonDrink1,radioButtonDrink2,radioButtonDrink3;
    private int drinkFlag;
    private int drinkPrice;
    private CheckBox checkBoxWaffle,checkBoxPancake,checkBoxMuffin;
    private EditText editTextWaffle,editTextPancake,editTextMuffin;
    private Button buttonCancel,buttonCheckout;
    private TextView textViewOrder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupDrink = (RadioGroup) findViewById(R.id.radioGroup_drink);
        radioButtonDrink1 = (RadioButton) findViewById(R.id.radioButton_drink1);
        radioButtonDrink2 = (RadioButton) findViewById(R.id.radioButton_drink2);
        radioButtonDrink3 = (RadioButton) findViewById(R.id.radioButton_drink3);

        drinkFlag=0;
        drinkPrice=0;
        radioGroupDrink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_drink1:
                        drinkFlag=1;
                        drinkPrice=100;
                        break;

                    case R.id.radioButton_drink2:
                        drinkFlag=2;
                        drinkPrice=80;
                        break;

                    case R.id.radioButton_drink3:
                        drinkFlag=3;
                        drinkPrice=50;
                        break;
                }
            }
        });

        checkBoxWaffle = (CheckBox) findViewById(R.id.checkBox_waffle);
        checkBoxPancake = (CheckBox) findViewById(R.id.checkBox_pancake);
        checkBoxMuffin = (CheckBox) findViewById(R.id.checkBox_muffin);

        editTextWaffle = (EditText) findViewById(R.id.editText_waffle);
        editTextPancake= (EditText) findViewById(R.id.editText_pancake);
        editTextMuffin = (EditText) findViewById(R.id.editText_muffin);

        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonCheckout = (Button) findViewById(R.id.button_checkout);
        textViewOrder = (TextView) findViewById(R.id.textView_order);
        textViewOrder.setText("");

        buttonCancel.setOnClickListener(new MyButton());
        buttonCheckout.setOnClickListener(new MyButton());
    }

    private class MyButton implements View.OnClickListener {
        private String number;

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_cancel:
                    radioGroupDrink.clearCheck();
                    drinkFlag=0;
                    drinkPrice=0;
                    checkBoxMuffin.setChecked(false);
                    checkBoxPancake.setChecked(false);
                    checkBoxWaffle.setChecked(false);
                    textViewOrder.setText("");
                    editTextMuffin.setText("");
                    editTextPancake.setText("");
                    editTextWaffle.setText("");
                    break;

                case R.id.button_checkout:
                    textViewOrder.setText("Your order is : \n");
                    int sum = 0;
                    if(drinkFlag==0){
                        textViewOrder.append("Please order drink. \n");
                    }else if(drinkFlag==1){
                        textViewOrder.append("Drink: "+radioButtonDrink1.getText()+"\n");
                        sum +=100;
                    }else if(drinkFlag==2){
                        textViewOrder.append("Drink: "+radioButtonDrink2.getText()+"\n");
                        sum +=80;
                    }else if(drinkFlag==3){
                        textViewOrder.append("Drink: "+radioButtonDrink2.getText()+"\n");
                        sum+=50;
                    }

                    textViewOrder.append("Dessert :\n");
                    if(checkBoxWaffle.isChecked()){
                        number = editTextWaffle.getText().toString();
                        if(number.length()!=0){
                            textViewOrder.append("Waffle, $100 x " + number + " , ");
                            sum+= 100 * Integer.parseInt(number);
                        }
                    }

                    if(checkBoxPancake.isChecked()){
                        number = editTextWaffle.getText().toString();
                        if(number.length()!=0){
                            textViewOrder.append("Pancake, $120 x " + number );
                            sum += 120 * Integer.parseInt(number);
                        }
                    }

                    if(checkBoxMuffin.isChecked()){
                        number = editTextMuffin.getText().toString();
                        if(number.length()!=0){
                            textViewOrder.append("Muffin, $80 x " + number );
                            sum+=80 * Integer.parseInt(number);
                        }
                    }

                    textViewOrder.append("\nThe total fee is :$"+sum);
                    break;
            }
        }
    }
}