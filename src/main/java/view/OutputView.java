package view;

import java.util.List;
import view.dto.ParticipantNamesDto;
import view.dto.UserCardDto;
import view.dto.UserProfitDto;
import view.dto.UserResultDto;

public class OutputView {

    private static final String DIVIDE_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String RECEIVED_CARD_MESSAGE = "%s 카드 : %s";
    private static final String GAME_RESULT_MESSAGE = "%s : %s";
    private static final String PLAYER_CARD_SUM_MESSAGE = " - 결과: %d";
    private static final String DEALER_ADD_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_PROMPT_MESSAGE = "## 최종 승패";

    public void printPlayerNames(ParticipantNamesDto participantNamesDto) {
        System.out.println(System.lineSeparator() +
                DIVIDE_CARD_MESSAGE.formatted(String.join(", ",participantNamesDto.getNames())));
    }

    public void printPlayerCards(List<UserCardDto> userCardDtos) {
        userCardDtos.forEach(userCardDto -> System.out.println(RECEIVED_CARD_MESSAGE
                        .formatted(userCardDto.getName(), String.join(", ", userCardDto.getCards()))));
    }

    public void printPlayerCardMessage(UserCardDto userCardDto) {
        System.out.println(RECEIVED_CARD_MESSAGE
                .formatted(userCardDto.getName(), String.join(", ", userCardDto.getCards())));
    }


    public void printBlackJackScores(List<UserResultDto> userResultDto) {
        userResultDto.forEach(this::printBlackJackScore);
    }

    private void printBlackJackScore(UserResultDto userResultDto) {
        System.out.print(RECEIVED_CARD_MESSAGE
                .formatted(userResultDto.getName(), String.join(", ", userResultDto.getCards())));
        System.out.println(PLAYER_CARD_SUM_MESSAGE.formatted(userResultDto.getScore()));
    }

    public void printDealerAddCard() {
        System.out.println(System.lineSeparator() + DEALER_ADD_CARD_MESSAGE);
    }

    public void printResults(List<UserProfitDto> userProfitDtos) {
        System.out.println(System.lineSeparator() + GAME_RESULT_PROMPT_MESSAGE);
        userProfitDtos
                .forEach(dto -> System.out.println(GAME_RESULT_MESSAGE.formatted(dto.getName(), dto.getProfit())));
    }
}
