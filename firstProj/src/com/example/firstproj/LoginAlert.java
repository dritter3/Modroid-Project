package com.example.firstproj;

import android.app.AlertDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;


public class LoginAlert extends DialogFragment  {


	public Dialog onCreateDialog(Bundle savedInstanceState) {
	
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.rejectLogin)
			.setPositiveButton(R.string.ok , new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id){
					System.out.println("pressed yes");
				}
			})
			.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					System.out.println("pressed Cancle");
				}
			});
		return builder.create();
	}
}
