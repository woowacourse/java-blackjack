package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.state.*;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @DisplayName("플레이어 생성")
    @Test
    void create() throws InvalidNameInputException {
        Player root = new Player("root", "0");
        assertThat(root).isEqualTo(new Player("root", "0"));
    }

    @Test
    @DisplayName("카드 한 장 뽑는 기능")
    void drawCard() throws InvalidNameInputException {
        Player root = new Player("root", "0");

        root.draw(Card.valueOf(Shape.DIAMOND, CardValue.ACE));
        assertThat(root.getHand()).isEqualToComparingFieldByField(
                new Hand(Collections.singletonList(Card.valueOf(Shape.DIAMOND, CardValue.ACE))));
    }

    @Test
    @DisplayName("유저가 카드를 계속 더 받을건지 입력")
    void willContinue() throws InvalidNameInputException {
        Player player = TestSetUp.createTiePlayer();

        player.willContinue("y");
        assertThat(player.isContinue()).isTrue();

        player.willContinue("n");
        assertThat(player.isContinue()).isFalse();

        assertThatThrownBy(() ->
                player.willContinue("x"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 입력 입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10000", "1234567"})
    @DisplayName("돈 생성 기능")
    void checkMoney(String input) {
        Money money = new Money(input);
        assertThat(TestSetUp.createBlackJackPlayer().getMoney()).isEqualTo(0);
        assertThat(money.getValue()).isEqualTo(Double.parseDouble(input));
    }

    @Test
    @DisplayName("상태 변화 기능")
    void updateState() {
        Player aaron = new Player("aaron", "0");
        assertThat(aaron.getState()).isInstanceOf(NotStarted.class);
        assertThat(TestSetUp.createBlackJackPlayer().getState()).isInstanceOf(BlackJackState.class);

        aaron = TestSetUp.createTiePlayer();
        assertThat(aaron.getState()).isInstanceOf(Hit.class);
        assertThat(TestSetUp.createBustPlayer().getState()).isInstanceOf(Bust.class);

        aaron.willContinue("n");
        assertThat(aaron.getState()).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("수익 구하는 기능")
    void getProfit() {
        Dealer dealer = TestSetUp.createDealer();
        assertThat(TestSetUp.createBlackJackPlayerWithMoney("1000").getProfit(dealer)).isEqualTo(1000 * 1.5);
        assertThat(TestSetUp.createBustPlayerWithMoney("1000").getProfit(dealer)).isEqualTo(1000 * -1);

        assertThat(TestSetUp.createLoserWithMoney("1000").getProfit(TestSetUp.createBustDealer()))
                .isEqualTo(1000);
        assertThat(TestSetUp.createBustPlayerWithMoney("1000").getProfit(TestSetUp.createBustDealer()))
                .isEqualTo(1000 * -1);
        assertThat(TestSetUp.createBlackJackPlayerWithMoney("1000").getProfit(TestSetUp.createBlackJackDealer()))
                .isEqualTo(0);
    }
}
