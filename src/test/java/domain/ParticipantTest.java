package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Participant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("초기 카드는 2장이어야 한다.")
    void validateCardsSize() {
        Cards cards = new Cards(List.of(
            new Card(Suit.CLOVER, Denomination.FIVE),
            new Card(Suit.CLOVER, Denomination.SIX),
            new Card(Suit.CLOVER, Denomination.A)));

        Participant participant = new Participant("roy") {
            @Override
            public boolean canAddCard() {
                return true;
            }
        };

        assertThatThrownBy(() -> participant.initCards(cards))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("초기 카드는 2장이어야 합니다.");
    }
}
