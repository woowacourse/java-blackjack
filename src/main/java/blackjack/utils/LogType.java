package blackjack.utils;

public enum LogType {

    ERROR_MESSAGE((message) -> System.out.println("[ERROR]: " + message));

    private final Log print;

    LogType(Log print) {
        this.print = print;
    }

    public void log(String message) {
        this.print.log(message);
    }
}