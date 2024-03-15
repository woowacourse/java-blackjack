package domain;

public enum Command {
    YES("y"), NO("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command get(String input) {
        if (YES.command.equals(input)) {
            return YES;
        }
        if (NO.command.equals(input)) {
            return NO;
        }
        throw new IllegalArgumentException("명령어는 y,n만 가능합니다.");
    }
}
