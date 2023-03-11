package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.Cards;
import blackjack.domain.gameplayer.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ScoreTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewScore() {
        assertDoesNotThrow(() -> Score.from(new Cards()));
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(9, 2, ace)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11() {
        // given
        Card card1 = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(12);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(9, ace, ace)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11_2() {
        // given
        Card card1 = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(21);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(ace, ace, ace)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11_3() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(13);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(ace, ace, ace, ace)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11_4() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Card card4 = new Card(CardNumber.ACE, CardSymbol.CLUBS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(14);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(ace, ace, ace, ace, 7)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11_5() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Card card4 = new Card(CardNumber.ACE, CardSymbol.CLUBS);
        Card card5 = new Card(CardNumber.SEVEN, CardSymbol.CLUBS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(21);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(ace, ace, ace, ace, 8)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11_6() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Card card4 = new Card(CardNumber.ACE, CardSymbol.CLUBS);
        Card card5 = new Card(CardNumber.EIGHT, CardSymbol.CLUBS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(12);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하지 않으면 A는 11점으로 간주한다.(ace, 10)")
    @Test
    void Should_AIs11_When_ScoreLessThanOrEqual11() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.TEN, CardSymbol.DIAMONDS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(21);
    }

    @DisplayName("A가 없을 때 점수 합이 11을 초과하더라도 점수의 합은 그대로 계산된다.")
    @Test
    void Should_Success_When_NoAce() {
        // given
        Card card1 = new Card(CardNumber.TEN, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.TEN, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.TEN, CardSymbol.CLUBS);

        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        // when, then
        Score score = Score.from(cards);
        assertThat(score.getScore()).isEqualTo(30);
    }

    @DisplayName("점수가 동일한 경우에 true를 반환할 수 있다.")
    @Test
    void Should_Success_When_SameScore() {
        // given
        Card card1 = new Card(CardNumber.TEN, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.TEN, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.TEN, CardSymbol.CLUBS);
        Card card4 = new Card(CardNumber.TEN, CardSymbol.HEARTS);

        Cards cards1 = new Cards();
        cards1.add(card1);
        cards1.add(card2);

        Cards cards2 = new Cards();
        cards2.add(card3);
        cards2.add(card4);

        // when, then
        Score score1 = Score.from(cards1);
        Score score2 = Score.from(cards2);

        assertThat(score1.isEqualTo(score2)).isTrue();
    }
}
