package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Participant;
import blackjack.utils.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GameTable implements Playable {
    private final Queue<Card> cards;

    public GameTable(CardDeck cardDeck){
        this.cards = cardDeck.getCards();
    }

    @Override
    public void giveCard(Participant participant) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 소진되었습니다.");
        }
        if(participant.isAvailableToTake()){
            participant.takeCard(cards.poll());
        }
    }

    //todo : 삭제
    @Override
    public Card pop() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 소진되었습니다.");
        }
        return cards.poll();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public List<Card> initCards() {
        List<Card> cardsValue = new ArrayList<>();
        cardsValue.add(pop());
        cardsValue.add(pop());
        return cardsValue;
    }

}
