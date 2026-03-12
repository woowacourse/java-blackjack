package domain;

import static org.assertj.core.api.Assertions.assertThat;

import infra.FixedCardShuffler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Cards deck = Cards.of(new FixedCardShuffler());
    private final Dealer dealer = Dealer.of(deck.drawInitialHand());

    @Test
    @DisplayName("딜러 객체 생성 시 2장의 카드를 보유한지 테스트")
    void holding_two_card_success() {
        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("딜러 합계가 16이하이면 true")
    void calculateScore_isHit_true() {
        List<Card> initialCards = new ArrayList<>(List.of(
                Card.of(Rank.ACE, Suit.DIAMOND),
                Card.of(Rank.FOUR, Suit.CLOVER)));
        Dealer dealer = Dealer.of(initialCards);

        assertThat(dealer.isHit()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 1장 추가 정상 동작 테스트")
    void add_one_card_test() {
        dealer.addCard(Card.of(Rank.FOUR, Suit.CLOVER));

        assertThat(dealer.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("딜러 카드 합이 16 이하가 아닐 때까지 카드를 추가한다.")
    void addCard_isAce_isHit_more_addCard() {
        List<Card> cards = new ArrayList<>(
                List.of(Card.of(Rank.ACE, Suit.DIAMOND), Card.of(Rank.J, Suit.CLOVER), Card.of(Rank.K, Suit.HEART)));

        while (dealer.isHit()) {
            dealer.addCard(cards.removeFirst());
        }

        assertThat(dealer.getCards()).hasSize(5);
    }

    @Test
    @DisplayName("딜러의 보유한 카드들의 종류가 올바르게 반환하는지 테스트")
    void have_card_type_return_value_correct() {
        List<Card> initialCards = new ArrayList<>(List.of(
                Card.of(Rank.TEN, Suit.DIAMOND),
                Card.of(Rank.FIVE, Suit.CLOVER)));
        Dealer dealer = Dealer.of(initialCards);

        List<Card> expect = List.of(Card.of(Rank.TEN, Suit.DIAMOND),
                Card.of(Rank.FIVE, Suit.CLOVER));
        assertThat(dealer.getCards()).isEqualTo(expect);
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
    @DisplayName("딜러 ACE 판정 이후 점수가 21 초과이면 True")
    void isBust_final_score_true() {
        List<Card> cards = List.of(
                Card.of(Rank.FIVE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        Dealer dealer = Dealer.of(cards);

        assertThat(dealer.isBust()).isTrue();
    }


}