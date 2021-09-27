package by.epamtc.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static Map<CommandType,Command> actions = new HashMap<>();
    private CommandProvider(){
        actions.put(CommandType.PARSE,new ParseCommand());
        actions.put(CommandType.NO_SUCH_COMMAND,new NoSuchCommand());
    }

    private static volatile CommandProvider instance;

    public static CommandProvider getInstance() {
        CommandProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandProvider();
                }
            }
        }
        return localInstance;
    }

    public Command getCommand(String commandName) {
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());

        Command command;

        if(commandType != null){
            command = actions.get(commandType);
        }
        else{
            command = actions.get(CommandType.NO_SUCH_COMMAND);
        }

        return command;
    }
}
