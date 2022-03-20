package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateFactoryTest {

    @Test
    @DisplayName("처음 받은 카드의 합이 Blackjack 인 경우, Blackjack State 를 생성한다.")
    void createBlackjackState() {
        final Cards cards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.DIAMOND, CardNumber.ACE)
        ));

        final State actual = StateFactory.createFirstState(cards);

        assertThat(actual).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("처음 받은 카드의 합이 Blackjack 이 아닌 경우, Hit State 를 생성한다.")
    void createHitState() {
        final Cards cards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.DIAMOND, CardNumber.TEN)
        ));

        final State actual = StateFactory.createFirstState(cards);

        assertThat(actual).isInstanceOf(Hit.class);
    }
}
