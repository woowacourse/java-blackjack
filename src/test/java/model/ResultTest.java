package model;

import static model.card.CardFace.ACE;
import static model.card.CardFace.JACK;
import static model.card.CardFace.KING;
import static model.card.CardFace.NINE;
import static model.card.CardFace.QUEEN;
import static model.card.CardFace.TEN;
import static model.card.CardFace.TWO;
import static model.card.CardSuit.CLOVER;
import static model.card.CardSuit.DIAMOND;
import static model.card.CardSuit.HEART;
import static model.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {
    private Cards playerBlackJack;
    private Cards dealerBlackJack;
    private Cards bigStand;
    private Cards smallStand;
    private Cards playerBust;
    private Cards dealerBust;

    @BeforeEach
    void setUp() {
        playerBlackJack = new Cards(List.of(new Card(HEART, KING), new Card(DIAMOND, ACE)));
        dealerBlackJack = new Cards(List.of(new Card(DIAMOND, KING), new Card(HEART, ACE)));
        bigStand = new Cards(List.of(new Card(DIAMOND, QUEEN), new Card(DIAMOND, NINE), new Card(DIAMOND, TWO)));
        smallStand = new Cards(List.of(new Card(SPADE, TEN), new Card(SPADE, KING)));
        playerBust = new Cards(List.of(new Card(CLOVER, KING), new Card(CLOVER, QUEEN), new Card(CLOVER, TWO)));
        dealerBust = new Cards(List.of(new Card(CLOVER, JACK), new Card(CLOVER, TEN), new Card(HEART, TWO)));
    }

    @Test
    void blackJackVsBlackJack() {
        assertThat(Result.of(playerBlackJack, dealerBlackJack)).isEqualTo(Result.DRAW);
    }

    @Test
    void blackJackVsStand() {
        assertThat(Result.of(playerBlackJack, bigStand)).isEqualTo(Result.WIN);
        assertThat(Result.of(smallStand, dealerBlackJack)).isEqualTo(Result.LOSE);
    }

    @Test
    void bustVsBlackJack() {
        assertThat(Result.of(playerBust, dealerBlackJack)).isEqualTo(Result.LOSE);
        assertThat(Result.of(playerBlackJack, dealerBust)).isEqualTo(Result.WIN);
    }

    @Test
    void bustVsStand() {
        assertThat(Result.of(playerBust, bigStand)).isEqualTo(Result.LOSE);
        assertThat(Result.of(smallStand, dealerBust)).isEqualTo(Result.WIN);
    }

    @Test
    void bustVsBust() {
        assertThat(Result.of(playerBust, dealerBust)).isEqualTo(Result.LOSE);
    }

    @Test
    void standVsStand() {
        assertThat(Result.of(bigStand, smallStand)).isEqualTo(Result.WIN);
        assertThat(Result.of(smallStand, bigStand)).isEqualTo(Result.LOSE);
    }
}
