package appoop.com.appoop.altro;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import appoop.com.appoop.Activity.AddRilevamento;
import appoop.com.appoop.Activity.Analisi;
import appoop.com.appoop.Activity.ModifySerra;
import appoop.com.appoop.Activity.aggiungi;


public  class DatePickerFragment extends DialogFragment   implements DatePickerDialog.OnDateSetListener {

    Date data;
    //permette di creare una finestra per selezionare la data
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        month=month+1;
        String s=""+day+'/'+month+'/'+year;
        if(aggiungi.isDateAggiungi){
            aggiungi.isDateAggiungi=false;
            aggiungi.setData(s);
        }

        if(AddRilevamento.isDateRilevamento){

            AddRilevamento.isDateRilevamento=false;
            AddRilevamento.setData (s);
        }
        if(ModifySerra.isDateModify){
            ModifySerra.isDateModify=false;
            ModifySerra.setData(s);
        }

    }

}
