package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  @DisplayName("각 플레이어 덱에 카드를 추가한다.")
  void addCardToDeck() {
    Player player = new Player();
    Card card = new Card(Symbol.CLOVER, CardNumber.EIGHT);

    player.addCardToDeck(card);
    List<Card> deck = player.getDeck();

    Assertions.assertThat(deck).containsExactly(card);
  }


}
