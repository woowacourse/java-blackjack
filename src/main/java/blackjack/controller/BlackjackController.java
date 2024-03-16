package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.PlayersBetAmount;
import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.card.Hands;
import blackjack.domain.user.PlayerNames;
import blackjack.domain.user.UserName;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    
    public void run() {
        PlayerNames playerNames = InputView.readPlayerNames();

        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        PlayersBetAmount playersBetAmount = saveBetAmounts(playerNames);

        playGame(blackjackGame);
        finishGame(blackjackGame, playersBetAmount);
    }

    private PlayersBetAmount saveBetAmounts(PlayerNames playerNames) {
        Map<UserName, BetAmount> playerBetAmounts = new LinkedHashMap<>();

        playerNames.getNames().forEach(name -> playerBetAmounts.put(name, InputView.readBetAmount(name)));

        return new PlayersBetAmount(playerBetAmounts);
    }

    private void playGame(BlackjackGame blackjackGame) {
        blackjackGame.giveStartCards();
        OutputView.printStartCards(blackjackGame.getPlayersHands(), blackjackGame.getDealerOpenedHands());

        List<UserName> playersName = blackjackGame.getPlayersName();
        for (UserName name : playersName) {
            runPlayerTurn(blackjackGame, name);

        }
        int count = blackjackGame.runDealerTurn();
        OutputView.printDealerMoreCard(count);
    }

    private void runPlayerTurn(BlackjackGame blackjackGame, UserName name) {
        while (blackjackGame.isPlayerCanHit(name) && InputView.readHitDesire(name)) {
            blackjackGame.playerDraw(name);
            OutputView.printPlayerCard(name, blackjackGame.playerHands(name));
        }
    }

    private void finishGame(BlackjackGame blackjackGame, PlayersBetAmount playersBetAmount) {
        FinalHandsScoreDto finalHandsScore = getFinalHandsScore(blackjackGame);
        BetRevenueResultDto betRevenueResult = getBetRevenueResult(blackjackGame, playersBetAmount);

        OutputView.printFinalResult(finalHandsScore, betRevenueResult);
    }

    private FinalHandsScoreDto getFinalHandsScore(BlackjackGame blackjackGame) {
        Map<UserName, Hands> playersHands = blackjackGame.getPlayersHands();
        Hands dealerHands = blackjackGame.getDealerHands();

        return FinalHandsScoreDto.of(playersHands, dealerHands);
    }

    private BetRevenueResultDto getBetRevenueResult(BlackjackGame blackjackGame, PlayersBetAmount playersBetAmount) {
        Map<UserName, BetLeverage> playersBetLeverage = blackjackGame.getPlayersBetLeverage();
        Map<UserName, BetRevenue> playersBetRevenue = playersBetAmount.calculateBetRevenue(playersBetLeverage);

        BetRevenue dealerRevenue = playersBetRevenue.values().stream()
                .reduce(BetRevenue::plus)
                .orElse(new BetRevenue(0F))
                .negate();

        return BetRevenueResultDto.of(playersBetRevenue, dealerRevenue);
    }
}
