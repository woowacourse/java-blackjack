package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.User;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    public static final int INITIAL_CARD_COUNT = 2;

    private final Queue<Card> deck = new LinkedList<>();

    public Deck(CardShuffleStrategy shuffleStrategy) {
        initDeck(shuffleStrategy);
    }

    private void initDeck(CardShuffleStrategy shuffleStrategy) {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        List<Card> shuffleCards = shuffleStrategy.shuffle(cards);
        this.deck.addAll(shuffleCards);
    }

    public void provideInitCards(Players players, Dealer dealer) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            for (Player player : players.all()) {
                player.addCard(deck.poll());
            }
            dealer.addCard(deck.poll());
        }
    }

    public void provideOneCard(User user) {
        user.addCard(deck.poll());
    }
}

