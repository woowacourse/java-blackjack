package domain.blackjack;

public enum UserCommand {
    YES("y"),
    NO("n");

    private final String input;

    UserCommand(final String input) {
        this.input = input;
    }

    public static UserCommand fromInput(final String input) {
        for (final UserCommand command : UserCommand.values()) {
            if (command.getInput().equals(input)) {
                return command;
            }
        }
        throw new IllegalArgumentException("허용하지 않는 입력입니다: " + input);
    }

    public boolean isYes() {
        return this.equals(UserCommand.YES);
    }

    private String getInput() {
        return input;
    }
}
