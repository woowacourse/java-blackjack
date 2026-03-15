package domain.card;

import constant.PolicyConstant;
import constant.Rank;
import constant.Suit;
import exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardMachine {

    private final Map<Card, Integer> decks;

    public CardMachine() {
        decks = new HashMap<>();
    }

    public Card drawCard() {
        if (isDrawFinished()) {
            throw new IllegalArgumentException(ErrorMessage.NO_CARDS_LEFT_TO_DRAW.getMessage());
        }
        Card newCard = pickRandomCard();
        while (decks.getOrDefault(newCard, 0) >= PolicyConstant.DECK_COUNT) {
            newCard = pickRandomCard();
        }
        decks.put(newCard, decks.getOrDefault(newCard, 0) + 1);
        return newCard;
    }

    private boolean isDrawFinished() {
        return decks.size() >= PolicyConstant.DECK_SIZE && areAllCardsExhausted();
    }

    private boolean areAllCardsExhausted() {
        return decks.values().stream()
            .allMatch(count -> count >= PolicyConstant.DECK_COUNT);
    }

    private Card pickRandomCard() {
        ArrayList<Rank> ranks = new ArrayList<>(List.of(Rank.values()));
        ArrayList<Suit> suits = new ArrayList<>(List.of(Suit.values()));
        Collections.shuffle(ranks);
        Collections.shuffle(suits);

        return new Card(ranks.getFirst(), suits.getFirst());
    }
}
