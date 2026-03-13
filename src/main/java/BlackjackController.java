import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.DealerDto;
import dto.ParticipantDto;
import dto.PlayerDto;
import java.util.List;
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
        readAndRegistPlayers();
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
        resultView.printResultStatistics(finalPlayerDtos, finalDealerDto);
    }

    private List<PlayerDto> toPlayerDtos(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .collect(Collectors.toList());
    }

    private void readAndRegistPlayers() {
        List<String> names = inputView.readPlayerNames();
        blackjackGame.registPlayers(names);
    }

    private void playerHitStand(List<Player> players) {
        for (Player player : players) {
            hitStand(player);
        }
    }



    private void hitStand(Player player) {
        while (inputView.readHitStand(player.getName()).equals("y")) {
            blackjackGame.giveCard(player);
            resultView.printCards(PlayerDto.from(player));
        }
    }
}
