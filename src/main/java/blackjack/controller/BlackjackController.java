package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameResult;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerHandDto;
import blackjack.dto.GamersHandDto;
import blackjack.dto.PlayerGameResultsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.object.Command;
import java.util.ArrayList;
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

        setUpInitialHands(players, deck, dealer);
        distributeCardToPlayers(players, deck);
        distributeCardToDealer(dealer, deck);
        printAllGamerScores(dealer, players);
        printResult(dealer, players);
    }

    private Players getPlayers() {
        List<String> playerNames = inputView.receivePlayerNames();

        return new Players(playerNames);
    }

    private void setUpInitialHands(Players players, Deck deck, Dealer dealer) {
        players.initAllPlayersCard(deck);
        dealer.initCard(deck);
        printInitialHands(players, dealer);
    }

    private void printInitialHands(Players players, Dealer dealer) {
        DealerInitialHandDto dealerInitialHandDto = DealerInitialHandDto.fromDealer(dealer);
        GamersHandDto playersInitialHandDto = GamersHandDto.fromPlayers(players);

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
            outputView.printPlayerHand(GamerHandDto.fromBlackjackGamer(player));
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
            outputView.printDealerMessage(dealer.getName().value());
        }
    }

    private void printAllGamerScores(Dealer dealer, Players players) {
        outputView.printEmptyLine();
        outputView.printScore(GamerHandDto.fromBlackjackGamer(dealer), dealer.getScore());
        printPlayersScores(players);
    }

    private void printPlayersScores(Players players) {
        for (Player player : players.getPlayers()) {
            outputView.printScore(GamerHandDto.fromBlackjackGamer(player), player.getScore());
        }
    }

    private void printResult(Dealer dealer, Players players) {
        Map<Name, GameResult> playerGameResults = players.collectPlayerGameResults(dealer.getScore());
        PlayerGameResultsDto playerGameResultsDto = PlayerGameResultsDto.fromPlayerGameResults(playerGameResults);
        List<GameResult> playerResults = new ArrayList<>(playerGameResultsDto.resultMap().values());
        DealerResultDto dealerResultDto = DealerResultDto.fromPlayerResults(playerResults);

        outputView.printResult(dealerResultDto, playerGameResultsDto);
    }
}
