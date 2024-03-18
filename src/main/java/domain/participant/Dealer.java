package domain.participant;

import domain.vo.Card;
import domain.card.Cards;
import domain.vo.Name;
import domain.vo.Profit;

import static domain.BlackjackGame.DEALER_HIT_THRESHOLD;

public class Dealer extends Participant {

    private static final Name DEFAULT_DEALER_NAME = new Name("딜러");

    private final Cards deck;

    public Dealer(final Cards cards) {
        super(DEFAULT_DEALER_NAME);
        deck = cards;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void shuffleCards() {
        deck.shuffle();
    }

    public void deal(final Participant participant) {
        participant.receive(deck.draw());
    }

    public Profit calculateProfit(final Players players) {
        double totalPlayersProfit = players.getPlayers()
                                        .stream()
                                        .map(player -> player.profit().value())
                                        .reduce(0.0, Double::sum);
        return new Profit(-totalPlayersProfit);
    }

    @Override
    public boolean canReceiveMoreCard() {
        return score().isEqualOrLessThan(DEALER_HIT_THRESHOLD);
    }
}
