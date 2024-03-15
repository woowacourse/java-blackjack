package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import static domain.card.CardFixture.cardOf;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardMachineTest {

    @DisplayName("블랙잭 게임을 위한 카드덱을 생성한다.")
    @Test
    void cardDecks() {
        Deck deck = CardMachine.newDeck();
        for (Rank expected : Rank.values()) {
            assertThat(deck.draw()).isEqualTo(cardOf(expected));
        }
    }
}
