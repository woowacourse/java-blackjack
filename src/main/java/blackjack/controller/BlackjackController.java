package blackjack.controller;

import blackjack.domain.gamer.BlackjackGamer;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackjackRevenueCalculator;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.GamerHandDto;
import blackjack.dto.GamerRevenueDto;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
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

        setUpInitialHands(dealer, players);
        distributeCardToPlayers(dealer, players);
        distributeCardToDealer(dealer, deck);
        printAllGamerScores(dealer, players);
        printAllGamerRevenues(dealer, players);
    }

    private Players getPlayers() {
        List<String> playerNames = inputView.receivePlayerNames();
        outputView.printBlankLine();

        return new Players(createPlayerBetAmounts(playerNames));
    }

    private Map<Player, Money> createPlayerBetAmounts(List<String> playerNames) {
        Map<Player, Money> playerBetAmountMap = new LinkedHashMap<>();

        for (String playerName : playerNames) {
            Money betAmount = new Money(inputView.receiveBetAmount(playerName));
            playerBetAmountMap.put(new Player(playerName), betAmount);
        }
        outputView.printBlankLine();

        return playerBetAmountMap;
    }

    private void setUpInitialHands(Dealer dealer, Players players) {
        dealer.setUpInitialCards(players);
        printInitialHands(players, dealer);
    }

    private void printInitialHands(Players players, Dealer dealer) {
        DealerInitialHandDto dealerInitialHandDto = DealerInitialHandDto.fromDealer(dealer);
        List<GamerHandDto> playerInitialHandDto = players.getPlayers().stream()
                .map(GamerHandDto::fromGamer)
                .toList();

        outputView.printInitialHands(dealerInitialHandDto, playerInitialHandDto);
        outputView.printBlankLine();
    }

    private void distributeCardToPlayers(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            distributeCardToPlayer(dealer, player);
        }
    }

    private void distributeCardToPlayer(Dealer dealer, Player player) {
        while (canDistribute(player)) {
            dealer.giveCardToPlayer(player);
            outputView.printGamerNameAndHand(GamerHandDto.fromGamer(player));
        }
        outputView.printBlankLine();
    }

    private boolean canDistribute(Player player) {
        return player.canReceiveCard() && isPlayerCommandHit(player);
    }

    private boolean isPlayerCommandHit(Player player) {
        Command command = inputView.receiveCommand(player.getName());
        return command.isHit();
    }

    private void distributeCardToDealer(Dealer dealer) {
        while (dealer.canReceiveCard()) {
            dealer.addCard();
            outputView.printDealerMessage(dealer.getName());
        }
        outputView.printBlankLine();
    }

    private void printAllGamerScores(Dealer dealer, Players players) {
        outputView.printScore(GamerHandDto.fromGamer(dealer), dealer.getScore());
        printPlayersScores(players);
        outputView.printBlankLine();
    }

    private void printPlayersScores(Players players) {
        for (Player player : players.getPlayers()) {
            outputView.printScore(GamerHandDto.fromGamer(player), player.getScore());
        }
    }

    private void printAllGamerRevenues(Dealer dealer, Players players) {
        Map<BlackjackGamer, Money> gamerRevenueMap = getGamerRevenueMap(dealer, players);
        outputView.printRevenues(GamerRevenueDto.fromOrderedMap(gamerRevenueMap));
    }

    private Map<BlackjackGamer, Money> getGamerRevenueMap(Dealer dealer, Players players) {
        BlackjackRevenueCalculator revenueCalculator = BlackjackRevenueCalculator.fromDealer(dealer);
        Map<BlackjackGamer, Money> gamerRevenueMap = new LinkedHashMap<>();

        gamerRevenueMap.put(dealer, revenueCalculator.calculateDealerRevenue(players));
        players.getPlayers().forEach(player -> {
            Money betAmount = players.getBetAmount(player);
            Money revenue = revenueCalculator.calculatePlayerRevenue(player, betAmount);
            gamerRevenueMap.put(player, revenue);
        });

        return gamerRevenueMap;
    }
}
