package com.cogniance.rodush;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

import android.os.Bundle;
import android.preference.PreferenceActivity;

@EActivity
public class ColibraPreferencesActivity extends PreferenceActivity {
	
	public void onCreate(Bundle prevState){
		super.onCreate(prevState);
		addPreferencesFromResource(R.xml.preferences);
	}	
		
}
