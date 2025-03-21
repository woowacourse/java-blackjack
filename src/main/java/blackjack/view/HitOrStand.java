package blackjack.view;

public enum HitOrStand {
    HIT("y"),
    STAND("n");

    private final String command;

    public String getCommand() {
        return command;
    }

    HitOrStand(String command) {
        this.command = command;
    }


    public static HitOrStand from(String command) {
        if (HIT.getCommand().equals(command)) {
            return HIT;
        }
        if (STAND.getCommand().equals(command)) {
            return STAND;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해 주세요.");
    }
}
