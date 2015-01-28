package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
    int mem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }

    public void recalculate() {
        //Calculate the expression and display the output

        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");
        int result=0,left,right;
        String op="";

        for(int i=0;i<tokens.length;i++){
            if(tokens[i].equals("+")||tokens[i].equals("-")||tokens[i].equals("*")||tokens[i].equals("/")){
                op = tokens[i];
            }else{
                if(op.equals("+")){
                    result = result + Integer.parseInt(tokens[i]);
                }else if(op.equals("-")){
                    result = result - Integer.parseInt(tokens[i]);
                }else if(op.equals("*")){
                    result = result * Integer.parseInt(tokens[i]);
                }else if(op.equals("/")){
                    result = result / Integer.parseInt(tokens[i]);
                }else{
                    result = Integer.parseInt(tokens[i]);
                }
            }
        }

        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(Integer.toString(result));
    }

    public void equalClicked(View v) {

        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String ans = tvAns.getText().toString();
        expr = new StringBuffer(ans);
        updateExprDisplay();
        int result=0;
        tvAns.setText(Integer.toString(result));
    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        //IF the last character in expr is not an operator and expr is not "",
        String oc = ((TextView) v).getText().toString();
        char oc_c = oc.charAt(0);
        System.out.println(oc_c);
        String expr_str = expr.toString();
        int x = expr_str.length() - 1;
        char last = expr_str.charAt(x);
        if (!expr_str.isEmpty()) {

            if (last != '+' && last != '-' && last != '*' && last != '/') {
                expr.append(oc);
                updateExprDisplay();
            }
        }
    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        updateExprDisplay();
        String expr_str = expr.toString();
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(expr_str);
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            updateExprDisplay();
            String expr_str = expr.toString();
            TextView tvAns = (TextView)findViewById(R.id.tvAns);
            tvAns.setText(expr_str);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Mem_expr(View v) {

        int id = v.getId();
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        int result = Integer.parseInt(tvAns.getText().toString());
        if(id == R.id.madd){
            mem = mem+result;
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Mem = "+mem, Toast.LENGTH_SHORT);
            t.show();
        }else if(id == R.id.msub){
            mem = mem-result;
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Mem = "+mem, Toast.LENGTH_SHORT);
            t.show();
        }else if(id == R.id.mr){
            expr = new StringBuffer(Integer.toString(mem));
            result = mem;
            updateExprDisplay();
            tvAns.setText(Integer.toString(result));
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Current Mem", Toast.LENGTH_SHORT);
            t.show();
        }else if(id == R.id.mc){
            mem = 0;
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Mem = "+mem, Toast.LENGTH_SHORT);
            t.show();
        }


    }
}
