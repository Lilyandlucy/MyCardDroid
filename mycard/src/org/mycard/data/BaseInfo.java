package org.mycard.data;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseInfo implements Cloneable, ResourcesConstants{
	
	public String id;
	
	@Override
	protected BaseInfo clone() {
		// TODO Auto-generated method stub
		try {
			return (BaseInfo)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void initFromJsonData(JSONObject data) throws JSONException {
		id = data.getString(JSON_KEY_ID);
	}

}
