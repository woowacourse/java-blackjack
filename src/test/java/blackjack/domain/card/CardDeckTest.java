package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.cardGenerator.RandomCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
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

    @DisplayName("카드덱에서 카드를 뽑아 player에게 전달되는지 확인")
    @Test
    void drawTo() {
        //given
        final Player dealer = new Dealer();

        final PlayingCard expectedCard = new PlayingCard(Suit.CLUBS, Denomination.FIVE);
        Deque<PlayingCard> playingCards = new ArrayDeque<>();
        playingCards.push(expectedCard);
        final CardDeck cardDeck = new CardDeck(() -> playingCards);

        //when
        cardDeck.drawTo(dealer);
        final int actual = dealer.getSumOfCards();

        //then
        assertThat(actual).isEqualTo(5);
    }
}
