package view;

import domain.Participant;
import domain.Result;
import dto.DealerHandsDto;
import dto.PlayerDto;
import dto.PlayersDto;
import java.util.List;
import java.util.Map;

public class OutputView {

    private final String FORM = "%s카드: %s";
    private final String TOTAL_SUM_FORM = "%s 카드: %s - 결과: %d";
    private final String RESULT_FORM = "%s: %s";

    public void printStartDeal(final DealerHandsDto dealerHandsDto, final PlayersDto playersDto) {
        final String dealerCard = dealerHandsDto.getDisplayedCard();

        final List<String> playerNames = playersDto.getNames();
        System.out.println("딜러와 " + format(playerNames) + " 에게 2장을 나누었습니다.");

        System.out.println("딜러: " + dealerCard);

        for (PlayerDto playerDto : playersDto.getPlayers()) {
            System.out.printf(FORM, playerDto.getName(), format(playerDto.getCards()));
            System.out.println();
        }
        System.out.println();
    }

    public void printHands(final PlayerDto playerDto) {
        System.out.printf(FORM, playerDto.getName(), format(playerDto.getCards()));
        System.out.println();
    }

    public void printDealerCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다."); //TODO 메서드 변경
        System.out.println();
    }

    public void printHandsResult(final PlayersDto playersDto) {
        for (PlayerDto playerDto : playersDto.getPlayers()) {
            System.out.printf(TOTAL_SUM_FORM, playerDto.getName(), format(playerDto.getCards()), playerDto.getTotalSum());
            System.out.println();
        }
    }

    public void printGameResult(final Map<Result, Integer> dealerResult, final Map<Participant, Result> playerResult) {
        System.out.println("## 최종결과");
        System.out.printf(RESULT_FORM, "딜러", format(dealerResult));
        System.out.println();
        for (Map.Entry<Participant, Result> entry : playerResult.entrySet()) {
            System.out.printf(RESULT_FORM, entry.getKey().getName(), entry.getValue().getValue());
            System.out.println();
        }
    }

    private String format(final Map<Result, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Result, Integer> entry : dealerResult.entrySet()) {
            stringBuilder.append(entry.getValue() + entry.getKey().getValue() + " ");
        }

        return stringBuilder.toString();
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }

    public void printBustMessage() {
        System.out.println("BUST");
        System.out.println();
    }

    public void printBlackJack() {
        System.out.println("BLACK JACK!!!");
        System.out.println();
    }
}
