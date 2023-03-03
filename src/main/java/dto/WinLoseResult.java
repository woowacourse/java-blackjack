package dto;

import domain.Player;

public class WinLoseResult {

    private final String name;
    private final boolean isWin;

    private WinLoseResult(final String name, final boolean isWin) {
        this.name = name;
        this.isWin = isWin;
    }

    public String getName() {
        return name;
    }

    public boolean isWin() {
        return isWin;
    }

    public static WinLoseResult toDto(final Player player, final boolean isWin) {
        return new WinLoseResult(player.getName(), isWin);
    }
}
