package org.mycard.core;

import java.util.concurrent.BlockingQueue;

import org.mycard.core.IBaseConnection.TaskStatusCallback;
import org.mycard.data.wrapper.BaseDataWrapper;
import org.mycard.net.IBaseConnector;

public class BaseDownloadThread extends BaseThread {

	private BlockingQueue<BaseDataWrapper> mQueue;

	protected IBaseConnector mConnector;
	
	public BaseDownloadThread(BlockingQueue<BaseDataWrapper> queue, TaskStatusCallback callback) {
		// TODO Auto-generated constructor stub
		super(callback);
		mQueue = queue;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BaseDataWrapper wrapper = null;
		while (isRunning && !isInterrupted()) {
			try {
				wrapper = mQueue.take();
				if (wrapper != null) {
					mConnector.get(wrapper);
					mCallback.onTaskFinish(wrapper);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}

