package Locks.Model;

public class Lock{
	Condition cond;

	public static void lock_init(Lock l){
		l = new Lock();
	}

	public static void lock_acquire(Lock l){
		l.cond.b = true;
		Lock.cond_wait(l);
	}

	public static void lock_release(Lock l){
		l.cond.b = false;
		Lock.cond_broadcast(l);
	}

	public static void cond_init(Lock l){
		l.cond.b = false;
	}
	public static void cond_wait(Lock l){
		l.cond.b = true;
		try{
			while(l.cond.b) l.cond.wait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void cond_signal(Lock l){
		l.cond.notify();
	}

	public static void cond_broadcast(Lock l){
		l.cond.notifyAll();
	}
	
	class Condition{
		boolean b;
		public Condition(){
			b = false;
		}
	}
}