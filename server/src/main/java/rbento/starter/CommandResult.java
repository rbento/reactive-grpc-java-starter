
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import rbento.api.v1.CommandResponse;

record CommandResult(String result, Integer code) {
    public CommandResponse toCommandResponse() {
        return CommandResponse.newBuilder().setResult(result).setCode(code).build();
    }
}
