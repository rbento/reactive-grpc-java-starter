
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    private static final int SERVER_PORT = 8080;

    @SneakyThrows
    public static void main(String[] args) {
        io.grpc.Server server = ServerBuilder.forPort(SERVER_PORT)
                .addService(new TerminalGrpcService())
                .addService(ProtoReflectionService.newInstance())
                .intercept(new CommandInterceptor())
                .build();

        log.info("Starting gRPC Server on port %d...".formatted(SERVER_PORT));

        server.start();
        server.awaitTermination();

        log.info("Done");
    }
}
