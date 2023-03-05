package blackjack.controller;

public enum AddCardOrNot {
    YES("y"),
    NO("n");

    private final String type;

    AddCardOrNot(String type) {
        this.type = type;
    }

    public static AddCardOrNot of(String type) {
        if (type.equals("y")) {
            return YES;
        }
        if (type.equals("n")) {
            return NO;
        }
        // TODO: y/n 이 아닐 경우 예외처리
        return NO;
    }
}
