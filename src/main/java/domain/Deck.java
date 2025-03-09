package domain;

import constant.Suit;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

  private final Queue<TrumpCard> deck;

  public Deck(final Queue<TrumpCard> deck) {
    validateDuplicateCard(deck);
    this.deck = new ArrayDeque<>(deck);
  }

  public static Deck generateShuffledDeck() {
    final List<TrumpCard> cards = Arrays.stream(Rank.values())
        .flatMap(Deck::generateCardEachSuitOnRank)
        .collect(Collectors.toList());
    Collections.shuffle(cards);
    return new Deck(new ArrayDeque<>(cards));
  }

  private static Stream<TrumpCard> generateCardEachSuitOnRank(Rank rank) {
    return Arrays.stream(Suit.values())
        .map(suit -> new TrumpCard(rank, suit));
  }

  private void validateDuplicateCard(final Queue<TrumpCard> deck) {
    final HashSet<TrumpCard> notDuplicateCards = new HashSet<>(deck);
    if (deck.size() != notDuplicateCards.size()) {
      throw new IllegalArgumentException("덱에는 중복된 카드가 들어올 수 없습니다!");
    }
  }

  public TrumpCard draw() {
    if (deck.isEmpty()) {
      throw new NullPointerException("덱에 남아있는 카드가 없습니다.");
    }
    return deck.poll();
  }
}
