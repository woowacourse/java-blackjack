package blackjack.controller;

import blackjack.domain.gamer.BlackjackGamer;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerBetAmounts;
import blackjack.domain.result.BlackjackEarningCalculator;
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
        PlayerBetAmounts playerBetAmounts = createPlayersWithBetAmount();
        List<Player> players = playerBetAmounts.getPlayers();
        Dealer dealer = new Dealer();

        setUpInitialHands(dealer, players);
        distributeCardToPlayers(dealer, players);
        distributeCardToDealer(dealer);
        printAllGamerScores(dealer, playerBetAmounts);
        printAllGamerRevenues(dealer, playerBetAmounts);
    }

    private PlayerBetAmounts createPlayersWithBetAmount() {
        List<String> playerNames = inputView.receivePlayerNames();
        outputView.printBlankLine();

        return new PlayerBetAmounts(createPlayerBetAmounts(playerNames));
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

    private void setUpInitialHands(Dealer dealer, List<Player> players) {
        dealer.setUpInitialCards(players);
        printInitialHands(players, dealer);
    }

    private void printInitialHands(List<Player> players, Dealer dealer) {
        DealerInitialHandDto dealerInitialHandDto = DealerInitialHandDto.fromDealer(dealer);
        List<GamerHandDto> playerInitialHandDto = players.stream()
                .map(GamerHandDto::fromGamer)
                .toList();

        outputView.printInitialHands(dealerInitialHandDto, playerInitialHandDto);
        outputView.printBlankLine();
    }

    private void distributeCardToPlayers(Dealer dealer, List<Player> players) {
        for (Player player : players) {
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

    private void printAllGamerScores(Dealer dealer, PlayerBetAmounts playerBetAmounts) {
        outputView.printScore(GamerHandDto.fromGamer(dealer), dealer.getScore());
        printPlayersScores(playerBetAmounts);
        outputView.printBlankLine();
    }

    private void printPlayersScores(PlayerBetAmounts playerBetAmounts) {
        for (Player player : playerBetAmounts.getPlayers()) {
            outputView.printScore(GamerHandDto.fromGamer(player), player.getScore());
        }
    }

    private void printAllGamerRevenues(Dealer dealer, PlayerBetAmounts playerBetAmounts) {
        Map<BlackjackGamer, Money> gamerRevenueMap = getGamerRevenueMap(dealer, playerBetAmounts);
        outputView.printRevenues(GamerRevenueDto.fromOrderedMap(gamerRevenueMap));
    }

    private Map<BlackjackGamer, Money> getGamerRevenueMap(Dealer dealer, PlayerBetAmounts playerBetAmounts) {
        BlackjackEarningCalculator earningCalculator = BlackjackEarningCalculator.fromDealer(dealer);
        Map<BlackjackGamer, Money> gamerRevenueMap = new LinkedHashMap<>();

        gamerRevenueMap.put(dealer, earningCalculator.calculateDealerEarning(playerBetAmounts));
        playerBetAmounts.getPlayers().forEach(player -> {
            Money betAmount = playerBetAmounts.getBetAmount(player);
            Money revenue = earningCalculator.calculatePlayerEarning(player, betAmount);
            gamerRevenueMap.put(player, revenue);
        });

        return gamerRevenueMap;
    }
}
