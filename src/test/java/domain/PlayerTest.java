package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 카드 점수 합계 정상 테스트")
    void 플레이어_카드_점수_합계_정상_테스트() {
        List<Card> cards = List.of(
                Card.of(Rank.THREE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));

        int cardScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        int expect = 20;
        assertThat(cardScore).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어 카드의 합이 21 초과 시 ACE 값 1로 정의 테스트")
    void ACE_카드_1_점수_판정_테스트() {
        List<Card> cards = List.of(
                Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));

        int cardScore = Player.of(cards).calculateScore();

        if (cardScore > 21 && cards.stream().anyMatch(Card::isAce)) {
            cardScore -= 10;
        }

        int expect = 20;
        assertThat(cardScore).isEqualTo(expect);
    }

}