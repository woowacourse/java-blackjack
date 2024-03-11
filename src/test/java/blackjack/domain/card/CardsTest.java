package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.FIVE;
import static blackjack.domain.card.CardScore.NINE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static blackjack.domain.card.CardSymbol.HEART;
import static blackjack.domain.card.CardSymbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드들")
public class CardsTest {
    @Test
    @DisplayName("뽑은 카드를 저장한다.")
    void cardsCreateTest() {
        // given & when
        Cards cards = new Cards();
        cards.addCard(new Card(SPADE, NINE));
        cards.addCard(new Card(CLUB, ACE));

        // then
        assertThat(cards.get().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("뽑은 카드들의 합을 구한다.")
    void cardsSumTest() {
        // given
        int expectedScore = 9 + 5;

        // when
        Cards cards = new Cards();
        cards.addCard(new Card(CLUB, FIVE));
        cards.addCard(new Card(SPADE, NINE));

        // then
        assertThat(cards.totalScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 점수의 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given
        Cards cards = new Cards();

        // when
        cards.addCard(new Card(SPADE, NINE));
        cards.addCard(new Card(CLUB, QUEEN));
        cards.addCard(new Card(CLUB, ACE));
        cards.addCard(new Card(HEART, ACE));

        // then
        assertThat(cards.totalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드들의 모든 점수 경우의 수를 구한다.")
    void calculateScoreCases() {
        // given
        Card card1 = new Card(CLUB, ACE);
        Card card2 = new Card(CLUB, FIVE);
        Card card3 = new Card(DIAMOND, ACE);
        Cards cards = new Cards();
        Set<Integer> expected = Set.of(7, 17, 27);

        // when
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        // then
        assertThat(cards.generateScoreCases()).isEqualTo(expected);
    }

}
