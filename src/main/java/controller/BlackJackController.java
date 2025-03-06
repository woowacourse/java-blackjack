package controller;

import domain.Dealer;
import domain.Deck;
import domain.Nickname;
import domain.Player;
import domain.Players;
import domain.constant.WinDrawLose;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        Deck deck = Deck.initialize();
        Players players = registerPlayers(inputView.readParticipantsNames(), deck);
        Dealer dealer = new Dealer(deck.drawInitialCards());

        outputView.printInitialGameSettings(players, dealer);

        for (Player player : players.getPlayers()) {
            while (inputView.readOneMoreCardResponse(player.getNickname()).equals("y")) {
                boolean isOverBustStandard = player.addOneCard(deck.drawOneCard());
                if (!isOverBustStandard) {
                    outputView.printPlayerIsOverBust(player);
                    break;
                }
                outputView.printPlayerCards(player);
            }
            outputView.printPlayerCards(player);
        }

        while (dealer.isSumUnderSixteen()) {
            outputView.printDealerOneMoreCardMessage();
            dealer.addOneCard(deck.drawOneCard());
        }

        outputView.printGameSummary(players, dealer);
        Map<Player, WinDrawLose> playerWinDrawLoseMap = players.deriveResults(dealer.sumCardNumbers());
        outputView.printGameResult(playerWinDrawLoseMap);

    }

    private Players registerPlayers(List<String> names, Deck deck) {
        return new Players(names.stream()
                .map(Nickname::new)
                .map(nickname -> new Player(nickname, deck.drawInitialCards()))
                .toList());
    }
}
