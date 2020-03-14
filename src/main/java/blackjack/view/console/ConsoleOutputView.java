package blackjack.view.console;

import blackjack.controller.dto.response.GamersResultResponse;
import blackjack.controller.dto.response.HandResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;
import blackjack.domain.card.Card;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class ConsoleOutputView implements OutputView {

    private static final String INITIAL_CARD_FORMAT = "%s에게 2장의 카드를 나누었습니다.";
    private static final String HAND_FORMAT = "%s 카드 : %s";
    private static final String HAND_WITH_SCORE_FORMAT = "%s 카드 : %s - 결과 : %d";
    private static final String RESULT_MESSAGE = "## 최종 승패";
    private static final String GAMERS_RESULT_FORMAT = "%s : %s";

    @Override
    public void printInitialCard(HandResponseDtos handResponseDtos) {
        System.out.println();
        String names = StringParser.parseNamesToString(handResponseDtos.getHandResponseDtos());
        String handStatement = String.format(INITIAL_CARD_FORMAT, names);
        System.out.println(handStatement);
        System.out.println();
        for (HandResponseDto handResponseDto : handResponseDtos.getHandResponseDtos()) {
            printHand(handResponseDto);
        }
    }

    @Override
    public void printHand(HandResponseDto handResponseDto) {
        List<Card> hand = handResponseDto.getHand();
        String handString = StringParser.parseHandToString(hand);
        String result = String.format(HAND_FORMAT, handResponseDto.getOwnerName(), handString);
        System.out.println(result);
    }

    @Override
    public void printHandWithScore(HandResponseDtos handResponseDtos) {
        System.out.println();
        for (HandResponseDto handResponseDto : handResponseDtos.getHandResponseDtos()) {
            List<Card> hand = handResponseDto.getHand();
            String handString = StringParser.parseHandToString(hand);
            String result = String.format(HAND_WITH_SCORE_FORMAT, handResponseDto.getOwnerName(), handString, handResponseDto.getScore());
            System.out.println(result);
        }
    }

    @Override
    public void printResult(GamersResultResponse gamersResultResponse) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        printDealerResult(gamersResultResponse.getDealerResult());
        printPlayersResult(gamersResultResponse.getPlayersResult());
    }

    private static void printDealerResult(Map<BlackJackResult, Integer> dealerResult) {
        System.out.println(String.format(GAMERS_RESULT_FORMAT, "딜러", makeDealerResult(dealerResult)));
    }

    private static void printPlayersResult(Map<Player, BlackJackResult> playersResult) {
        for (Map.Entry<Player, BlackJackResult> entry : playersResult.entrySet()) {
            System.out.println(String.format(GAMERS_RESULT_FORMAT, entry.getKey().getName(), entry.getValue().getKoreanName()));
        }
    }

    private static String makeDealerResult(Map<BlackJackResult, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<BlackJackResult, Integer> entry : dealerResult.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            stringBuilder.append(entry.getValue() + entry.getKey().getKoreanName());
        }
        return stringBuilder.toString();
    }
}
