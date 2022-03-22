package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDenomination;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void dealer_normal_player_normal_higher_score() {
        Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))));
        Player player = new Player(new Cards(List.of(new Card(CardDenomination.FIVE, CardSuit.CLOVER))),
            new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isTrue(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isFalse());
    }

    @Test
    void dealer_normal_player_normal_lower_score() {
        Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))));
        Player player = new Player(new Cards(List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER))),
            new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isFalse(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isTrue());
    }

    @Test
    void dealer_normal_player_normal_equal_score() {
        Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))));
        Player player = new Player(new Cards(List.of(new Card(CardDenomination.NINE, CardSuit.CLOVER))),
            new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isFalse(),
            () -> assertThat(dealer.isDraw(player)).isTrue(),
            () -> assertThat(dealer.isLose(player)).isFalse());
    }

    @Test
    void dealer_blackjack_player_blackjack() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.CLOVER))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.HEART))),
            new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isFalse(),
            () -> assertThat(dealer.isDraw(player)).isTrue(),
            () -> assertThat(dealer.isLose(player)).isFalse());
    }

    @Test
    void dealer_blackjack_player_normal() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.CLOVER))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.TEN, CardSuit.SPADE),
                new Card(CardDenomination.ACE, CardSuit.HEART))), new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isTrue(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isFalse());
    }

    @Test
    void dealer_normal_player_blackjack() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
                new Card(CardDenomination.ACE, CardSuit.CLOVER))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.HEART))),
            new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isFalse(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isTrue());
    }

    @Test
    void dealer_bust_player_normal() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
                new Card(CardDenomination.TWO, CardSuit.SPADE))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART))),
            new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isFalse(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isTrue());
    }

    @Test
    void dealer_bust_player_blackjack() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
                new Card(CardDenomination.TWO, CardSuit.CLOVER))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.HEART))),
            new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isFalse(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isTrue());
    }

    @Test
    void dealer_bust_player_bust() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.JACK, CardSuit.CLOVER),
                new Card(CardDenomination.TWO, CardSuit.CLOVER))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART),
                new Card(CardDenomination.TWO, CardSuit.HEART))), new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isTrue(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isFalse());
    }

    @Test
    void dealer_normal_player_bust() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER),
                new Card(CardDenomination.JACK, CardSuit.CLOVER))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART),
                new Card(CardDenomination.TWO, CardSuit.HEART))), new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isTrue(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isFalse());
    }

    @Test
    void dealer_blackjack_player_bust() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.CLOVER))));
        Player player = new Player(new Cards(
            List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.JACK, CardSuit.HEART),
                new Card(CardDenomination.TWO, CardSuit.HEART))), new Name("pobi"));
        assertAll(() -> assertThat(dealer.isWin(player)).isTrue(),
            () -> assertThat(dealer.isDraw(player)).isFalse(),
            () -> assertThat(dealer.isLose(player)).isFalse());
    }
}
