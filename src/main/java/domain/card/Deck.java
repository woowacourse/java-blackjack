package domain.card;

import constant.Suit;
import exceptions.BlackjackArgumentException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Deck {

  private final Queue<TrumpCard> deck;

  public Deck(final Queue<TrumpCard> deck) {
    this.deck = new ArrayDeque<>(deck);
  }

  public static Deck createDecks(final int numberOfDeck) {
    final List<TrumpCard> deck = IntStream.range(0, numberOfDeck)
        .boxed()
        .flatMap(i -> createSingleDeck().stream())
        .toList();

    return new Deck(new ArrayDeque<>(deck));
  }

  private static List<TrumpCard> createSingleDeck() {
    return Arrays.stream(Rank.values())
        .flatMap(rank -> Arrays.stream(Suit.values())
            .map(suit -> new TrumpCard(rank, suit)))
        .toList();
  }

  public Deck shuffle() {
    var cards = new ArrayList<>(deck);
    Collections.shuffle(cards);
    return new Deck(new ArrayDeque<>(cards));
  }

  public int getNumberOfCards() {
    return deck.size();
  }

  public TrumpCard draw() {
    if (deck.isEmpty()) {
      throw new BlackjackArgumentException("덱에 남아있는 카드가 없습니다.");
    }
    return deck.poll();
  }
}
