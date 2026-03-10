package domain;

import domain.constant.WinningCondition;

public record PlayerWinningInfo(String name, WinningCondition winningCondition) {
    public static PlayerWinningInfo from(String name, WinningCondition winningCondition) {
        return new PlayerWinningInfo(name, winningCondition);
    }

    public String description() {
        return winningCondition.description();
    }
}
