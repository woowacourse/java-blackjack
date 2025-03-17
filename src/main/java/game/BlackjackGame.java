package game;

import constant.Answer;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    private static final int INITIAL_CARDS = 2;

    private final InputView inputView;
    private final OutputView outputView;

    private BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static BlackjackGame of(InputView inputView, OutputView outputView) {
        return new BlackjackGame(inputView, outputView);
    }

    public void run() {
        try {
            gameStart();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void gameStart() {
        Dealer dealer = Dealer.of(CardDeck.of());
        Players players = initParticipants();

        distributeCards(players, dealer);
        outputView.printInitCards(dealer, players);

        drawToPlayers(players, dealer);
        drawToDealer(players, dealer);
        outputView.printFinalCardsContent(dealer, players);

        players.calculateResult(dealer);
        outputView.printResult(dealer, players);
    }

    private Players initParticipants() {
        List<String> playerNames = inputView.getPlayerNames();
        List<Player> players = playerNames
                .stream()
                .map(name -> Player.of(name, Money.of(inputView.getBetAmount(name))))
                .toList();
        return Players.of(players);
    }

    private void distributeCards(Players players, Dealer dealer) {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            players.receiveCards(dealer);
            dealer.receive(dealer.drawCard());
        }
        players.judgeBlackjack(dealer);
    }

    private void drawToPlayers(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            processPlayerDecision(player, dealer);
        }
    }

    private void drawToDealer(Players players, Dealer dealer) {
        while (!dealer.isBust() && dealer.canHit()) {
            dealer.receive(dealer.drawCard());
            outputView.printDealerReceived();
        }

        if (dealer.isBust()) {
            players.winAll(dealer);
        }
    }

    private void processPlayerDecision(Player player, Dealer dealer) {
        while (player.canHit()) {
            if (!answerHit(player)) {
                outputView.printCardsByName(player);
                break;
            }
            player.receive(dealer.drawCard());
            outputView.printCardsByName(player);
        }
    }

    private boolean answerHit(Player player) {
        String answer = inputView.askReceive(player.getName());
        return Answer.getAnswer(answer) == Answer.YES;
    }
}
