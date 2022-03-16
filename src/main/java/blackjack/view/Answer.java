package blackjack.view;

public enum Answer {
    YES,
    NO;

    public static Answer of(String value) {
        if (value.equals("y")) {
            return YES;
        } else if (value.equals("n")) {
            return NO;
        }
        throw new IllegalArgumentException("잘못된 값입니다...");
    }

    public boolean isKeepGoing() {
       return this == YES;
    }
}
