package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.result.Result;

class DealerTest {

    private final Players players = new Players(List.of("짱구", "짱아"));
    private Dealer dealer = new Dealer();

    @BeforeEach
    void init() {
        players.getPlayers()
            .forEach(player -> {
                player.drawCard(new Card(Denomination.JACK, Suit.CLUBS));
                player.drawCard(new Card(Denomination.FIVE, Suit.CLUBS));
            });
    }

    @Test
    @DisplayName("카드의 합이 16 이하여서 뽑을 수 있는지 확인")
    void canDrawTest() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.THREE, Suit.CLUBS));

        assertThat(dealer.canDraw()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("결과가 정상적으로 판단되는지 확인")
    @CsvSource(value = {
        "FIVE,SIX,WIN",
        "KING,FIVE,DRAW",
        "TEN,JACK,LOSE"
    })
    void judgeTest(Denomination d1, Denomination d2, Result result) {
        dealer.drawCard(new Card(d1, Suit.DIAMONDS));
        dealer.drawCard(new Card(d2, Suit.DIAMONDS));

        Map<Player, Result> judgeResult = dealer.judgeResult(players);

        assertThat(judgeResult.get(players.getPlayers().get(0))).isEqualTo(result);
        assertThat(judgeResult.get(players.getPlayers().get(1))).isEqualTo(result);
    }
}
