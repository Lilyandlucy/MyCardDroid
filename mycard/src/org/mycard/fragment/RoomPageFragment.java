package org.mycard.fragment;

import java.util.List;

import org.mycard.R;
import org.mycard.data.ResourcesConstants;
import org.mycard.data.RoomInfo;
import org.mycard.widget.adapter.RoomAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class RoomPageFragment extends BaseFragment implements OnItemClickListener, ResourcesConstants {
	
	private static final String TAG = "RoomPageFragment";
	
	private ListView mContentView;
	private RoomAdapter mAdapter;
	private List<RoomInfo> mData;
	
	private boolean isDataBinded = false;
	
	public static RoomPageFragment newInstance(int index) {
		RoomPageFragment fragment = new RoomPageFragment();
		
		Bundle data = new Bundle();
		data.putInt("index", index);
		fragment.setArguments(data);
		return fragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onAttach: E");
		super.onAttach(activity);
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPause: E");
		super.onPause();
		mData = null;
		isDataBinded = false;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onResume: E");
		super.onResume();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView: E");
		mContentView = (ListView) inflater.inflate(R.layout.common_list, null);
		mContentView.setOnItemClickListener(this);
		isDataBinded = false;
		return mContentView;
	}
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDetach: E");
		super.onDetach();
	}

	/* (non-Javadoc)
	 * @see android.os.Handler.Callback#handleMessage(android.os.Message)
	 */
	@Override
	public boolean handleMessage(Message msg) {
		return false;
	}

	/**
	 * 
	 * @return
	**/
	/*package*/ void setData(List<RoomInfo> data) {
		mData = data;
		if (mAdapter == null) {
			Log.d(TAG, "create new adapter " + getArguments().getInt("index", 0));
			mAdapter = new RoomAdapter(mData, mActivity, getArguments().getInt("index", 0));
		} else {
			mAdapter.setData(mData);
			mAdapter.notifyDataSetChanged();
		}
		if (mContentView != null && !isDataBinded && !isDetached()) {
			Log.d(TAG, "bind view data with index " + getArguments().getInt("index", 0));
			mContentView.setAdapter(mAdapter);
			mContentView.setDivider(null);
			mContentView.setDividerHeight(4);
			isDataBinded = true;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		RoomInfo info = (RoomInfo) mAdapter.getItem(position);
		Bundle data = new Bundle();
		data.putString(ROOM_INFO_NAME, info.name);
		data.putInt(ROOM_INFO_MODE, info.mode);
		data.putInt(ROOM_INFO_LIFEPOINTS, info.startLp == -1 ? 8000 : info.startLp);
		data.putInt(ROOM_INFO_INITIALHAND, info.startHand == -1 ? 5 : info.startHand);
		data.putInt(ROOM_INFO_DRAWCARDS, info.drawCount == -1 ? 1 : info.drawCount);
		showDialog(data);
	}

}
