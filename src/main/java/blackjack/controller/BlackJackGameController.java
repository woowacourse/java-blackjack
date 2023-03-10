package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Game;
import blackjack.domain.GamePlayer;
import blackjack.domain.GameResult;
import blackjack.domain.Players;
import blackjack.domain.TryCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = Game.from(new GamePlayer(makePlayers(), new Dealer()));

        String dealerFirstCard = game.showDealerCards().getCardNumberToString()
                + game.showDealerCards().getCardSymbolToString();
        Map<String, List<String>> players = playersToPrintFormat(game);
        List<String> playersName = new ArrayList<>(players.keySet());

        outputView.printGameStartMessage(players, dealerFirstCard);

        int playersSize = players.size();
        for (int i = 0; i < playersSize; i++) {
            while (game.isPlayerHitPossibleByIndex(i)) {
                String playerName = playersName.get(i);
                String tryCommand = inputView.readTryCommand(playerName);

                if (!TryCommand.hit(tryCommand)) {
                    outputView.printCards(playerName, cardsToPrintFormat(game.showPlayerCardsByIndex(i)));
                    break;
                }
                game.playerHit(i);
                outputView.printCards(playerName, cardsToPrintFormat(game.showPlayerCardsByIndex(i)));
            }
        }

        while (game.isDealerHitPossible()) {
            outputView.printDealerHit();
            game.dealerHit();
        }

        outputView.printDealerResult(cardsToPrintFormat(game.showDealerAllCards()), game.getDealerScore());
        outputView.printPlayerResult(playersToPrintFormat(game), game.getPlayersScore());

        GameResult gameResult = new GameResult(game);
        outputView.printFinalVictoryOrDefeat(gameResult.getDealerResult(), gameResult.getPlayersResult());
    }

    private Players makePlayers() {
        try {
            return Players.from(inputView.readPlayersName());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return makePlayers();
        }
    }

    private Map<String, List<String>> playersToPrintFormat(Game game) {
        Map<String, List<String>> players = new LinkedHashMap<>();
        for (int i = 0; i < game.getPlayersCount(); i++) {
            String playerName = game.showPlayerNameByIndex(i);
            List<String> cards = cardsToPrintFormat(game.showPlayerCardsByIndex(i));
            players.put(playerName, cards);
        }
        return players;
    }

    private List<String> cardsToPrintFormat(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCardNumberToString() + card.getCardSymbolToString())
                .collect(Collectors.toList());
    }
}
