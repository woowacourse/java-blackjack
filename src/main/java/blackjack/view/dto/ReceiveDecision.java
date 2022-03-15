package blackjack.view.dto;

public enum ReceiveDecision {
    YES("y"),
    NO("n"),
    ;

    private final String command;

    ReceiveDecision(String command) {
        this.command = command;
    }

    public static boolean wantMore(String receiveDecision) {
        return receiveDecision.equals(YES.getCommand());
    }

    public String getCommand() {
        return command;
    }
}
