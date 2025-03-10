package domain.card;

import domain.card.cardsGenerator.CardsGenerator;
import domain.participant.Participant;
import java.util.List;
import java.util.Stack;

public class Deck {
    private final static int INIT_COUNT = 2;

    private final Stack<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        List<Card> generateCards = cardsGenerator.generate();
        cards = new Stack<>();
        cards.addAll(generateCards);
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
        return cards.pop();
    }

    public void handoutCards(List<Participant> participants) {
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
