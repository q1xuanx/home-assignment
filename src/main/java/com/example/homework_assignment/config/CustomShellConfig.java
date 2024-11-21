package com.example.homework_assignment.config;

import org.jline.terminal.Terminal;
import org.springframework.shell.Input;
import org.springframework.shell.ResultHandlerService;
import org.springframework.shell.Shell;
import org.springframework.shell.command.CommandCatalog;
import org.springframework.shell.context.ShellContext;
import org.springframework.shell.exit.ExitCodeMappings;

public class CustomShellConfig extends Shell {
    public CustomShellConfig(ResultHandlerService resultHandlerService, CommandCatalog commandRegistry, Terminal terminal,
                             ShellContext shellContext, ExitCodeMappings exitCodeMappings) {
        super(resultHandlerService, commandRegistry, terminal, shellContext, exitCodeMappings);
    }
    public Object evaluate(Input input){
        return super.evaluate(input);
    }
}
