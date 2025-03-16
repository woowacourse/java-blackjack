package object.card;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardDeckTest {

    @Test
    void 카드셋_정적_팩토리_메서드_확인() {
        //given
        CardDeck cardDeck = CardDeck.generateFullPlayingCard();

        //when
        Assertions.assertThat(cardDeck.getCards().size()).isEqualTo(52);
    }

    @Test
    void 카드셋_정적_팩토리_메서드_확인_2() {
        //given
        CardDeck cardDeck = CardDeck.generateEmptySet();

        //when
        Assertions.assertThat(cardDeck.getCards().size()).isEqualTo(0);
    }

    @Test
    void 카드_섞기() {
        //given
        CardDeck originCardDeck = CardDeck.generateFullPlayingCard();
        CardDeck shuffledCardDeck = CardDeck.generateFullPlayingCard();

        //when
        shuffledCardDeck.shuffle();

        //then
        Assertions.assertThat(originCardDeck).isNotEqualTo(shuffledCardDeck);
    }

    @Test
    void 카드_뽑기() {
        //given
        CardDeck cardDeck = CardDeck.generateFullPlayingCard();

        //when
        Card card = cardDeck.draw();

        //then
        assertAll(
                () -> Assertions.assertThat(cardDeck.getCards().size()).isEqualTo(51),
                () -> Assertions.assertThat(card).isInstanceOf(Card.class)
        );
    }

    @Test
    void 카드_넣기() {
        //given
        CardDeck cardDeck = CardDeck.generateEmptySet();
        Card card = new Card(CardNumber.TWO, CardSymbol.CLOVER);

        //when
        cardDeck.addCard(card);

        //then
        Assertions.assertThat(cardDeck.getCards().size()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO, THREE, 5", "FOUR, FIVE, 9"})
    void 카드_점수_계산_테스트(CardNumber cardNumber1, CardNumber cardNumber2, int expected) {
        // given
        CardDeck cardDeck = CardDeck.generateEmptySet();
        Card card1 = new Card(cardNumber1, CardSymbol.CLOVER);
        Card card2 = new Card(cardNumber2, CardSymbol.CLOVER);
        cardDeck.addCard(card1);
        cardDeck.addCard(card2);

        // when
        int actual = cardDeck.getScore().getScore();

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
