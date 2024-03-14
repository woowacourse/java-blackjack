package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.GameAccount;
import blackjack.domain.game.Money;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameResult;
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
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Players players = getPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        deck.shuffle();

        GameAccount gameAccount = new GameAccount();
        betMoney(gameAccount, players);
        setUpInitialHands(players, deck, dealer);
        distributeCardToPlayers(players, deck);
        distributeCardToDealer(dealer, deck);
        printAllGamerScores(dealer, players);
        printResult(players, dealer, gameAccount);
    }

    private Players getPlayers() {
        List<String> playerNames = inputView.receivePlayerNames();

        return new Players(playerNames);
    }

    private void betMoney(GameAccount gameAccount, Players players) {
        for (Player player : players.getPlayers()) {
            int betMoney = inputView.receivePlayerMoney(player.getName().value());
            gameAccount.betMoney(player, new Money(betMoney));
        }
    }

    private void setUpInitialHands(Players players, Deck deck, Dealer dealer) {
        players.initAllPlayersCard(deck);
        dealer.initCard(deck);
        printInitialHands(players, dealer);
    }

    private void printInitialHands(Players players, Dealer dealer) {
        DealerInitialHandDto dealerInitialHandDto = DealerInitialHandDto.fromDealer(dealer);
        PlayersHandDto playersInitialHandDto = PlayersHandDto.fromPlayers(players);

        outputView.printInitialHands(dealerInitialHandDto, playersInitialHandDto);
    }

    private void distributeCardToPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            distributeCardToPlayer(deck, player);
        }
    }

    private void distributeCardToPlayer(Deck deck, Player player) {
        while (canDistribute(player)) {
            player.addCard(deck.draw());
            outputView.printPlayerHand(PlayerHandDto.fromPlayer(player));
        }
    }

    private boolean canDistribute(Player player) {
        return player.canReceiveCard() && Command.isHit(getCommand(player));
    }

    private Command getCommand(Player player) {
        return inputView.receiveCommand(player.getName().value());
    }

    private void distributeCardToDealer(Dealer dealer, Deck deck) {
        while (dealer.canReceiveCard()) {
            dealer.addCard(deck.draw());
            outputView.printDealerMessage();
        }
    }

    private void printAllGamerScores(Dealer dealer, Players players) {
        printDealerScore(dealer);
        printPlayersScores(players);
    }

    private void printDealerScore(Dealer dealer) {
        outputView.printEmptyLine();
        outputView.printDealerHandScore(HandDto.fromHand(dealer.getHand()));
    }

    private void printPlayersScores(Players players) {
        outputView.printPlayersHandScore(PlayersHandDto.fromPlayers(players));
    }

    private void printResult(Players players, Dealer dealer, GameAccount gameAccount) {
        Map<Player, GameResult> playerGameResults = players.collectPlayerGameResults(dealer.getHandValue());
        gameAccount.applyGameResults(playerGameResults);
        Money dealerIncome = gameAccount.calculateDealerIncome();

        PlayerGameResultsDto playerGameResultsDto = PlayerGameResultsDto.fromPlayerBetResults(gameAccount.getStore());
        outputView.printResult(dealerIncome.value(), playerGameResultsDto);
    }
}
