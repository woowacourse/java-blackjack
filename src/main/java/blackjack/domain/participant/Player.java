package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;

public final class Player extends Participant {

    private final String name;
    private BettingAmount bettingAmount;

    private Player(final String name, final List<Card> cards) {
        super(cards);
        validateNameNotBlank(name);
        this.name = name;
    }

    private static void validateNameNotBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
        }
    }

    public static Player readyToPlay(final String name, final List<Card> cards) {
        return new Player(name, cards);
    }

    public void betAmount(final int amount) {
        if (bettingAmount != null) {
            throw new IllegalStateException("이미 베팅을 했습니다.");
        }
        this.bettingAmount = new BettingAmount(amount);
    }

    public void drawCard(final Card card) {
        this.state = state.drawCard(card);
    }

    public void stay() {
        this.state = state.stay();
    }

    public boolean equalsName(final String name) {
        return name.equals(this.name);
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return bettingAmount.getAmount();
    }

}
