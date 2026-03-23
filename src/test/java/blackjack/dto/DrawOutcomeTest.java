package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawOutcomeTest {

    @Test
    @DisplayName("카드를 뽑은 결과를 저장하는 dto 생성을 확인한다.")
    void makeDrawResult() {
        // given
        Deck drewDeck = Deck.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART),
            new Card(Rank.TEN, Suit.CLOVER)
        ));
        Hand drewCards = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE)
        ));

        // when
        DrawOutcome drawOutcome = DrawOutcome.of(drewCards.getCards(), drewDeck.getCards());

        // then
        assertAll(
            () -> assertThat(drawOutcome.drewDeck().getCards()).isEqualTo(drewDeck.getCards()),
            () -> assertThat(drawOutcome.drewCard().getCards()).isEqualTo(drewCards.getCards())
        );
    }
}
