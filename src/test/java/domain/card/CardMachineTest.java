package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.card.CardGenerator.cardOf;

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
                cardOf(CardRank.ACE), cardOf(CardRank.TWO), cardOf(CardRank.THREE),
                cardOf(CardRank.FOUR), cardOf(CardRank.FIVE), cardOf(CardRank.SIX),
                cardOf(CardRank.SEVEN), cardOf(CardRank.EIGHT), cardOf(CardRank.NINE),
                cardOf(CardRank.TEN), cardOf(CardRank.JACK), cardOf(CardRank.QUEEN),
                cardOf(CardRank.KING)
        );
    }
}
