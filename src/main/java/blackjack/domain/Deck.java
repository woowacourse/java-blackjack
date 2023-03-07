package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public List<Card> draw(int count) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(takeOutOneCard());
        }
        return drawnCards;
    }

    public void handCardsTo(Participant participant, int count) {
        for (Card card : draw(count)) {
            participant.take(card);
        }
    }

    public void handCardsTo(List<Player> players, int eachCount) {
        players.forEach(player -> handCardsTo(player, eachCount));
    }

    private Card takeOutOneCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 꺼낼 카드가 없습니다.");
        }
        return cards.poll();
    }
}
