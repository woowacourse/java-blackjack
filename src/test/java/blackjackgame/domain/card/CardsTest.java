package blackjackgame.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("블랙잭 게임의 카드 덱에서 ")
class CardsTest {
    private final CardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();

    @DisplayName("52장까지 뽑을 수 있다. 53번째부터는 예외가 발생한다.")
    @Test
    void cardCountTest() {
        Cards cards = new Cards(shuffledCardsGenerator);

        IntStream.range(0,52).forEach(index -> cards.drawCard());

        assertThrows(IllegalArgumentException.class, cards::drawCard);
    }

    @DisplayName("가장 위에 있는 카드를 뽑고, 덱에서 없앤다.")
    @Test
    void drawCardTest() {
        Cards cards = new Cards((int deckCount) -> new ArrayList<>(
                List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FOUR, DiamondCard.DIAMOND_SEVEN)
        ));

        Card firstCard = cards.drawCard();
        Card secondCard = cards.drawCard();

        assertThat(firstCard).isEqualTo(CloverCard.CLOVER_FIVE);
        assertThat(secondCard).isEqualTo(HeartCard.HEART_JACK);
    }

    @DisplayName("카드를 두 장씩 뽑아서 반환할 수 있다.")
    @Test
    void drawTwoCardsTest() {
        Cards cards = new Cards(shuffledCardsGenerator);

        List<Card> firstTurnCards = cards.drawCards(2);

        assertThat(firstTurnCards.size()).isEqualTo(2);
    }
}
