package com.nshane.picstore.http;

import java.util.concurrent.Executor;

public abstract class MyTask implements Runnable {
	private static final MoreExecutor MORE_EXECUTOR = new MoreExecutor();
	private static final int CORE_POOL_SIZE = 5;
	

	/**
	 * 异步任务类
	 */
	public MyTask() {
	}

	/** 外部接口，处理请求
	 * @param run
	 * @param isLTimeOper
	 */
	public static void runInBackground(Runnable run, boolean isLTimeOper) {
		if (isLTimeOper) {
			MORE_EXECUTOR.execute(run);
		} else {
			AsyncTaskAssistant.executeOnThreadPool(run);
		}

	}


	private static class MoreExecutor implements Executor {
		public static int runCount = 0;

		/**
		 * @param add
		 * 同步执行，维护runCount字段
		 */
		private synchronized void editRuncount(boolean add) {
			if (add) {
				runCount++;
			} else {
				runCount--;
			}
		}

		/**
		 * 占用线程池CORE_POOL_SIZE-1个,如果满了立即创建thread执行;
		 */
		public synchronized void execute(final Runnable r) {
			if (runCount >= (CORE_POOL_SIZE-1)) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						r.run();
					}
				}).start();
			} else {
				executeTask(new Runnable() {
					public void run() {
						editRuncount(true);
						r.run();
						editRuncount(false);
					}
				});
			}

		}

		/**
		 * @param runtask
		 */
		protected synchronized void executeTask(Runnable runtask) {
			AsyncTaskAssistant.executeOnThreadPool(runtask);
		}
	}

}
