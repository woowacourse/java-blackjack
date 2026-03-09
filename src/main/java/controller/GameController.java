package controller;

import domain.CardDto;
import domain.Command;
import domain.Dealer;
import domain.Deck;
import domain.GameManager;
import domain.player.Player;
import domain.player.PlayerParser;
import domain.player.Players;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String rawPlayerName = inputView.readPlayerName();
        Players players = PlayerParser.parseToPlayers(rawPlayerName);
        Dealer dealer = new Dealer();

        GameManager gameManager = new GameManager(Deck.create());
        gameManager.dealCard(dealer);
        gameManager.dealCard(dealer);
        gameManager.dealCardTo(players, 2);

        Map<String, CardDto> result = new LinkedHashMap<>();
        result.put(dealer.getName(), gameManager.getStartingCard(dealer));
        for (Player player : players) {
            result.put(player.getName(), gameManager.getCardsResult(player));
        }
        outputView.printGameInitResult(result);

        for (Player player : players) {
            while (player.canReceive()) {
                Command yesOrNo = Command.from(inputView.askPlayHit(player.getName()));

                if (yesOrNo.isNo()) {
                    break;
                }
                gameManager.dealCard(player);
                outputView.printParticipantCard(player.getName(),gameManager.getCardsResult(player).getFormattedCards());
            }
        }

    }
}
