package blackjack.controller;

import blackjack.model.CardCalculator;
import blackjack.model.CardProvider;
import blackjack.model.Dealer;
import blackjack.model.Player;
import blackjack.util.PlayerParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final CardProvider cardProvider;
    private final CardCalculator cardCalculator;

    public BlackjackController(CardProvider cardProvider, CardCalculator cardCalculator) {
        this.cardProvider = cardProvider;
        this.cardCalculator = cardCalculator;
    }

    public void run() {
        String input = InputView.readPlayerName();
        List<Player> players = PlayerParser.parse(input);
        Dealer dealer = new Dealer();

        cardProvider.provideInitCards(players, dealer);
        checkBlackjack(players, dealer);
        OutputView.printInitCards(players, dealer);

        hit(players, dealer);
    }

    public void hit(List<Player> players, Dealer dealer) {

        for (Player player : players) {
            while (InputView.readCardAdd(player).equals("y") && checkAddCard(player, cardCalculator)) {
                cardProvider.provideOneCard(player);
                OutputView.printPlayerCards(player);
            }
        }

        while (cardCalculator.totalScore(dealer.getCardStatus().getCards()) < 17) {
            OutputView.printDealerHit();
            cardProvider.provideOneCard(dealer);
        }
    }

    private void checkBlackjack(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            if (cardCalculator.totalScore(player.getCardStatus().getCards()) == 21) {
                player.markBlackjack();
            }
        }
    }


    private boolean checkAddCard(Player player, CardCalculator cardCalculator) {
        if (cardCalculator.totalScore(player.getCardStatus().getCards()) >= 21) {
            OutputView.printCantAddCard();
            return false;
        }
        return true;
    }
}
