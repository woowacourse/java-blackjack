package blackjack.controller;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.HandDto;
import blackjack.dto.PlayerGameResultsDto;
import blackjack.dto.PlayerHandDto;
import blackjack.dto.PlayersHandDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.object.Command;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public BlackjackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.blackjackGame = new BlackjackGame();
    }

    public void run() {
        Players players = getPlayers();
        Dealer dealer = new Dealer();
        blackjackGame.distributeInitialHand(players, dealer);
        blackjackGame.betPlayerMoney(receivePlayersBetMoney(players));
        printInitialHands(players, dealer);

        distributeCardToPlayers(players);
        blackjackGame.distributeCardToDealer(dealer);

        printDealerCanReceiveCardMessage(dealer);
        printAllGamerScores(dealer, players);
        printResult(players, dealer);
    }

    private Players getPlayers() {
        List<String> playerNames = inputView.receivePlayerNames();
        return new Players(playerNames);
    }

    private Map<Player, Money> receivePlayersBetMoney(Players players) {
        Map<Player, Money> playerBetMoney = new HashMap<>();
        for (Player player : players.getPlayers()) {
            int betMoney = inputView.receivePlayerMoney(player.getName().value());
            playerBetMoney.put(player, new Money(betMoney));
        }
        return playerBetMoney;
    }

    private void printInitialHands(Players players, Dealer dealer) {
        outputView.printInitialHands(DealerInitialHandDto.fromDealer(dealer), PlayersHandDto.fromPlayers(players));
    }

    private void distributeCardToPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            distributeCardToPlayer(player);
        }
    }

    private void distributeCardToPlayer(Player player) {
        while (player.canReceiveCard() && Command.isHit(getCommand(player))) {
            blackjackGame.addCardToPlayer(player);
            outputView.printPlayerHand(PlayerHandDto.fromPlayer(player));
        }
    }

    private Command getCommand(Player player) {
        return inputView.receiveCommand(player.getName().value());
    }

    private void printDealerCanReceiveCardMessage(Dealer dealer) {
        if (dealer.canReceiveCard()) {
            outputView.printDealerMessage();
        }
    }

    private void printAllGamerScores(Dealer dealer, Players players) {
        outputView.printDealerHandScore(HandDto.fromHand(dealer.getHand()));
        outputView.printPlayersHandScore(PlayersHandDto.fromPlayers(players));
    }

    private void printResult(Players players, Dealer dealer) {
        Money dealerIncome = blackjackGame.calculateDealerIncome(players, dealer);
        PlayerGameResultsDto playerGameResultsDto = PlayerGameResultsDto.fromPlayerBetResults(blackjackGame.getStore());
        outputView.printResult(dealerIncome.value(), playerGameResultsDto);
    }
}
