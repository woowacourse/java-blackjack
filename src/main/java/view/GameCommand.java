package view;

public enum GameCommand {
    HIT("y"),
    STAND("n");

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand of(final String input) {
        if (HIT.command.equals(input)) {
            return HIT;
        }
        if (STAND.command.equals(input)) {
            return STAND;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }

    public boolean isHit() {
        return this.equals(HIT);
    }
}
