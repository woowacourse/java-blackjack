package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Player;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {

    @DisplayName("플레이어 생성")
    @Test
    void create() throws InvalidNameInputException {
        Player root = new Player("root");
        assertThat(root).isEqualTo(new Player("root"));
    }

    @Test
    @DisplayName("카드 한 장 뽑는 기능")
    void drawCard() throws InvalidNameInputException {
        Player root = new Player("root");

        root.draw(Card.valueOf(Shape.DIAMOND, CardValue.ACE));
        assertThat(root.getHand()).isEqualToComparingFieldByField(
                new Hand(Collections.singletonList(Card.valueOf(Shape.DIAMOND, CardValue.ACE))));
    }

    @Test
    @DisplayName("유저가 카드를 계속 더 받을건지 입력")
    void willContinue() throws InvalidNameInputException {
        Player root = new Player("root");

        root.willContinue("y");
        assertThat(root.isContinue()).isTrue();

        root.willContinue("n");
        assertThat(root.isContinue()).isFalse();

        assertThatThrownBy(() ->
                root.willContinue("x"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 입력 입니다.");
    }

    @Test
    @DisplayName("버스트가 없는 경우 승패 결과")
    void match() throws InvalidNameInputException {
        assertThat(TestSetUp.createWinner().match(TestSetUp.createDealer())).isEqualTo(ResultType.WIN);
        assertThat(TestSetUp.createTiePlayer().match(TestSetUp.createDealer())).isEqualTo(ResultType.TIE);
        assertThat(TestSetUp.createLoser().match(TestSetUp.createDealer())).isEqualTo(ResultType.LOSE);
    }

    @Test
    @DisplayName("버스트인 경우 승패 결과")
    void matchWithBust() throws InvalidNameInputException {
        assertThat(TestSetUp.createBustPlayer().match(TestSetUp.createDealer())).isEqualTo(ResultType.LOSE);
        assertThat(TestSetUp.createBustPlayer().match(TestSetUp.createDealer())).isEqualTo(ResultType.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트인 경우 승패 결과")
    void matchWithDealerBust() throws InvalidNameInputException {
        assertThat(TestSetUp.createWinner().match(TestSetUp.createBustDealer())).isEqualTo(ResultType.WIN);
        assertThat(TestSetUp.createLoser().match(TestSetUp.createBustDealer())).isEqualTo(ResultType.WIN);
        assertThat(TestSetUp.createBustPlayer().match(TestSetUp.createBustDealer())).isEqualTo(ResultType.LOSE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10000", "-1234567"})
    @DisplayName("돈 생성 기능")
    void checkMoney(String input) {
        Money money = new Money(input);
        assertThat(TestSetUp.createWinner().getMoney()).isEqualTo(0);
        assertThat(money.getValue()).isEqualTo(Double.parseDouble(input));
    }

    @Test
    void name() {
    }
}
