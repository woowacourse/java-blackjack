package domain.card;

import domain.cardsGenerator.CardsGenerator;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;

public class Deck {
    private final static int INIT_COUNT = 2;

    private final List<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        cards = cardsGenerator.generate();
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
        Card card = cards.getLast();
        cards.removeLast();
        return card;
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

    public void handoutCards(Dealer dealer, List<Player> participants) {
        giveInitCards(dealer);
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
