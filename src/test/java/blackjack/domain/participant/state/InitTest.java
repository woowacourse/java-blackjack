package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitTest {

    @Test
    @DisplayName("처음 받은 2장의 카드를 받은 후 Blackjack인지 테스트")
    void testInitDrawBlackjack() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.ACE);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);

        State blackjack = Init.draw(firstCard, secondCard);

        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("처음 받은 2장의 카드를 받은 후 Hit인지 테스트")
    void testInitDrawHit() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.TWO);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);

        State hit = Init.draw(firstCard, secondCard);

        assertThat(hit).isInstanceOf(Hit.class);
    }
}