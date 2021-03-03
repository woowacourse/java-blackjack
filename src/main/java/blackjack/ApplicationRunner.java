package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;

public class ApplicationRunner {

  public static void main(String[] args) {
   new BlackjackController(new Cards(CardFactory.getNormalCards())).run();
  }
}
