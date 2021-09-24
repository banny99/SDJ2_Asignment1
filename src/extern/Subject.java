package extern;

public interface Subject
{
  void addListener(Listener l);
  void removeListener(Listener l);
  void notifyListeners();
}
