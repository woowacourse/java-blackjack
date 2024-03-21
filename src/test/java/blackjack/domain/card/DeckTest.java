package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.FIVE;
import static blackjack.domain.card.CardSuit.CLUB;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("덱")
public class DeckTest {
    @Test
    @DisplayName("에서 카드 두 장을 뽑을 수 있다.")
    void draw() {
        // given
        Deck deck = new Deck(cards -> List.of(new Card(CLUB, ACE), new Card(CLUB, FIVE)));
        int count = 2;

        // when
        List<Card> cards = deck.draw(count);

        // then
        assertThat(cards.size()).isEqualTo(2);
    }
}
