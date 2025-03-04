import blackjack.domain.BlackjackShuffle;
import blackjack.domain.Card;
import blackjack.domain.CardNumber;
import blackjack.domain.CardPack;
import blackjack.domain.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackjackTest {

    @DisplayName("각 플레이어 마다 기본 카드 2장을 발급한다")
    @Test
    void give_two_cards() {
        // given
        Player player = new Player(new CardPack(new SortShuffle()));

        // when
        int result = player.getCards().size();

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("카드는 숫자와 모양을 가진다.")
    void card() {
        // given
        Card card = new Card(CardNumber.EIGHT, CardShape.CLOVER);

        // when & than
        assertAll(
                () -> assertThat(card.getNumber()).isEqualTo(8),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.CLOVER)
        );
    }

    @Test
    @DisplayName("카드팩 객체에 52장의 카드를 생성한다")
    void 카드팩_객체에_52장의_카드를_생성한다() {
        // given
        CardPack cardPack = new CardPack(new SortShuffle());

        // when
        List<Card> cards = cardPack.getCards();

        // then
        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드팩의 맨 뒤에서 카드를 한장 뽑는다")
    void shuffle_and_deal_test() {
        CardPack cardPack = new CardPack(new SortShuffle());
        Card card = cardPack.getDeal();
        assertThat(card).isEqualTo(new Card(CardNumber.KING, CardShape.CLOVER));
    }

    private static class SortShuffle implements BlackjackShuffle {

        @Override
        public void shuffle(List<Card> cards) {
            cards.sort(Comparator
                    .comparing(Card::getNumber)
                    .thenComparing(Card::getShape));
        }
    }
}
