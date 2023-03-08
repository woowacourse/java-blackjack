package blackjack.domain.participants;

import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.SPADE;
import static blackjack.domain.result.JudgeResult.LOSE;
import static blackjack.domain.result.JudgeResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.dto.HandStatus;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUpDealer() {
        dealer = new Dealer("딜러");
    }

    @DisplayName("딜러의 카드 합이 17 미만이면 참을 반환한다.")
    @Test
    void should_ReturnTrue_WhenSumOfCardsUnder17() {
        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SIX));

        assertThat(dealer.isAbleToHit()).isTrue();
    }

    @DisplayName("딜러의 카드 합이 17 이상이면 거짓을 반환한다.")
    @Test
    void should_ReturnFalse_WhenSumOfCardsOver17() {
        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SEVEN));

        assertThat(dealer.isAbleToHit()).isFalse();
    }

    @DisplayName("플레이어들을 전달받아 판정 결과들을 반환한다.")
    @Test
    void should_ReturnPlayerJudgeResults_When_Given_Players() {
        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(SPADE, SEVEN));

        final Player player1 = new Player("pobi");
        final Player player2 = new Player("wony");
        player1.take(new Card(CLUB, QUEEN));
        player1.take(new Card(CLUB, SIX));
        player2.take(new Card(DIAMOND, QUEEN));
        player2.take(new Card(DIAMOND, JACK));
        
        assertThat(dealer.judgeAllPlayersResult(new Players(List.of(player1, player2))))
                .containsExactlyEntriesOf(Map.of("pobi", LOSE, "wony", WIN));
    }

    @DisplayName("딜러는 카드 오픈 시 첫 번째 카드 상태만 확인한다.")
    @Test
    void should_OpenOnlyFirstCard_When_DealerOpenHandStatus() {
        final Card card = new Card(SPADE, JACK);
        final Participant dealer = new Dealer("딜러");
        dealer.take(card);

        final HandStatus status = dealer.toHandStatus();
        final List<Card> openedCards = status.getCards();

        assertThat(openedCards).containsExactly(card);
    }

    @DisplayName("딜러는 카드 합이 17 미만이면 히트 가능하다.")
    @Test
    void should_ReturnTrue_OfIsAbleToHit_When_HandSumUnder17() {
        final Card card1 = new Card(SPADE, JACK);
        final Card card2 = new Card(SPADE, SIX);
        final Participant dealer = new Dealer("딜러");

        dealer.take(card1);
        dealer.take(card2);

        assertThat(dealer.isAbleToHit()).isTrue();
    }
}
