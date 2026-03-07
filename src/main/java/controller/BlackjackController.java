package controller;

import java.util.List;
import model.CardDispenser;
import model.Cards;
import model.Dealer;
import model.GameStatus;
import model.Player;
import model.PlayerResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.readPlayerNames();
        OutputView.printCardOpen(names);

        Cards deck = Cards.createDeck();
        CardDispenser dispenser = new CardDispenser(deck);

        // 딜러 카드 2장 뽑기
        Dealer dealer = new Dealer();
        dispenser.dispense(dealer, 2);

        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        players.forEach(player -> dispenser.dispense(player, 2));

        // 딜러와 참가자들 카드 목록 출력
        OutputView.printCardByPlayer(dealer);
        OutputView.printCardByPlayers(players);

        // 카드를 더 받을건지 플레이어에게 물어보는
        players.forEach(player -> {
            boolean isFirst = true;
            while (player.canHit()) {
                String input = InputView.readMoreCard(player.getName());
                Continuation command = Continuation.from(input);
                if (command.isStop()) {
                    break;
                }
                dispenser.dispense(player, 1);
                isFirst = false;

                if (command.isContinue()) {
                    OutputView.printCardByPlayer(player);
                }
            }
            if (isFirst) {
                OutputView.printCardByPlayer(player);
            }
        });

        // 딜러의 카드 더 받기 판정
        while (dealer.canHit()) {
            OutputView.printToOpenNewCard(dealer.getName(), 16);
            dispenser.dispense(dealer, 1);
        }

        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        for (Player player : players) {
            OutputView.printCardByPlayerWithScore(player);
        }

        PlayerResult playerResult = PlayerResult.initResult();
        players.forEach(player -> playerResult.judgeByPlayer(dealer, player));

        int winCount = playerResult.countByStatus(GameStatus.LOSE);
        int loseCount = playerResult.countByStatus(GameStatus.WIN);
        int drawCount = playerResult.countByStatus(GameStatus.DRAW);

        OutputView.printFinalResultHeader();
        OutputView.printDealerResult(winCount, loseCount, drawCount);
        OutputView.printResultByPlayers(playerResult.getResult());
    }
}
