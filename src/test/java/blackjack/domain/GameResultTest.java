package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

  GameResult gameResult;

  @BeforeEach
  void setUp() {
    Map<Player, Integer> gamerScore = new HashMap<>();

    gamerScore.put(new Gamer("nabom"), 15);
    gamerScore.put(new Gamer("neozal"), 8);

    Dealer dealer = new Dealer();
    dealer.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.KING));
    Gamer gamer1 = new Gamer("nabom");
    gamer1.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.JACK));
    gamer1.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.FIVE));
    Gamer gamer2 = new Gamer("neozal");
    gamer2.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.THREE));
    gamer2.addCardToDeck(new Card(Symbol.HEART, CardNumber.FIVE));

    gameResult = new GameResult(
        dealer, Arrays.asList(gamer1, gamer2)
    );
  }

  @Test
  @DisplayName("게이머의 최종 결과를 반환한다.")
  void getGamerResult() {
    Map<String, Boolean> gamerResult = gameResult.getGamerResult();

    assertThat(gamerResult.get("nabom")).isTrue();
    assertThat(gamerResult.get("neozal")).isFalse();
  }

  @Test
  @DisplayName("딜러의 최종 결과를 반환한다.")
  void getDealerResult() {
    List<Boolean> dealerResult = gameResult.getDealerResult();
    Map<Boolean, Long> collect = dealerResult.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    assertThat(collect.get(true)).isEqualTo(1);
    assertThat(collect.get(false)).isEqualTo(1);
  }
}
