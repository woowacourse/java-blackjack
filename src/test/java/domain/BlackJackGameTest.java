package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Symbol;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    BlackJackGame blackJackGame;
    Dealer dealer;

    @BeforeEach
    void init() {
        Card card1 = new Card(Symbol.CLOVER, Rank.ACE);
        Card card2 = new Card(Symbol.DIAMOND, Rank.EIGHT);
        Card card3 = new Card(Symbol.SPADE, Rank.KING);
        Card card4 = new Card(Symbol.CLOVER, Rank.SEVEN);
        Card card5 = new Card(Symbol.DIAMOND, Rank.THREE);
        Card card6 = new Card(Symbol.HEART, Rank.FIVE);
        Card card7 = new Card(Symbol.CLOVER, Rank.NINE);
        Card card8 = new Card(Symbol.DIAMOND, Rank.THREE);

        List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8);
        Deck deck = new Deck(cards);
        BetAmounts betAmounts = new BetAmounts();
        blackJackGame = new BlackJackGame(deck, betAmounts);
        dealer = new Dealer();
    }

    @DisplayName("딜러와 플레이어에게 카드를 2장씩 준다.")
    @Test
    void prepareCardTest() {
        // given
        Name name1 = new Name("lini");
        Name name2 = new Name("kaki");
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Players players = new Players(List.of(player1, player2));

        // when
        blackJackGame.prepareCards(dealer, players);

        // then
        assertAll(
                () -> assertThat(dealer.getCardsInHand()).hasSize(2),
                () -> assertThat(players.getPlayers().get(0).getCardsInHand()).hasSize(2),
                () -> assertThat(players.getPlayers().get(1).getCardsInHand()).hasSize(2)
        );
    }

    @DisplayName("딜러의 점수가 17 미만이므로 카드 주기를 성공한다.")
    @Test
    void giveDealerCardSuccessTest() {
        // given
        blackJackGame.giveCard(dealer); // 3 다이아몬드
        blackJackGame.giveCard(dealer); // 9 클로버

        // when
        blackJackGame.giveCard(dealer); // 5 하트

        int expectedDealerSize = 3;

        // then
        assertAll(
                () -> assertThat(dealer.getCardsInHand()).hasSize(expectedDealerSize)
        );
    }

    @DisplayName("플레이어의 버스트가 아니므로, 카드 추가에 성공한다.")
    @Test
    void givePlayerCardSuccessTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);

        blackJackGame.giveCard(player); // 3 다이아몬드
        blackJackGame.giveCard(player); // 9 클로버
        blackJackGame.giveCard(player); // 5 하트

        // when
        blackJackGame.giveCard(player); // 3 다이아몬드

        int expectedPlayerSize = 4;

        // then
        assertAll(
                () -> assertThat(player.getCardsInHand()).hasSize(expectedPlayerSize)
        );
    }


    @DisplayName("딜러의 점수가 17 이상이므로 카드 주기를 실패한다.")
    @Test
    void giveDealerCardFailureTest() {
        // given
        blackJackGame.giveCard(dealer); // 3 다이아몬드
        blackJackGame.giveCard(dealer); // 9 클로버
        blackJackGame.giveCard(dealer); // 5 하트

        // when
        int expectedPlayerSize = 3;

        // then
        assertThat(dealer.getCardsInHand()).hasSize(expectedPlayerSize);
    }

    @DisplayName("플레이어의 버스트이면 카드 주기를 실패한다.")
    @Test
    void givePlayerCardFailureTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);

        blackJackGame.giveCard(player); // 3 다이아몬드
        blackJackGame.giveCard(player); // 9 클로버
        blackJackGame.giveCard(player); // 5 하트
        blackJackGame.giveCard(player); // 3 다이아몬드
        blackJackGame.giveCard(player); // 7 클로버

        // when
        int expectedPlayerSize = 5;

        // then
        assertThat(player.getCardsInHand()).hasSize(expectedPlayerSize);
    }

    @DisplayName("플레이어와 딜러의 배팅 결과를 반환한다.")
    @Nested
    class BetProfitTest {
        Player pobi, jason;
        Players players;

        @BeforeEach
        void init() {
            Name name1 = new Name("pobi");
            Name name2 = new Name("jason");
            pobi = new Player(name1);
            jason = new Player(name2);

            players = new Players(List.of(pobi, jason));
        }

        @DisplayName("플레이어의 배팅 결과를 생성한다.")
        @Test
        void createPlayersResultTest() {
            // given
            blackJackGame.bet(pobi, 10000);
            blackJackGame.bet(jason, 20000);

            blackJackGame.giveCard(pobi);
            blackJackGame.giveCard(jason);
            blackJackGame.giveCard(dealer);
            // 포비 : 3 다이아몬드
            // 제이슨 : 9 클로버
            // 딜러 : 5 하트

            // when
            Map<Player, Integer> playersResult = blackJackGame.createPlayersResult(dealer, players);

            // then
            assertAll(
                    () -> assertThat(playersResult.get(pobi)).isEqualTo(-10000),
                    () -> assertThat(playersResult.get(jason)).isEqualTo(20000)
            );
        }

        @DisplayName("딜러 배팅 수익을 계산한다.")
        @Test
        void calculateDealerProfitTest() {
            // given
            blackJackGame.bet(pobi, 10000);
            blackJackGame.bet(jason, 20000);

            blackJackGame.giveCard(pobi);
            blackJackGame.giveCard(jason);
            blackJackGame.giveCard(dealer);
            // 포비 : 3 다이아몬드
            // 제이슨 : 9 클로버
            // 딜러 : 5 하트

            // when
            int dealerProfit = blackJackGame.calculateDealerProfit(dealer, players);

            // then
            assertThat(dealerProfit).isEqualTo(-10000);
        }
    }
}
