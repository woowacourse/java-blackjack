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

    public void printDealer(final Dealer dealer) {

    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }
}
