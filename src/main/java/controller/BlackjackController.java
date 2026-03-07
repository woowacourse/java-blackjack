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

    private CardDispenser dispenser;

    // 게임에 사용할 덱과 카드 디스펜서를 초기화한다.
    public BlackjackController() {
        Cards deck = Cards.createDeck();
        dispenser = new CardDispenser(deck);
    }

    // 입력받은 이름 목록으로 플레이어 객체들을 생성한다.
    private List<Player> createPlayers(List<String> names) {
        return names.stream()
                .map(Player::new)
                .toList();
    }

    // 딜러와 모든 플레이어에게 초기 카드 2장씩을 분배한다.
    private void distributeInitialCards(Dealer dealer, List<Player> players) {
        dispenser.dispense(dealer, 2);
        for (Player player : players) {
            dispenser.dispense(player, 2);
        }
    }

    // 초기 카드 분배 결과를 화면에 출력한다.
    private void printInitialCards(Dealer dealer, List<Player> players) {
        OutputView.printCardOpen(players);
        OutputView.printCardByPlayer(dealer);
        OutputView.printCardByPlayers(players);
    }

    // 초기 카드 분배와 초기 카드 출력을 수행한다.
    private void setupInitialGame(Dealer dealer, List<Player> players) {
        distributeInitialCards(dealer, players);
        printInitialCards(dealer, players);
    }

    // 플레이어의 추가 카드 여부 입력을 받아 명령으로 변환한다.
    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player);
        return Continuation.from(inputCommand);
    }

    // 플레이어가 카드를 더 받을 것인지 확인한다.
    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    // 플레이어에게 카드를 한 장 주고 현재 카드를 출력한다.
    private void hitAndPrintCard(Player player) {
        dispenser.dispense(player, 1);
        OutputView.printCardByPlayer(player);
    }

    // 한 명의 플레이어가 hit 또는 stand를 선택하는 턴을 진행한다.
    private void chooseHitOrStand(Player player) {
        boolean drawCard = false;
        while (canHitMore(player)) {
            hitAndPrintCard(player);
            drawCard = true;
        }
        if (!drawCard) {
            OutputView.printCardByPlayer(player);
        }
    }

    // 모든 플레이어를 돌며 카드를 더 받을지 확인하며 게임을 진행한다.
    private void hitOrStandByPlayers(List<Player> players) {
        for (Player player : players) {
            chooseHitOrStand(player);
        }
    }

    // 딜러의 카드 합이 16 이하면 17이상이 될 때까지 추가로 카드를 받는다.
    private void hitUntilStandByDealer(Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.getName());
            dispenser.dispense(dealer, 1);
        }
    }

    // 딜러와 플레이어의 최종 카드 목록과 점수를 출력한다.
    private void printFinalCards(Dealer dealer, List<Player> players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        for (Player player : players) {
            OutputView.printCardByPlayerWithScore(player);
        }
    }

    // 딜러와 플레이어의 승패 결과를 계산하고 출력한다.
    private void printGameResult(Dealer dealer, List<Player> players) {
        PlayerResult playerResult = PlayerResult.initResult();
        players.forEach(player -> playerResult.judgeByPlayer(dealer, player));

        int winCount = playerResult.countByStatus(GameStatus.LOSE);
        int loseCount = playerResult.countByStatus(GameStatus.WIN);
        int drawCount = playerResult.countByStatus(GameStatus.DRAW);

        OutputView.printFinalResultHeader();
        OutputView.printDealerResult(winCount, loseCount, drawCount);
        OutputView.printResultByPlayers(playerResult.getResult());
    }

    // 블랙잭 게임의 전체 흐름을 제어한다.
    public void run() {
        List<String> names = InputView.readPlayerNames();
        Dealer dealer = new Dealer();
        List<Player> players = createPlayers(names);
        setupInitialGame(dealer, players);

        hitOrStandByPlayers(players);
        hitUntilStandByDealer(dealer);

        printFinalCards(dealer, players);
        printGameResult(dealer, players);
    }

    /* 초기 버전 run 메서드
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
    */
}
