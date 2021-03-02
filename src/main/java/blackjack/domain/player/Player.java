package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import blackjack.exception.CardDuplicateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

  private List<Card> deck;

  public Player() {
    this.deck = new ArrayList<>();
  }

  public void addCardToDeck(Card card) {
    if (deck.contains(card)) {
      throw new CardDuplicateException();
    }

    deck.add(card);
  }

  public List<Card> getDeck() {
    return Collections.unmodifiableList(deck);
  }

  protected int getScore() {
    return deck.stream()
        .mapToInt(Card::getScore)
        .sum();
  }

  public Status getStatus() {
    return Status.evaluateScore(getScore());
  }
}
