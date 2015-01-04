package com.laazer.common.sync;

import java.util.Collection;
import java.util.LinkedList;

/**
 * a synchronized queue
 */
public class GenQueue<E> {

	private LinkedList<E> list = new LinkedList<E>();
	private Object Sync = new Object();
	private long TotalProcessed = 0;

	public void add(E item) {
		synchronized(Sync){
			list.add(item);
			TotalProcessed++;
		}
	}

	public E poll() {
		synchronized(Sync){
			return list.poll();
		}
	}

	public E peek() {
		synchronized(Sync){
			return list.peek();
		}
	}

	public boolean hasItems() {
		synchronized(Sync){
			return !list.isEmpty();
		}
	}

	public int size() {
		synchronized(Sync){
			return list.size();
		}
	}

	public void addItems(GenQueue<? extends E> q) {
		while (q.hasItems())
		{
			synchronized(Sync){
				list.add(q.poll());
				TotalProcessed++;
			}
		}
	}

	public void addAll(Collection<? extends E> q) {
		synchronized(Sync){
			list.addAll(q);
			TotalProcessed+=q.size();
		}
	}

	public long totalProcessed()
	{
		synchronized(Sync){
			return this.TotalProcessed;
		}
	}


}