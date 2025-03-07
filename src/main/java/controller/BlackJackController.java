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

        selectPlayersHitOrStand(players, deck);
        checkDealerSumUnderSixteen(dealer, deck);
        outputView.printGameSummary(players, dealer);
        outputView.printGameResult(players.deriveResults(dealer.sumCardNumbers()));
    }

    private Players registerPlayers(List<String> names, Deck deck) {
        return new Players(names.stream()
                .map(Nickname::new)
                .map(nickname -> new Player(nickname, deck.drawInitialCards()))
                .toList());
    }

    private void selectPlayersHitOrStand(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            selectHitOrStand(player, deck);
            outputView.printPlayerCards(player);
        }
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

    private void checkDealerSumUnderSixteen(Dealer dealer, Deck deck) {
        while (dealer.isSumUnderSixteen()) {
            outputView.printDealerOneMoreCardMessage();
            dealer.addOneCard(deck.drawOneCard());
        }
    }
}
