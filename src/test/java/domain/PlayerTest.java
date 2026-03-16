package domain;

import static org.assertj.core.api.Assertions.assertThat;

import infra.FixedCardShuffler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private final String userName = "pobi";
    private final Deck cards = Deck.of(new FixedCardShuffler());

    @Test
    @DisplayName("플레이어 초기 카드 추가 시 2장의 카드를 보유한지 테스트")
    void holding_two_card_success() {
        Player player = Player.of(userName);

        player.addInitialCards(cards.drawInitialHand());

        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어 합계가 21 초과 여부 테스트")
    void isBust_player_score_success() {
        Player player = Player.of(userName);
        player.addInitialCards(cards.drawInitialHand());

        player.addCard(Card.of(Rank.J, Suit.SPADE));
        player.addCard(Card.of(Rank.Q, Suit.SPADE));

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 보유한 카드들의 종류가 올바르게 반환하는지 테스트")
    void have_card_type_return_value_correct() {
        List<Card> initialCards = new ArrayList<>(List.of(
                Card.of(Rank.TEN, Suit.DIAMOND),
                Card.of(Rank.FIVE, Suit.CLOVER)));
        Player player = Player.of(userName);

        player.addInitialCards(initialCards);

        List<Card> expectCards = List.of(Card.of(Rank.TEN, Suit.DIAMOND),
                Card.of(Rank.FIVE, Suit.CLOVER));
        assertThat(player.getCards()).isEqualTo(expectCards);
    }

    @Test
    @DisplayName("플레이어 카드의 합을 계산, 보유한 ACE 개수만큼 반복, 합계 21 초과 시 -> 합계 조정 (ACE 11 -> 1 처리)")
    void adjust_player_cards_sum_success() {
        List<Card> cards = List.of(
                Card.of(Rank.TWO, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND),
                Card.of(Rank.ACE, Suit.HEART));
        Player player = Player.of(userName);

        player.addInitialCards(cards);

        int cardScore = player.calculateScore();

        assertThat(cardScore).isEqualTo(20);
    }

    @Test
    @DisplayName("플레이어 ACE 판정 이후 점수가 21 초과이면 0점 처리")
    void isBust_final_score_true() {
        List<Card> cards = List.of(
                Card.of(Rank.FIVE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER),
                Card.of(Rank.EIGHT, Suit.DIAMOND));
        Player player = Player.of(userName);

        player.addInitialCards(cards);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어 카드 1장 추가 테스트")
    void add_one_card_test() {
        Player player = Player.of(userName);
        player.addInitialCards(cards.drawInitialHand());

        player.addCard(cards.draw());

        assertThat(player.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("플레이어 카드가 블랙잭(초기 2장의 카드가 21)인지 테스트")
    void player_isBlackjack() {
        Player player = Player.of(userName);
        List<Card> blackjackCards = List.of(Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART));

        player.addInitialCards(blackjackCards);

        assertThat(player.isBlackjack()).isTrue();
    }

}