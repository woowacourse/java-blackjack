package util;

import domain.Player;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static List<Player> parseUsers(String input) {
        return Arrays.stream(input.split(","))
                .map(Player::new)
                .toList();
    }

    public static int parseAmount(String amount) {
        try{
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자여야 합니다.");
        }
    }
}
