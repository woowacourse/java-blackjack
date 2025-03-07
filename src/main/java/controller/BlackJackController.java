package controller;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final String YES_SIGN = "y";
    private static final String NO_SIGN = "n";
    private final InputView inputView;
    private final OutputView outputView;
    private final Cards deck;

    public BlackJackController(InputView inputView, OutputView outputView, Cards deck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.deck = deck;
    }

    public void run() {
        List<String> names = inputView.readNames();
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();

        Dealer dealer = new Dealer();

        dealer.prepareGame(deck);
        players.forEach(player -> player.prepareGame(deck));

        outputView.printInitialCards(dealer, players);

        for (Player player : players) {
            String option;
            do {
                if (player.isBurst()) {
                    break;
                }

                option = inputView.readYesOrNo(player);

                if (option.equals(NO_SIGN)) {
                    break;
                }

                player.hit(deck);
                outputView.printCards(player);
            }
            while (option.equals(YES_SIGN));
        }

        if (dealer.dealerHit(deck)) {
            outputView.printDealerHitSuccess();
        }

        outputView.printResult(dealer, players);
    }
}
