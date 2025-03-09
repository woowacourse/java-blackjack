package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.CardSymbol;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

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
}
