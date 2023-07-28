
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerminalService {
    private static final CommandResult DEFAULT_RESULT = new CommandResult("Bad command or filename", -1);
    private static final Map<String, CommandResult> ACCEPTED_COMMANDS = Map.of(
            "uname", new CommandResult("Darwin", 3),
            "ls", new CommandResult("README.md", 32),
            "whoami", new CommandResult("rbento", 2));

    public CommandResult executeCommand(String command) {
        log.info("executeCommand: {}", command);
        return ACCEPTED_COMMANDS.getOrDefault(command, DEFAULT_RESULT);
    }
}
