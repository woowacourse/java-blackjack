package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("심판")
public class RefereeTest {
    @Test
    @DisplayName("두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이기는지 테스트한다.")
    void playerWinTest() {
        List<String> name = List.of("lemone");
        Players players = Players.from(name);
        Dealer dealer = new Dealer(new Hand(List.of()));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();

        Deck playerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_QUEEN);
            }
        };
        Deck dealerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_EIGHT, Card.CLUB_QUEEN);
            }
        };

        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.WIN);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어는 자신의 숫자 합이 21을 초과할 경우 패배한다.")
    void playerLoseWhenBustTest() {
        List<String> name = List.of("lemone");
        Players players = Players.from(name);
        Dealer dealer = new Dealer(new Hand(List.of()));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();

        Deck playerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_QUEEN, Card.CLUB_THREE);
            }
        };
        Deck dealerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_EIGHT, Card.SPADE_TWO, Card.CLUB_QUEEN);
            }
        };

        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.LOSE);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("딜러의 합과 플레이어의 합이 같으면 무승부이다.")
    void playerDrawWhenSameBlackjackTest() {
        List<String> name = List.of("lemone");
        Players players = Players.from(name);
        Dealer dealer = new Dealer(new Hand(List.of()));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();

        Deck playerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_THREE);
            }
        };
        Deck dealerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_THREE);
            }
        };

        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.PUSH);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어의 첫 두개의 카드의 합이 21이면 승리한다.")
    void playerDealCardsBlackjackTest() {
        List<String> name = List.of("lemone");
        Players players = Players.from(name);
        Dealer dealer = new Dealer(new Hand(List.of()));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();

        Deck playerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.CLUB_ACE, Card.CLUB_JACK);
            }
        };
        Deck dealerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_FIVE);
            }
        };

        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.WIN);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 낮을 경우 패배한다.")
    void playerLoseTest() {
        List<String> name = List.of("lemone");
        Players players = Players.from(name);
        Dealer dealer = new Dealer(new Hand(List.of()));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();

        Deck playerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.CLUB_TWO, Card.CLUB_JACK);
            }
        };
        Deck dealerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_FIVE);
            }
        };
        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.LOSE);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 bust 이면, 플레이어가 패배한다.")
    void playerDealerAllBustPlayerLoseTest() {
        List<String> name = List.of("lemone");
        Players players = Players.from(name);
        Dealer dealer = new Dealer(new Hand(List.of()));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();

        Deck playerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };
        Deck dealerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };
        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.LOSE);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("딜러가 bust이고 플레이가 bust가 아닐 경우, 플레이어가 승리한다.")
    void dealerBustPlayerNonBustWinTest() {
        List<String> name = List.of("lemone");
        Players players = Players.from(name);
        Dealer dealer = new Dealer(new Hand(List.of()));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();

        Deck playerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK);
            }
        };
        Deck dealerCardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };
        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.WIN);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }
}
