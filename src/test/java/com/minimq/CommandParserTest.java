package com.minimq;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    @Test 
    void parsesValidPing() {
        Command cmd = CommandParser.parse("PING");
        assertEquals(new PingCommand(), cmd);
    }

    @Test 
    void parsesValidQuit() {
        Command cmd = CommandParser.parse("QUIT");
        assertEquals(new QuitCommand(), cmd);
    }

    @Test 
    void parsesValidPub() {
        Command cmd = CommandParser.parse("PUB sports football is great");
        assertEquals(new PubCommand("sports", "football is great"), cmd);
    }

    @Test 
    void parsesValidSub() {
        Command cmd = CommandParser.parse("SUB sports");
        assertEquals(new SubCommand("sports"), cmd);
    }

    @Test 
    void parsesValidUnsub() {
        Command cmd = CommandParser.parse("UNSUB sports");
        assertEquals(new UnsubCommand("sports"), cmd);
    }

    @Test 
    void rejectsUnknownCommand() {
        Command cmd = CommandParser.parse("FOOBAR");
        assertInstanceOf(ErrorCommand.class, cmd);
    }

    @Test 
    void parsesCommandWithWhiteSpace() {
        Command cmd = CommandParser.parse("    SUB sports");
        assertEquals(new SubCommand("sports"), cmd);
    }

    @Test
    void rejectsMissingTopic() {
        Command cmd = CommandParser.parse("SUB");
        assertInstanceOf(ErrorCommand.class, cmd);
    }

    @Test
    void rejectsEmptyMessage() {
        Command cmd = CommandParser.parse("PUB sports");
        assertInstanceOf(ErrorCommand.class, cmd);
    }

    @Test
    void rejectsEmptyInput() {
        Command cmd = CommandParser.parse("");
        assertInstanceOf(ErrorCommand.class, cmd);
    }
}
