package br.com.fhp.equacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  final  static  String EXTRA_MESSAGE =  "com.mycompany.myfirstapp.MESSAGE" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void calcular (View view) {
        Intent intent = new Intent(this, DisplayMessage.class);
        EditText campoA = (EditText) findViewById(R.id.editTextA);
        EditText campoB = (EditText) findViewById(R.id.editTextB);
        EditText campoC = (EditText) findViewById(R.id.editTextC);

        Double a = Double.parseDouble(campoA.getText().toString());
        Double b = Double.parseDouble(campoB.getText().toString());
        Double c = Double.parseDouble(campoC.getText().toString());
        Double delta = this.delta(a, b, c);

        String message;

        if (delta < 0) {
            message = "Delta = " + delta + "\n\nA Equação não possui Raizes Reais";
        } else {
            List<Double> listaX = this.formulaDeBhaskara(a, b, delta);
            message = "Delta = " + delta + "\n xI = " + listaX.get(0) + "\n xII = " + listaX.get(1);
        }

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity ( intent );
    }

    public List<Double> formulaDeBhaskara (Double a, Double b, Double delta) {
        List<Double> xIandXII = new ArrayList<Double>();
        b = b * -1;
        Double x = (b + Math.sqrt(delta)) / 2 * a;
        xIandXII.add(x);
        x = (b - Math.sqrt(delta)) / 2 * a;
        xIandXII.add(x);
        return xIandXII;
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return Double
     */
    private Double delta (Double a, Double b, Double c) {
        return  (b * b) - 4 * a * c;
    }
}
