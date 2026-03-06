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

        int cardScore = Player.of(cards, "워니").calculateScore();

        if (cardScore > 21 && cards.stream().anyMatch(Card::isAce)) {
            cardScore -= 10;
        }

        int expect = 20;
        assertThat(cardScore).isEqualTo(expect);
    }

    @Test
    @DisplayName("ACE가 2개 이상인 경우 ACE 1/11 처리")
    void ACE_2개_이상_정상_테스트() {
        List<Card> cards = List.of(
                Card.of(Rank.ACE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));

        Player player = Player.of(cards, "포비");

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

    @Test
    @DisplayName("플레이어 객체 생성 시 2장의 카드를 보유한지 테스트")
    void 카드_2장_보유_테스트() {
        Cards cards = Cards.of();
        Player player = Player.of(cards.drawInitialHand(), "pobi");

        int cardSize = player.getCardsInfo().size();

        int expect = 2;
        assertThat(cardSize).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어 카드 1장 추가 테스트")
    void 카드_1장_추가_테스트() {
        Cards cards = Cards.of();
        Player player = Player.of(cards.drawInitialHand(), "pobi");

        player.addCard(cards.draw());
        int cardSize = player.getCardsInfo().size();

        int expect = 3;
        assertThat(cardSize).isEqualTo(expect);
    }

}