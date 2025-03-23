package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.card.initializer.StandardCardsInitializer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StandardCardsInitializerTest {

    @DisplayName("초기 카드 뭉치를 생성한다.")
    @Test
    void createCards() {
        //given
        StandardCardsInitializer cardsInitializer = new StandardCardsInitializer();

        //when //then
        assertThatCode(cardsInitializer::initialize)
                .doesNotThrowAnyException();

    }

    @DisplayName("초기 카드 뭉치는 중복되지 않는다")
    @Test
    void notDuplicatedCards() {
        //given
        StandardCardsInitializer cardsInitializer = new StandardCardsInitializer();

        //when
        List<Card> cards = cardsInitializer.initialize();
        Set<Card> cardSet = new HashSet<>(cards);

        int actual = cardSet.size();
        int expected = cards.size();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
