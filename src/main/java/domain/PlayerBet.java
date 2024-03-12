package domain;

import domain.gamer.Player;

import java.util.regex.Pattern;

public class PlayerBet {

    public static final int MIN_BETTING_AMOUNT = 10000;
    private final Player player;
    private final int betAmount;

    public PlayerBet(Player player, String playerBetInput) {
        validate(playerBetInput);
        this.player = player;
        this.betAmount = Integer.parseInt(playerBetInput);
    }

    private void validate(String playerBetInput) {
        validateDigit(playerBetInput);
        validateMinBettingAmount(playerBetInput);
    }

    private void validateDigit(String playerBetInput) {
        if (!Pattern.matches("\\d+", playerBetInput)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요.");
        }
    }

    private void validateMinBettingAmount(String playerBetInput) {
        if (Integer.parseInt(playerBetInput) < MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 10000원 이상 베팅해주세요.");
        }
    }
}
