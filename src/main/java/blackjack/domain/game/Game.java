package blackjack.domain.game;

import blackjack.domain.gameresult.*;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class Game {

    private final GameParticipants gameParticipants;
    private final GameBattings gameBattings;

    public Game(GameParticipants gameParticipants, GameBattings gameBattings) {
        this.gameParticipants = gameParticipants;
        this.gameBattings = gameBattings;
    }

    public static Game of(GameParticipants gameParticipants, GameBattings gameBattings, Deck deck) {
        gameParticipants.handOutInitialCards(deck);
        return new Game(gameParticipants, gameBattings);
    }

    public Map<Player, Profit> makeGameResult() {
        Dealer dealer = gameParticipants.getDealer();
        Players players = gameParticipants.getPlayers();

        Map<Player, Profit> gameResult = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            gameResult.put(player, calculateProfit(dealer, player));
        }
        return gameResult;
    }

    private Profit calculateProfit(Dealer dealer, Player player) {
        Result result = ResultJudge.judge(player, dealer);
        Batting playerBat = gameBattings.findPlayerBatting(player);
        double profit = ProfitRate.calculateProfit(result, playerBat.getBat());
        return Profit.from(profit);
    }

    public Dealer getDealer() {
        return gameParticipants.getDealer();
    }

    public Players getPlayers() {
        return gameParticipants.getPlayers();
    }
}
