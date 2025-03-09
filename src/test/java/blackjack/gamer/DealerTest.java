package blackjack.gamer;

import blackjack.ConstantFixture;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DealerTest {

    @ParameterizedTest
    @CsvSource({"1", "2", "5", "10"})
    @DisplayName("딜러 생성 시점에 플레이어의 수와 무관하게 0장의 카드 패를 가진다")
    void initializedDealerShouldHave0Card(int playerCount) {
        // given
        Dealer dealer = Dealer.create(playerCount);

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
                .forEach(dealer::drawCard);

        // when
        // then
        assertThat(dealer.shouldHit()).isEqualTo(expectedShouldHit);
    }

    @Test
    @DisplayName("딜러는 플레이어와 자신에게 초기 카드 딜링을 통해서 카드를 {초기 카드 개수}장씩 나눠줄 수 있다")
    void canDealInitialCard() {
        // given
        int initialDealingCardCount = ConstantFixture.getInitialDealingCardCount("테스트 전용");

        Dealer dealer = GameParticipantFixture.createDealer();
        List<Player> players = List.of(
                GameParticipantFixture.createPlayer("강산"),
                GameParticipantFixture.createPlayer("재중"),
                GameParticipantFixture.createPlayer("파프"));

        // when
        dealer.dealInitialCards(players);

        // then
        assertAll(
                () -> {
                    players.forEach(player ->
                            assertThat(player.getCards().size()).isEqualTo(initialDealingCardCount));

                    assertThat(dealer.getCards().size()).isEqualTo(initialDealingCardCount);
                }
        );
    }
}
