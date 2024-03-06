package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어")
public class PlayerTest {

    @DisplayName("플레이어에게 카드를 더 뽑을지 물어본다.")
    @Test
    void canReceiveCard() {
        //given
        Card cardAceClover = new Card(Rank.ACE, Suit.CLOVER);
        Card cardAceDiamond = new Card(Rank.ACE, Suit.DIAMOND);
        Card cardKingSpade = new Card(Rank.KING, Suit.SPADE);


        //when
        Player canAddCardPlayer = new Player("choco");
        canAddCardPlayer.addCard(cardAceClover, cardAceDiamond);

        Player cantAddCardPlayer = new Player("clover");
        cantAddCardPlayer.addCard(cardKingSpade, cardAceDiamond);

        //then
        assertThat(canAddCardPlayer.canReceiveCard()).isTrue();
        assertThat(cantAddCardPlayer.canReceiveCard()).isFalse();
    }
}
