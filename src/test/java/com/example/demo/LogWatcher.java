package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class LogWatcher {
    private List<String> warnings = new ArrayList<>();
    private List<String> errors = new ArrayList<>();

    public void logWarning(String message) {
        warnings.add(message);
    }

    public void logError(String message) {
        errors.add(message);
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public List<String> getErrors() {
        return errors;
    }
}
