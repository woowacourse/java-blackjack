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
import model.participator.Dealer;
import model.participator.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {
    private Player player;
    private Dealer dealer;
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

    void initParticipators(Cards playerCards, Cards dealerCards) {
        initPlayer(playerCards);
        initDealer(dealerCards);
    }

    private void initPlayer(Cards playerCards) {
        player = new Player("player", 1);
        for (Card card : playerCards.getCardsByStrategy()) {
            player.receiveCard(card);
        }
    }

    private void initDealer(Cards dealerCards) {
        dealer = new Dealer();
        for (Card card : dealerCards.getCardsByStrategy()) {
            dealer.receiveCard(card);
        }
    }

    @Test
    void blackJackVsBlackJack() {
        initParticipators(playerBlackJack, dealerBlackJack);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    void blackJackVsStand() {
        initParticipators(playerBlackJack, bigStand);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.WIN);
        initParticipators(smallStand, dealerBlackJack);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    void bustVsBlackJack() {
        initParticipators(playerBust, dealerBlackJack);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.LOSE);
        initParticipators(playerBlackJack, dealerBust);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    void bustVsStand() {
        initParticipators(playerBust, bigStand);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.LOSE);
        initParticipators(smallStand, dealerBust);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    void bustVsBust() {
        initParticipators(playerBust, dealerBust);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    void standVsStand() {
        initParticipators(bigStand, smallStand);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.WIN);
        initParticipators(smallStand, bigStand);
        assertThat(Result.of(player, dealer)).isEqualTo(Result.LOSE);
    }
}
