package com.example.mahmoudosman.bluetoothexample1;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    BluetoothAdapter mBluetoothAdapter;
    Intent btEnablingIntent;
    int requestcodeForEnable = 1;
    Button btnOn, btnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOn = findViewById(R.id.btn_on);
        btnOff = findViewById(R.id.btn_off);
        //TODO(3) init adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btEnablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);


        bluetoothOnMethod();
        bluetoothOffMethod();


    }

    private void bluetoothOffMethod() {
    btnOff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mBluetoothAdapter.isEnabled()){
                mBluetoothAdapter.disable();
            }
        }
    });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == requestcodeForEnable) {
            if (requestCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Bluetooth is Enable", Toast.LENGTH_LONG).show();
            }else if (requestCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Bluetooth is Enabling Cancell", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void bluetoothOnMethod() {
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO(4) Check if the device support the bluetooth
                if (mBluetoothAdapter == null) {
                    // that mean device not supported bluetooth
                    Toast.makeText(getApplicationContext(), TAG + "onClick: Bluetooth Not Support on this device", Toast.LENGTH_LONG).show();
                } else {
                    //TODO(5) Check if the bluetooth is Enable or not
                    if (!mBluetoothAdapter.isEnabled()) {
                        // blutooth is disable
                        startActivityForResult(btEnablingIntent, requestcodeForEnable);
                    }

                }
            }
        });
    }

}
