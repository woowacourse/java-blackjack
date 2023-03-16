package blackjack.domain.participants;

import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.dto.HandStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @DisplayName("딜러의 카드 합이 17 미만이면 히트 가능하다.")
    @Test
    void should_ReturnTrue_WhenSumOfCardsUnder17() {
        final Dealer dealer = new Dealer(List.of(new Card(SPADE, QUEEN), new Card(CLUB, SIX)));

        assertThat(dealer.isHitAble()).isTrue();
    }

    @DisplayName("딜러의 카드 합이 17 이상이면 더 이상 히트할 수 없다.")
    @Test
    void should_ReturnFalse_WhenSumOfCardsOver17() {
        final Dealer dealer = new Dealer(List.of(new Card(SPADE, QUEEN), new Card(CLUB, SEVEN)));

        assertThat(dealer.isHitAble()).isFalse();
    }

    @DisplayName("딜러는 카드 오픈 시 첫 번째 카드 상태만 확인한다.")
    @Test
    void should_OpenOnlyFirstCard_When_DealerOpenHandStatus() {
        final Card firstCard = new Card(SPADE, QUEEN);
        final Dealer dealer = new Dealer(List.of(firstCard, new Card(CLUB, SEVEN)));

        final HandStatus status = dealer.toHandStatus();
        final List<Card> openedCards = status.getCards();

        assertThat(openedCards).containsExactly(firstCard);
    }
}
