import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackController {
    private final CardDistributor cardDistributor;

    public BlackjackController(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public void startGame() {
        // 플레이어 이름 입력 받아서 플레이어 객체 생성
        String playerNamesStr = InputView.askPlayerNames();
        List<String> playerNames = InputParser.splitPlayerNames(playerNamesStr);
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        Dealer dealer = new Dealer();

        // 2장씩 카드 분배
        distributeInitialCards(players, dealer);

        //출력 텟트
        Map<String, List<String>> playerCards = new HashMap<>();
        for (Player player : players) {
            playerCards.put(player.getName(), player.getCardNames());
        }
        OutputView.printAllUserCards(playerCards, dealer.getCardNames());

        // 플레이어에게 카드 추가로 받을지 물어보고, 카드 분배
        for (Player player : players) {
            while (true) {
                String hitOrStand = InputView.askHitOrStand(player.getName());
                if (!isHit(hitOrStand)) {
                    break;
                }
                cardDistributor.distributeCardToPlayer(player);
                OutputView.printDrawnCards(player.getName(), player.getCardNames());
                if (player.isBust()) {
                    break;
                }
            }
        }

        // 딜러가 카드 추가로 받을지 판단하고, 카드 분배
        Game game = new Game(cardDistributor);
        game.dealerDrawsCardsUntilDone(dealer);
        OutputView.printDealerCardDrawnResult(dealer.getAdditionalDrawnCardCount());

        // 게임 결과 계산
        calculateFinalScore(players, dealer);

        // 최종 승패 출력
        GameResult gameResult = game.judgeTotalGameResult(players, dealer);
        Map<ScoreCompareResult, Integer> dealerResult = gameResult.dealerResult();
        Map<Player, ScoreCompareResult> playerResult = gameResult.playerResults();
        HashMap<String, ScoreCompareResult> playerNameResult = new HashMap<>();

        for (Entry<Player, ScoreCompareResult> entry : playerResult.entrySet()) {
            playerNameResult.put(entry.getKey().getName(), entry.getValue());
        }
        OutputView.printFinalResult(dealerResult, playerNameResult);
    }

    private void calculateFinalScore(List<Player> players, Dealer dealer) {
        Map<String, List<String>> playerCards = new HashMap<>();
        Map<String, Integer> playerScores = new HashMap<>();
        for (Player player : players) {
            playerCards.put(player.getName(), player.getCardNames());
            playerScores.put(player.getName(), player.calculateTotalScore());
        }

        List<String> dealerCards = dealer.getCardNames();
        int dealerScore = dealer.calculateTotalScore();

        OutputView.printFinalCardScores(playerCards, dealerCards, playerScores, dealerScore);
    }

    private void distributeInitialCards(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            cardDistributor.distributeTwoCardsToPlayer(player);
        }
        cardDistributor.distributeTwoCardsToDealer(dealer);
    }

    private boolean isHit(String hitOrStand) {
        if (hitOrStand.equals("y")) {
            return true;
        }
        if (hitOrStand.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다. y 또는 n을 입력해주세요");
    }

}
