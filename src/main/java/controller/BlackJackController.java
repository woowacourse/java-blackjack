package controller;

import domain.Dealer;
import domain.Deck;
import domain.Nickname;
import domain.Player;
import domain.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public static final String HIT_COMMAND = "y";

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
            selectHitOrStand(player, deck);
            outputView.printPlayerCards(player);
        }

        while (dealer.isSumUnderSixteen()) {
            outputView.printDealerOneMoreCardMessage();
            dealer.addOneCard(deck.drawOneCard());
        }

        outputView.printGameSummary(players, dealer);
        outputView.printGameResult(players.deriveResults(dealer.sumCardNumbers()));
    }

    private void selectHitOrStand(Player player, Deck deck) {
        while (inputView.readOneMoreCardResponse(player.getNickname()).equals(HIT_COMMAND)) {
            boolean isUnderBustStandard = player.addOneCard(deck.drawOneCard());
            if (!isUnderBustStandard) {
                outputView.printPlayerIsOverBust(player);
                break;
            }
            outputView.printPlayerCards(player);
        }
    }

    private Players registerPlayers(List<String> names, Deck deck) {
        return new Players(names.stream()
                .map(Nickname::new)
                .map(nickname -> new Player(nickname, deck.drawInitialCards()))
                .toList());
    }
}
