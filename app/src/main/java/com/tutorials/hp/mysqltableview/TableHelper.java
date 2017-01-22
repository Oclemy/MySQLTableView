package com.tutorials.hp.mysqltableview;

import android.content.Context;


import com.tutorials.hp.mysqltableview.mModel.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Oclemy on 12/1/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class TableHelper {

    Context c;
    private String[] spaceProbeHeaders={"Name","Propellant","Destination"};
    private String[][] spaceProbes;

    /*
    CONSTRUCTOR
     */
    public TableHelper(Context c) {
        this.c = c;
    }

    /*
    RETURN TABLE HEADERS
     */
    public String[] getSpaceProbeHeaders()
    {
        return spaceProbeHeaders;
    }


    /*
    RETURN 2D TABLE ROWS-COLUMNS DATA
     */
    public  String[][] returnSpaceProbesArray(ArrayList<Spacecraft> spacecrafts)
    {
        spaceProbes= new String[spacecrafts.size()][3];
        Spacecraft s;
        for (int i=0;i<spacecrafts.size();i++) {

             s=spacecrafts.get(i);

            spaceProbes[i][0]=s.getName();
            spaceProbes[i][1]=s.getPropellant();
            spaceProbes[i][2]=s.getDestination();
        }

        return spaceProbes;





    }
}





