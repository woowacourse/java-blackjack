package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static blackjack.domain.card.CardScore.*;
import static blackjack.domain.card.CardSuit.CLUB;
import static blackjack.domain.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("심판")
public class RefereeTest {
    @Test
    @DisplayName("심판은 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 플레이어를 승자로 여긴다.")
    void playerWin() {
        // given
        String name = "lemone";
        List<Card> dealerCards = List.of(new Card(SPADE, EIGHT), new Card(CLUB, QUEEN));
        List<Card> playerCards = List.of(new Card(SPADE, NINE), new Card(CLUB, QUEEN));
        GameResult expectedPlayerResult = GameResult.WIN;

        // when
        Dealer dealer = new Dealer(new Deck(cards -> dealerCards));
        Players players = Players.of(List.of(name), new Deck(cards -> playerCards));
        Referee referee = new Referee();

        dealer.deal();
        players.deal();
        referee.calculatePlayersResults(dealer, players);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(Map.of(name, expectedPlayerResult));
    }

    @Test
    @DisplayName("심판은 숫자 합이 21을 초과한 플레이어를 패배자로 여긴다.")
    void playerLoseWhenBust() {
        // given
        String name = "lemone";
        List<Card> dealerCards = List.of(new Card(SPADE, EIGHT), new Card(SPADE, TWO), new Card(CLUB, QUEEN));
        List<Card> playerCards = List.of(new Card(SPADE, NINE), new Card(CLUB, QUEEN), new Card(CLUB, THREE));
        GameResult expectedPlayerResult = GameResult.LOSE;

        // when
        Dealer dealer = new Dealer(new Deck(cards -> dealerCards));
        Players players = Players.of(List.of(name), new Deck(cards -> playerCards));
        Referee referee = new Referee();

        dealer.deal();
        dealer.hit();
        players.deal();
        players.get().forEach(Player::hit);
        referee.calculatePlayersResults(dealer, players);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(Map.of(name, expectedPlayerResult));
    }

    @Test
    @DisplayName("심판은 딜러의 합과 플레이어의 합이 같으면 무승부로 본다.")
    void playerDrawWhenSameBlackjack() {
        // given
        String name = "lemone";
        List<Card> dealerCards = List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, THREE));
        List<Card> playerCards = List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, THREE));
        GameResult expectedPlayerResult = GameResult.DRAW;

        // when
        Dealer dealer = new Dealer(new Deck(cards -> dealerCards));
        Players players = Players.of(List.of(name), new Deck(cards -> playerCards));
        Referee referee = new Referee();

        dealer.deal();
        dealer.hit();
        players.deal();
        players.get().forEach(Player::hit);
        referee.calculatePlayersResults(dealer, players);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(Map.of(name, expectedPlayerResult));
    }

    @Test
    @DisplayName("심판은 플레이어의 첫 두개의 카드의 합이 21이면 플레이어를 승자로 여긴다.")
    void playerDealCardsBlackjack() {
        // given
        String name = "lemone";
        List<Card> dealerCards = List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, FIVE));
        List<Card> playerCards = List.of(new Card(CLUB, ACE), new Card(CLUB, JACK));
        GameResult expectedPlayerResult = GameResult.WIN;

        // when
        Dealer dealer = new Dealer(new Deck(cards -> dealerCards));
        Players players = Players.of(List.of(name), new Deck(cards -> playerCards));
        Referee referee = new Referee();

        dealer.deal();
        dealer.hit();
        players.deal();
        referee.calculatePlayersResults(dealer, players);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(Map.of(name, expectedPlayerResult));
    }

    @Test
    @DisplayName("심판은 플레이어의 점수가 딜러보다 낮을 경우 플레이어를 패배자로 여긴다.")
    void playerLose() {
        // given
        String name = "lemone";
        List<Card> dealerCards = List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, FIVE));
        List<Card> playerCards = List.of(new Card(CLUB, TWO), new Card(CLUB, JACK));
        GameResult expectedPlayerResult = GameResult.LOSE;

        // when
        Dealer dealer = new Dealer(new Deck(cards -> dealerCards));
        Players players = Players.of(List.of(name), new Deck(cards -> playerCards));
        Referee referee = new Referee();

        dealer.deal();
        dealer.hit();
        players.deal();
        referee.calculatePlayersResults(dealer, players);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(Map.of(name, expectedPlayerResult));
    }

    @Test
    @DisplayName("심판은 플레이어와 딜러가 모두 버스트이면, 플레이어를 패배자로 여긴다.")
    void playerDealerAllBustPlayerLose() {
        // given
        String name = "lemone";
        List<Card> dealerCards = List.of(new Card(CLUB, KING), new Card(CLUB, JACK), new Card(CLUB, THREE));
        List<Card> playerCards = List.of(new Card(CLUB, KING), new Card(CLUB, JACK), new Card(CLUB, THREE));
        GameResult expectedPlayerResult = GameResult.LOSE;

        // when
        Dealer dealer = new Dealer(new Deck(cards -> dealerCards));
        Players players = Players.of(List.of(name), new Deck(cards -> playerCards));
        Referee referee = new Referee();

        dealer.deal();
        dealer.hit();
        players.deal();
        players.get().forEach(Player::hit);
        referee.calculatePlayersResults(dealer, players);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(Map.of(name, expectedPlayerResult));
    }

    @Test
    @DisplayName("심판은 딜러가 버스트이고 플레이어는 아닐 경우, 플레이어를 승리자로 여긴다.")
    void dealerBustPlayerNonBustWin() {
        // given
        String name = "lemone";
        List<Card> dealerCards = List.of(new Card(CLUB, KING), new Card(CLUB, JACK), new Card(CLUB, THREE));
        List<Card> playerCards = List.of(new Card(CLUB, KING), new Card(CLUB, JACK));
        GameResult expectedPlayerResult = GameResult.WIN;

        // when
        Dealer dealer = new Dealer(new Deck(cards -> dealerCards));
        Players players = Players.of(List.of(name), new Deck(cards -> playerCards));
        Referee referee = new Referee();

        dealer.deal();
        dealer.hit();
        players.deal();
        referee.calculatePlayersResults(dealer, players);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(Map.of(name, expectedPlayerResult));
    }
}
