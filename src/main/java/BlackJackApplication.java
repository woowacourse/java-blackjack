import domain.ProfitResult;
import domain.YesOrNo;
import domain.card.CardDeck;
import domain.gambler.*;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackApplication {
    public static final int FIRST_CARD_COUNT = 2;

    public static void main(String[] args) {
        try {
            runBlackJack();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runBlackJack() {
        Names names = new Names(InputView.inputNames());
        Map<Name, Money> playerInfo = new LinkedHashMap<>();
        for (Name name : names.getNames()) {
            playerInfo.put(name, Money.fromPositive(InputView.inputMoney(name)));
        }
        Players players = new Players(playerInfo);
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        drawFirstCards(dealer, players, cardDeck);
        drawMoreCards(dealer, players, cardDeck);
        printCalculatedResult(dealer, players);
    }

    private static void drawFirstCards(Dealer dealer, Players players, CardDeck cardDeck) {
        dealer.drawCard(cardDeck, FIRST_CARD_COUNT);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, FIRST_CARD_COUNT);
        }
        OutputView.printFirstCards(dealer, players);
    }

    private static void drawMoreCards(Dealer dealer, Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            drawMorePlayerCard(cardDeck, player);
        }
        drawMoreDealerCard(dealer, cardDeck);
    }

    private static void drawMoreDealerCard(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            OutputView.printDealerHitMessage();
            dealer.drawCard(cardDeck);
        }
    }

    private static void drawMorePlayerCard(CardDeck cardDeck, Player player) {
        while (player.canHit() && YesOrNo.isYes(InputView.inputMoreCard(player))) {
            player.drawCard(cardDeck);
            OutputView.printUserCards(player, false);
        }
    }

    private static void printCalculatedResult(Dealer dealer, Players players) {
        OutputView.printUsersCards(dealer, players, true);
        OutputView.printFinalResult(dealer, new ProfitResult(dealer, players));
    }
}
