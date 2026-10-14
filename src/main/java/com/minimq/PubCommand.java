package com.minimq;

public record PubCommand(String topic, String message) 
implements Command {}
