package com.grpctest.grpc.server;

import com.grpctest.grpc.service.MyServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(5443)
                .addService(new MyServiceImpl())
                .build();

        server.start();
        System.out.println("Server started on port 5443");
        server.awaitTermination();
    }
}
