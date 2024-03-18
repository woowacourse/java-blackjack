package blackjack.domain.participant;

import static blackjack.domain.deck.Kind.DIAMOND;
import static blackjack.domain.deck.Kind.SPADE;
import static blackjack.domain.deck.Value.ACE;
import static blackjack.domain.deck.Value.KING;
import static blackjack.domain.deck.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {

    @DisplayName("카드패에 카드를 추가할 수 있다")
    @Test
    void should_addCard_IntoHands() {
        Hands hands = new Hands();

        hands.addCard(new Card(SPADE, TWO));
        assertThat(hands.getHands()).hasSize(1);

        hands.addCard(new Card(DIAMOND, ACE));
        assertThat(hands.getHands()).hasSize(2);
    }

    @DisplayName("카드패의 총 점수는 보유한 카드 점수들의 합이다")
    @Test
    void should_HandsScore_Equals_SumOfCardScores() {
        Hands hands = new Hands();

        hands.addCard(new Card(SPADE, TWO));
        hands.addCard(new Card(SPADE, KING));

        assertThat(hands.findHandsScore()).isEqualTo(12);
    }


}
