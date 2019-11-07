package ru.javawebinar.topjava.util.exception;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String detail;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String detail) {
        this.url = url.toString();
        this.type = type;
        this.detail = detail;
        this.details = new String[0];
    }

    public ErrorInfo(CharSequence url, ErrorType type, String detail, String[] details) {
        this.url = url.toString();
        this.type = type;
        this.detail = detail;
        this.details = details;
    }
}