package domain.gamer;

import domain.card.PlayingCards;

public class Player extends Gamer {
    public Player(PlayingCards playingCards, String name) {
        super(playingCards, name);
        if (isNotNumber(name)) {
            throw new IllegalArgumentException("알파벳 이외의 문자는 허용하지 않습니다.");
        }
    }

    private static boolean isNotNumber(final String playerNamesValue) {
        return playerNamesValue.chars()
                .anyMatch(c -> !Character.isAlphabetic(c));
    }
}
