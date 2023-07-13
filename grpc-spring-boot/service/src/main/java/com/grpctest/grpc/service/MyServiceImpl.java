package com.grpctest.grpc.service;

import com.grpctest.grpc.MyServiceGrpc;
import io.grpc.stub.StreamObserver;
//import com.grpctest.grpc.HelloRequest;
//import com.grpctest.grpc.HelloReply;
import com.grpctest.grpc.Request;
import com.grpctest.grpc.Response;

import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {
//    @Override
//    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
//        HelloReply reply = HelloReply.newBuilder()
//                .setMessage("Hello ==> " + request.getName())
//                .build();
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//    }

    @Override
    public void processRequest(Request request, StreamObserver<Response> responseObserver) {
        List<String> errors = validateRequest(request);
        if (!errors.isEmpty()) {
            sendErrorResponse(responseObserver, errors.get(0));
        } else {
            sendSuccessResponse(responseObserver);
        }
    }

    private List<String> validateRequest(Request request) {
        List<String> errors = new ArrayList<>();

        if (request.getId().isEmpty()) {
            errors.add("id is empty");
        }
        if (request.getMethod().isEmpty()) {
            errors.add("method is empty");
        }
        if (!request.hasParams()) {
            errors.add("params is empty");
        } else {
            Request.Params params = request.getParams();
            if (params.getPnfl().isEmpty()) {
                errors.add("pnfl is empty");
            } else if (params.getPnfl().length() != 14 || !params.getPnfl().matches("\\d+")) {
                errors.add("pnfl is invalid");
            }
            if (params.getMfo().isEmpty()) {
                errors.add("mfo is empty");
            }
            if (params.getOrgid().isEmpty()) {
                errors.add("orgid is empty");
            }
            if (params.getPhone().isEmpty()) {
                errors.add("phone is empty");
            }
        }

        return errors;
    }

    private void sendErrorResponse(StreamObserver<Response> responseObserver, String errorMsg) {
        Response response = Response.newBuilder()
                .setError("1")
                .setMsg("Field " + errorMsg)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private void sendSuccessResponse(StreamObserver<Response> responseObserver) {
        Response response = Response.newBuilder()
                .setError("0")
                .setMsg("OK")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}