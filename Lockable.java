package ch5Projects;

public interface Lockable {
	public void setKey(int k);
	public void lock(int k);
	public void unlock(int k);
	public boolean unlocked();
}
