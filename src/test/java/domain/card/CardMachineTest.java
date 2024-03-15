package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.card.CardFixture.cardOf;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardMachineTest {

    @DisplayName("블랙잭 게임을 위한 카드덱을 생성한다.")
    @Test
    void cardDecks() {
        Cards cards = CardMachine.cardDecks();
        List<Card> cardList = cards.stream().toList();
        assertThat(cardList).hasSize(312);
        assertThat(cardList).containsSequence(
                cardOf(Rank.ACE), cardOf(Rank.TWO), cardOf(Rank.THREE),
                cardOf(Rank.FOUR), cardOf(Rank.FIVE), cardOf(Rank.SIX),
                cardOf(Rank.SEVEN), cardOf(Rank.EIGHT), cardOf(Rank.NINE),
                cardOf(Rank.TEN), cardOf(Rank.JACK), cardOf(Rank.QUEEN),
                cardOf(Rank.KING)
        );
    }
}
