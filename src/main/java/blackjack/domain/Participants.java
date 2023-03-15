package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.common.BlackJackRule.INITIAL_CARD_SIZE;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Deck deck, final Map<String, Integer> playerStatuses) {
        this.dealer = new Dealer(makeInitialCards(deck));
        this.players = playerStatuses.keySet().stream()
                .map(name -> new Player(makeInitialCards(deck), name, playerStatuses.get(name)))
                .collect(Collectors.toList());
    }

    private Cards makeInitialCards(final Deck deck) {
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_SIZE.getValue(); i++) {
            initialCards.add(deck.draw());
        }
        return new Cards(initialCards);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
