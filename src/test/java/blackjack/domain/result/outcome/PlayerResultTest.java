package blackjack.domain.result.outcome;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.Figure;
import blackjack.domain.card.component.Type;
import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static blackjack.domain.card.Card.NULL_ERR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerResultTest {

    @DisplayName("예외 테스트: 생성자에 Null이 들어온 경우 Exception 발생")
    @Test
    void test1() {
        assertThatThrownBy(() -> new PlayerResult<>(null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult<>(new Player("쪼밀리"), new Dealer(), null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult<>(new BettingPlayer("쪼밀리", 1000), new Dealer(), null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult<>(new Player("쪼밀리"), null, new WinOrLoseResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult<>(new BettingPlayer("쪼밀리", 1000), null, new BettingResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult<>(null, new Dealer(), new WinOrLoseResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult<>(null, new Dealer(), new BettingResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("승/무/패 게임 결과 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE, HEART, BLACKJACK", "TEN, HEART, WIN", "FIVE, HEART, DRAW", "TWO, HEART, LOSE"})
    void test2(Type type, Figure figure, ResultType expectedResult) {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.JACK, Figure.DIAMOND));
        dealer.addCard(Card.of(Type.FIVE, Figure.DIAMOND));

        Player player = new Player("쪼밀리");
        player.addCard(Card.of(Type.JACK, Figure.HEART));
        player.addCard(Card.of(type, figure));

        PlayerResult<Player, ResultType, Map<ResultType, Long>> result
                = new PlayerResult<>(player, dealer, new WinOrLoseResultResolver());

        ResultType actualResult = result.showPlayerResult();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("배팅 게임 결과 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE, HEART, 1500", "TEN, HEART, 1000", "FIVE, HEART, 0", "TWO, HEART, -1000"})
    void test3(Type type, Figure figure, double expectedResult) {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.JACK, Figure.DIAMOND));
        dealer.addCard(Card.of(Type.FIVE, Figure.DIAMOND));

        BettingPlayer bettingPlayer = new BettingPlayer("쪼밀리", 1000);
        bettingPlayer.addCard(Card.of(Type.JACK, Figure.HEART));
        bettingPlayer.addCard(Card.of(type, figure));

        PlayerResult<BettingPlayer, Double, Double> result
                = new PlayerResult<>(bettingPlayer, dealer, new BettingResultResolver());

        Double actualResult = result.showPlayerResult();

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
