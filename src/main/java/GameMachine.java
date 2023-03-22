import card.Hand;
import card.RandomShuffleMachine;
import gameState.BettingAmount;
import gameState.Playing;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import playerState.Hittable;
import playerState.dealerState.UnderSixteen;
import user.Dealer;
import user.Player;
import user.PlayerAction;
import user.PlayerName;

public class GameMachine {

    private BlackJackGame blackJackGame;

    public void ready() {
        List<Player> players = generatePlayers();
        blackJackGame = new BlackJackGame(players, generateDealer());
        OutputView.printReady(blackJackGame.getPlayers().stream().map(Player::getName).collect(Collectors.toList()));
        blackJackGame.initializeHand();
        OutputView.printDealerReadyStatus(blackJackGame.getDealer().getCards());
        for (Player player : blackJackGame.getPlayers()) {
            OutputView.printPlayerReadyStatus(player.getName(), player.getCards());
        }
    }

    private Dealer generateDealer() {
        return new Dealer(
            UnderSixteen.of(new Playing(new BettingAmount(0)), new Hand(null))
            , new RandomShuffleMachine());
    }

    private List<Player> generatePlayers() {
        try {
            String participantNamesInput = InputView.getParticipantNames();
            String[] participantNames = participantNamesInput.split(",", -1);
            List<PlayerName> playerNames = Arrays.stream(participantNames).map(PlayerName::new)
                .collect(Collectors.toList());
            return playerNames.stream().map(this::generatePlayer).collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            InputView.printErrorMessage(exception);
            return generatePlayers();
        }
    }

    private Player generatePlayer(PlayerName name) {
        return new Player(name, Hittable.of(
            new Playing(generateBettingAmount(name.getName())),
            new Hand(null))
        );
    }

    private BettingAmount generateBettingAmount(String name) {
        try {
            String bettingAmountInput = InputView.getBettingAmount(name);
            return new BettingAmount(Integer.parseInt(bettingAmountInput));
        } catch (NumberFormatException exception) {
            InputView.printErrorMessage(new IllegalArgumentException("배팅 금액은 숫자여야 합니다."));
            return generateBettingAmount(name);
        }
    }

    public void play() {
        while (blackJackGame.isNotFinish()) {
            Player currentPlayer = blackJackGame.getCurrentPlayer();
            blackJackGame.playerTurn(generatePlayerAction(currentPlayer.getName()));
            OutputView.printPlayerReadyStatus(currentPlayer.getName(), currentPlayer.getCards());
        }
        Dealer dealer = blackJackGame.getDealer();
        while (dealer.isUnderSixteen()) {
            OutputView.printDealerReceivedCard();
            blackJackGame.dealerTurn();
        }
    }

    private PlayerAction generatePlayerAction(String name) {
        try {
            return PlayerAction.getActionByInput(InputView.inputNeedMoreCard(name));
        } catch (IllegalArgumentException exception) {
            InputView.printErrorMessage(exception);
            return generatePlayerAction(name);
        }
    }

    public void printResult() {
        Dealer dealer = blackJackGame.getDealer();
        List<Player> players = blackJackGame.getPlayers();
        OutputView.printDealerNameAndHandAndPoint(dealer.getCards(), dealer.getPoint());
        for (Player player : players) {
            OutputView.printPlayerNameAndHandAndPoint(player.getName(), player.getCards(), player.getPoint());
        }
        int totalBetAmount = players.stream().mapToInt(Player::getBetAmount).sum();
        List<String> playerNames = players.stream().map(Player::getName).collect(Collectors.toList());
        List<Integer> playerFinalBenefits = players.stream()
            .map((player) -> player.getFinalBenefit(dealer.getDealerStatus()) - player.getBetAmount())
            .collect(Collectors.toList());
        OutputView.printFinalBenefits(totalBetAmount, playerNames, playerFinalBenefits);
    }
}
