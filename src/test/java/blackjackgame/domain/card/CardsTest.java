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
        Cards cards = new Cards(new FixedCardsGenerator());

        Card firstCards = cards.drawCard();
        Card secondCards = cards.drawCard();

        assertThat(firstCards).isEqualTo(CloverCard.CLOVER_FIVE);
        assertThat(secondCards).isEqualTo(HeartCard.HEART_JACK);
    }

    @DisplayName("카드를 두 장씩 뽑아서 반환할 수 있다.")
    @Test
    void drawTwoCardsTest() {
        Cards cards = new Cards(shuffledCardsGenerator);

        List<Card> firstTurnCards = cards.drawCards(2);

        assertThat(firstTurnCards.size()).isEqualTo(2);
    }

    private class FixedCardsGenerator implements CardsGenerator {

        /*
        5클로버, J하트, 4스페이드, 7다이아몬드, A하트, 6클로버, 8클로버, 9다이아몬드, 7하트
        위 순서대로 담겨져 있는 List<Card>를 반환
        */
        @Override
        public List<Card> generate() {
            return new ArrayList<>(
                    List.of(CloverCard.CLOVER_FIVE, HeartCard.HEART_JACK, SpadeCard.SPADE_FOUR,
                            DiamondCard.DIAMOND_SEVEN, HeartCard.HEART_ACE, CloverCard.CLOVER_SIX,
                            CloverCard.CLOVER_EIGHT,
                            DiamondCard.DIAMOND_NINE, HeartCard.HEART_SEVEN)
            );
        }
    }
}
