package domain.participant;

import domain.card.Card;
import domain.deck.DeckStrategy;
import domain.game.Bet;
import domain.game.GamePoint;
import domain.card.Cards;

public final class Player extends Participant {

    private final Bet bet;

    protected Player(final Name name, final int bet) {
        super(name);
        this.bet = Bet.of(bet);
    }

    protected Player(final Name name, final Cards cards, final int bet) {
        super(name, cards);
        this.bet = Bet.of(bet);
    }

    public static Player of(final Name name, final int bet) {
        validateName(name);
        return new Player(name, bet);
    }

    public static Player create(final Name name, final Cards cards, final int bet) {
        validateName(name);
        return new Player(name, cards, bet);
    }

    public void takeCard(final DeckStrategy deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.cards = cards.add(deck.drawCard());
        }
        checkBetProfit();
    }

    public void takeCard(final Card card) {
        this.cards = cards.add(card);
        checkBetProfit();
    }

    private void checkBetProfit() {
        // 블랙잭인지 버스트인지 확인해서 배팅 금액을 조정해야 함
        // 배팅 금액을 조정하든가, 수익률을 따로 필드로 두고 조정해야 함
        if (isBusted()) {
            bet.applyBust();
        }
        if (isBlackJack() && cards.isSameCount(2)) {
            bet.applyBlackJack();
        }
    }

    private static void validateName(final Name name) {
        if (name.getValue().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(
                    String.format("'%s'라는 이름을 가질 수 없습니다.", DEALER_NAME));
        }
    }

    public Bet getBet() {
        return bet;
    }

    public boolean hasLowerThan(final GamePoint gamePoint) {
        return calculatePoint().isLowerThan(gamePoint);
    }

    public boolean hasSameAs(final GamePoint gamePoint) {
        return calculatePoint().isSameAs(gamePoint);
    }

    public boolean hasGreaterThan(final GamePoint gamePoint) {
        return calculatePoint().isGreaterThan(gamePoint);
    }
}
