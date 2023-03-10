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

        gameStart(game);
        gamePlay(game);
        gameEnd(game);
        gameResult(game);
    }

    private Players makePlayers() {
        try {
            return Players.from(inputView.readPlayersName());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return makePlayers();
        }
    }

    private void gameStart(Game game) {
        outputView.printGameStartMessage(playersToPrintFormat(game), dealerFirstCardToPrintFormat(game));
    }

    private void gamePlay(Game game) {
        playersHitOrStand(game);
        dealerHitOrStand(game);
    }

    private void playersHitOrStand(Game game) {
        List<String> playersName = new ArrayList<>(playersToPrintFormat(game).keySet());
        int playersSize = playersToPrintFormat(game).size();
        for (int i = 0; i < playersSize; i++) {
            String playerName = playersName.get(i);
            playerHitOrStand(game, playerName, i);
        }
    }

    private void playerHitOrStand(Game game, String playerName, int i) {
        while (game.isPlayerHitPossibleByIndex(i)) {
            String tryCommand = inputView.readTryCommand(playerName);
            if (!TryCommand.isHit(tryCommand)) {
                outputView.printCards(playerName, cardsToPrintFormat(game.showPlayerCardsByIndex(i)));
                break;
            }
            game.playerHit(i);
            outputView.printCards(playerName, cardsToPrintFormat(game.showPlayerCardsByIndex(i)));
        }
    }

    private void dealerHitOrStand(Game game) {
        while (game.isDealerHitPossible()) {
            outputView.printDealerHit();
            game.dealerHit();
        }
    }

    private void gameEnd(Game game) {
        outputView.printDealerResult(cardsToPrintFormat(game.showDealerAllCards()), game.getDealerScore());
        outputView.printPlayerResult(playersToPrintFormat(game), game.getPlayersScore());
    }

    private void gameResult(Game game) {
        GameResult gameResult = new GameResult(game);
        outputView.printFinalVictoryOrDefeat(gameResult.getDealerResult(), gameResult.getPlayersResult());
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

    private String dealerFirstCardToPrintFormat(Game game) {
        return game.showDealerCards().getCardNumberToString() + game.showDealerCards().getCardSymbolToString();
    }

    private List<String> cardsToPrintFormat(List<Card> cards) {
        return cards.stream().map(card -> card.getCardNumberToString() + card.getCardSymbolToString())
                .collect(Collectors.toList());
    }
}
