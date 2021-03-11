package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.state.Stay;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("점수가 16 초과일 경우 카드를 뽑지 않는 기능")
    void isContinue() throws InvalidNameInputException {
        Dealer dealer = new Dealer();
        List<Card> cardDeck = new ArrayList<>(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
                Card.valueOf(Shape.SPADE, CardValue.SEVEN)));

        for (Card card : cardDeck) {
            dealer.draw(card);
        }
        assertThat(dealer.isContinue()).isFalse();
    }

    @Test
    @DisplayName("Stay 상태 변환")
    void updateState_Stay() {
        assertThat(TestSetUp.createDealer().getState()).isInstanceOf(Stay.class);
    }
}
