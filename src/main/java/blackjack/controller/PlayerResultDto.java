package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import java.util.List;

public class PlayerResultDto {

  private List<Card> cardList;
  private String name;
  private int score;

  public PlayerResultDto(Player player) {
    this.cardList = player.getDeck();
    this.name = player.getName();
    this.score = player.getScore();
  }

  public List<Card> getCardList() {
    return cardList;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }
}
