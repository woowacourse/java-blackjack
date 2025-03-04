package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoundTest {
    @DisplayName("특정 플레이어에게 지정한 장 수의 카드를 지급한다")
    @Test
    void distributeCardsTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2));
        Round round = new Round(cardDeck);
        Player player = new Player("라젤");
        round.register(player);

        // when
        round.distributeCards("라젤", 2);

        // then
        assertThat(player.calculateSum()).isEqualTo(18);
        assertThat(player.getCards()).contains(card1, card2);
    }

}