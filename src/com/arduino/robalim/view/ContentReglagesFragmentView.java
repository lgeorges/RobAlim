package com.arduino.robalim.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.arduino.robalim.model.ActionModel;
import com.arduino.robalim.model.AlimentEnum;
import com.arduino.robalim.model.AvancementEnum;
import com.arduino.robalim.model.DistanceEnum;
import com.arduino.robalim.model.FinActionEnum;
import com.arduino.robalim.model.PassageModel;
import com.arduino.robalim.model.VitesseEnum;
import com.arduino.robalim.view.ReglagesFragmentView.DisplayContentListener;
import com.example.robalim.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class ContentReglagesFragmentView extends Fragment {
	private RobAlimInterfaceIn robot_in;
	private RobAlimInterfaceOut robot_out; 
	private Activity main_activity;
	private ActionModel action;
	private Spinner avancement_value;
	private Spinner fin_action_value;
	private Spinner distance_value;
	private TableLayout passages_container;
	private ReglagesFragmentView fragment_parent;
	private boolean view_created =false;
	
	public ContentReglagesFragmentView(){
		super();
	}
	
	public ContentReglagesFragmentView(ActionModel action, ReglagesFragmentView f){
		super();
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        fragment_parent = f;
        this.action=action;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reglages_content_view, container, false);

        main_activity=getActivity();
        passages_container = (TableLayout)rootView.findViewById(R.id.passages_container);
        avancement_value =(Spinner)rootView.findViewById(R.id.avancement_value);
        fin_action_value =(Spinner)rootView.findViewById(R.id.fin_action_value);

        distance_value=(Spinner)rootView.findViewById(R.id.distance_value);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(main_activity, android.R.layout.simple_spinner_item, DistanceEnum.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distance_value.setAdapter(adapter);
        distance_value.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				action.setDistance(Short.parseShort(DistanceEnum.getList()[position]));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("ContentReglagesFragmentView", "nothing selected");
			}
        	
		});
        
        adapter = new ArrayAdapter<CharSequence>(main_activity, android.R.layout.simple_spinner_item, AvancementEnum.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        avancement_value.setAdapter(adapter);
        avancement_value.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				AvancementEnum a = AvancementEnum.values()[position];
				action.setAvancement(a);
				if(a == AvancementEnum.ULTRASON_GAUCHE){
					for(int i=0;i<fragment_parent.getNbPassages(); i++ ){
						action.getPassages().add(new PassageModel());
						Log.d("ContentReglagesFragmentView", ""+i);
					}
					updateValues();
				}
						
				Log.d("ContentReglagesFragmentView", ""+fragment_parent.getNbPassages()+" "+a);
				displayPassagesView();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("ContentReglagesFragmentView", "nothing selected");
			}
        	
		});
        
        adapter = new ArrayAdapter<CharSequence>(main_activity, android.R.layout.simple_spinner_item, FinActionEnum.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fin_action_value.setAdapter(adapter);
        fin_action_value.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				FinActionEnum fa = FinActionEnum.values()[position];
				action.setFinAction(fa);
				
				Log.d("ContentReglagesFragmentView", ""+fa);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("ContentReglagesFragmentView", "nothing selected");
			}
        	
		});

        updateValues();
        
        view_created=true;
        return rootView;
	}

	private void updateValues(){
		avancement_value.setSelection(action.getAvancement().ordinal());
        fin_action_value.setSelection(action.getFinAction().ordinal());
        distance_value.setSelection(DistanceEnum.getOrdinal(action.getDistance()));
        
		for(PassageModel p : action.getPassages())
			passages_container.addView(getPassageRow(p));
	}

	public void show(FragmentManager manager){
		manager.beginTransaction().show(this).commit();
	}
	
	public void hide(FragmentManager manager){
		manager.beginTransaction().hide(this).commit();
		
	}
	
	private TableRow getPassageRow(PassageModel p){
		TableRow result = (TableRow)LayoutInflater.from(main_activity).inflate(R.layout.passages_content_view, null);
		final int index = action.getPassages().indexOf(p);
		TextView num  =(TextView)result.findViewById(R.id.num_passage_value);
		num.setText("passage n°"+(index));       
		
		Spinner aliment_value=(Spinner)result.findViewById(R.id.aliment_value);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(main_activity, android.R.layout.simple_spinner_item, AlimentEnum.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aliment_value.setAdapter(adapter);
        aliment_value.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				action.setAlimentPassage(index, (short)AlimentEnum.values()[position].ordinal());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("ContentReglagesFragmentView", "nothing selected");
			}
        	
		});

		
		
		
        Spinner vitesse_value=(Spinner)result.findViewById(R.id.vitesse_value);
        adapter = new ArrayAdapter<CharSequence>(main_activity, android.R.layout.simple_spinner_item, VitesseEnum.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vitesse_value.setAdapter(adapter);
        vitesse_value.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				action.setVitessePassage(index, Integer.parseInt(VitesseEnum.getList()[position]));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d("ContentReglagesFragmentView", "nothing selected");
			}
        	
		});
		return result;
	}
	
	private void displayPassagesView(){
		if(action.getAvancement()==AvancementEnum.ULTRASON_GAUCHE)
			passages_container.setVisibility(View.VISIBLE);
		else
			passages_container.setVisibility(View.GONE);
	}
	
}
