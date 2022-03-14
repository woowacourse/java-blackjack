package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.cardGenerator.RandomCardGenerator;
import java.util.ArrayDeque;
import java.util.Deque;
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
        //given
        final PlayingCard expectedCard = new PlayingCard(Suit.CLUBS, Denomination.FIVE);

        Deque<PlayingCard> playingCards = new ArrayDeque<>();
        playingCards.push(expectedCard);

        //when
        final PlayingCard card = new CardDeck(() -> playingCards).pop();

        //then
        assertThat(card).isEqualTo(expectedCard);
    }
}
