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
import model.card.Card;
import model.card.CardFace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("클레이");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"딜러"})
    void invalidNameCreate(String name) {
        assertThatThrownBy(() -> new Player(name))
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
        Dealer dealer = new Dealer();
        dealer.cards.addCard(new Card(DIAMOND, QUEEN));
        dealer.cards.addCard(new Card(SPADE, QUEEN));
        dealer.cards.addCard(new Card(CLOVER, QUEEN));

        player.cards.addCard(new Card(DIAMOND, KING));
        player.cards.addCard(new Card(SPADE, KING));
        player.cards.addCard(new Card(CLOVER, KING));
        assertThat(player.matchWith(dealer)).isEqualTo(Result.LOSE);
    }
}
