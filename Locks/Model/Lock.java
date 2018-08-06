package Model;

public class Lock{
	
	public Lock(){
		
	}

	public void static lock_init(Lock l){
		l = new Lock();
	}

	public void static lock_acquire(Lock l){
		try{
			while
			l.wait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void static lock_release(Lock l){
		l.notifyAll();
	}

	public void static cond_init(Lock l){

	}

	public void static cond_wait(Lock l){

	}

	public void static cond_signal(Lock l){

	}

	public void static cond_broadcast(Lock l){

	}

}