package view;

import java.util.List;
import java.util.stream.Collectors;

import card.Card;
import player.DealerFirstOpenDto;
import player.PlayerOpenDto;

public class OutputView {

    public void printFirstDrawMessage(List<String> names) {
        String joinedNames = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", joinedNames);
    }

    public void printFirstOpenCards(DealerFirstOpenDto dealerFirstOpen, List<PlayerOpenDto> playersCards) {
        System.out.println(dealerFirstOpen.getName().getValue() + ": " + dealerFirstOpen.getCard());
        playersCards.forEach(this::printPlayerCard);
    }

    public void printPlayerCard(PlayerOpenDto playerOpenDto) {
        System.out.println(
                playerOpenDto.getName().getValue() + "카드: " + parseCards(playerOpenDto));
    }

    private String parseCards(PlayerOpenDto playerOpenDto) {
        return playerOpenDto.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }
}
