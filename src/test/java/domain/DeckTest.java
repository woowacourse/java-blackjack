package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import domain.model.Deck;
import domain.model.DeckStatus;
import domain.model.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 초기_카드_2장의_합이_21인_경우() {
        // given
        List<Card> cards = List.of(
                Card.of(CardRank.ACE, CardShape.CLUB),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );

        // when
        Deck deck = Deck.of(cards);

        // then
        assertThat(deck.getDeckStatus()).isEqualTo(DeckStatus.BLACK_JACK);
    }
}
