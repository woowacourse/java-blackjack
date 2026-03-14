package service;

import domain.card.Card;
import domain.card.NoShuffleStrategy;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import domain.player.Player;
import domain.vo.Cost;
import dto.ParticipantBetResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {
    private static final int BET_AMOUNT = 10000;

    GameService gameService;

    public GameServiceTest() {
        this.gameService = new GameService();
        gameService.createDeck(new NoShuffleStrategy());
    }

    @Test
    void 플레이어_등록_테스트(){
        List<String> playerNames = new ArrayList<>(List.of("pobi", "jason"));

        gameService.joinPlayers(playerNames);
        assertThat(gameService.getPlayerGroupSize()).isEqualTo(playerNames.size());
    }

    @Test
    void 플레이어_블랙잭_승리() {
        List<String> playerNames = new ArrayList<>(List.of("pobi"));

        gameService.joinPlayers(playerNames);
        Player player1 = gameService.getPlayers().getFirst();
        player1.addCard(new Card(TrumpSuit.HEART, TrumpNumber.ACE));
        player1.addCard(new Card(TrumpSuit.HEART, TrumpNumber.TEN));

        gameService.dealerHit(); // 카드 한 장 추가

        assertBetResult(player1.getName(), 15000, -15000);
    }

    @Test
    void 플레이어_일반_승리() {
        List<String> playerNames = new ArrayList<>(List.of("pobi"));

        gameService.joinPlayers(playerNames);
        Player player1 = gameService.getPlayers().getFirst();
        player1.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.TEN));
        player1.addCard(new Card(TrumpSuit.HEART, TrumpNumber.TEN));

        gameService.dealerHit(); // 카드 한 장 추가

        assertBetResult(player1.getName(), 10000, -10000);
    }

    @Test
    void 플레이어_무승부() {
        List<String> playerNames = new ArrayList<>(List.of("pobi"));

        gameService.joinPlayers(playerNames);
        Player player1 = gameService.getPlayers().getFirst();
        player1.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.ACE));

        gameService.dealerHit(); // 카드 한 장 추가

        assertBetResult(player1.getName(), 0, 0);
    }

    @Test
    void 플레이어_패배() {
        List<String> playerNames = new ArrayList<>(List.of("pobi"));

        gameService.joinPlayers(playerNames);
        Player player1 = gameService.getPlayers().getFirst();
        player1.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.TWO));

        gameService.dealerHit(); // 카드 한 장 추가

        assertBetResult(player1.getName(), -10000, 10000);
    }

    private void assertBetResult(String playerName, int expectedPlayerProfit, int expectedDealerProfit) {
        Map<String, Cost> betInfo = Map.of(playerName, new Cost(BET_AMOUNT));

        Map<String, Integer> results = gameService.bettingResult(betInfo).stream()
                .collect(Collectors.toMap(ParticipantBetResult::name, ParticipantBetResult::cost));

        String dealerName = gameService.getDealerResult().name();
        assertThat(results.get(playerName)).isEqualTo(expectedPlayerProfit);
        assertThat(results.get(dealerName)).isEqualTo(expectedDealerProfit);
    }
}
