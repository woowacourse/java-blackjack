package model;

import static model.card.CardFace.ACE;
import static model.card.CardFace.KING;
import static model.card.CardFace.QUEEN;
import static model.card.CardFace.TWO;
import static model.card.CardSuit.CLOVER;
import static model.card.CardSuit.DIAMOND;
import static model.card.CardSuit.HEART;
import static model.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import model.betting.BlackJacCalculateStrategy;
import model.betting.NormalCalculateStrategy;
import model.card.Card;
import model.card.CardFace;
import model.participator.Dealer;
import model.participator.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    private Player player;
    private Player thousandBettedPlayer;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("클레이", 0);
        thousandBettedPlayer = new Player("베팅한 플레이어", 1000);
        dealer = new Dealer();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"딜러"})
    void invalidNameCreate(String name) {
        assertThatThrownBy(() -> new Player(name, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("이름 입력 실패 :");
    }

    @Test
    void getCard() {
        final Card firstCard = new Card(SPADE, ACE);
        final Card secondCard = new Card(SPADE, TWO);

        player.receiveCard(firstCard);
        player.receiveCard(secondCard);

        assertThat(player.getCards()).isEqualTo(Arrays.asList(firstCard, secondCard));
    }

    @Test
    void cannotReceiveCard() {
        player.receiveCard(new Card(SPADE, CardFace.QUEEN));
        player.receiveCard(new Card(HEART, CardFace.QUEEN));
        player.receiveCard(new Card(DIAMOND, CardFace.QUEEN));

        assertThat(player.canReceiveCard()).isFalse();
    }

    @Test
    void canReceiveCard() {
        player.receiveCard(new Card(SPADE, CardFace.QUEEN));
        player.receiveCard(new Card(HEART, CardFace.QUEEN));

        assertThat(player.canReceiveCard()).isTrue();
    }

    @Test
    void canReceiveCardWithAce() {
        player.receiveCard(new Card(SPADE, CardFace.EIGHT));
        player.receiveCard(new Card(HEART, ACE));
        player.receiveCard(new Card(DIAMOND, ACE));

        assertThat(player.canReceiveCard()).isTrue();
    }

    @Test
    void cannotReceiveCardWithAce() {
        player.receiveCard(new Card(HEART, ACE));
        player.receiveCard(new Card(DIAMOND, ACE));
        player.receiveCard(new Card(SPADE, CardFace.NINE));

        assertThat(player.canReceiveCard()).isFalse();
    }

    @Test
    void matchWithDealerBothBust() {
        dealer.receiveCard(new Card(DIAMOND, QUEEN));
        dealer.receiveCard(new Card(SPADE, QUEEN));
        dealer.receiveCard(new Card(CLOVER, QUEEN));

        player.receiveCard(new Card(DIAMOND, KING));
        player.receiveCard(new Card(SPADE, KING));
        player.receiveCard(new Card(CLOVER, KING));
        assertThat(player.matchWith(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    void initBettingAmount() {
        assertThat(thousandBettedPlayer.getBettingAmount()).isEqualTo(1000);
    }

    @Test
    void lostBet() {
        thousandBettedPlayer.lostBet(dealer);
        assertThat(thousandBettedPlayer.getProfit()).isEqualTo(-1000);
        assertThat(dealer.getProfit()).isEqualTo(1000);
    }

    @Test
    void normalWinBet() {
        thousandBettedPlayer.winBet(dealer, new NormalCalculateStrategy());
        assertThat(thousandBettedPlayer.getProfit()).isEqualTo(1000);
        assertThat(dealer.getProfit()).isEqualTo(-1000);
    }

    @Test
    void blackJackWinBet() {
        thousandBettedPlayer.winBet(dealer, new BlackJacCalculateStrategy());
        assertThat(thousandBettedPlayer.getProfit()).isEqualTo(1500);
        assertThat(dealer.getProfit()).isEqualTo(-1500);
    }
}
