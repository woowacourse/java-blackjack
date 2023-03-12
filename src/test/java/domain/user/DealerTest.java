package domain.user;

import domain.CardFixtures;
import domain.card.Card;
import domain.card.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @Test
    @DisplayName("17이면 히트가 아니다")
    void notHit() {
        Dealer dealer = new Dealer(new CardPool(List.of(
                CardFixtures.ofNumber(CardNumber.SIX),
                CardFixtures.ofNumber(CardNumber.ACE)
        )));

        assertThat(dealer.needsHit()).isFalse();
    }

    @Test
    @DisplayName("16이면 히트다")
    void isHit() {
        Dealer dealer = new Dealer(new CardPool(List.of(
                CardFixtures.ofNumber(CardNumber.FIVE),
                CardFixtures.ofNumber(CardNumber.ACE)
        )));

        assertThat(dealer.needsHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 두 장의 카드를 가질 때, 하나의 카드만 공개한다")
    void getLimitedCardPool() {
        List<Card> cards = List.of(
                CardFixtures.ofNumber(CardNumber.JACK),
                CardFixtures.ofNumber(CardNumber.FIVE)
        );
        CardPool cardPool = new CardPool(cards);
        Dealer dealer = new Dealer(cardPool);

        List<Card> expected = List.of(
                CardFixtures.ofNumber(CardNumber.JACK)
        );

        assertThat(dealer.getLimitedCardPool().getCards().size()).isEqualTo(1);
        assertThat(dealer.getLimitedCardPool().getCards()).isEqualTo(expected);
    }
}
