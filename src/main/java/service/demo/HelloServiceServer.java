package service.demo;


import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by wangyulin on 7/16/16.
 */
public class HelloServiceServer {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
        try {
            // 设置服务端口为 9527
            TServerSocket serverTransport = new TServerSocket(9527);
            // 关联处理器与 Hello 服务的实现
            TProcessor processor = new Hello.Processor(new HelloServiceImpl());
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Start server on port 9527...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
