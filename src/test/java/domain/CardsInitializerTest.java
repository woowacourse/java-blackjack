package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsInitializerTest {

    @DisplayName("초기 카드 뭉치를 생성한다.")
    @Test
    void createCards() {
        //given
        CardsInitializer cardsInitializer = new CardsInitializer();

        //when
        assertThatCode(cardsInitializer::initialize)
                .doesNotThrowAnyException();

    }

    @DisplayName("초기 카드 뭉치는 중복되지 않는다")
    @Test
    void notDuplicatedCards() {
        //given
        CardsInitializer cardsInitializer = new CardsInitializer();

        //when
        Cards cards = cardsInitializer.initialize();
        List<Card> cardList = cards.getCards();
        Set<Card> cardSet = new HashSet<>(cardList);

        //then
        assertThat(cardList.size()).isEqualTo(cardSet.size());
    }

    @DisplayName("카드리스트는 불변이다")
    @Test
    void immutableCardList() {
        CardsInitializer cardsInitializer = new CardsInitializer();

        //when
        Cards cards = cardsInitializer.initialize();
        List<Card> cardList = cards.getCards();
        //then
        assertThatThrownBy(() -> cardList.add(new Card(Symbol.HEART, Rank.FIVE)))
                .isInstanceOf(RuntimeException.class);
    }

}
