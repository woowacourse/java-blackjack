package blackjack.view.console;

import blackjack.controller.dto.response.GamersResultResponseDto;
import blackjack.controller.dto.response.HandResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;
import blackjack.domain.card.Card;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleOutputView implements OutputView {

    private static final String INITIAL_CARD_FORMAT = "%s에게 2장의 카드를 나누었습니다.";
    private static final String HAND_FORMAT = "%s 카드 : %s";
    private static final String HAND_WITH_SCORE_FORMAT = "%s 카드 : %s - 결과 : %d";
    private static final String RESULT_MESSAGE = "## 최종 수익";
    private static final String GAMERS_RESULT_FORMAT = "%s : %d";
    private static final String DEALER_DRAW_ONE_MORE_CARD = "딜러는 16이하라 한 장의 카드를 더 뽑았습니다.";
    private static final String COMMA = ",";

    @Override
    public void printInitialHand(HandResponseDtos handResponseDtos) {
        System.out.println();
        String names = parseNamesToString(handResponseDtos.getHandResponseDtos());
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
        String handString = parseHandToString(hand);
        String result = String.format(HAND_FORMAT, handResponseDto.getOwnerName(), handString);
        System.out.println(result);
    }

    @Override
    public void printHandWithScore(HandResponseDtos handResponseDtos) {
        System.out.println();
        for (HandResponseDto handResponseDto : handResponseDtos.getHandResponseDtos()) {
            List<Card> hand = handResponseDto.getHand();
            String handString = parseHandToString(hand);
            String result = String.format(HAND_WITH_SCORE_FORMAT, handResponseDto.getOwnerName(), handString, handResponseDto.getScore());
            System.out.println(result);
        }
    }

    @Override
    public void printResult(GamersResultResponseDto gamersResultResponseDto) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        Map<String, Integer> map = gamersResultResponseDto.getNameMoneyTable();
        for (Map.Entry<String, Integer> nameMoneyEntrySet : map.entrySet()) {
            System.out.println(String.format(GAMERS_RESULT_FORMAT, nameMoneyEntrySet.getKey(), nameMoneyEntrySet.getValue()));
        }
    }

    @Override
    public void printDealerDrawCard() {
        System.out.println(DEALER_DRAW_ONE_MORE_CARD);
    }

    private static String parseHandToString(List<Card> hand) {
        return hand.stream()
                .map(card -> card.getSymbol() + card.getType())
                .collect(Collectors.joining(COMMA));
    }

    private static String parseNamesToString(List<HandResponseDto> handResponseDtos) {
        return handResponseDtos.stream()
                .map(HandResponseDto::getOwnerName)
                .collect(Collectors.joining(COMMA));
    }
}
