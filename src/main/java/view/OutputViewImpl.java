package view;

import dto.CardDto;
import dto.DealerResultDto;
import dto.GameResultDto;
import dto.GameStateDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class OutputViewImpl implements OutputView {
    private static final String DELIMITER = ", ";
    private static final String NAME_PROMPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BET_AMOUNT_PROMPT = "\n%s의 배팅 금액은?\n";
    private static final String INITIAL_CARD_SHARE = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String HIT_OR_STAND_PROMPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String DEALER_ADD_CARD_NOTICE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private static final String PARTICIPANT_CARD_INFO_FORMAT = "%s카드: %s";
    private static final String WIN_LOSS_RESULT_HEADER = "\n## 최종 승패";
    private static final String PARTICIPANT_CARD_INFO_WITH_SUM_FORMAT = "%s - 결과: %d\n";
    private static final String RESULT_FORMAT = "%s: %s\n";

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printNamePrompt() {
        System.out.println(NAME_PROMPT);
    }

    public void printBetAmountPrompt(String name) {
        System.out.printf(BET_AMOUNT_PROMPT, name);
    }

    public void printInitialStates(GameStateDto gameStateDto) {
        List<ParticipantDto> playersDtos = gameStateDto.multiPlayersDtos();
        ParticipantDto dealerDto = gameStateDto.dealerDto();
        String playerNames = playersDtos.stream()
                .map(ParticipantDto::name)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(INITIAL_CARD_SHARE, playerNames);
        printUserState(dealerDto);
        playersDtos.forEach(this::printUserState);

        System.out.println();
    }

    public void printHitOrStandPrompt(String name) {
        System.out.printf(HIT_OR_STAND_PROMPT, name);
    }

    public void printUserState(ParticipantDto participantDto) {
        System.out.println(formatParticipantCards(participantDto));
    }

    public void printDealerAddCardNotice() {
        System.out.println(DEALER_ADD_CARD_NOTICE);
    }

    public void printGameResult(GameResultDto gameResultDto) {
        printFinalStates(gameResultDto);
        printFinalWinLoss(gameResultDto);
    }

    private void printFinalStates(GameResultDto gameResultDto) {
        DealerResultDto dealerResultDto = gameResultDto.dealerResultDto();
        printParticipantFinalState(dealerResultDto.dealerDto(), dealerResultDto.score());

        List<PlayerResultDto> playerResultDtos = gameResultDto.playerResultDto();
        playerResultDtos.forEach(dto -> printParticipantFinalState(dto.playerDto(), dto.score()));
    }

    private void printParticipantFinalState(ParticipantDto participantDto, int score) {
        System.out.printf(PARTICIPANT_CARD_INFO_WITH_SUM_FORMAT, formatParticipantCards(participantDto), score);
    }

    private void printFinalWinLoss(GameResultDto gameResultDto) {
        System.out.println(WIN_LOSS_RESULT_HEADER);

        DealerResultDto dealerResultDto = gameResultDto.dealerResultDto();
        System.out.print(formatDealerResult(dealerResultDto));

        List<PlayerResultDto> playerResultDtos = gameResultDto.playerResultDto();
        playerResultDtos.forEach(dto -> System.out.printf(formatPlayerResult(dto)));
    }

    private String formatParticipantCards(ParticipantDto participantDto) {
        List<CardDto> ownCards = participantDto.cards();
        return String.format(PARTICIPANT_CARD_INFO_FORMAT, participantDto.name(), formatCardsInfo(ownCards));
    }

    private String formatCardsInfo(List<CardDto> cards) {
        return cards.stream()
                .map(card -> card.cardContentNumber() + card.cardShape())
                .collect(Collectors.joining(DELIMITER));
    }

    private String formatDealerResult(DealerResultDto dealerResultDto) {
        String dealerName = dealerResultDto.dealerDto().name();
        double dealerEarnMoney = dealerResultDto.dealerEarnMoney();
        String formattedDealerEarnMoney = formatToKoreanCurrencyInstance(dealerEarnMoney);
        return String.format(RESULT_FORMAT, dealerName, formattedDealerEarnMoney);
    }

    private String formatPlayerResult(PlayerResultDto playerResultDto) {
        String dealerName = playerResultDto.playerDto().name();
        double dealerEarnMoney = playerResultDto.playerEarnMoney();
        String formattedPlayerEarnMoney = formatToKoreanCurrencyInstance(dealerEarnMoney);
        return String.format(RESULT_FORMAT, dealerName, formattedPlayerEarnMoney);
    }

    private String formatToKoreanCurrencyInstance(double money) {
        NumberFormat krwFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return krwFormat.format(money);
    }
}
