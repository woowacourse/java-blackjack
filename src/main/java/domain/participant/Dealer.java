package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.name.Name;
import vo.Profit;

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

    public Profit calculateProfit(Players players) {
        int totalPlayersProfit = players.getPlayers()
                                   .stream()
                                   .map(player -> player.profit().value())
                                   .reduce(0, Integer::sum);
        return new Profit(-totalPlayersProfit);
    }

    @Override
    public boolean canReceiveMoreCard() {
        return score() <= DEALER_HIT_THRESHOLD;
    }
}
