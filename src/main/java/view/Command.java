package view;

public enum Command {
    YES("y"),
    NO("n"),
    ;

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean compareToString(String input) {
        if (YES.command.equals(input)) {
            return true;
        }
        if (NO.command.equals(input)) {
            return false;
        }
        throw new IllegalArgumentException("y/n 만 입력할 수 있습니다.");
    }
}
