package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Pattern;
import blackjack.model.card.Rank;
import blackjack.model.deck.DeckGenerator;
import blackjack.model.deck.PlayingDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayingDeckTest {

    @DisplayName("playingDeck은 List<Card>를 shuffle한다.")
    @Test
    void shuffleCard() {
        //given
        List<Card> generatedDeck = DeckGenerator.generateDeck();
        PlayingDeck playingDeck = new PlayingDeck(generatedDeck);

        Card card = playingDeck.drawCard();
        Card firstCard = new Card(Pattern.SPADE, Rank.ACE);

        //when
        boolean isShuffled = !card.equals(firstCard);

        //then
        assertThat(isShuffled).isTrue();
    }

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
        PlayingDeck playingDeck = new PlayingDeck(new LinkedList<>());

        //when, then
        assertThatThrownBy(playingDeck::drawCard)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
