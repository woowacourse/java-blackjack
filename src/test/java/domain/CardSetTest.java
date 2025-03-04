package domain;

import domain.card.Card;
import domain.card.CardSet;
import domain.card.CardType;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

public class CardSetTest {

    @Test
    void 카드셋_정적_팩토리_메서드_확인() {
        //given
        CardSet cardSet = CardSet.generateFullSet();

        //when
        Assertions.assertThat(cardSet.getCards().size()).isEqualTo(52);
    }

    @Test
    void 카드셋_정적_팩토리_메서드_확인_2() {
        //given
        CardSet cardSet = CardSet.generateEmptySet();

        //when
        Assertions.assertThat(cardSet.getCards().size()).isEqualTo(0);
    }

    @Test
    void 카드_섞기() {
        //given
        CardSet originCardSet = CardSet.generateFullSet();
        CardSet shuffledCardSet = CardSet.generateFullSet();

        //when
        shuffledCardSet.shuffle();

        //then
        Assertions.assertThat(originCardSet).isNotEqualTo(shuffledCardSet);
    }

    @Test
    void 카드_뽑기() {
        //given
        CardSet cardSet = CardSet.generateFullSet();

        //when
        Card card = cardSet.draw();

        //then
        assertAll(
                () -> Assertions.assertThat(cardSet.getCards().size()).isEqualTo(51),
                () -> Assertions.assertThat(card).isInstanceOf(Card.class)
        );
    }

    @Test
    void 카드_넣기() {
        //given
        CardSet cardSet = CardSet.generateEmptySet();
        Card card = new Card(1, CardType.CLOVER);

        //when
        cardSet.addCard(card);

        //then
        Assertions.assertThat(cardSet.getCards().size()).isEqualTo(1);
    }
}
