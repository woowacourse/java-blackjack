package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.Figure;
import blackjack.domain.card.component.Type;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static blackjack.domain.participant.Player.NOT_SUPPORTED_REPLY_ERR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("카드를 더 받을 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"SIX, true", "SEVEN, false", "ACE, true"})
    void test1(Type type, boolean expected) {
        Player player = new Player("포비");
        player.addCard(Card.of(Type.TEN, Figure.CLOVER));
        player.addCard(Card.of(Type.FIVE, Figure.CLOVER));
        player.addCard(Card.of(type, Figure.CLOVER));

        boolean actual = player.canGetMoreCard();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("게임 결과 생성 확인")
    @ParameterizedTest
    @CsvSource(value = {"FOUR, LOSE", "FIVE, DRAW", "SIX, WIN"})
    void test2(Type type, ResultType resultType) {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.TEN, Figure.HEART));
        dealer.addCard(Card.of(Type.FIVE, Figure.HEART));

        Player player = new Player("포비");
        player.addCard(Card.of(Type.TEN, Figure.CLOVER));
        player.addCard(Card.of(type, Figure.CLOVER));

        PlayerResult actualResult = player.createPlayerResult(dealer);
        PlayerResult expectedResult = new PlayerResult(player, resultType);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("추가 카드 희망 여부 확인")
    @ParameterizedTest
    @CsvSource(value = {"y, true", "n, false"})
    void test3(String reply, boolean expected) {
        Player player = new Player("포비");
        boolean actual = player.wantMoreCard(reply);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("예외 테스트: 추가 카드 희망 여부에 y/n이 외의 값이 전달된 경우 Exception 발생")
    @ParameterizedTest
    @NullAndEmptySource
    @CsvSource(value = "x")
    void test4(String reply) {
        Player player = new Player("포비");

        assertThatThrownBy(() -> player.wantMoreCard(reply))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_SUPPORTED_REPLY_ERR_MSG);
    }
}
