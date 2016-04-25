package tn.iit.e_shopping.webservice;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import tn.iit.e_shopping.database.DataBaseHelper;

/**
 * Created by user on 21/04/2016.
 */
public class GetData extends AsyncTask<Void,Void,String> {
    Context c;

    public GetData(Context c) {
        this.c = c;
    }

    String jsonstring = null;
    String url = "http://10.0.3.2/eshoping/select.php";
    TextView v;
    StringBuilder sb = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        Toast.makeText(c, "Remplissage de liste avec succes", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... params) {
        URL url_bj = null;


        try {

            url_bj = new URL(url);
            HttpURLConnection htp = (HttpURLConnection) url_bj.openConnection();
            InputStream is = htp.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            while ((jsonstring = br.readLine()) != null) {
                sb.append(jsonstring + "\n");
            }

            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parsejson(sb.toString());
        return sb.toString();
    }

    private void parsejson(String s) {


        JSONObject jsonResponse;

        try {

            /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
            jsonResponse = new JSONObject(s);

            /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
            /*******  Returns null otherwise.  *******/
            JSONArray jsonMainNode = jsonResponse.optJSONArray("server_response");

            /*********** Process each JSON Node ************/

            int lengthJsonArr = jsonMainNode.length();
            String marque,prix,caracteristique,adresse,categorie = "";
            DataBaseHelper  dbh=new DataBaseHelper(c);
            for (int i = 0; i < lengthJsonArr; i++) {
                /****** Get Object for each JSON node.***********/
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                /******* Fetch node values **********/
                marque = jsonChildNode.optString("marque").toString();
                prix = jsonChildNode.optString("prix").toString();
                caracteristique = jsonChildNode.optString("caracteristique").toString();
                adresse = jsonChildNode.optString("adresse").toString();
                categorie = jsonChildNode.optString("categorie").toString();

                dbh.insertDB(marque,caracteristique,prix,adresse,categorie,"df");


                Log.i("JSON parse", marque+""+prix+""+caracteristique+""+adresse+""+categorie);
            }

            /************ Show Output on screen/activity **********/

            //  v.setText( texte );

        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
}
