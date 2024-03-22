package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Deck;
import blackjack.domain.fixture.CardFixture;
import blackjack.domain.game.Result;
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

    @DisplayName("플레이어의 점수가 딜러보다 높으면 배팅 금액 만큼 수익을 얻는다")
    @Test
    void testJudgePlayersResult1() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("mark", new Betting(1000));
        Players players = new Players(List.of(player));
        dealer.putCard(CardFixture.diamond3());
        player.putCard(CardFixture.heartJack());

        Result result = dealer.judgePlayersResult(players);

        assertThat(result.getResults().get(player)).isEqualTo(1000);
    }

    @DisplayName("플레이어와 딜러의 점수가 같으면 수익이 없다")
    @Test
    void testJudgePlayersResult2() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("mark", new Betting(1000));
        Players players = new Players(List.of(player));
        dealer.putCard(CardFixture.diamond3());
        player.putCard(CardFixture.diamond3());

        Result result = dealer.judgePlayersResult(players);

        assertThat(result.getResults().get(player)).isEqualTo(0);
    }

    @DisplayName("플레이어가 버스트 되면 항상 배팅 금액을 잃는다")
    @Test
    void testJudgePlayersResult3() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk", new Betting(1000));
        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(-1000);
    }

    @DisplayName("플레이어의 점수가 딜러 보다 낮으면 배팅 금액을 잃는다")
    @Test
    void testJudgePlayersResult4() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk", new Betting(1000));
        dealer.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.diamond3());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(-1000);
    }

    @DisplayName("플레이어의 점수가 블랙잭이고 딜러가 블랙잭이 아니라면 배팅 금액의 1.5배의 수익을 얻는다")
    @Test
    void testJudgePlayersResult5() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk", new Betting(1000));
        dealer.putCard(CardFixture.heartJack());
        dealer.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.cloverAce());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(1500);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 수익이 없다")
    @Test
    void testJudgePlayersResult6() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk", new Betting(1000));
        dealer.putCard(CardFixture.heartJack());
        dealer.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(0);
    }

    @DisplayName("플레이어가 버스트되지 않고 딜러가 버스트된다면 배팅 금액 만큼 수익이 얻는다")
    @Test
    void testJudgePlayersResult7() {
        Dealer dealer = new Dealer(new Deck(List.of()));
        Player player = new Player("pk", new Betting(1000));
        dealer.putCard(CardFixture.heartJack());
        dealer.putCard(CardFixture.heartJack());
        dealer.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.cloverAce());
        player.putCard(CardFixture.diamond3());

        Result result = dealer.judgePlayersResult(new Players(List.of(player)));

        assertThat(result.getResults().get(player)).isEqualTo(1000);
    }
}
