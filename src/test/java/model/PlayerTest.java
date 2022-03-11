package model;

import static model.card.CardFace.ACE;
import static model.card.CardFace.KING;
import static model.card.CardFace.QUEEN;
import static model.card.CardFace.TWO;
import static model.card.CardSuit.CLOVER;
import static model.card.CardSuit.DIAMOND;
import static model.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import model.card.Card;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void getCard() {
        Participator player = new Player("클레이");
        final Card firstCard = new Card(SPADE, ACE);
        final Card secondCard = new Card(SPADE, TWO);

        player.receiveCard(firstCard);
        player.receiveCard(secondCard);

        assertThat(player.getCards()).isEqualTo(Arrays.asList(firstCard, secondCard));
    }

    @Test
    void matchWithDealerBothBust() {
        Dealer dealer = new Dealer();
        dealer.cards.addCard(new Card(DIAMOND, QUEEN));
        dealer.cards.addCard(new Card(SPADE, QUEEN));
        dealer.cards.addCard(new Card(CLOVER, QUEEN));

        Player player = new Player("klay");
        player.cards.addCard(new Card(DIAMOND, KING));
        player.cards.addCard(new Card(SPADE, KING));
        player.cards.addCard(new Card(CLOVER, KING));
        assertThat(player.matchWith(dealer)).isEqualTo(Result.LOSE);
    }
}
