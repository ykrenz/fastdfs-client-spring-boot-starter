//package com.ykrenz.fastdfs.autoconfigure;
//
//import org.springframework.boot.actuate.health.AbstractHealthIndicator;
//import org.springframework.boot.actuate.health.Health;
//
//import java.io.ByteArrayInputStream;
//
//public class FastDFSHealthIndicator extends AbstractHealthIndicator {
//
//    private static final byte[] FILE_CONTENT = new byte[]{
//            (byte) 0,
//    };
//
//    private static final long FILE_LENGTH = FILE_CONTENT.length;
//
//    @Override
//    protected void doHealthCheck(Health.Builder builder) {
//
//        try {
//            String filePath = FastDFS.upload(new ByteArrayInputStream(FILE_CONTENT), FILE_LENGTH, "tmp");
//            FastDFS.delete(filePath);
//            builder.up();
//        } catch (Exception e) {
//            builder.down(e);
//        }
//    }
//
//}