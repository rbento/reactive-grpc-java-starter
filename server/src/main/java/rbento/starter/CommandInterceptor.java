
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        log.info("Intercepted call...");
        log.info("headers: {}", headers);
        return new RequestListener<>(next.startCall(new ResponseListener<>(call), headers));
    }

    static class ResponseListener<ReqT, RespT> extends ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT> {
        protected ResponseListener(ServerCall<ReqT, RespT> delegate) {
            super(delegate);
        }

        @Override
        public void sendMessage(RespT message) {
            log.info("sendMessage: {}", message);
            super.sendMessage(message);
        }
    }

    static class RequestListener<ReqT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
        protected RequestListener(ServerCall.Listener<ReqT> delegate) {
            super(delegate);
        }

        @Override
        public void onMessage(ReqT message) {
            log.info("onMessage: {}", message);
            super.onMessage(message);
        }

        @Override
        public void onComplete() {
            log.info("onComplete");
            super.onComplete();
        }
    }
}
