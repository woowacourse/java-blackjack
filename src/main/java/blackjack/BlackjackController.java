package blackjack;

import blackjack.domain.CardGame;
import blackjack.domain.CardGameJudge;
import blackjack.domain.CardGameResult;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public static void main(String[] args) {
        final CardGame cardGame = new CardGame();
        final List<String> names = InputView.readPlayerNames();
        final List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        final Dealer dealer = new Dealer();

        cardGame.initializeHand(dealer, players);
        OutputView.printInitialHandOfEachPlayer(dealer, players);

        givePlayerMoreCardsIfWanted(cardGame, players);
        giveDealerMoreCardsIfNeeded(cardGame, dealer);

        printHandStatusOfEachPlayer(dealer, players);
        printCardGameResult(dealer, players);
    }

    private static void givePlayerMoreCardsIfWanted(final CardGame cardGame, final List<Player> players) {
        for (Player player : players) {
            while (!player.isDead()) {
                // TODO: 변수명 수정
                boolean needAnotherCard = InputView.askForAnotherCard(player.getName());
                if (!needAnotherCard) {
                    break;
                }
                cardGame.giveCard(player);
                OutputView.printPlayerCard(player);
            }
        }
    }

    private static void giveDealerMoreCardsIfNeeded(final CardGame cardGame, final Dealer dealer) {
        while (dealer.needMoreCard()) {
            cardGame.giveCard(dealer);
            OutputView.printDealerDrawMessage(dealer);
        }
    }

    private static void printHandStatusOfEachPlayer(final Dealer dealer, final List<Player> players) {
        OutputView.printHandSum(dealer);
        for (Player player : players) {
            OutputView.printHandSum(player);
        }
    }

    private static void printCardGameResult(final Dealer dealer, final List<Player> players) {
        final CardGameJudge cardGameJudge = new CardGameJudge();
        final CardGameResult cardGameResult = cardGameJudge.judge(dealer, players);
        OutputView.printResult(cardGameResult);
    }
}
