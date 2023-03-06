package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static final String DEALER_NAME = "딜러";
    private static final OutputView INSTANCE = new OutputView();
    private static final String ERROR = "[ERROR] : ";


    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printRequestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void showInitialCards(final Dealer dealer, final Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
        printCards(dealer.getName(), List.of(makeCardName(dealer.getCards().get(0))));
        players.getPlayers().forEach(player -> printCards(player.getName(), makeCardNames(player)));
    }

    public void printPlayerCards(Player player) {
        printCards(player.getName(), makeCardNames(player));
    }


    private void printCards(String playerName, List<String> cardNames) {
        System.out.println(playerName + "카드: " + String.join(", ", cardNames));
    }


    public void printRequestIntention(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerReceived() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCards(final Dealer dealer, final Players players) {
        printFinalCards(dealer.getName(), makeCardNames(dealer), dealer.getScore());
        players.getPlayers().forEach(
                player -> printFinalCards(player.getName(), makeCardNames(player), player.getScore()));
    }

    private void printFinalCards(String name, List<String> cardNames, int score) {
        System.out.println(name + "카드: " + String.join(", ", cardNames) + " - 결과: " + score);
    }


    private List<String> makeCardNames(final Participant participant) {
        return participant.getCards().stream()
                .map(this::makeCardName)
                .collect(Collectors.toList());
    }

    private String makeCardName(final Card card) {
        return card.getCardLetter().getName() + card.getCardSuit().getShape();
    }

    public void printDealerResults(final Map<Result, Integer> dealerResults) {
        System.out.print(System.lineSeparator() + "## 최종 승패" + System.lineSeparator() + "딜러: ");
        Map<String, Integer> convertedDealerResult = getConvertedDealerResult(dealerResults);
        convertedDealerResult.keySet().forEach(result -> {
            if (convertedDealerResult.get(result) > 0) {
                System.out.print(convertedDealerResult.get(result) + result);
            }
        });
        System.out.println();
    }

    private Map<String, Integer> getConvertedDealerResult(Map<Result, Integer> dealerResults) {
        return dealerResults.keySet().stream()
                .collect(Collectors.toMap(
                        Result::getTerm,
                        dealerResults::get
                ));
    }

    public void printPlayerResult(final Player player, final Result playerResult) {
        System.out.println(player.getName() + ": " + playerResult.getTerm());
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR + errorMessage);
    }
}
