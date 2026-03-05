package domain;

import constant.Rank;
import constant.Suit;
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
            return null;
        }

        Card newCard = pickRandomCard();
        while (decks.getOrDefault(newCard, 0) >= 6) {
            newCard = pickRandomCard();
        }
        decks.put(newCard, decks.getOrDefault(newCard, 0) + 1);

        return newCard;
    }

    private boolean isDrawFinished() {
        return decks.size() >= 52 && validateCardCount();
    }

    private boolean validateCardCount() {
        return decks.values().stream()
            .allMatch(count -> count >= 6);
    }

    private Card pickRandomCard() {
        ArrayList<Rank> ranks = new ArrayList<>(List.of(Rank.values()));
        ArrayList<Suit> suits = new ArrayList<>(List.of(Suit.values()));
        Collections.shuffle(ranks);
        Collections.shuffle(suits);

        return new Card(ranks.getFirst(), suits.getFirst());
    }
}
