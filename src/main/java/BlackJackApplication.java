import domain.BlackJackGame;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.BettingResults;
import domain.result.Results;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int SINGLE_CARD_NUM = 1;

    public static void main(String[] args) {
        BettingResults betting = new BettingResults();
        Players players = generatePlayers(betting);
        Dealer dealer = new Dealer();
        betting.initParticipantBet(dealer, new Money());
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        startPhase(players, dealer, blackJackGame);
        endPhase(betting, players, dealer, blackJackGame);
    }

    private static void endPhase(BettingResults betting, Players players, Dealer dealer, BlackJackGame blackJackGame) {
        askEachPlayers(blackJackGame, players);
        dealerExecute(dealer);
        printFinalGameStatus(players, dealer);
        printFinalFightResult(betting, players, dealer);
    }

    private static void startPhase(Players players, Dealer dealer, BlackJackGame blackJackGame) {
        initSetting(blackJackGame, players, dealer);
        OutputView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        OutputView.printInitPlayerCards(players);
    }

    private static Players generatePlayers(BettingResults betting) {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            List<Player> players = new ArrayList<>();
            initPlayerResult(betting, playerNames, players);
            return new Players(players);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return generatePlayers(betting);
        }
    }

    private static void initPlayerResult(BettingResults betting, List<String> playerNames, List<Player> players) {
        for (String playerName : playerNames) {
            Name name = new Name(playerName);
            Money playerBet = readMoney(name);
            Player player = new Player(name);
            players.add(player);
            betting.initParticipantBet(player, playerBet);
        }
    }

    private static Money readMoney(Name name) {
        try {
            String inputBettingAmount = InputView.inputBettingAmount(name);
            return new Money(inputBettingAmount);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return readMoney(name);
        }
    }

    private static void initSetting(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        blackJackGame.initSettingCards();
        OutputView.printInitMessage(players.getPlayerNames());
    }


    private static void askEachPlayers(BlackJackGame blackJackGame, Players players) {
        OutputView.printSpaceLine();
        for (Player player : players.getPlayers()) {
            askPlayerDistribute(blackJackGame, player);
        }
    }

    private static void dealerExecute(Dealer dealer) {
        while (dealer.isDrawable()) {
            OutputView.printDealerReceivedMessage();
            dealer.getHandCards().takeCard(Deck.generateCard());
        }
    }

    private static void askPlayerDistribute(BlackJackGame blackJackGame, Player player) {
        try {
            askDistribute(blackJackGame, player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            askPlayerDistribute(blackJackGame, player);
        }
    }

    private static void askDistribute(BlackJackGame blackJackGame, Player player) {
        if (player.getScoreSum() < BLACK_JACK_NUMBER) {
            boolean playerRespond = InputView.inputReceiveOrNot(player.getName());
            askAnotherDistribute(blackJackGame, player, playerRespond);
        }
    }

    private static void askAnotherDistribute(BlackJackGame blackJackGame, Player player, boolean playerRespond) {
        if (playerRespond) {
            blackJackGame.distributeCard(player, SINGLE_CARD_NUM);
            OutputView.printParticipantResult(player.getName(), player.getCardNames());
            askPlayerDistribute(blackJackGame, player);
        }
    }

    private static void printFinalGameStatus(Players players, Dealer dealer) {
        OutputView.printSpaceLine();
        OutputView.printParticipantFinalResult(dealer.getName(), dealer.getCardNames(), dealer.getScoreSum());
        printFinalPlayerResults(players);
    }

    private static void printFinalPlayerResults(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printParticipantFinalResult(player.getName(), player.getCardNames(), player.getScoreSum());
        }
    }

    private static void printFinalFightResult(BettingResults bettingResults, Players players, Dealer dealer) {
        Results resultCalculator = new Results(bettingResults, players, dealer);
        resultCalculator.executeGame(players, dealer);
        OutputView.printFinalProceeds(players, dealer, bettingResults);
    }

}
