package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Deck;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class DealerTest {

    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void makePlayer(){
        dealer = new Dealer();
        deck = Deck.initDeck();
    }

    @Test
    @DisplayName("16이 넘은 상태에서 카드를 뽑을 경우 에러를 발생시킨다.")
    void hitCardOverLimitError() {
        while (dealer.canHit()) {
            dealer.hit(deck);
        }

        assertThatThrownBy(() ->  dealer.hit(deck))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
    }

    @Test
    @DisplayName("숫자가 16이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        while (dealer.canHit()) {
            dealer.hit(deck);
        }

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("숫자가 16이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        dealer.hit(deck);

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러의 결과값을 받는다.")
    void checkResultTest() {
        List<Result> playerResult = Arrays.asList(Result.WIN,Result.LOSE, Result.WIN, Result.DRAW);
        Map<Result, Integer> dealerResult = dealer.checkResult(playerResult);

        assertThat(dealerResult.get(Result.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(Result.DRAW)).isEqualTo(1);
        assertThat(dealerResult.get(Result.LOSE)).isEqualTo(2);
    }
}