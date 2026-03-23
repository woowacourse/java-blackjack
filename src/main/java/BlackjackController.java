import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.DealerDto;
import dto.PlayerDto;
import dto.TotalProfitResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;
    private final BlackjackGame blackjackGame;

    public BlackjackController(InputView inputView, ResultView resultView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        List<String> names = readPlayers();
        List<Integer> betAmounts = readBetAmount(names);

        blackjackGame.registPlayers(names, betAmounts);
        blackjackGame.giveHand();

        List<Player> players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();

        DealerDto dealerDto = DealerDto.from(dealer);
        List<PlayerDto> playerDtos = toPlayerDtos(players);

        resultView.printParticipantsCards(playerDtos, dealerDto);

        playerHitStand(players);
        resultView.printDealerHitStand(blackjackGame.dealerHitsStand());

        List<PlayerDto> finalPlayerDtos = toPlayerDtos(players);
        DealerDto finalDealerDto = DealerDto.from(dealer);

        resultView.printCardsWithResult(finalPlayerDtos, finalDealerDto);

        Map<String, Integer> playerProfits = blackjackGame.calculatePlayerProfits();
        Map<String, Integer> dealerProfit = blackjackGame.calculateDealerProit(playerProfits);

        TotalProfitResponse response = new TotalProfitResponse(playerProfits, dealerProfit);

        resultView.printFinalProfit(response);
    }

    private List<String> readPlayers() {
        return inputView.readPlayerNames();
    }

    private List<Integer> readBetAmount(List<String> players) {
        List<Integer> betAmounts = new ArrayList<>();

        for (String player : players) {
            int betAmount = inputView.readBetAmount(player);
            betAmounts.add(betAmount);
        }

        return betAmounts;
    }

    private void playerHitStand(List<Player> players) {
        for (Player player : players) {
            hitStand(player);
        }
    }

    private void hitStand(Player player) {
        if (player.isBust()) {
            resultView.printBustMessage(player.getName());
            return;
        }

        while (true) {
            String choice = inputView.readHitStand(player.getName());

            if (choice.equals("n")) {
                break;
            }

            blackjackGame.giveCard(player);
            resultView.printCards(PlayerDto.from(player));

            if (player.isBust()) {
                resultView.printBustMessage(player.getName());
                return;
            }
        }
    }

    private List<PlayerDto> toPlayerDtos(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .collect(Collectors.toList());
    }
}
