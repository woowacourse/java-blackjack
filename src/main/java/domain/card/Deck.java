package domain.card;

import domain.card.cardsGenerator.CardsGenerator;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Set;
import java.util.Stack;

public class Deck {
    private final static int INIT_COUNT = 2;

    private final Stack<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        cards = new Stack<>();
        cards.addAll(cardsGenerator.generate());
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
        return cards.pop();
    }

    public void giveCardTo(Participant participant) {
        participant.addCard(pick());
    }

    public int countDealerDraw(Dealer dealer) {
        int count = 0;
        while (dealer.hasToDraw()) {
            giveCardTo(dealer);
            count++;
        }
        return count;
    }

    public void handoutCards(Dealer dealer, Players players) {
        giveInitCards(dealer);
        Set<Player> participants = players.getPlayers();
        for (Participant participant : participants) {
            giveInitCards(participant);
        }
    }

    private void giveInitCards(Participant participant) {
        for (int i = 0; i < INIT_COUNT; ++i) {
            participant.addCard(pick());
        }
    }
}
