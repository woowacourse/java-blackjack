package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandGeneratorTest {
    @DisplayName("카드를 2장 가진 Hand를 생성한다.")
    @Test
    void generateHandTest() {
        // TODO : Test용 구현체로 수정할 것
        Deck deck = RandomDeck.getInstance();
        HandGenerator handGenerator = new HandGenerator(deck);

        Hand hand = handGenerator.generate();

        assertThat(hand.getCards().size()).isEqualTo(2);
    }
}
