package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ParticipantCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Deck deck, final Map<String, Integer> playerStatuses) {
        this.dealer = new Dealer(makeInitialCards(deck));
        this.players = playerStatuses.keySet().stream()
                .map(name -> new Player(makeInitialCards(deck), name, playerStatuses.get(name)))
                .collect(Collectors.toList());
    }

    private ParticipantCards makeInitialCards(final Deck deck) {
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            initialCards.add(deck.draw());
        }
        return new ParticipantCards(initialCards);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
