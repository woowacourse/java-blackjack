package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.participant.Player;
import blackjack.model.result.TotalResult;
import java.util.List;

public class OutputView {

    private static final String COMMA_AND_SPACE = ", ";
    private static final String NEW_LINE = "\n";

    private static final String PLAYER_NAME_INPUT_PROMPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String MORE_CARD_INPUT_PROMPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE;
    private static final String CARD_DISTRIBUTION_COMPLETED = NEW_LINE + "%s와 %s에게 2 장을 나누었습니다." + NEW_LINE;
    private static final String PARTICIPANT_CARDS = "%s카드: %s";
    private static final String RESULT = " - 결과: %s" + NEW_LINE;
    private static final String DEALER_PICK_CARD = NEW_LINE + "딜러는 16이하라 한 장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String TOTAL_RESULT = NEW_LINE + "## 최종 승패";
    private static final String DEALER_RESULT = "%s: %s" + NEW_LINE;

    public void printPlayerNamesInputPrompt() {
        System.out.println(PLAYER_NAME_INPUT_PROMPT);
    }

    public void printMoreCardInputPrompt(String name) {
        System.out.printf(MORE_CARD_INPUT_PROMPT, name);
    }

    public void printCardDistributionCompleted(List<Player> players, String dealerName) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();

        System.out.printf(CARD_DISTRIBUTION_COMPLETED,
                dealerName,
                String.join(COMMA_AND_SPACE, names)
        );

    }

    public void printParticipantCards(String name, List<Card> openedCards) {
        List<String> cardNames = openedCards.stream()
                .map(Card::toString)
                .toList();

        String joinedCardNames = String.join(COMMA_AND_SPACE, cardNames);
        System.out.printf(PARTICIPANT_CARDS + NEW_LINE,
                name,
                joinedCardNames
        );
    }

    public void printParticipantCardsWithScore(String name, List<Card> cards, int score) {
        List<String> cardNames = cards.stream()
                .map(Card::toString)
                .toList();

        String joinedCardNames = String.join(COMMA_AND_SPACE, cardNames);

        System.out.printf(PARTICIPANT_CARDS + RESULT,
                name,
                joinedCardNames,
                score
        );
    }

    public void printDealerPicksCard() {
        System.out.println(DEALER_PICK_CARD);
    }

    public void printResult(TotalResult totalResult, String dealerName) {
        System.out.println(TOTAL_RESULT);
        printDealerResult(totalResult.getDealerResult(), dealerName);
        printAllPlayerResult(totalResult);
    }

    private void printDealerResult(String dealerResult, String dealerName) {
        System.out.printf(DEALER_RESULT, dealerName, dealerResult);
    }

    private void printAllPlayerResult(TotalResult totalResult) {
        List<String> playerResults = totalResult.getPlayerResults();
        System.out.println(String.join(NEW_LINE, playerResults));
    }

    public void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
