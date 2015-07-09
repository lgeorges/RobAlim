package com.arduino.robalim.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.arduino.RobAlimInterfaceOut;
import com.arduino.robalim.model.ActionModel;
import com.example.robalim.R;


public class ReglagesFragmentView extends Fragment implements Observer {
	private RobAlimInterfaceIn robot_in;
	private RobAlimInterfaceOut robot_out; 
	private Activity main_activity;
	private LinearLayout actions_container;
	private ContentReglagesFragmentView fragments[];
	private FragmentManager fragment_manager;
	private ArrayList<ActionModel> actions;
	private boolean view_created =false;
	
	public ReglagesFragmentView(){
		super();
        robot_out= RobAlimInterfaceOut.getInstance();
        robot_in= RobAlimInterfaceIn.getInstance();
        robot_in.addObserver(this);
        actions = robot_in.getActions();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reglages_view, container, false);

        main_activity=getActivity();
        actions_container=(LinearLayout)rootView.findViewById(R.id.actions_container);
        Button add_action_btn = (Button)rootView.findViewById(R.id.add_action_btn);
        add_action_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActionModel action = new ActionModel();
				actions.add(action);
				addActionView(action);
			}
		});
        Button rm_action_btn = (Button)rootView.findViewById(R.id.rm_action_btn);
        Button send_actions_btn = (Button)rootView.findViewById(R.id.send_actions_btn);
        send_actions_btn.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				robot_out.sendActions(main_activity);
				
			}
		});

        Button import_actions_btn = (Button)rootView.findViewById(R.id.import_actions_btn);
        import_actions_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				robot_out.importActions(main_activity);
			}
		});
        fragment_manager = getFragmentManager();
        for(ActionModel action : actions){
        	this.addActionView(action);
        }
        
        view_created=true;
        update(null,null);
        return rootView;
	}

	@Override
	public void update(Observable observable, Object data) {
		if(view_created){
			actions_container.removeAllViews();
			for(ActionModel a : actions){
				addActionView(a);
			}

		}
	}
	
	public void addActionView(ActionModel action){
		int index  = actions.indexOf(action)+1;//index doit etre différent de zero
		TextView action_title = new TextView(main_activity);
		actions_container.addView(action_title);
		action_title.setText("N°action "+index);
		action_title.setClickable(true);
		
		ContentReglagesFragmentView fragment = new ContentReglagesFragmentView(action);
		FrameLayout action_content = new FrameLayout(main_activity);

		actions_container.addView(action_content);
		action_content.setId(index);
		fragment_manager.beginTransaction().add(action_content.getId(),fragment).commit();
//		fragment_manager.beginTransaction().hide(fragment).commit();
		fragment.hide(fragment_manager);
		action_title.setOnClickListener(new DisplayContentListener(fragment));
		
	}
	
	class DisplayContentListener implements OnClickListener{
		
		private boolean is_displayed;
		private ContentReglagesFragmentView fragment;
		
		public DisplayContentListener(ContentReglagesFragmentView fragment) {
			this.fragment=fragment;
			is_displayed=false;
		}
		
		@Override
		public void onClick(View v) {
			if(is_displayed){
//				fragment_manager.beginTransaction().hide(fragment).commit();
				fragment.hide(fragment_manager);
				is_displayed=false;
			}
			else{
				fragment.show(fragment_manager);
//				fragment_manager.beginTransaction().show(fragment).commit();
				is_displayed=true;
		    }
		}
		
	}
}
