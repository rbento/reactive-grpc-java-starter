
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import lombok.extern.slf4j.Slf4j;
import rbento.api.v1.CommandRequest;
import rbento.api.v1.CommandResponse;

@Slf4j
public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {

        log.info("Starting gRPC Client...");

        TerminalGrpcClient client = new TerminalGrpcClient(HOST, PORT);

        CommandRequest request = CommandRequest.newBuilder().setStatement("ls").build();
        log.info("request: {}", request);

        CommandResponse response = client.executeCommand(request);
        log.info("response: {}", response);

        log.info("Done");
    }
}
