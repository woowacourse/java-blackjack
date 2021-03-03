package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

  private Game game;

  @BeforeEach
  void setUp() {
    Player dealer = new Dealer();
    List<Card> cards = Arrays.asList(
        new Card(Symbol.CLOVER, CardNumber.ACE),
        new Card(Symbol.HEART, CardNumber.ACE),
        new Card(Symbol.SPADE, CardNumber.ACE),
        new Card(Symbol.DIAMOND, CardNumber.ACE),
        new Card(Symbol.CLOVER, CardNumber.TWO),
        new Card(Symbol.HEART, CardNumber.TWO),
        new Card(Symbol.HEART, CardNumber.THREE)
    );
    game = new Game(new Cards(cards), dealer, new Gamers("nabom", "neozal"));
  }

  @Test
  @DisplayName("Game 초기화 시 각 플레이어에게 두 장의 카드를 배분")
  void initialize_drawTwoCardsToPlayers() {
    assertThat(game.getDealer().getDeckAsList().size()).isEqualTo(2);
    for (Player player : game.getGamersAsList()) {
      assertThat(player.getDeckAsList().size()).isEqualTo(2);
    }
  }

}
