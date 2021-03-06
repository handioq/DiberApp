package com.github.handioq.diberapp.ui.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.github.handioq.diberapp.R;

import java.util.Calendar;

public class CustomTimePickerDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public interface DialogListener {
        void onTimeSetClick(DialogFragment dialog, int hourOfDay, int minute);
    }

    private DialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), R.style.DialogTheme, this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        listener.onTimeSetClick(this, hourOfDay, minute);
    }

    public void attachListener(DialogListener dialogListener) {
        this.listener = dialogListener;
    }
}