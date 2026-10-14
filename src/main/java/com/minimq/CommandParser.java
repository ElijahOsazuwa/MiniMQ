package com.minimq;

public class CommandParser {
    public static Command parse(String line){
        if(line==null || line.isBlank()){
            return new ErrorCommand("line is empty");
        }
        line = line.trim();
        var parts = line.split(" ", 3);
        var commandType = parts[0];

        switch(commandType){
            case "PING" -> {
                return new PingCommand();
            }
            case "QUIT" -> {
                return new QuitCommand();
            }
            case "SUB" -> {
                if(parts.length < 2 || parts[1].isBlank()){
                    return new ErrorCommand("No sub topic");
                }
                return new SubCommand(parts[1]);
            }
            case "UNSUB" -> {
                if(parts.length < 2 || parts[1].isBlank()){
                    return new ErrorCommand("No sub topic");
                }
                return new UnsubCommand(parts[1]);
            }
            case "PUB" -> {
                if(parts.length < 2 || parts[1].isBlank()){
                    return new ErrorCommand("No sub topic");
                }
                if(parts.length < 3 || parts[2].isBlank()){
                    return new ErrorCommand("No message");
                }
                return new PubCommand(parts[1], parts[2]);
            }
            default -> {
                return new ErrorCommand("new error" + commandType);
            }

        }
    }
}
