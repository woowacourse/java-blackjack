package controller;

import domain.BlackJackGame;
import domain.Dealer;
import domain.Deck;
import domain.Money;
import domain.Player;
import domain.Result;
import dto.ParticipantCardsDto;
import dto.ParticipantGameResultDto;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        List<String> playerNames = getPlayerNames();
        List<Player> players = initPlayer(playerNames);

        Deck deck = new Deck();

        BlackJackGame blackJackGame = new BlackJackGame(deck, players, Dealer.create());
        blackJackGame.distributeInitialCards();

        OutputView.printGameInitialMessage(playerNames);

        OutputView.printInitialDealerCards(ParticipantCardsDto.from(blackJackGame.getDealer()));
        for (Player player : blackJackGame.getPlayers()) {
            OutputView.printCards(ParticipantCardsDto.from(player));
        }

        playGame(blackJackGame);

        Result gameResult = blackJackGame.getGameResult();
        endGame(blackJackGame, gameResult);
    }

    private List<String> getPlayerNames() {
        return InputView.askPlayerNames();
    }

    private List<Player> initPlayer(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(playerName -> Player.from(playerName, (getPlayerBettingMoney(playerName))))
                .toList();
        return players;
    }

    private Money getPlayerBettingMoney(String playerName) {
        return new Money(InputView.askPlayerBettingMoney(playerName));
    }


    private void playGame(BlackJackGame blackJackGame) {
        blackJackGame.getPlayers().forEach(player -> playGameWithPlayer(blackJackGame, player));
        playGameWithDealer(blackJackGame);
    }

    private void playGameWithPlayer(BlackJackGame blackJackGame, Player player) {
        while (blackJackGame.canPlayerReceiveCard(player)) {
            if (isStopGame(player)) {
                break;
            }
            blackJackGame.playGameWithPlayer(player);
            OutputView.printCards(ParticipantCardsDto.from(player));
        }
    }

    private void playGameWithDealer(BlackJackGame blackJackGame) {
        while (blackJackGame.canDealerReceiveCard()) {
            blackJackGame.playGameWithDealer();
            OutputView.printDealerMessage();
        }
    }

    private boolean isStopGame(Player player) {
        if (isStop(InputView.askContinue(player.getName()))) {
            OutputView.printCards(ParticipantCardsDto.from(player));
            return true;
        }
        return false;
    }

    private boolean isStop(String response) {
        return response.equals("n");
    }

    private static void endGame(BlackJackGame blackJackGame, Result gameResult) {
        OutputView.printFinalCards(ParticipantCardsDto.from(blackJackGame.getDealer()));
        printFinalScores(blackJackGame);
        OutputView.printGameResult(ParticipantGameResultDto.from(gameResult));
    }

    private static void printFinalScores(BlackJackGame blackJackGame) {
        blackJackGame.getPlayers().stream()
                .map(ParticipantCardsDto::from)
                .forEach(OutputView::printFinalCards);
    }
}
