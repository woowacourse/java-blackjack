package controller;

import domain.CardDto;
import domain.Command;
import domain.Dealer;
import domain.Deck;
import domain.GameManager;
import domain.GameResultDto;
import domain.Referee;
import domain.player.Player;
import domain.player.PlayerParser;
import domain.player.Players;
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

        // 초기 카드 2장 지급
        Deck deck = Deck.create();
        deck.shuffle();
        GameManager gameManager = new GameManager(deck);
        gameManager.dealCard(dealer);
        gameManager.dealCard(dealer);
        gameManager.dealCardTo(players, 2);

        // 초기 카드 지급 결과 출력
        Map<String, CardDto> result = new LinkedHashMap<>();
        result.put(dealer.getName(), gameManager.getStartingCard(dealer));
        for (Player player : players) {
            result.put(player.getName(), gameManager.getCardsResult(player));
        }
        outputView.printGameInitResult(result);
        outputView.printNewLine();

        // 플레이어 턴
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
        outputView.printNewLine();

        // TODO: 하우스엣지 상황
        // 딜러 턴
        while (dealer.canReceive()) {
            gameManager.dealCard(dealer);
            outputView.printCompleteDealerTurn();
        }
        outputView.printNewLine();

        outputView.printParticipantResult(dealer.getName(), gameManager.getCardsResult(dealer).getFormattedCards(), dealer.getScore());
        for (Player player : players) {
            outputView.printParticipantResult(player.getName(), gameManager.getCardsResult(player).getFormattedCards(), player.getScore());
        }
        outputView.printNewLine();

        Referee referee = new Referee();
        Map<String, Integer> scoreByPlayerNames = new LinkedHashMap<>();
        for (Player player : players) {
            scoreByPlayerNames.put(player.getName(), player.getScore());
        }
        GameResultDto gameResultDto = referee.createGameResult(dealer.getScore(), scoreByPlayerNames);
        outputView.printResult(gameResultDto);
    }
}
