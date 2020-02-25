package com.tqc.gdd02;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GDD02 extends Activity implements View.OnClickListener {

    Button btnSubmit;
    EditText edInputNum;
    TextView tvRespond;
    //答案
    String luckyNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnSubmit = (Button) findViewById(R.id.submit);
        edInputNum = (EditText) findViewById(R.id.input_number);
        tvRespond = (TextView) findViewById(R.id.respond);

        btnSubmit.setOnClickListener(this);
        luckyNum = generateLuckyNum();
    }

    private String generateLuckyNum() {
        int num = 0;
        String strNum = "";
        do {
            num = (int) (Math.random() * 1000);
            strNum = String.format("%03d", num);
        } while (isLegal(strNum) == false);
        return strNum;
    }

    //判斷輸入數字符合規則
    private boolean isLegal(String num) {
        // TO DO
        if (num == null || num.length() < 3) {
            return false;
        } else if (num.charAt(0) == num.charAt(1) || num.charAt(1) == num.charAt(2) || num.charAt(0) == num.charAt(2)) {
            return false;
        }
        return true;
    }

    // TO DO
    private int countA(String inputNum, String luckyNum) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (inputNum.charAt(i) == luckyNum.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private int countB(String inputNum, String luckyNum) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && inputNum.charAt(i) == luckyNum.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    //按鈕事件
    @Override
    public void onClick(View v) {
        String userNum = edInputNum.getText().toString();
        // TO DO
        int Acount = countA(userNum, luckyNum);
        int Bcount = countB(userNum, luckyNum);
        if (isLegal(userNum) == false) {
            edInputNum.setText("");
            tvRespond.setText("輸入錯誤，請輸入三個不重複的數字");
        } else {
            if (Acount == 3) {
                edInputNum.setText(luckyNum);
                tvRespond.setText("INPUT的數字:" + userNum + "  恭喜你答對囉~答案:" + luckyNum );
            } else {
                edInputNum.setText("");
                tvRespond.setText(String.format("INPUT的數字:%s=%dA%dB", userNum, Acount, Bcount));
            }
        }
    }
}

