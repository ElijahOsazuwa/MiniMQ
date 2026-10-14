package com.minimq;

public sealed interface Command permits PubCommand, SubCommand,
UnsubCommand, PingCommand, QuitCommand, ErrorCommand {
    
}