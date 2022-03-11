package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Symbol;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        dealer.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(new Card(Symbol.SPADE, Denomination.NINE));

        assertThatThrownBy(() ->  dealer.hit(new Card(Symbol.SPADE, Denomination.ACE)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
    }

    @Test
    @DisplayName("숫자가 16이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        dealer.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(new Card(Symbol.SPADE, Denomination.NINE));

        assertThat(dealer.canDrawCard()).isFalse();
    }

    @Test
    @DisplayName("숫자가 16이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        dealer.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(new Card(Symbol.SPADE, Denomination.FIVE));

        assertThat(dealer.canDrawCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 결과값을 받는다.")
    void checkResultTest() {
        List<Result> playerResult = Arrays.asList(Result.WIN,Result.LOSE, Result.WIN, Result.DRAW);

        assertThat(dealer.checkResult(playerResult)).containsExactly(1,1,2);
    }
}