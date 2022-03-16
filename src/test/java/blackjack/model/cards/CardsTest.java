package blackjack.model.cards;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);


    @Test
    @DisplayName("공개 카드 반환")
    void openCard() {
        Cards cards = new Cards(ACE, JACK, QUEEN, KING);
        Cards openCards = cards.openedCards(2);
        assertThat(openCards).isEqualTo(new Cards(ACE, JACK));
    }

    @Test
    @DisplayName("카드 발급")
    void takeCard() {
        Cards cards = new Cards(ACE, JACK);
        cards.take(QUEEN);
        assertThat(cards.values()).hasSize(3);
        assertThat(cards.values()).contains(ACE, JACK, QUEEN);
    }
}
