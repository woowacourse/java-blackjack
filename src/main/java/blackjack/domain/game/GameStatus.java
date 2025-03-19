package blackjack.domain.game;

import blackjack.domain.Bet;

public enum GameStatus {

    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1),
    TIE("무", 0),
    LOSE("패", -1);

    private final String name;
    private final double multiplier;

    GameStatus(final String name, final double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public double calculateBetResult(final Bet bet) {
        return multiplier * bet.amount();
    }

    public String getName() {
        return name;
    }
}
