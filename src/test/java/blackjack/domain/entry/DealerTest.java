package blackjack.domain.entry;

import static blackjack.fixtures.CardFixtures.SPADE_ACE;
import static blackjack.fixtures.CardFixtures.SPADE_EIGHT;
import static blackjack.fixtures.CardFixtures.SPADE_KING;
import static blackjack.fixtures.CardFixtures.SPADE_NINE;
import static blackjack.fixtures.CardFixtures.SPADE_SEVEN;
import static org.assertj.core.api.Assertions.assertThat;
import blackjack.domain.card.HoldCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(SPADE_KING, SPADE_ACE));

        assertThat(dealer.countCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드의 합이 17이하이면 True 반환한다.")
    void shouldHaveMoreCard() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(SPADE_EIGHT, SPADE_SEVEN));

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("보유한 카드의 합이 17이상일 경우 False 반환한다.")
    void shouldNotHaveMoreCard() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(SPADE_NINE, SPADE_EIGHT));

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러는 한장의 카드를 오픈한다.")
    void openCardByDealer() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(SPADE_NINE, SPADE_EIGHT));

        assertThat(dealer.openCard()).hasSize(1);
    }

    @Test
    @DisplayName("딜러의 이름은 딜러이다.")
    void getDealerName() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(SPADE_NINE, SPADE_EIGHT));

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

}
