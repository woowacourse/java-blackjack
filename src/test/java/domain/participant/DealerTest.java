package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Symbol;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void makePlayer(){
        dealer = new Dealer();
    }

    @Test
    @DisplayName("16이 넘은 상태에서 카드를 뽑을 경우 에러를 발생시킨다.")
    void hitCardOverLimitError() {
        dealer.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(Card.of(Symbol.SPADE, Denomination.NINE));

        assertThatThrownBy(() ->  dealer.hit(Card.of(Symbol.SPADE, Denomination.ACE)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
    }

    @Test
    @DisplayName("숫자가 16이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        dealer.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(Card.of(Symbol.SPADE, Denomination.NINE));

        assertThat(dealer.canDrawCard()).isFalse();
    }

    @Test
    @DisplayName("숫자가 16이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        dealer.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(Card.of(Symbol.SPADE, Denomination.FIVE));

        assertThat(dealer.canDrawCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 결과값을 받는다.")
    void checkResultTest() {
        Map<Player, Result> testPlayerResult = new HashMap<>();

        testPlayerResult.put(new Player("name", 1000), Result.LOSE);
        testPlayerResult.put(new Player("name", 2000), Result.DRAW);
        testPlayerResult.put(new Player("name", 3000), Result.WIN);
        testPlayerResult.put(new Player("name", 4000), Result.BLACKJACK);

        assertThat(dealer.getResultMoney(testPlayerResult)).isEqualTo(-8000);
    }
}