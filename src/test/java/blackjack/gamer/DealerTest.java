package blackjack.gamer;

import blackjack.ConstantFixture;
import blackjack.domain.card.Card;
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
        Dealer dealer = Dealer.create(playerCount, null);

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
    @DisplayName("딜러는 카드를 규칙에 정해진 개수만큼 숨길 수 있다")
    void canHideCard() {
        // given
        Dealer dealer = GameParticipantFixture.createDealer();
        dealer.drawCard(CardFixture.createCard());
        dealer.drawCard(CardFixture.createCard());

        // when
        dealer.hideCard();

        // then
        long hideCount = dealer.hand.getCards().stream()
                .filter(Card::isHidden)
                .count();

        assertThat(hideCount).isEqualTo(ConstantFixture.getInitialHideCardCount("테스트 전용"));
    }

}
