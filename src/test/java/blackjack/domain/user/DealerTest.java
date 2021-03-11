package blackjack.domain.user;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    public static final int GAME_OVER_SCORE = 21;

    @DisplayName("딜러는 첫 카드 2장을 받는다.")
    @Test
    void dealer_add_first_card() {
        Dealer dealer = new Dealer(Arrays.asList(Card.of("스페이드", "10"),
                Card.of("하트", "J")));
        List<Card> cards = dealer.getCards();
        assertThat(cards).hasSize(2);
    }

    @DisplayName("딜러의 첫 카드가 21점 초과하는 경우(A가 2장) 하나의 A를 1로 계산한다.")
    @Test
    void dealer_first_card_is_over() {
        //given
        Dealer dealer = new Dealer(Arrays.asList(
                Card.of("스페이드", "A"),
                Card.of("하트", "A")));
        assertThat(dealer.isFinished()).isEqualTo(false);
        assertThat(dealer.scoreToInt()).isEqualTo(12);
    }

    @DisplayName("딜러의 첫 카드가 17점 이상인 경우 경우 딜러의 턴은 끝난다. ")
    @Test
    void dealer_is_game_over() {
        Dealer dealer = new Dealer(Arrays.asList(
                Card.of("스페이드", "7"),
                Card.of("하트", "10")));
        assertThat(dealer.canAddCard()).isEqualTo(false);
        assertThat(dealer.scoreToInt()).isEqualTo(17);
    }
}