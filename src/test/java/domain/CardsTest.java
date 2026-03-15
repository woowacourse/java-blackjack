package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("플레이어 카드의 점수 총 합을 정상적으로 계산하는 지 테스트한다.")
    void 플레이어_카드_점수_합계_정상_계산_테스트() {
        List<Card> cardList = List.of(
                Card.of(Rank.THREE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));

        Cards cards = Cards.of(cardList);
        int cardScore = cards.calculateScore();

        int expect = 20;
        assertThat(cardScore).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어 카드의 합이 21 초과 시 ACE의 값을 1로 보정하여 계산한다.")
    void ACE_카드_값_보정_후_합계_계산_정상_테스트() {
        List<Card> cardsList = List.of(
                Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));

        Cards cards = Cards.of(cardsList);
        int cardScore = cards.calculateScore();

        int expect = 20;
        assertThat(cardScore).isEqualTo(expect);
    }

    @Test
    @DisplayName("ACE 카드를 2개 이상 보유한 경우의 점수 합계를 계산한다.")
    void ACE_2개_이상_보유시_합계_계산_정상_테스트() {
        List<Card> cards = List.of(
                Card.of(Rank.ACE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));

        int aceCount = (int) cards.stream().filter(Card::isAce).count();
        int cardScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        for (int i = 0; i < aceCount; i++) {
            if (cardScore <= 21) break;
            cardScore -= 10;
        }

        int expect = 19;
        assertThat(cardScore).isEqualTo(expect);
    }
}
