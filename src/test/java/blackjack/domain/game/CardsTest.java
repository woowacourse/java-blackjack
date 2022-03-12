package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    private Card queenSpade;

    @BeforeEach
    void before() {
        queenSpade = Card.of(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("카드들의 총합이 올바르게 계산되는지 확인한다.")
    @Test
    void calculate_total() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(queenSpade, queenSpade));

        assertThat(playingCards.calculateTotal()).isEqualTo(20);
    }

    @DisplayName("카드들의 총합이 21을 초과하는지 확인한다.")
    @Test
    void is_over_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(queenSpade, queenSpade, queenSpade));

        assertThat(playingCards.isOverBlackjack()).isTrue();
    }

    @DisplayName("카드들의 총합이 21미만인지 확인한다.")
    @Test
    void is_under_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(queenSpade, queenSpade));

        assertThat(playingCards.isUnderBlackjack()).isTrue();
    }
}
