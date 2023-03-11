package domain.participant;

import domain.card.Card;
import domain.deck.DeckStrategy;
import domain.game.Bet;
import domain.game.GamePoint;
import domain.game.Hand;

public final class Player extends Participant {

    private final Bet bet;

    private Player(final Name name, final int bet) {
        super(name);
        this.bet = Bet.of(bet);
    }

    private Player(final Name name, final Hand hand, final int bet) {
        super(name, hand);
        this.bet = Bet.of(bet);
    }

    public static Player of(final Name name, final int bet) {
        validateName(name);
        return new Player(name, bet);
    }

    public static Player of(final Player player) {
        return new Player(player.getName(), player.getCards(), player.getBet());
    }

    public static Player create(final Name name, final Hand hand, final int bet) {
        validateName(name);
        return new Player(name, hand, bet);
    }

    public void takeInitialCards(final DeckStrategy deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.hand = hand.add(deck.drawCard());
        }
    }

    public void takeCard(final Card card) {
        this.hand = hand.add(card);
    }

    private static void validateName(final Name name) {
        if (name.getValue().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(
                    String.format("'%s'라는 이름을 가질 수 없습니다.", DEALER_NAME));
        }
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

    public int getBet() {
        return bet.getBet();
    }
}
