package tn.iit.e_shopping.webservice;

/**
 * Created by user on 23/04/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;




/**
 * Created by user on 21/04/2016.
 */
public class InsertData extends AsyncTask<String,Void,String> {
    Context ctx;
    ProgressDialog dialog;
    String marque,prix,adresse,categorie,caracteristique;
    @Override
    protected String doInBackground(String... params) {
             String texte=params[0];

        String par[]=texte.split("/");

        String url="http://10.0.3.2/eshoping/insert.php";
        try {
            URL url_bj=new URL(url);
            HttpURLConnection htp= (HttpURLConnection) url_bj.openConnection();
            htp.setRequestMethod("POST");
            htp.setDoOutput(true);
            OutputStream osr=htp.getOutputStream();
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(osr,"UTF-8"));
            String data= URLEncoder.encode("marque", "UTF-8")+"="+URLEncoder.encode(par[0],"UTF-8")+"&"+
                    URLEncoder.encode("prix", "UTF-8")+"="+URLEncoder.encode(par[1],"UTF-8")+"&"+
            URLEncoder.encode("caracteristique", "UTF-8")+"="+URLEncoder.encode(par[2],"UTF-8")+"&"+
            URLEncoder.encode("categorie", "UTF-8")+"="+URLEncoder.encode(par[4],"UTF-8")+"&"+
            URLEncoder.encode("adresse", "UTF-8")+"="+URLEncoder.encode(par[3],"UTF-8");
            Log.e("d",data);
            bw.write(data);
            bw.flush();
            bw.close();
            InputStream IS= htp.getInputStream();
            IS.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "ok";
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public InsertData(Context ctx, String s, String toString, String string, String s1, String toString1) {
        this.ctx=ctx;


    }

    public InsertData(Context ctx) {
        this.ctx = ctx;

    }
}
