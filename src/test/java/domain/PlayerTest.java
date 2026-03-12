package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import domain.shuffle.NoShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어 카드의 합이 21 초과일 시 Bust 판정을 적절히 하는지 테스트한다.")
    void 플레이어_카드_Bust_판정_테스트() {
        List<Card> cardList = List.of(
                Card.of(Rank.NINE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        String name = "포비";

        Player player = Player.of(Cards.of(cardList), name);
        boolean isBust = player.isBust();

        boolean expect = true;
        assertThat(isBust).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어 카드의 합이 21 초과일 시 최종 결과가 0으로 보정되는지 테스트한다.")
    void 플레이어_카드_Bust시_0으로_보정_테스트() {
        List<Card> cardList = List.of(
                Card.of(Rank.NINE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        String name = "포비";

        Player player = Player.of(Cards.of(cardList), name);
        int result = player.getScoreOrZeroIfBust();

        int expect = 0;
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어 객체 생성 시 2장의 카드를 정상 분배하는지 확인한다.")
    void 카드_초기_draw시_2장_보유_확인_테스트() {
        Deck deck = Deck.of(new NoShuffleStrategy());
        Player player = Player.of(deck.drawInitialHand(), "pobi");

        assertThat(player.isInitialHand()).isTrue();
    }

    @Test
    @DisplayName("플레이어에게 한 장의 카드를 추가 분배하면 초기 카드 상태가 아니다.")
    void 카드_한장_draw_후_initial_hand_아님() {
        Deck deck = Deck.of(new NoShuffleStrategy());
        Player player = Player.of(deck.drawInitialHand(), "pobi");

        player.addCard(deck.draw());

        assertThat(player.isInitialHand()).isFalse();
    }

    @Test
    @DisplayName("플레이어 카드의 합이 22이이고, 딜러 카드의 합이 21일 때 패배를 반환하는지 테스트한다.")
    void 플레이어_카드_Bust_딜러_카드_합이_21일때_패배_테스트() {
        Deck deck = Deck.of(new NoShuffleStrategy());
        List<Card> cardList = List.of(
                Card.of(Rank.NINE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        String name = "포비";

        Player player = Player.of(Cards.of(cardList), name);
        Dealer dealer = Dealer.of(deck.drawInitialHand());

        GameResult result = player.compareResult(dealer);

        GameResult expect = GameResult.LOSE;
        assertThat(result).isEqualTo(expect);
    }
}