//package com.grpctest;
//
//import com.grpctest.grpc.MyServiceGrpc;
//import com.grpctest.grpc.HelloReply;
//import com.grpctest.grpc.HelloRequest;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//
//public class GrpcClient {
//    public static void main(String[] args) {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5443)
//                .usePlaintext()
//                .build();
//
//        MyServiceGrpc.MyServiceBlockingStub stub
//                = MyServiceGrpc.newBlockingStub(channel);
//
//        HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder()
//
//                .build());
//
//        channel.shutdown();
//    }
//}