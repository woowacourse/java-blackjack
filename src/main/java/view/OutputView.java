package view;

import domain.Dealer;
import dto.DealerHandsDto;
import dto.PlayerDto;
import dto.PlayersDto;
import java.util.List;

public class OutputView {

    private final String FORM = "%s카드: %s";

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
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }
}
