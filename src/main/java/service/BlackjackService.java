package service;

import domain.Card;
import domain.CardDeck;
import infra.CardShuffler;
import java.util.ArrayList;
import java.util.List;

public class BlackjackService {

    private final CardShuffler cardShuffler;

    public BlackjackService(CardShuffler cardShuffler) {
        this.cardShuffler = cardShuffler;
    }

    public List<Card> drawCard(CardDeck cardDeck, int drawCount) {

        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < drawCount; i++) {
            int cardIndex = cardShuffler.shuffleCardDeck(cardDeck.getDeckSize());
            cards.add(cardDeck.getCardOf(cardIndex));
            cardDeck.removeCardOf(cardIndex);
        }

        return cards;
    }
}
