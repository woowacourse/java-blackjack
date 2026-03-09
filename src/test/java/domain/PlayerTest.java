package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private final String userName = "pobi";
    private final Cards cards = Cards.of();

    @Test
    @DisplayName("플레이어 객체 생성 시 2장의 카드를 보유한지 테스트")
    void holding_two_card_success() {
        Player player = Player.of(Cards.of().drawInitialHand(), userName);

        assertThat(player.getCardsInfo()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어 합계가 21 초과 여부 테스트")
    void isBust_player_score_success() {
        Player player = Player.of(cards.drawInitialHand(), userName);

        assertThat(player.isBust(22)).isTrue();
    }

    @Test
    @DisplayName("플레이어의 보유한 카드들의 종류가 올바르게 반환하는지 테스트")
    void have_card_type_return_value_correct() {
        List<Card> initialCards = new ArrayList<>(List.of(
                Card.of(Rank.TEN, Suit.DIAMOND),
                Card.of(Rank.FIVE, Suit.CLOVER)));
        Player player = Player.of(initialCards, userName);

        List<String> expect = List.of(Rank.TEN.getName() + Suit.DIAMOND.getName(),
                Rank.FIVE.getName() + Suit.CLOVER.getName());
        assertThat(player.getCardsInfo()).isEqualTo(expect);
    }

    @Test
    @DisplayName("플레이어 카드의 합을 계산, 보유한 ACE 개수만큼 반복, 합계 21 초과 시 -> 합계 조정 (ACE 11 -> 1 처리)")
    void adjust_player_cards_sum_success() {
        List<Card> cards = List.of(
                Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));

        int cardScore = Player.of(cards, userName).calculateScore();

        assertThat(cardScore).isEqualTo(20);
    }

    @Test
    @DisplayName("플레이어 ACE 판정 이후 점수가 21 초과이면 0점 처리")
    void isBust_final_score_zero_success() {
        List<Card> cards = List.of(
                Card.of(Rank.FIVE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        Player player = Player.of(cards, userName);

        assertThat(player.getScoreOrZeroIfBust()).isZero();
    }

    @Test
    @DisplayName("플레이어 카드 1장 추가 테스트")
    void add_one_card_test() {
        Player player = Player.of(cards.drawInitialHand(), userName);

        player.addCard(cards.draw());

        assertThat(player.getCardsInfo()).hasSize(3);
    }

}