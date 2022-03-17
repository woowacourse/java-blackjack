package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardPattern.DIAMOND;
import static blackjack.domain.card.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultTest {

    @Test
    @DisplayName("딜러가 Bust일 경우 플레이어가 우승한다.")
    void playerWinsByDealerBust() {
        final Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, FIVE),
                new Card(DIAMOND, TEN)));
        final Deck dealerDeck = new Deck(List.of(
                new Card(DIAMOND, TWO),
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, JACK)));

        final Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        final Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        dealer.drawCard(dealerDeck);

        final PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.WIN;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어가 Blackjack일 경우 플레이어가 우승한다.")
    void playerWinsWithBlackjack() {
        final Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, ACE),
                new Card(DIAMOND, TEN)
        ));
        final Deck dealerDeck = new Deck(List.of(
                new Card(DIAMOND, ACE),
                new Card(DIAMOND, JACK),
                new Card(DIAMOND, QUEEN)
        ));

        final Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        final Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        dealer.drawCard(dealerDeck);

        final PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.WIN;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 높을 경우 플레이어가 우승한다.")
    void playerWinsByHigherScore() {
        final Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, JACK),
                new Card(DIAMOND, TEN)
        ));
        final Deck dealerDeck = new Deck(List.of(
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, FIVE),
                new Card(DIAMOND, FOUR)
        ));

        final Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        final Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        dealer.drawCard(dealerDeck);

        final PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.WIN;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어가 Bust일 경우 플레이어가 패배한다.")
    void playerLosesByBurst() {
        Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, JACK),
                new Card(DIAMOND, FIVE)
        ));
        Deck dealerDeck = new Deck(List.of(
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, JACK)
        ));

        Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        player.drawCard(playerDeck);

        PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.LOSS;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 Blackjack일 경우 플레이어가 패배한다.")
    void playerLosesByDealerWithBlackjack() {
        Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, JACK),
                new Card(DIAMOND, FIVE)
        ));
        Deck dealerDeck = new Deck(List.of(
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, JACK)
        ));

        Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        player.drawCard(playerDeck);

        PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.LOSS;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 낮을 경우 플레이어가 패배한다.")
    void playerLosesByLowerScore() {
        Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, JACK),
                new Card(DIAMOND, FIVE),
                new Card(DIAMOND, FOUR)
        ));
        Deck dealerDeck = new Deck(List.of(
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, JACK)
        ));

        Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        player.drawCard(playerDeck);

        PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.LOSS;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어와 딜러의 점수가 같아 무승부를 반환한다.")
    void playerDrawsWithDealer() {
        Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, TEN),
                new Card(DIAMOND, JACK)
        ));
        Deck dealerDeck = new Deck(List.of(
                new Card(SPADE, TEN),
                new Card(SPADE, JACK)
        ));

        Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        Dealer dealer = Dealer.startWithTwoCards(dealerDeck);

        PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.DRAW;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘다 Blackjack이어서 무승부를 반환한다.")
    void playerDrawsWithDealerByBlackjack() {
        Deck playerDeck = new Deck(List.of(
                new Card(DIAMOND, ACE),
                new Card(DIAMOND, JACK)
        ));
        Deck dealerDeck = new Deck(List.of(
                new Card(SPADE, ACE),
                new Card(SPADE, JACK)
        ));

        Player player = Players.startWithTwoCards(List.of("sun"), List.of(1000), playerDeck)
                .getStatuses()
                .get(0);
        Dealer dealer = Dealer.startWithTwoCards(dealerDeck);

        PlayerResult actual = PlayerResult.of(player, dealer);
        final PlayerResult expected = PlayerResult.DRAW;

        assertThat(actual).isEqualTo(expected);
    }
}
