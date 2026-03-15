package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체 생성 시 2장의 카드를 보유한지 테스트")
    void holding_two_card_success() {
        Dealer dealer = dealerWithInitialCards();

        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("딜러 합계가 16이하이면 true")
    void calculateScore_isHit_true() {
        Dealer dealer = dealerWithInitialCards();

        assertThat(dealer.isHit()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 1장 추가 정상 동작 테스트")
    void add_one_card_test() {
        Dealer dealer = dealerWithInitialCards();

        dealer.addCard(Card.of(Rank.FOUR, Suit.CLOVER));

        assertThat(dealer.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("딜러 카드 합이 16 이하이면 17 이상이 될 때까지 카드를 추가한다.")
    void addCard_isAce_isHit_more_addCard() {
        Dealer dealer = dealerWithInitialCards(); // 초기 카드 (2, 3 -> 합: 5)

        List<Card> drawCards = new ArrayList<>(List.of(
                Card.of(Rank.ACE, Suit.DIAMOND),
                Card.of(Rank.J, Suit.CLOVER),
                Card.of(Rank.K, Suit.HEART),
                Card.of(Rank.Q, Suit.SPADE))); // Q 추가 X

        while (dealer.isHit()) {
            dealer.addCard(drawCards.removeFirst());
        }

        assertThat(dealer.calculateScore()).isGreaterThan(17);
        assertThat(dealer.getCards()).doesNotContain(Card.of(Rank.Q, Suit.SPADE));
    }

    @Test
    @DisplayName("딜러 생성 시 전달한 초기 카드를 보유한다")
    void create_dealer_with_initial_cards() {
        Dealer dealer = dealerWithInitialCards();

        List<Card> expect = List.of(Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.THREE, Suit.CLOVER));
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
    @DisplayName("딜러 점수가 21 초과이면 True")
    void isBust_final_score_true() {
        List<Card> cards = List.of(
                Card.of(Rank.FIVE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        Dealer dealer = Dealer.of(cards);

        assertThat(dealer.isBust()).isTrue();
    }

    private Dealer dealerWithInitialCards() {
        return Dealer.of(new ArrayList<>(List.of(
                Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.THREE, Suit.CLOVER))));
    }


}