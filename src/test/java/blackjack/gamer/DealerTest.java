package blackjack.gamer;

import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러 생성 시점에 0장의 카드를 가진다")
    void initializedDealerShouldHave0Card() {
        // given
        Dealer dealer = Dealer.create();

        // when
        // then
        assertThat(dealer.getCards().size()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
            "15, true",
            "16, true",
            "17, false",
            "18, false"
    })
    @DisplayName("딜러는 자신의 카드 패의 합계를 기반으로, 히트를 결정할 수 있다")
    void canDecideToHit(int accumulatedSum, boolean expectedShouldHit) {
        // given
        Dealer dealer = GameParticipantFixture.createDealer();
        Cards cardsForSum = CardFixture.createCardsForSum(accumulatedSum);

        cardsForSum.getCards()
                .forEach(dealer::addCard);

        // when
        // then
        assertThat(dealer.shouldHit()).isEqualTo(expectedShouldHit);
    }
}
