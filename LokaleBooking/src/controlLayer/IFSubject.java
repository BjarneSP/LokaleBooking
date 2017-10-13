package controlLayer;

public interface IFSubject {
	public void addObserver(IFObserver o);
	public void removeObserver(IFObserver o);
	public void notifyAllObservers();
}
