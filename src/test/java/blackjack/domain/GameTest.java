package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.GamerState;
import blackjack.domain.player.Player;
import blackjack.exception.PlayerNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

  private Game game;

  @BeforeEach
  void setUp() {
    Dealer dealer = new Dealer();
    Gamer gamer1 = new Gamer("nabom");
    Gamer gamer2 = new Gamer("neozal");
    List<Card> cards = Arrays.asList(
        new Card(Symbol.CLOVER, CardNumber.ACE),
        new Card(Symbol.HEART, CardNumber.ACE),
        new Card(Symbol.SPADE, CardNumber.ACE),
        new Card(Symbol.DIAMOND, CardNumber.ACE),
        new Card(Symbol.CLOVER, CardNumber.TWO),
        new Card(Symbol.HEART, CardNumber.TWO),
        new Card(Symbol.HEART, CardNumber.THREE)
    );
    game = new Game(new Cards(cards), dealer, Arrays.asList(gamer1, gamer2));
  }

  @Test
  @DisplayName("Game 초기화 시 각 플레이어에게 두 장의 카드를 배분")
  void initialize_drawTwoCardsToPlayers() {
    assertThat(game.getDealer().getDeck().size()).isEqualTo(2);
    for (Player player : game.getGamers()) {
      assertThat(player.getDeck().size()).isEqualTo(2);
    }
  }

  @Test
  @DisplayName("이름을 받아 해당하는 플레이어를 찾을 때 플레이어가 존재하지 않는다면 예외 발생")
  void drawTo_PlayerNotFoundException() {
    String name = "pobi";

    assertThatThrownBy(
        () -> game.drawTo(name)
    ).isInstanceOf(PlayerNotFoundException.class);
  }

  @Test
  @DisplayName("이름을 받아 그 이름에 해당하는 플레이어에게 카드 배분 후 playerState 리턴")
  void drawTo() {
    String name = "nabom";

    GamerState gamerState = game.drawTo(name);
    assertThat(gamerState.getStatus()).isEqualTo(Status.HIT);
    assertThat(gamerState.getName()).isEqualTo(name);
    assertThat(gamerState.getDeck()).containsExactly(
        new Card(Symbol.HEART, CardNumber.ACE),
        new Card(Symbol.CLOVER, CardNumber.TWO),
        new Card(Symbol.HEART, CardNumber.THREE)
    );
  }
}
