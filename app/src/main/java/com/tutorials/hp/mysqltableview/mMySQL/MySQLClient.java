package com.tutorials.hp.mysqltableview.mMySQL;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.tutorials.hp.mysqltableview.TableHelper;
import com.tutorials.hp.mysqltableview.mModel.Spacecraft;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

/**
 * Created by Oclemy on 8/12/2016 for ProgrammingWizards Channel and http://www.camposha.info.
 */
public class MySQLClient {

    //RETRIEVE URLS
    private static final String DATA_RETRIEVE_URL="http://10.0.2.2/android/Aristotle/index.php";

    //INSTANCE FIELDS
    private final Context c;
    private SimpleTableDataAdapter adapter ;

    public MySQLClient(Context c) {
        this.c = c;

    }


    /*
    RETRIEVE/SELECT/REFRESH
     */
    public void retrieve(final TableView tb)
    {
        final ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

        AndroidNetworking.get(DATA_RETRIEVE_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;
                        Spacecraft s;
                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);

                                int id=jo.getInt("id");
                                String name=jo.getString("name");
                                String propellant=jo.getString("propellant");
                                String destination=jo.getString("destination");

                                s=new Spacecraft();
                                s.setId(id);
                                s.setName(name);
                                s.setPropellant(propellant);
                                s.setDestination(destination);

                                spacecrafts.add(s);
                            }

                            //SET TO TABLEVIEW
                            adapter =new SimpleTableDataAdapter(c,new TableHelper(c).returnSpaceProbesArray(spacecrafts));
                            tb.setDataAdapter(adapter);


                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });

    }



}
