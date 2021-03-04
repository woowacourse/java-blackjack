package blackjack.domain.gamer;

import blackjack.domain.Score;
import blackjack.domain.card.Cards;

import static blackjack.controller.BlackJackGame.BLACKJACK_NUMBER;

public class Player extends Person {
    private static final String WRONG_PLAYER_NAME_ERROR_MESSAGE = "유효하지 않은 플레이어 이름입니다.";

    public Player(String name) {
        validateNameLength(name);
        super.name = name;
        super.cards = new Cards();
    }

    private void validateNameLength(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException(WRONG_PLAYER_NAME_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean canDraw() {
        return Score.calculatorScore(cards) < BLACKJACK_NUMBER;
    }
}
