package user;

import card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @DisplayName("카드를 뽑았을 때 21을 넘기면 버스트된다")
    @Test
    void test() {
        // given
        Participant player = new Player("수양");
        Participant dealer = new Dealer();

        CardDeck cardDeck = new CardDeck();
        player.drawCard(cardDeck.drawCard());
        player.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());
        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck.drawCard());
            dealer.drawCard(cardDeck.drawCard());
        }

        // when && then
        Assertions.assertThat(player.isBust()).isEqualTo(true);
        Assertions.assertThat(dealer.isBust()).isEqualTo(true);
    }
}