package blackjack.view;

public enum Command {

    HIT,
    STAY;

    public static Command of(String command) {
        if ("y".equals(command)) {
            return HIT;
        }

        if ("n".equals(command)) {
            return STAY;
        }

        throw new IllegalArgumentException("y, n 중에 입력해야 합니다.");
    }

    public boolean isStay() {
        return this == Command.STAY;
    }
}
