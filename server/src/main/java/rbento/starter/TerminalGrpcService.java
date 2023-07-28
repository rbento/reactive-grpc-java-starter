
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import lombok.extern.slf4j.Slf4j;
import rbento.api.v1.CommandRequest;
import rbento.api.v1.CommandResponse;
import rbento.api.v1.TerminalGrpc;

@Slf4j
public class TerminalGrpcService extends TerminalGrpc.TerminalImplBase {

    private final TerminalService service = new TerminalService();

    @Override
    public void executeCommand(CommandRequest request, io.grpc.stub.StreamObserver<CommandResponse> responseObserver) {
        responseObserver.onNext(doExecuteCommand(request));
        responseObserver.onCompleted();
    }

    private CommandResponse doExecuteCommand(CommandRequest commandRequest) {
        String statement = commandRequest.getStatement();
        CommandResult result = service.executeCommand(statement);
        return result.toCommandResponse();
    }
}
