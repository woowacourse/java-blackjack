package domain;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.Cards;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantTest {

    @Test
    @DisplayName("초기 카드는 2장이어야 한다.")
    void validateCardsSize() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(List.of(
                new Card(CardType.CLOVER, CardValue.FIVE),
                new Card(CardType.CLOVER, CardValue.SIX),
                new Card(CardType.CLOVER, CardValue.ACE)));
        Money money = new Money(1000);

        assertThatThrownBy(() -> new Participant(name, cards, money) {
            @Override
            public boolean canReceiveOneMoreCard() {
                return true;
            }
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초기 카드는 2장이어야 합니다.");
    }
}
