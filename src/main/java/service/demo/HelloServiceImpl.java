package service.demo;

/**
 * Created by wangyulin on 7/16/16.
 */
import org.apache.thrift.TException;

public class HelloServiceImpl implements Hello.Iface {

    @Override
    public boolean helloBoolean(boolean para) throws TException {
        System.out.printf("hello true/false");
        return para;
    }

    @Override
    public int helloInt(int para) throws TException {
        System.out.println("hello times: " + para);
        return para;
    }

    @Override
    public String helloNull() throws TException {
        System.out.println("hello null");
        return null;
    }

    @Override
    public String helloString(String para) throws TException {
        System.out.println("hello " + para);
        return para;
    }

    @Override
    public String helloVoid(String msg) throws TException {
        System.out.println(msg);
        return "服务器已经收到如下信息 : " + msg;
    }
}
