package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitDto;
import blackjack.view.cardview.OriginCardNumber;
import blackjack.view.cardview.OriginalCardType;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String INIT_DISTRIBUTE_FORMAT = "%s에게 2장의 카드를 나누었습니다.";
    private static final String MORE_DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_SEPARATOR = ", ";
    private static final String CARD_PRINT_FORMAT = "%s 카드: %s";
    private static final String SCORE_FORMAT = " - 점수 : %d";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 수익";
    private static final String USER_RESULT_FORMAT = "%s: %.0f";

    public static void printInitDistribute(List<ParticipantDto> participantDtos) {
        System.out.printf(lineSeparator() + INIT_DISTRIBUTE_FORMAT + lineSeparator(),
                participantDtos.stream()
                        .map(ParticipantDto::getName)
                        .collect(Collectors.joining(CARD_SEPARATOR)));

        for (ParticipantDto participantDto : participantDtos) {
            printParticipantCards(participantDto);
        }
        System.out.print(lineSeparator());
    }

    public static void printParticipantCards(ParticipantDto participant) {
        System.out.printf(CARD_PRINT_FORMAT + lineSeparator(), participant.getName(), getHoldingCards(participant.getCards()));
    }

    private static String getHoldingCards(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(OriginCardNumber.getOriginalName(card.getCardNumber()))
                    .append(OriginalCardType.getOriginalName(card.getType()))
                    .append(CARD_SEPARATOR);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    public static void printDealerDraw() {
        System.out.println(lineSeparator() + MORE_DEALER_DRAW_CARD);
    }

    public static void printFinalCard(List<ParticipantDto> participantDtos) {
        for (ParticipantDto participantDto : participantDtos) {
            printParticipantCardsWithScore(participantDto);
        }
    }

    private static void printParticipantCardsWithScore(ParticipantDto participant) {
        System.out.printf(lineSeparator() + CARD_PRINT_FORMAT + SCORE_FORMAT,
                participant.getName(), getHoldingCards(participant.getCards()), participant.getScore());
    }

    public static void printFinalResult(List<ProfitDto> profitDtos) {
        System.out.println(lineSeparator() + lineSeparator() + FINAL_RESULT_MESSAGE);

        for (ProfitDto profitDto : profitDtos) {
            printUserResult(profitDto);
        }
    }

    private static void printUserResult(ProfitDto profitDto) {
        System.out.printf(USER_RESULT_FORMAT + lineSeparator(), profitDto.getName(), profitDto.getProfit());
    }
}
