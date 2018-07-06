import model.StudentScoreInquiryServer;

public class startServer {

    public static void main(String[] args){
        if(args.length > 0) {
            //参数指定端口
            new StudentScoreInquiryServer(Integer.parseInt(args[0]));
        } else {
            //默认端口5454
            new StudentScoreInquiryServer(5454);
        }
    }

}
