package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.EIGHT;
import static blackjack.domain.card.CardScore.FIVE;
import static blackjack.domain.card.CardScore.JACK;
import static blackjack.domain.card.CardScore.KING;
import static blackjack.domain.card.CardScore.NINE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardScore.SEVEN;
import static blackjack.domain.card.CardScore.THREE;
import static blackjack.domain.card.CardScore.TWO;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.SPADE;
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
        List<Card> playerCards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(QUEEN, CLUB)));
        List<Card> DealerCards = new ArrayList<>(List.of(new Card(EIGHT, SPADE), new Card(QUEEN, CLUB)));

        Deck playerCardPicker = new Deck(playerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return playerCards;
            }
        };
        Deck dealerCardPicker = new Deck(DealerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return DealerCards;
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
        List<Card> playerCards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(QUEEN, CLUB), new Card(THREE, CLUB)));
        List<Card> DealerCards = new ArrayList<>(List.of(new Card(EIGHT, SPADE), new Card(QUEEN, CLUB), new Card(TWO, SPADE)));

        Deck playerCardPicker = new Deck(playerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return playerCards;
            }
        };
        Deck dealerCardPicker = new Deck(DealerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return DealerCards;
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
        List<Card> playerCards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(THREE, CLUB)));
        List<Card> DealerCards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(THREE, SPADE)));

        Deck playerCardPicker = new Deck(playerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return playerCards;
            }
        };
        Deck dealerCardPicker = new Deck(DealerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return DealerCards;
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
        List<Card> playerCards = new ArrayList<>(List.of(new Card(ACE, CLUB), new Card(JACK, CLUB)));
        List<Card> DealerCards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(FIVE, CLUB)));

        Deck playerCardPicker = new Deck(playerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return playerCards;
            }
        };
        Deck dealerCardPicker = new Deck(DealerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return DealerCards;
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
        List<Card> playerCards = new ArrayList<>(List.of(new Card(TWO, CLUB), new Card(JACK, CLUB)));
        List<Card> DealerCards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(FIVE, CLUB)));

        Deck playerCardPicker = new Deck(playerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return playerCards;
            }
        };
        Deck dealerCardPicker = new Deck(DealerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return DealerCards;
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
        List<Card> playerCards = new ArrayList<>(List.of(new Card(KING, CLUB), new Card(JACK, CLUB), new Card(THREE, CLUB)));
        List<Card> DealerCards = new ArrayList<>(List.of(new Card(KING, CLUB), new Card(JACK, CLUB), new Card(THREE, CLUB)));

        Deck playerCardPicker = new Deck(playerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return playerCards;
            }
        };
        Deck dealerCardPicker = new Deck(DealerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return DealerCards;
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
        List<Card> playerCards = new ArrayList<>(List.of(new Card(KING, CLUB), new Card(JACK, CLUB)));
        List<Card> DealerCards = new ArrayList<>(List.of(new Card(KING, CLUB), new Card(JACK, CLUB), new Card(THREE, CLUB)));

        Deck playerCardPicker = new Deck(playerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return playerCards;
            }
        };
        Deck dealerCardPicker = new Deck(DealerCards) {
            @Override
            public List<Card> pickCards(int count) {
                return DealerCards;
            }
        };

        players.forEach(player -> player.draw(playerCardPicker.pickCards(2)));
        dealer.draw(dealerCardPicker.pickCards(2));
        referee.calculatePlayersResults(players, dealer);
        expectedPlayersResult.put("lemone", PlayerGameResult.WIN);

        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }
}
