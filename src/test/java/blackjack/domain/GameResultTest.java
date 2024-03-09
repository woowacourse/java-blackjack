package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @DisplayName("플레이어 승 : 플레이어 카드패 > 딜러 카드패")
    @Test
    void should_returnWin_When_PlayerHands_Higher_Than_DealerHands(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(0));
        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.WIN)).isOne();
    }

    @DisplayName("플레이어 승 : 플레이어 - NonBurst, 딜러 - Burst" )
    @Test
    void should_returnWin_When_PlayerNonBurst_DealerBurst(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.WIN)).isOne();
    }

    @DisplayName("플레이어 패 : 플레이어 카드패 < 딜러 카드패")
    @Test
    void should_returnLose_When_PlayerHands_Lower_Than_DealerHands(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(1));

        dealer.addCard(Card.create(0));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.LOSE)).isOne();
    }
    @DisplayName("플레이어 패 : 플레이어 - Burst, 딜러 - NonBurst" )
    @Test
    void should_returnLose_When_PlayerBurst_DealerNonBurst(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.LOSE)).isOne();
    }

    @DisplayName("무승부 : 플레이어 카드패 == 딜러 카드패")
    @Test
    void should_returnDraw_When_PlayerHands_Equal_DealerHands(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));

        dealer.addCard(Card.create(0));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.DRAW)).isOne();
    }

    @DisplayName("무승부 : 플레이어- Burst, 딜러- Burst")
    @Test
    void should_returnDraw_When_Both_Burst(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.DRAW)).isOne();
    }
}
