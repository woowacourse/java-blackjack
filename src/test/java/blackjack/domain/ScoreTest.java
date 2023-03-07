package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ScoreTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewScore() {
        assertDoesNotThrow(() -> Score.from(new ArrayList<>()));
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(9, 2, ace)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11() {
        // given
        Card card1 = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        // when, then
        Score score = Score.from(List.of(card1, card2, card3));
        assertThat(score.getScore()).isEqualTo(12);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(9, ace, ace)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11_2() {
        // given
        Card card1 = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        // when, then
        Score score = Score.from(List.of(card1, card2, card3));
        assertThat(score.getScore()).isEqualTo(21);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.(ace, ace, ace)")
    @Test
    void Should_AIs1_When_ScoreMoreThan11_3() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        // when, then
        Score score = Score.from(List.of(card1, card2, card3));
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

        // when, then
        Score score = Score.from(List.of(card1, card2, card3, card4));
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

        // when, then
        Score score = Score.from(List.of(card1, card2, card3, card4, card5));
        assertThat(score.getScore()).isEqualTo(21);
    }

    @DisplayName("A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하지 않으면 A는 11점으로 간주한다.(ace, 10)")
    @Test
    void Should_AIs11_When_ScoreLessThanOrEqual11() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardSymbol.SPADES);
        Card card2 = new Card(CardNumber.TEN, CardSymbol.DIAMONDS);

        // when, then
        Score score = Score.from(List.of(card1, card2));
        assertThat(score.getScore()).isEqualTo(21);
    }
}
