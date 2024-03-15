package blackjack.domain;

import static blackjack.domain.deck.Kind.SPADE;
import static blackjack.domain.deck.Value.ACE;
import static blackjack.domain.deck.Value.EIGHT;
import static blackjack.domain.deck.Value.NINE;
import static blackjack.domain.deck.Value.TEN;
import static blackjack.domain.deck.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("플레이어 승 : 플레이어 카드패 > 딜러 카드패")
    @Test
    void should_returnWin_When_PlayerHands_Higher_Than_DealerHands() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, NINE)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, ACE),
                new Card(SPADE, EIGHT)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 승 : 플레이어 - NonBurst, 딜러 - Burst")
    @Test
    void should_returnWin_When_PlayerNonBurst_DealerBurst() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, NINE)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, TEN),
                new Card(SPADE, TEN),
                new Card(SPADE, TEN)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 블랙 잭 : 플레이어 - BLACK_JACK, 딜러 - NON_BLACK_JACK")
    @Test
    void should_returnBlackJack_When_PlayerBlackJack_DealerNonBlackJACK() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, TEN)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, TEN),
                new Card(SPADE, NINE)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.BLACK_JACK);
    }

    @DisplayName("플레이어 패 : 플레이어 카드패 < 딜러 카드패")
    @Test
    void should_returnLose_When_PlayerHands_Lower_Than_DealerHands() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, TWO)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, ACE),
                new Card(SPADE, TEN)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어 패 : 플레이어 - Burst, 딜러 - NonBurst")
    @Test
    void should_returnLose_When_PlayerBurst_DealerNonBurst() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, TEN),
                        new Card(SPADE, TEN),
                        new Card(SPADE, TEN)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, TEN),
                new Card(SPADE, TEN)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("무승부 : 플레이어 카드패 == 딜러 카드패")
    @Test
    void should_returnDraw_When_PlayerHands_Equal_DealerHands() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, ACE)
        ));

        System.out.println(dealer.getHands().findHandsScore());
        System.out.println(player.getHands().findHandsScore());
        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("무승부 : 플레이어- Burst, 딜러- Burst")
    @Test
    void should_returnDraw_When_Both_Burst() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, TEN),
                        new Card(SPADE, TEN),
                        new Card(SPADE, TEN)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, TEN),
                new Card(SPADE, TEN),
                new Card(SPADE, TEN)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("무승부 : 플레이어- BLACK_JACK, 딜러- BLACK_JACK")
    @Test
    void should_returnDraw_When_Both_BlackJack() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, TEN)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, ACE),
                new Card(SPADE, TEN)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("딜러의 수익을 계산합니다")
    @Test
    void should_returnDealerProfit() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(new Card(SPADE, ACE),
                        new Card(SPADE, NINE)));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                new Card(SPADE, ACE),
                new Card(SPADE, EIGHT)));
        BetManager betManager = new BetManager(Map.of(player, new BetMoney(1000)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.calculateDealerProfit(betManager)).isEqualTo(-1_000);
    }
}
