package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Card;
import blackjack.domain.PlayingCards;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class DrawResultTest {

    @Test
    void makeDrawResult() {
        // given
        PlayingCards drewDeck = PlayingCards.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TEN, Suit.CLOVER)
        ));
        PlayingCards drewCards = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE)
        ));

        // when
        DrawResult drawResult = DrawResult.of(drewCards, drewDeck);

        // then
        assertAll(
            () -> assertThat(drawResult.drewDeck().getStatusByDisplayName()).isEqualTo("A스페이드, 10하트, 10클로버"),
            () -> assertThat(drawResult.drewCard().getStatusByDisplayName()).isEqualTo("10스페이드")
        );
    }
}
