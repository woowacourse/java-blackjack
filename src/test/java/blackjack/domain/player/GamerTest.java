package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import blackjack.exception.CardDuplicateException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

    @Test
    @DisplayName("각 플레이어 덱에 카드를 추가한다.")
    void addCardToDeck() {
        Player player = new Gamer("player");
        Card card = new Card(Symbol.CLOVER, CardNumber.EIGHT);

        player.addCardToDeck(card);
        List<Card> deck = player.getDeckAsList();

        assertThat(deck).containsExactly(card);
    }

    @Test
    @DisplayName("카드를 추가할 때 중복되면 예외를 발생한다.")
    void addCardToDeck_CardDuplicateException() {
        Player player = new Gamer("player");
        Card card1 = new Card(Symbol.CLOVER, CardNumber.EIGHT);
        Card card2 = new Card(Symbol.CLOVER, CardNumber.EIGHT);

        player.addCardToDeck(card1);
        Assertions.assertThatThrownBy(
            () -> player.addCardToDeck(card2)
        ).isInstanceOf(CardDuplicateException.class);
    }

    @Test
    @DisplayName("덱의 점수를 계산한다")
    void getScore() {
        Player player = new Gamer("player");

        player.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.FIVE));
        player.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.SIX));

        assertThat(player.getScore()).isEqualTo(11);
    }
}
