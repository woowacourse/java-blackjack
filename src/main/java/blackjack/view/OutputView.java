package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.participant.Players;
import blackjack.view.dto.DistributionCompleteOutputRequest;
import blackjack.view.dto.ParticipantCardsOutputRequest;
import blackjack.view.dto.ParticipantCardsWithScoreOutputRequest;
import java.util.List;

public class OutputView {

    private static final String COMMA_AND_SPACE = ", ";
    private static final String NEW_LINE = "\n";

    private static final String PLAYER_NAME_INPUT_PROMPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String MORE_CARD_INPUT_PROMPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String CARD_DISTRIBUTION_COMPLETED = "%s와 %s에게 2 장을 나누었습니다.";
    private static final String PARTICIPANT_CARDS = "%s카드: %s";
    private static final String RESULT = " - 결과: %s";
    private static final String DEALER_PICK_CARD = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String FINAL_PRIZE = "## 최종 수익";
    private static final String BET_AMOUNT_INPUT_PROMPT = "%s의 배팅 금액은?";
    private static final String PLAYER_PRIZE = "%s: %d";

    public void printPlayerNamesInputPrompt() {
        System.out.println(PLAYER_NAME_INPUT_PROMPT);
    }

    public void printBetAmountInputPrompt(String name) {
        System.out.printf(BET_AMOUNT_INPUT_PROMPT + NEW_LINE, name);
    }

    public void printCardDistributionCompleted(DistributionCompleteOutputRequest request) {
        System.out.printf(
                CARD_DISTRIBUTION_COMPLETED + NEW_LINE,
                request.dealerName(),
                String.join(COMMA_AND_SPACE, request.playerNames())
        );
    }

    public void printParticipantCards(ParticipantCardsOutputRequest request) {
        List<String> cardNames = request.cards().stream()
                .map(Card::toString)
                .toList();
        String joinedCardNames = String.join(COMMA_AND_SPACE, cardNames);

        System.out.printf(
                PARTICIPANT_CARDS + NEW_LINE,
                request.name(),
                joinedCardNames
        );
    }

    public void printMoreCardInputPrompt(String name) {
        System.out.printf(MORE_CARD_INPUT_PROMPT + NEW_LINE, name);
    }

    public void printDealerPicksCard() {
        System.out.println(DEALER_PICK_CARD);
    }

    public void printParticipantCardsWithScore(ParticipantCardsWithScoreOutputRequest request) {
        List<String> cardNames = request.cards().stream()
                .map(Card::toString)
                .toList();
        String joinedCardNames = String.join(COMMA_AND_SPACE, cardNames);

        System.out.printf(
                PARTICIPANT_CARDS + RESULT + NEW_LINE,
                request.name(),
                joinedCardNames,
                request.score()
        );
    }

    public void printPlayerPrizes(Players players, String dealerName) {
        System.out.println(FINAL_PRIZE);
        System.out.printf(PLAYER_PRIZE + NEW_LINE, dealerName, players.getDealerProfit());
        players.perform(player -> System.out.printf(
                PLAYER_PRIZE + NEW_LINE,
                player.getName(),
                player.getPrize())
        );
    }
}
