package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

//    @Test
//    @DisplayName("카드의 합을 계산해 반환한다")
//    void calculate_calculation_check_rot() {
//        // given
//        Player player = new Gambler("두리", 0);
//        player.addCards(new CardPack(new SortedBlackjackShuffle()), 3);
//
//        // when
//        int result = player.getCardScore();
//
//        // then
//        assertThat(result).isEqualTo(30);
//    }
//
//    @DisplayName("에이스가 2장이면 12로 계산한다")
//    @Test
//    void ace_test() {
//        Player player = new Gambler("두리", 0);
//        player.addCards(new CardPack(new ReversedBlackjackShuffle()), 2);
//
//        // when
//        int result = player.getCardScore();
//
//        // then
//        assertThat(result).isEqualTo(12);
//    }

    @Test
    @DisplayName("플레이어가 블랙잭인지 판단해 반환한다")
    void 플레이어가_블랙잭인지_판단해_반환한다() {
        Card ace = new Card(CardNumber.ACE, CardShape.CLOVER);
        Card ten = new Card(CardNumber.TEN, CardShape.CLOVER);

        Player player = new TestPlayer();
        player.addCards(List.of(ace, ten));

        boolean result = player.isBlackJack();
        assertThat(result).isTrue();
    }

    static class TestPlayer extends Player {

        public TestPlayer() {
            super("Test", 0);
        }

        @Override
        public List<Card> getOpenedCards() {
            return this.getCards().getCards();
        }
    }
}
