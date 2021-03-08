package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Shape;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("점수가 16 초과일 경우 카드를 뽑지 않는 기능")
    void isContinue() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));
        Dealer dealer = new Dealer(deck);

        assertThat(dealer.isContinue()).isFalse();
    }

    @Test
    @DisplayName("점수가 16 초과일 경우 계속 드로우 하려고 하는 경우 예외처리")
    void drawCard() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN),
            Card.valueOf(Shape.SPADE, CardValue.ACE)));
        Dealer dealer = new Dealer(deck);

        assertThatIllegalStateException().isThrownBy(() ->
            dealer.drawCard(deck))
            .withMessage("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
    }
}
