package domain;

import domain.constant.WinningCondition;

public record PlayerWinningInfo(String name, String winningCondition) {
    public static PlayerWinningInfo from (String name, WinningCondition winningCondition) {
        return new PlayerWinningInfo(name, winningCondition.description());
    }
}
