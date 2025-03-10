package controller;

import domain.Deck;
import domain.gambler.Dealer;
import domain.gambler.Nickname;
import domain.gambler.Player;
import domain.gambler.Players;
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
        checkDealerSumUnderThreshold(dealer, deck);
        outputView.printGameSummary(players, dealer);
        outputView.printGameResult(players.deriveResults(dealer.sumCardScores()));
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
        while (playerCanHit(player)) {
            player.addOneCard(deck.drawOneCard());
            outputView.printPlayerCards(player);
        }
        if (player.isBust()) {
            outputView.printPlayerIsOverBust(player);
        }
    }

    private boolean playerCanHit(Player player) {
        return inputView.readOneMoreCardResponse(player.getNickname()).equals(HIT_COMMAND) & !player.isBust();
    }

    private void checkDealerSumUnderThreshold(Dealer dealer, Deck deck) {
        while (dealer.isSumUnderThreshold()) {
            outputView.printDealerOneMoreCardMessage();
            dealer.addOneCard(deck.drawOneCard());
        }
    }
}
