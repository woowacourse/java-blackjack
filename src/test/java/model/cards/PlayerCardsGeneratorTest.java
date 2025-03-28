package model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.deck.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerCardsGeneratorTest {

    @DisplayName("덱에서 2장을 뽑아 플레이어 카드를 생성한다.")
    @Test
    void test1() {
        Card fisrtCard = new Card(CardNumber.QUEEN, CardShape.SPADE);
        Card secondCard = new Card(CardNumber.EIGHT, CardShape.HEART);
        Cards cards = new PlayerCardsGenerator().generate(new Deck(
                new ArrayList<>(List.of(fisrtCard, secondCard))
        ));

        assertThat(cards.getCards()).containsExactlyInAnyOrder(fisrtCard, secondCard);
    }
}
