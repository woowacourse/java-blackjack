package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayingCardsTest {

    private Card aceSpade;
    private Card queenSpade;

    @BeforeEach
    void before() {
        aceSpade = Card.of(Denomination.ACE, Suit.SPADE);
        queenSpade = Card.of(Denomination.QUEEN, Suit.SPADE);
    }

    @DisplayName("카드들의 총합이 올바르게 계산되는지 확인한다.")
    @Test
    void calculate_total() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(queenSpade, queenSpade));

        assertThat(playingCards.total()).isEqualTo(20);
    }

    @DisplayName("카드들의 총합이 21을 초과하는지 확인한다.")
    @Test
    void is_over_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(queenSpade, queenSpade, queenSpade));

        assertThat(playingCards.isBust()).isTrue();
    }

    @DisplayName("카드들의 총합이 21미만인지 확인한다.")
    @Test
    void is_under_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(queenSpade, queenSpade));

        assertThat(playingCards.isUnderBlackjack()).isTrue();
    }

    @DisplayName("카드가 블랙잭인지 확인한다.")
    @Test
    void is_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(aceSpade, queenSpade));

        assertThat(playingCards.isBlackjack()).isTrue();
    }
}
