package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingCardsTest {

    @Test
    @DisplayName("52장의 중복 없는 트럼프 카드로 구성된 덱을 생성한다.")
    void createDeck() {
        // given
        int expectedTotalCardSum = 340;

        // when
        PlayingCards deck = PlayingCards.createdDeck();

        // then
        assertThat(deck.calculateTotalScore()).isEqualTo(expectedTotalCardSum);
    }

    @Test
    @DisplayName("빈 카드 뭉치에서 카드를 뽑으려 하면 예외가 발생한다.")
    void drawFromEmptyDeckThrowsException() {
        // given
        PlayingCards emptyHands = PlayingCards.createEmptyHands();

        // when, then
        assertThatThrownBy(emptyHands::draw)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("뽑을 카드가 남아있지 않습니다.");
    }

    @Test
    @DisplayName("ACE가 없는 일반 카드들의 총합을 계산한다.")
    void calculateScoreWithoutAce() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));

        // when
        int totalScore = hands.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(17);

    }

    @Test
    @DisplayName("카드의 합이 21 이하일 때 카드를 더 뽑을 수 있다.")
    void checkHandsDrawable() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));

        // when & then
        assertThat(hands.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("ACE 카드가 포함되었을 때 11점으로 점수를 계산한다.")
    void calculateScoreWithAceAsEleven() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.NINE, Suit.HEART)
        ));

        // when & then
        assertThat(hands.calculateTotalScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("총합이 21점을 초과하면 ACE 카드를 1점으로 계산한다.")
    void calculateScoreWithAceAsOne() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.NINE, Suit.DIAMOND)
        ));

        // when & then
        assertThat(hands.calculateTotalScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("ACE 카드가 여러 장일 경우, 21점 이하가 될 때까지만 1점으로 변환한다.")
    void calculateScoreWithMultipleAces() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.ACE, Suit.DIAMOND),
            new Card(Rank.EIGHT, Suit.CLOVER)
        ));

        // when & then
        assertThat(hands.calculateTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("총 카드가 두장이고, 합이 21일 경우 블랙잭이다.")
    void isBlackJack() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART)
        ));

        // when & then
        assertThat(hands.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("처음 카드 두 장의 합이 21이더라도 한장을 더 뽑았을 경우 블랙잭이 아니다.")
    void shouldReturnFalseForBlackJackWhenHavingThreeCards() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.EIGHT, Suit.CLOVER)
        ));

        // when & then
        assertThat(hands.isBlackJack()).isFalse();
    }

    @Test
    @DisplayName("최종 카드의 합이 21이더라도 카드 수가 두장이 아니라면 블랙잭이 아니다.")
    void shouldNotBeBlackJackWhenHavingMoreThanTwoCards() {
        // given
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TEN, Suit.CLOVER)
        ));

        // when & then
        assertAll(
            () -> assertThat(hands.calculateTotalScore()).isEqualTo(21),
            () -> assertThat(hands.isBlackJack()).isFalse()
        );
    }
}
