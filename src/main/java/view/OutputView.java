package view;

import domain.Dealer;
import dto.DealerHandsDto;
import dto.PlayerDto;
import dto.PlayersDto;
import java.util.List;

public class OutputView {

    private final String FORM = "%s카드: %s";
    private final String TOTAL_SUM_FORM = "%s 카드: %s - 결과: %d";

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
            System.out.printf(TOTAL_SUM_FORM, playerDto.getName(), format(playerDto.getCards()), playerDto.getTotalSum() );
            System.out.println();
        }
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }
}
