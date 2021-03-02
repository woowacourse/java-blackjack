package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

  private List<Card> deck;

  public Player() {
    this.deck = new ArrayList<>();
  }

  public void addCardToDeck(Card card) {
    deck.add(card);
  }

  public List<Card> getDeck() {
    return Collections.unmodifiableList(deck);
  }
}
