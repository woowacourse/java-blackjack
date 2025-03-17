package blackjack.domain.card;

import blackjack.domain.SortedBlackjackShuffle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드팩 테스트")
class CardPackTest {

    @Test
    @DisplayName("카드팩 객체에 52장의 카드를 생성한다")
    void cardPack_Crafts52CardsInObjects() {
        CardPack cardPack = new CardPack(new RandomBlackjackShuffle());
        List<Card> cards = cardPack.getCards();

        assertThat(cards.size())
                .isEqualTo(52);
    }

    @Test
    @DisplayName("카드팩의 맨 뒤에서 카드를 한장 뽑는다")
    void shuffle_and_deal_test() {
        CardPack cardPack = new CardPack(new SortedBlackjackShuffle());
        Card result = cardPack.getDealByCount(1).getCards().getFirst();

        assertThat(result)
                .isEqualTo(new Card(CardNumber.KING, CardShape.CLOVER));
    }
}
