package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import blackjack.exception.CardDuplicateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

  private List<Card> deck;
  private String name;

  public Player(String name) {
    this.deck = new ArrayList<>();
    this.name = name;
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

  public int getScore() {

    return getScoreIncludeAce(getScoreExceptAce());
  }

  private int getScoreExceptAce() {
    return deck.stream().filter(card -> !card.isAce()).mapToInt(Card::getScore).sum();
  }

  private int getScoreIncludeAce(int scoreExceptAce) {
    return deck.stream().filter(Card::isAce).mapToInt(card -> {
      if(scoreExceptAce + card.getScore() + 10 > 21) {
        return card.getScore();
      }
      return card.getScore() + 10;
    }).sum() + scoreExceptAce;
  }

  public Status getStatus() {
    return Status.evaluateScore(getScore());
  }

  public String getName() {
    return name;
  }

  public boolean isSameName(String name) {
    return getName().equals(name);
  }

}
