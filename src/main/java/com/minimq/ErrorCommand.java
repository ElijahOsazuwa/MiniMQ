package com.minimq;

public record ErrorCommand(String reason) implements Command {}
