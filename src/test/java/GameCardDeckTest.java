import card.Card;
import card.GameCardDeck;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class GameCardDeckTest {

    @Test
    void 카드셋_정적_팩토리_메서드_확인() {
        //given
        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();

        //when
        Assertions.assertThat(gameCardDeck.getCards().size()).isEqualTo(52);
    }

    @Test
    void 카드_섞기() {
        //given
        GameCardDeck originGameCardDeck = GameCardDeck.generateFullPlayingCard();
        GameCardDeck shuffledGameCardDeck = GameCardDeck.generateFullPlayingCard();

        //when
        shuffledGameCardDeck.shuffle();

        //then
        Assertions.assertThat(originGameCardDeck).isNotEqualTo(shuffledGameCardDeck);
    }

    @Test
    void 카드_뽑기() {
        //given
        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();

        //when
        List<Card> cards = gameCardDeck.draw(1);

        //then
        assertAll(
                () -> Assertions.assertThat(gameCardDeck.getCards().size()).isEqualTo(51),
                () -> Assertions.assertThat(cards.getFirst()).isInstanceOf(Card.class)
        );
    }

}