package domain.cards.gamercards;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.cards.gamercards.DealerCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerCardsTest {

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 16 이하인지 알려준다.")
    @Test
    void lowerThanCanHitThresholdTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.K, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.SIX, CardShape.SPADE));

        assertThat(dealerCards.hasScoreUnderHitThreshold()).isTrue();
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 17 이상인지 알려준다.")
    @Test
    void lowerThanCannotHitThresholdTest() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>());
        dealerCards.addCard(new Card(CardNumber.K, CardShape.HEART));
        dealerCards.addCard(new Card(CardNumber.SEVEN, CardShape.SPADE));

        assertThat(dealerCards.hasScoreUnderHitThreshold()).isFalse();
    }
}
