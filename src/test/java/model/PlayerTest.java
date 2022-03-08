package model;

import static model.CardFace.ACE;
import static model.CardFace.TWO;
import static model.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void getCard() {
        Participator player = new Player(new PlayerName("클레이"));
        final Card firstCard = new Card(SPADE, ACE);
        final Card secondCard = new Card(SPADE, TWO);

        player.receiveCard(firstCard);
        player.receiveCard(secondCard);

        assertThat(player.getCards()).isEqualTo(Arrays.asList(firstCard, secondCard));
    }
}
