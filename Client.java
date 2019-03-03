import kallup.dbase.MyClassLoader;
import kallup.dbase.ProcessAlgorithm;

public class Client
{
    public static void main(String[] args) throws Exception
    {
	MyClassLoader myClassLoader = new MyClassLoader();
	Class clazz = myClassLoader.findClass("pkg.Action");

	ProcessAlgorithm iAction = (ProcessAlgorithm) clazz.newInstance();

	iAction.doProcess();
    }
}
