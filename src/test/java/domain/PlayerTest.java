package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어 카드의 점수 총 합을 정상적으로 계산하는 지 테스트한다.")
    void 플레이어_카드_점수_합계_정상_계산_테스트() {
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
    @DisplayName("플레이어 카드의 합이 21 초과 시 ACE의 값을 1로 보정하여 계산한다.")
    void ACE_카드_값_보정_후_합계_계산_정상_테스트() {
        List<Card> cards = List.of(
                Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));

        int cardScore = Cards.of(cards).calculateScore();

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

        Player player = Player.of(Cards.of(cards), "포비");

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
    @DisplayName("플레이어 객체 생성 시 2장의 카드를 정상 분배하는지 확인한다.")
    void 카드_초기_draw시_2장_보유_확인_테스트() {
        Deck deck = Deck.of(new NoShuffleStrategy());
        Player player = Player.of(deck.drawInitialHand(), "pobi");

        int cardSize = player.getCardSize();

        int expect = 2;
        assertThat(cardSize).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어에게 한 장의 카드를 추가 분배할 수 있는지 확인한다.")
    void 카드_한장_draw_여부_확인_테스트() {
        Deck deck = Deck.of(new NoShuffleStrategy());
        Player player = Player.of(deck.drawInitialHand(), "pobi");

        player.addCard(deck.draw());
        int cardSize = player.getCardSize();

        int expect = 3;
        assertThat(cardSize).isEqualTo(expect);
    }
}