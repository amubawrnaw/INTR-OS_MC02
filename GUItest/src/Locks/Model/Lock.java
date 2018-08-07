package Locks.Model;

public class Lock{
	
	public Lock(){
		
	}

	public static void lock_init(Lock l){
		l = new Lock();
	}

	public static void lock_acquire(Lock l){
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void lock_release(Lock l){
		l.notifyAll();
	}

	public static void cond_init(Lock l){

	}

	public static void cond_wait(Lock l){

	}

	public static void cond_signal(Lock l){

	}

	public static void cond_broadcast(Lock l){

	}

}