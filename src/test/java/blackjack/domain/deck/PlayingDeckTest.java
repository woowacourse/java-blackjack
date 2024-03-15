package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.deck.shuffle.NoShuffle;
import blackjack.domain.deck.shuffle.RandomShuffle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayingDeckTest {

    @DisplayName("playingDeck은 List<Card>를 shuffle하거나 하지 않을 수 있다.")
    @Test
    void shuffleCard() {
        //given
        List<Card> generatedDeck = DeckGenerator.generateDeck();
        PlayingDeck playingDeck_notShuffled_First = new PlayingDeck(generatedDeck, new NoShuffle());
        PlayingDeck playingDeck_notShuffled_Second = new PlayingDeck(generatedDeck, new NoShuffle());
        PlayingDeck playingDeck_Shuffled = new PlayingDeck(generatedDeck, new RandomShuffle());

        boolean isNotShuffled;
        boolean isShuffled;

        //when
        isNotShuffled = playingDeck_notShuffled_First.equals(playingDeck_notShuffled_Second);
        isShuffled = !playingDeck_notShuffled_First.equals(playingDeck_Shuffled);

        //then
        assertAll(
                () -> assertThat(isNotShuffled).isTrue(),
                () -> assertThat(isShuffled).isTrue()
        );

    }

    @DisplayName("덱에서 카드를 한 장 뽑는다.")
    @Test
    void drawCard() {
        //given
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());

        //when, then
        assertThat(playingDeck.drawCard()).isInstanceOf(Card.class);

    }

    @DisplayName("비어있는 덱에서 카드를 뽑을 경우 예외를 발생시킨다.")
    @Test
    void drawCardByEmptyDeck() {
        //given
        PlayingDeck playingDeck = new PlayingDeck(new LinkedList<>(), new NoShuffle());

        //when, then
        assertThatThrownBy(playingDeck::drawCard)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
