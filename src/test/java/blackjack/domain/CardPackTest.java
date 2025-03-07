package blackjack.domain;

import blackjack.domain.card.BlackjackShuffle;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPack;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardPackTest {

    @Test
    @DisplayName("카드팩 객체에 52장의 카드를 생성한다")
    void cardPack_Crafts52CardsInObjects() {
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
                    .comparing(Card::getValue)
                    .thenComparing(Card::getShape));
        }
    }
}
