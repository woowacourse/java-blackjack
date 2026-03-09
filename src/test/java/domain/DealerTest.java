package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Cards deck = Cards.of();
    private final Dealer dealer = Dealer.of(deck.drawInitialHand());

    @Test
    @DisplayName("딜러 객체 생성 시 2장의 카드를 보유한지 테스트")
    void holding_two_card_success() {
        assertThat(dealer.getCardsInfo()).hasSize(2);
    }

    @Test
    @DisplayName("딜러 합계가 21 초과 여부 테스트")
    void isBust_dealer_score_success() {
        assertThat(dealer.isBust(22)).isTrue();
    }

    @Test
    @DisplayName("딜러 합계가 16이하이면 카드 1장 추가 테스트")
    void add_one_card_test() {
        List<Card> initialCards = new ArrayList<>(List.of(
                Card.of(Rank.TEN, Suit.DIAMOND),
                Card.of(Rank.FIVE, Suit.CLOVER)));
        Dealer dealer = Dealer.of(initialCards);

        dealer.drawUntilHitAndReturnCount(deck);

        assertThat(dealer.getCardsInfo()).hasSize(3);
    }

    @Test
    @DisplayName("딜러의 보유한 카드들의 종류가 올바르게 반환하는지 테스트")
    void have_card_type_return_value_correct() {
        List<Card> initialCards = new ArrayList<>(List.of(
                Card.of(Rank.TEN, Suit.DIAMOND),
                Card.of(Rank.FIVE, Suit.CLOVER)));
        Dealer dealer = Dealer.of(initialCards);

        List<String> expect = List.of(Rank.TEN.getName() + Suit.DIAMOND.getName(),
                Rank.FIVE.getName() + Suit.CLOVER.getName());
        assertThat(dealer.getCardsInfo()).isEqualTo(expect);
    }

    @Test
    @DisplayName("딜러 카드의 합을 계산, 보유한 ACE 개수만큼 반복, 합계 21 초과 시 -> 합계 조정 (ACE 11 -> 1 처리)")
    void adjust_dealer_cards_sum_success() {
        List<Card> cards = List.of(
                Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));

        int cardScore = Dealer.of(cards).calculateScore();

        assertThat(cardScore).isEqualTo(20);
    }

    @Test
    @DisplayName("딜러 ACE 판정 이후 점수가 21 초과이면 0점 처리")
    void isBust_final_score_zero_success() {
        List<Card> cards = List.of(
                Card.of(Rank.FIVE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        Dealer dealer = Dealer.of(cards);

        assertThat(dealer.getScoreOrZeroIfBust()).isZero();
    }


}