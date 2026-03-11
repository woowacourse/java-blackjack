package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.view.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Deck deck;

    public BlackjackController(InputView inputView, OutputView outputView, Deck deck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.deck = deck;
    }

    public void run() {
        List<String> playerNames = retry(() -> {
            String input = inputView.readPlayerName();
            return InputParser.parse(input);

        });

        List<Player> allPlayers = new ArrayList<>();

        for (String playerName : playerNames) {
            int betAmount = retry(() -> inputView.readBetAmount(playerName));
            allPlayers.add(new Player(playerName, betAmount));
        }

        Players players = new Players(allPlayers);

        // 플레이어별로 이름 받아오고

        // 플레이어별로 반복문 -> Player 객체 만들고 그 객체를 Players에 넣어주기 ?

        Dealer dealer = new Dealer();

        deck.provideInitCards(players, dealer);
        outputView.printInitCards(players.all(), dealer);

        hit(players, dealer);

        List<GameSummary> gameSummaries = players.calculateGameResult(dealer);

        for (GameSummary gameSummary : gameSummaries) {
            outputView.printCardStatus(gameSummary);
        }

        outputView.printGameResult(players, dealer);

        inputView.closeScanner();
    }

    private void hit(Players players, Dealer dealer) {
        for (Player player : players.all()) {
            while (player.canHit() && retry(() -> inputView.readCardAdd(player))) {
                deck.provideOneCard(player);
                outputView.printPlayerCards(player);
            }
        }

        while (dealer.canHit()) {
            outputView.printDealerHit();
            deck.provideOneCard(dealer);
        }
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}

