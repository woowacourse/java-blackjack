package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingCardsTest {

    @DisplayName("52장의 중복 없는 트럼프 카드로 구성된 덱을 생성한다.")
    @Test
    void createDeck() {
        PlayingCards deck = PlayingCards.createdDeck();

//      ...isEqualTo(380); -> 340 합계 계산에서 에이스가 있는 만큼 -10을 시도, 총점에서 -40 해줘야
        assertThat(deck.calculateTotalScore()).isEqualTo(340);
    }

    @DisplayName("빈 카드 뭉치에서 카드를 뽑으려 하면 예외가 발생한다.")
    @Test
    void drawFromEmptyDeckThrowsException() {
        PlayingCards emptyHands = PlayingCards.createEmptyHands();

        assertThatThrownBy(emptyHands::draw)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("뽑을 카드가 남아있지 않습니다.");
    }

    @DisplayName("ACE가 없는 일반 카드들의 총합을 계산한다.")
    @Test
    void calculateScoreWithoutAce() {
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));

        assertThat(hands.calculateTotalScore()).isEqualTo(17);
        assertThat(hands.isDrawable()).isTrue();
    }

    @DisplayName("ACE 카드가 포함되었을 때 11점으로 점수를 계산한다.")
    @Test
    void calculateScoreWithAceAsEleven() {
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.NINE, Suit.HEART)
        ));

        assertThat(hands.calculateTotalScore()).isEqualTo(20);
    }

    @DisplayName("총합이 21점을 초과하면 ACE 카드를 1점으로 계산한다.")
    @Test
    void calculateScoreWithAceAsOne() {
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.NINE, Suit.DIAMOND)
        ));

        assertThat(hands.calculateTotalScore()).isEqualTo(20);
    }

    @DisplayName("ACE 카드가 여러 장일 경우, 21점 이하가 될 때까지만 1점으로 변환한다.")
    @Test
    void calculateScoreWithMultipleAces() {
        PlayingCards hands = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.ACE, Suit.HEART),
            new Card(Rank.ACE, Suit.DIAMOND),
            new Card(Rank.EIGHT, Suit.CLOVER)
        ));

        assertThat(hands.calculateTotalScore()).isEqualTo(21);
    }
}
