package blackjack.controller;

import blackjack.domain.card.CardDeck;

import java.util.Objects;

public abstract class BlackJackController {
    private static final String DEFAULT_GAME = "1";
    private static final String BETTING_GAME = "2";
    private static final String NULL_ERR_MSG = "게임이 선택되지 않았습니다.";
    private static final String INVALID_GAME_SELECTED_ERR_MSG = "게임 선택이 잘못되었습니다.";
    protected CardDeck deck = new CardDeck();

    public static BlackJackController of(String game) {
        Objects.requireNonNull(game, NULL_ERR_MSG);

        if (DEFAULT_GAME.equals(game)) {
            return new DefaultGame();
        }
        if (BETTING_GAME.equals(game)) {
            return new BettingGame();
        }

        throw new IllegalArgumentException(INVALID_GAME_SELECTED_ERR_MSG);
    }

    public abstract void run();
}
