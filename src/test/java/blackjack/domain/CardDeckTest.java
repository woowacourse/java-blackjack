package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.cardGenerator.RandomCardGenerator;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드덱 랜덤 생성 테스트")
    void deck_random_create() {
        assertThatCode(
            () -> new CardDeck(new RandomCardGenerator())
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드덱 수동 생성 테스트")
    void deck_manual_create() {
        Stack<PlayingCard> playingCards = new Stack<>();
        playingCards.push(new PlayingCard(Suit.CLUBS, Denomination.FIVE));
        playingCards.push(new PlayingCard(Suit.CLUBS, Denomination.SIX));
        playingCards.push(new PlayingCard(Suit.CLUBS, Denomination.SEVEN));

        final CardDeck cardDeck = new CardDeck( () -> playingCards);
        final PlayingCard poppedCard = cardDeck.pop();

        assertThat(poppedCard).isEqualTo(new PlayingCard(Suit.CLUBS, Denomination.SEVEN));
    }
}
