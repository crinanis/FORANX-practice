package com.grpctest.grpc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = com.grpctest.grpc.server.GrpcServer.class)
class GrpcSpringBootApplicationTests {
	@Test
	void contextLoads() {
	}
}
