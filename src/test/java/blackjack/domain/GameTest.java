package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  @DisplayName("Game 초기화 시 각 플레이어에게 두 장의 카드를 배분")
  void initialize_drawTwoCardsToPlayers() {
    Dealer dealer = new Dealer();
    Player player1 = new Player();
    Player player2 = new Player();
    List<Card> normalCards = CardFactory.getNormalCards();
    Game game = new Game(new Cards(normalCards), dealer, Arrays.asList(player1, player2));

    assertThat(game.getDealer().getDeck().size()).isEqualTo(2);
    for (Player player : game.getPlayers()) {
      assertThat(player.getDeck().size()).isEqualTo(2);
    }
  }
}
