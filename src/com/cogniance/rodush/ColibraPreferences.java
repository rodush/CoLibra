package com.cogniance.rodush;

import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultString;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface ColibraPreferences {
	
	@DefaultString("list")
	String viewMode();
	
	@DefaultString("default")
	String theme();
}
