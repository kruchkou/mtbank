package by.bsuir.alexey.mtbank.controller.command.util;

import by.bsuir.alexey.mtbank.controller.command.Command;
import by.bsuir.alexey.mtbank.controller.command.impl.CreateClientCommand;
import by.bsuir.alexey.mtbank.controller.command.impl.DeleteClientCommand;
import by.bsuir.alexey.mtbank.controller.command.impl.UpdateClientCommand;
import by.bsuir.alexey.mtbank.controller.command.impl.go.GoToAllClientsCommand;
import by.bsuir.alexey.mtbank.controller.command.impl.go.GoToClientCommand;
import by.bsuir.alexey.mtbank.controller.command.impl.go.GoToCreateClientCommand;
import by.bsuir.alexey.mtbank.controller.command.impl.go.GoToUpdateClientCommand;
import lombok.Getter;

import java.util.HashMap;

public class CommandProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static CommandProvider instance = new CommandProvider();

    //HashMap, хранящий экземпляры всех комманд
    private final HashMap<CommandName, Command> commands = new HashMap<>();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CommandProvider() {
        commands.put(CommandName.GO_TO_ALL_CLIENTS_COMMAND, new GoToAllClientsCommand());
        commands.put(CommandName.GO_TO_CLIENT_COMMAND, new GoToClientCommand());
        commands.put(CommandName.GO_TO_CREATE_CLIENT_COMMAND, new GoToCreateClientCommand());
        commands.put(CommandName.GO_TO_UPDATE_CLIENT_COMMAND, new GoToUpdateClientCommand());
        commands.put(CommandName.CREATE_CLIENT_COMMAND, new CreateClientCommand());
        commands.put(CommandName.UPDATE_CLIENT_COMMAND, new UpdateClientCommand());
        commands.put(CommandName.DELETE_CLIENT_COMMAND, new DeleteClientCommand());
    }

    //Метод, возвращающий команду по ее имени
    public Command getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        CommandName enumName = CommandName.valueOf(commandName);

        return commands.get(enumName);
    }
}
