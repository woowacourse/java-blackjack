package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.money.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Rank.EIGHT;
import static blackjack.domain.card.Rank.FIVE;
import static blackjack.domain.card.Rank.JACK;
import static blackjack.domain.card.Rank.KING;
import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.QUEEN;
import static blackjack.domain.card.Rank.SEVEN;
import static blackjack.domain.card.Rank.TEN;
import static blackjack.domain.card.Rank.THREE;
import static blackjack.domain.card.Rank.TWO;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("심판")
public class ResultJudgeTest {
    @Test
    @DisplayName("두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이기는지 테스트한다.")
    void playerWinTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(NINE, SPADE), Card.of(QUEEN, CLUB)));
        dealer.draw(List.of(Card.of(EIGHT, SPADE), Card.of(QUEEN, CLUB)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("플레이어는 자신의 숫자 합이 21을 초과할 경우 패배한다.")
    void playerLoseWhenBustTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(NINE, SPADE), Card.of(QUEEN, CLUB), Card.of(THREE, CLUB)));
        dealer.draw(List.of(Card.of(EIGHT, SPADE), Card.of(QUEEN, CLUB), Card.of(TWO, SPADE)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("딜러의 합과 플레이어의 합이 같으면 무승부이다.")
    void pushTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(NINE, SPADE), Card.of(SEVEN, CLUB), Card.of(THREE, CLUB)));
        dealer.draw(List.of(Card.of(NINE, SPADE), Card.of(SEVEN, CLUB), Card.of(THREE, SPADE)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.PUSH);
    }

    @Test
    @DisplayName("딜러의 합과 플레이어의 합이 블랙잭으로 같으면 무승부이다.")
    void pushWhenSameBlackjackTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(ACE, SPADE), Card.of(TEN, CLUB)));
        dealer.draw(List.of(Card.of(ACE, DIAMOND), Card.of(TEN, SPADE)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.PUSH);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 블랙잭 승리한다.")
    void playerDealCardsBlackjackTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(ACE, CLUB), Card.of(JACK, CLUB)));
        dealer.draw(List.of(Card.of(NINE, SPADE), Card.of(SEVEN, CLUB), Card.of(FIVE, CLUB)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 낮을 경우 패배한다.")
    void playerLoseTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(TWO, CLUB), Card.of(JACK, CLUB)));
        dealer.draw(List.of(Card.of(NINE, SPADE), Card.of(SEVEN, CLUB), Card.of(FIVE, CLUB)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 bust 이면, 플레이어가 패배한다.")
    void playerDealerAllBustPlayerLoseTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(KING, CLUB), Card.of(JACK, CLUB), Card.of(THREE, CLUB)));
        dealer.draw(List.of(Card.of(KING, CLUB), Card.of(JACK, CLUB), Card.of(THREE, CLUB)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 bust이고 플레이가 bust가 아닐 경우, 플레이어가 승리한다.")
    void dealerBustPlayerNonBustWinTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(KING, CLUB), Card.of(JACK, CLUB)));
        dealer.draw(List.of(Card.of(KING, CLUB), Card.of(JACK, CLUB), Card.of(THREE, CLUB)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이 아닌 21이면 딜러가 승리한다.")
    void dealerBlackjackAndPlayerMaximumTest() {
        Player player = new Player(new Name("lemone"), new Chip(0L));
        Dealer dealer = new Dealer(new Chip(0L));
        ResultJudge resultJudge = new ResultJudge();

        player.draw(List.of(Card.of(KING, CLUB), Card.of(NINE, CLUB), Card.of(TWO, CLUB)));
        dealer.draw(List.of(Card.of(ACE, CLUB), Card.of(JACK, CLUB)));

        assertThat(resultJudge.judgePlayerResult(dealer, player))
                .isEqualTo(PlayerResult.LOSE);
    }
}
