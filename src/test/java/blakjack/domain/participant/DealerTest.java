package blakjack.domain.participant;

import blakjack.domain.card.Card;
import blakjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.card.MockCard.CLUB_NINE;
import static blakjack.domain.card.MockCard.CLUB_TWO;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DealerTest {
    @Test
    @DisplayName("딜러의 카드점수가 16이하인 경우에 hit")
    void noException() {
        final Dealer dealer = new Dealer();

        dealer.initCards(new CardDeck() {
            @Override
            public Card draw() {
                return CLUB_TWO;
            }
        });

        assertThatCode(() -> dealer.hit(CLUB_TWO))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러의 카드점수가 16을 넘는 경우에 hit 시 예외발생")
    void exception() {
        final Dealer dealer = new Dealer();

        dealer.initCards(new CardDeck() {
            @Override
            public Card draw() {
                return CLUB_NINE;
            }
        });

        assertThatThrownBy(() -> dealer.hit(CLUB_TWO))
                .isInstanceOf(IllegalStateException.class);
    }
}
