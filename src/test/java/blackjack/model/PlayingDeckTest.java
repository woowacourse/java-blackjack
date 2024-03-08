package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.deck.DeckGenerator;
import blackjack.model.deck.PlayingDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingDeckTest {

    @DisplayName("덱에서 카드를 한 장 뽑는다.")
    @Test
    void drawCard() {
        //given
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck());

        //when, then
        assertThat(playingDeck.drawCard()).isInstanceOf(Card.class);

    }

    @DisplayName("비어있는 덱에서 카드를 뽑을 경우 예외를 발생시킨다.")
    @Test
    void drawCardByEmptyDeck() {
        //given
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck());

        //when
        for (int i = 0; i < 52; i++) {
            playingDeck.drawCard();
        }

        //when, then
        assertThatThrownBy(playingDeck::drawCard)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
