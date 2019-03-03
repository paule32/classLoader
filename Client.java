import java.net.URL;
import java.net.URLClassLoader;

import kallup.dbase.Action;

public class Client
{
    public static void main(String[] args) throws Exception
    {
	String url1 = "file:///config/Action.class";
	Action action = new Action();

	URLClassLoader urlLoader = new URLClassLoader(new URL[] { new URL(url1) });
	Class<?> newClass = urlLoader.loadClass("Action");

	action = (Action) newClass.newInstance();
	action.doAction();
    }
}
