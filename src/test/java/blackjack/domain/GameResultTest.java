package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("플레이어 승 : 플레이어 카드패 > 딜러 카드패")
    @Test
    void should_returnWin_When_PlayerHands_Higher_Than_DealerHands() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(0),
                        Card.valueOf(8)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(0),
                Card.valueOf(7)));
        
        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 승 : 플레이어 - NonBurst, 딜러 - Burst")
    @Test
    void should_returnWin_When_PlayerNonBurst_DealerBurst() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(0),
                        Card.valueOf(8)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(9),
                Card.valueOf(9),
                Card.valueOf(9)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 블랙 잭 : 플레이어 - BLACK_JACK, 딜러 - NON_BLACK_JACK")
    @Test
    void should_returnBlackJack_When_PlayerBlackJack_DealerNonBlackJACK() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(0),
                        Card.valueOf(9)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(9),
                Card.valueOf(8)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.BLACK_JACK);
    }

    @DisplayName("플레이어 패 : 플레이어 카드패 < 딜러 카드패")
    @Test
    void should_returnLose_When_PlayerHands_Lower_Than_DealerHands() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(0),
                        Card.valueOf(1)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(0),
                Card.valueOf(9)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어 패 : 플레이어 - Burst, 딜러 - NonBurst")
    @Test
    void should_returnLose_When_PlayerBurst_DealerNonBurst() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(9),
                        Card.valueOf(9),
                        Card.valueOf(9)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(9),
                Card.valueOf(9)));


        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("무승부 : 플레이어 카드패 == 딜러 카드패")
    @Test
    void should_returnDraw_When_PlayerHands_Equal_DealerHands() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(0)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(0)
        ));

        System.out.println(dealer.getHands().getHandsScore());
        System.out.println(player.getHands().getHandsScore());
        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("무승부 : 플레이어- Burst, 딜러- Burst")
    @Test
    void should_returnDraw_When_Both_Burst() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(9),
                        Card.valueOf(9),
                        Card.valueOf(9)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(9),
                Card.valueOf(9),
                Card.valueOf(9)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("무승부 : 플레이어- BLACK_JACK, 딜러- BLACK_JACK")
    @Test
    void should_returnDraw_When_Both_BlackJack() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(0),
                        Card.valueOf(9)),
                new BetMoney(1));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(0),
                Card.valueOf(9)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("딜러의 수익을 계산합니다")
    @Test
    void should_returnDealerProfit() {
        Player player = Player.createPlayer(new Name("pobi"),
                List.of(Card.valueOf(0),
                        Card.valueOf(8)),
                new BetMoney(1_000));
        Players players = new Players(List.of(player));
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(0),
                Card.valueOf(7)));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getDealerProfit()).isEqualTo(-1_000);
    }
}
