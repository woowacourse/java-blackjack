package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import blackjack.domain.result.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        Participant player = new Player("pobi", cards -> 0);
        Card card = new Card(CardType.DIAMOND, CardValue.TEN);
        player.receiveCard(card);
        Assertions.assertThat(player.showCards().contains(card)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("딜러가 카드를 한장을 더 뽑을 수 있는지 확인한다")
    @CsvSource(value = {
            "21:true", "22:false"
    }, delimiter = ':')
    void test_player_is_receive_card(int totalScore, boolean actual) {
        //given
        Participant player = new Player("pobi", cards -> totalScore);

        //when
        boolean isReceived = player.isReceiveCard();

        //then
        assertThat(isReceived).isEqualTo(actual);
    }

    @DisplayName("배팅하는 기능을 테스트한다")
    @Test
    void testBet() {
        //given
        Player player = new Player("pobi", cards -> 0);

        //when
        player.bet(1_000);

        //then
        assertThat(player).extracting("battingMoney").extracting("value").isEqualTo(1_000);
    }

    @DisplayName("Player의 수익을 계산하는기능")
    @ParameterizedTest
    @MethodSource
    void testCalculateEarningMoney(int bettingMoney, GameResult gameResult, double expected) {
        //given
        Player player = new Player("pobi", cards -> 0);
        player.bet(bettingMoney);

        //when
        double actual = player.calculateEarnings(gameResult);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testCalculateEarningMoney() {
        return Stream.of(
                Arguments.of(1_000, GameResult.WIN, 1000.0),
                Arguments.of(1_000, GameResult.DRAW, 0.0),
                Arguments.of(1_000, GameResult.LOSE, -1000.0)
        );
    }

    @DisplayName("Player가 BlackJack일 때, Player의 수익을 계산하는기능")
    @ParameterizedTest
    @CsvSource(value = {
            "1000:WIN:1500.0", "1000:DRAW:0.0", "1000:LOSE:-1000.0"
    }, delimiter = ':')
    void testCalculateEarningMoneyIfPlayerIsBlackJack(int bettingMoney, GameResult gameResult, double expected) {
        //given
        Player player = new Player("pobi", cards -> 21);
        player.receiveCard(new Card(CardType.DIAMOND, CardValue.ACE));
        player.receiveCard(new Card(CardType.HEART, CardValue.TEN));
        player.bet(bettingMoney);

        //when
        double actual = player.calculateEarnings(gameResult);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
