package blackjack;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.Names;
import blackjack.domain.YesOrNo;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.gambler.Players;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackApplication {

    private static final int FIRST_TIME_DRAW_COUNT = 2;

    public static void main(String[] args) {
        try {
            startGame();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private static void startGame() {
        Names names = new Names(InputView.inputNames());
        Map<Name, Money> playerInfo = new LinkedHashMap<>();
        for (Name name : names.getNames()) {
            playerInfo.put(name, Money.fromPositive(InputView.inputMoney(name)));
        }
        Players players = new Players(playerInfo);
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        distributeFirstCards(dealer, players, cardDeck);
        drawMoreCards(dealer, players, cardDeck);
        printCalculatedResult(dealer, players);
    }

    private static void distributeFirstCards(Dealer dealer, Players players, CardDeck cardDeck) {
        dealer.drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        }
        OutputView.printCardDistribution(dealer, players);
        OutputView.printUsersCards(dealer, players);
    }

    private static void drawMoreCards(Dealer dealer, Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            drawMorePlayerCardManual(cardDeck, player);
        }
        drawMoreDealerCardAuto(cardDeck, dealer);
    }

    private static void drawMorePlayerCardManual(CardDeck cardDeck, Player player) {
        while (player.canDrawCard() && YesOrNo.of(InputView.inputMoreCard(player)) == YesOrNo.YES) {
            player.drawCard(cardDeck);
            OutputView.printPlayerCards(player);
        }
    }

    private static void drawMoreDealerCardAuto(CardDeck cardDeck, Dealer dealer) {
        while (dealer.canDrawCard()) {
            OutputView.printDealerOneMoreCard(dealer);
            dealer.drawCard(cardDeck);
        }
    }

    private static void printCalculatedResult(Dealer dealer, Players players) {
        GameResult gameResult = new GameResult(dealer, players);
        OutputView.printUsersCardsAndScore(dealer, players);
        OutputView.printFinalResult(dealer, gameResult);
    }
}
