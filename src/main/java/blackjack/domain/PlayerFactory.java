package blackjack.domain;

public class PlayerFactory {

    public static Players from(String[] names) {
        validateLength(names);
        return null;
    }

    private static void validateLength(String[] names) {
        if (names.length < 2 || names.length > 8) {
            throw new IllegalArgumentException("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
        }
    }
}
