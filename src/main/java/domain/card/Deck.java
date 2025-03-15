package domain.card;

import exceptions.BlackjackArgumentException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public final class Deck {

  private final Queue<TrumpCard> deck;

  public Deck(final Queue<TrumpCard> deck) {
    this.deck = new ArrayDeque<>(deck);
  }

  public static Deck createShuffledDecks(final int numberOfDeck) {
    final var deck = new ArrayList<>(IntStream.range(0, numberOfDeck)
        .boxed()
        .flatMap(i -> createSingleDeck().stream())
        .toList());
    Collections.shuffle(deck);
    return new Deck(new ArrayDeque<>(deck));
  }

  private static List<TrumpCard> createSingleDeck() {
    return Arrays.stream(Rank.values())
        .flatMap(rank -> Arrays.stream(Suit.values())
            .map(suit -> new TrumpCard(rank, suit)))
        .toList();
  }

  public TrumpCard draw() {
    if (deck.isEmpty()) {
      throw new BlackjackArgumentException("덱에 남아있는 카드가 없습니다.");
    }
    return deck.poll();
  }

  public List<TrumpCard> drawForInitialDeal() {
    final List<TrumpCard> cards = new ArrayList<>();
    cards.add(draw());
    cards.add(draw());
    return cards;
  }

  public int getNumberOfCards() {
    return deck.size();
  }
}
