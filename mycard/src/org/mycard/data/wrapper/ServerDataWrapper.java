package org.mycard.data.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mycard.data.BaseInfo;
import org.mycard.data.ServerInfo;

public class ServerDataWrapper extends BaseDataWrapper {
	
	private List<JSONObject> mData;
	
	public ServerDataWrapper(String scheme) {
		super();
		mUrls.add(scheme + SERVER_LIST_URL);
		mData = new ArrayList<JSONObject>();
	}

	@Override
	public void parse(JSONArray data) {
		// TODO Auto-generated method stub
		for (int i = 0; i < data.length(); i++) {
			try {
				mData.add(data.getJSONObject(i));
			} catch (JSONException e) {
			}
		}
	}
	
	public int size() {
		return mData.size();
	}
	
	public BaseInfo getItem(int index) {
		BaseInfo info = new ServerInfo();
		try {
			info.initFromJsonData(mData.get(index));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return info;
	}


}
