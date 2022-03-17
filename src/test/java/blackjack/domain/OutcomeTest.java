package blackjack.domain;

import static blackjack.domain.Outcome.DRAW;
import static blackjack.domain.Outcome.LOSE;
import static blackjack.domain.Outcome.WIN;
import static blackjack.domain.Outcome.WIN_BLACKJACK;
import static blackjack.util.BlackjackTestUtil.createDealer;
import static blackjack.util.BlackjackTestUtil.createDeck;
import static blackjack.util.BlackjackTestUtil.createPlayer;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OutcomeTest {

    @ParameterizedTest
    @CsvSource(value = {"20, WIN", "19, DRAW", "18, LOSE"})
    @DisplayName("딜러와 플레이어 둘 다 버스트하지 않았을 경우 점수가 더 큰 쪽이 이긴다.")
    void bothNotBust(int playerScore, String outcomeName) {
        // given
        Dealer dealer = createDealer(19);
        Player player = createPlayer(playerScore);
        Outcome expected = Outcome.valueOf(outcomeName);

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    @DisplayName("플레이어가 버스트면 무조건 딜러가 이긴다")
    void playerBust(int additionalCardScore) {
        // given
        Player player = createPlayer(12);
        Dealer dealer = createDealer(6);
        CardDeck deckForPlayer = createDeck(10);
        CardDeck deckForDealer = createDeck(additionalCardScore);
        player.hit(deckForPlayer);
        dealer.hit(deckForDealer);

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트면 플레이어가 이긴다")
    void dealerBust() {
        // given
        Player player = createPlayer(20);

        Dealer dealer = createDealer(16);
        dealer.hit(createDeck(6));

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(WIN);
    }

    @Test
    @DisplayName("똑같이 21점이어도 블랙잭이 이긴다.")
    void blackJackDoesNotDefeat() {
        // given
        Dealer dealer = createDealer(16);

        Player player = createPlayer(21);
        dealer.hit(createDeck(5));

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(WIN_BLACKJACK);
    }

    @Test
    @DisplayName("블랙잭 끼리는 비긴다")
    void blackJackDrawWithBlackJack() {
        // given
        Dealer dealer = createDealer(21);

        Player player = createPlayer(21);

        // when
        Outcome actual = Outcome.judge(player, dealer);

        // then
        assertThat(actual).isEqualTo(DRAW);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN_BLACKJACK,1.5", "WIN,1", "DRAW,0", "LOSE,-1"})
    @DisplayName("각각의 결과값은 수익률을 가지고 있다")
    void returnOpposite(String inputName, double expected) {
        // given
        Outcome outcome = Outcome.valueOf(inputName);

        // when
        double actual = outcome.getProfitRate();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
