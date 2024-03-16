package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러 생성")
    @Test
    void create() {
        assertThatCode(() -> new Dealer(new Deck(List.of(CardFixture.diamond3()))))
                .doesNotThrowAnyException();
    }

    @DisplayName("덱에서 카드를 한 장 반환한다")
    @Test
    void draw() {
        Dealer dealer = new Dealer(new Deck(List.of(CardFixture.diamond3())));

        assertThat(dealer.draw()).isEqualTo(new Card(CardSuit.DIAMOND, CardNumber.THREE));
    }

    @DisplayName("플레이어의 점수가 딜러보다 높으면 이긴다")
    @Test
    void testJudgePlayersResult1() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk");

        dealer.putCard(CardFixture.diamond3());
        player.putCard(CardFixture.heartJack());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(ResultStatus.WIN);
    }

    @DisplayName("플레이어가 버스트 되면 항상 진다")
    @Test
    void testJudgePlayersResult2() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk");

        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(ResultStatus.LOSE);
    }

    @DisplayName("플레이어의 점수가 딜러보다 낮으면 진다")
    @Test
    void testJudgePlayersResult3() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk");

        dealer.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.diamond3());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(ResultStatus.LOSE);
    }
}
