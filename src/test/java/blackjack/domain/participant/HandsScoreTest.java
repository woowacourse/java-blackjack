package blackjack.domain.participant;

import static blackjack.domain.deck.Kind.DIAMOND;
import static blackjack.domain.deck.Kind.SPADE;
import static blackjack.domain.deck.Value.ACE;
import static blackjack.domain.deck.Value.JACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.deck.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsScoreTest {

    @DisplayName("카드패의 점수가 21을 초과하지 않았는 지 판단한다")
    @Test
    void should_bust_when_Below_BlackJack() {
        List<Card> overCards = List.of(new Card(SPADE, JACK), new Card(DIAMOND, ACE));

        HandsScore handsScore = HandsScore.of(overCards);

        assertTrue(handsScore.isBust());
    }

    @DisplayName("카드패의 점수가 21을 초과했는지 판단한다")
    @Test
    void should_bust_when_Over_BlackJack() {
        List<Card> overCards = List.of(new Card(SPADE, ACE), new Card(DIAMOND, ACE), new Card(SPADE, JACK));

        HandsScore handsScore = HandsScore.of(overCards);

        assertTrue(handsScore.isBust());
    }

    @DisplayName("에이스는 21이 초과되지 않으면 11로 사용할 수 있다")
    @Test
    void should_getHighAce_When_Under_BlackJack() {
        HandsScore handsScore = HandsScore.of(List.of(new Card(SPADE, ACE), new Card(SPADE, JACK)));

        assertThat(handsScore.getScore()).isEqualTo(21);
    }

    @DisplayName("에이스는 21이 초과이 초과되면 1로 사용할 수 있다")
    @Test
    void should_getLowACE_When_Over_BlackJack() {
        HandsScore handsScore = HandsScore.of(List.of(new Card(SPADE, ACE), new Card(DIAMOND, ACE)));

        assertThat(handsScore.getScore()).isEqualTo(12);
    }
}
