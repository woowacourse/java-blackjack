package blackjack.gamer;

import blackjack.domain.GameRule;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @ParameterizedTest
    @CsvSource({"1", "2", "5", "10"})
    @DisplayName("딜러 생성 시점에 플레이어의 수와 무관하게 0장의 카드 패를 가진다")
    void initializedDealerShouldHave0Card(int playerCount) {
        // given
        Dealer dealer = Dealer.create(playerCount);

        // when
        // then
        assertThat(dealer.getHand().size()).isEqualTo(0);
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
                .forEach(dealer::drawCard);

        // when
        // then
        assertThat(dealer.shouldHit()).isEqualTo(expectedShouldHit);
    }

    @Test
    @DisplayName("딜러는 초기에 카드를 보여줄 때, 한 장만 보여준다.")
    void whenAfterInitialDealingShouldShowFirstCard() {
        // given
        Dealer dealer = GameParticipantFixture.createDealer();
        for (int i = 0; i < GameRule.INITIAL_DEALING_CARD_COUNT.getValue(); i++) {
            dealer.drawCard(CardFixture.createCard());
        }

        // when
        Cards cards = dealer.showHand();

        // then
        assertThat(cards.size()).isEqualTo(1);
    }
}
