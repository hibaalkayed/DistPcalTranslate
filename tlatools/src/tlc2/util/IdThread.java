// Copyright (c) 2003 Compaq Corporation.  All rights reserved.
// Portions Copyright (c) 2003 Microsoft Corporation.  All rights reserved.
package tlc2.util;

import tlc2.tool.TLCState;
import tlc2.value.IValue;

/** An <code>IdThread</code> is a <code>Thread</code> with an
    integer identifier. */

public class IdThread extends Thread {
	private static final ThreadLocal<TLCState> currentState = new ThreadLocal<>();
	
	/**
	 * @return null during the generation of initial states (see
	 *         tlc2.tool.ModelChecker.doInit(boolean) and a TLCState during the
	 *         generation of next-states in tlc2.tool.ModelChecker.doNext(TLCState,
	 *         ObjLongTable, Worker). It corresponds to the predecessor state of the
	 *         next-state currently being generated by the worker thread.
	 */
	public static final TLCState getCurrentState() {
		return currentState.get();
	}
	public static final void setCurrentState(final TLCState state) {
		currentState.set(state);
	}
	public static final TLCState resetCurrentState() {
		final TLCState tlcState = currentState.get();
		currentState.remove();
		return tlcState;
	}
	
    private final int id;
	private IValue[] localValues = new IValue[4];
   
    /** Create a new thread with ID <code>id</code>. */
    public IdThread(int id) {
        this.id = id;
    }
   
    public IdThread(Runnable runnable, String name, int id) {
    	super(runnable, name);
    	this.id = id;
	}

	/** Return this thread's ID. */
    // This method was originally called getId, but a later
    // version of Java introduced a getId method into the Thread
    // class which returned a long, and it doesn't allow it to be
    // overridden by a method that returns an int.  So 
    // LL changed the return type from int to long on 31 Aug 2007
    // However, apparently a later version of Java complained when
    // its use in class tlc.tool.liveness.LiveWorker assigned
    // its value to an int variable, so it was renamed myGetId.
    // It is used only in LiveWorker.
    public final int myGetId() { // getId() {
        return this.id;
    }

    /** Return the Id of the calling thread. This method
        will result in a <TT>ClassCastException</TT> if
        the calling thread is not of type <TT>IdThread</TT>. */
    public static int GetId() {
        return ((IdThread)Thread.currentThread()).id;
    }

    /** If the calling thread is of type <TT>IdThread</TT>,
        return its ID. Otherwise, return <TT>otherId</TT>. */
    public static int GetId(int otherId) {
        Thread th = Thread.currentThread();
        return (th instanceof IdThread) ? ((IdThread)th).id : otherId;
    }
    
	public IValue getLocalValue(int idx) {
		if (idx < this.localValues.length) {
			return this.localValues[idx];
		}
		return null;
	}

	public void setLocalValue(int idx, IValue val) {
		if (idx >= this.localValues.length) {
			IValue[] vals = new IValue[idx + 1];
			System.arraycopy(this.localValues, 0, vals, 0, this.localValues.length);
			this.localValues = vals;
		}
		this.localValues[idx] = val;
	}
}
