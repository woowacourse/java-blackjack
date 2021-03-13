package blackjack.domain.card;

import static blackjack.domain.card.CardSpec.ACE;
import static blackjack.domain.card.CardSpec.KING;
import static blackjack.domain.card.CardSpec.QUEEN;
import static blackjack.domain.card.CardSpec.THREE;
import static blackjack.domain.card.CardSpec.TWO;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("ACE가 없는 덱의 스코어 계산 확인")
    void score_withoutAce() {
        Deck deck = Deck.of(
            TWO.card(),
            THREE.card()
        );
        Assertions.assertThat(deck.score()).isEqualTo(Score.of(5));
    }

    @Test
    @DisplayName("ACE가 1로 계산이 되는 경우(ACE가 11로 게산될 경우 BUST가 될 때)")
    void score_withAceAsOne() {
        Deck deck = Deck.of(
            KING.card(),
            QUEEN.card(),
            ACE.card()
        );
        Assertions.assertThat(deck.score()).isEqualTo(Score.of(21));
    }

    @Test
    @DisplayName("ACE가 11로 계산이 되는 경우(ACE가 11로 계산해도 HIT일 경우)")
    void score_withAceAsEleven() {
        Deck deck = Deck.of(
            ACE.card(),
            TWO.card()
        );
        Assertions.assertThat(deck.score()).isEqualTo(Score.of(13));
    }
}