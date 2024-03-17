package view;

public enum GameCommand {
    HIT("y"),
    STAND("n");

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand of(final String input) {
        if (input.equals(HIT.command)) {
            return HIT;
        }
        if (input.equals(STAND.command)) {
            return STAND;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }

    public boolean isHit() {
        return this.equals(HIT);
    }
}
