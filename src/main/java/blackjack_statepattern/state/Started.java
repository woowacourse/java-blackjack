package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public abstract class Started implements State {

    private final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(State state, double money) {
        throw new IllegalArgumentException("[ERROR] 게임이 끝나야 수익을 계산할 수 있습니다.");
    }

    @Override
    public int score() {
        return cards().computeScore();
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
