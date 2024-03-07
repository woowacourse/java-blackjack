package view;

import domain.Card;
import domain.dto.GameStatusDto;
import domain.dto.GamerDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitialStatus(GameStatusDto gameStatusDto) {
        GamerDto dealerDto = gameStatusDto.getDealerDto();
        List<GamerDto> gamerDtos = gameStatusDto.getGamerDtos();
        // "딜러와 pobi, jason에게 2장을 나누었습니다.";
        String gamerNames = gamerDtos.stream().map(GamerDto::getName).collect(Collectors.joining(", "));
        System.out.println("딜러와 %s에게 2장을 나누었습니다.".formatted(gamerNames));
        System.out.println("");
        System.out.println(
                String.join(": ",
                        dealerDto.getName(),
                        dealerDto.getCards().get(0).toString()));
        gamerDtos.stream()
                .map(gamerDto -> buildNameCards(gamerDto.getName(), gamerDto.getCards()))
                .forEach(System.out::println);
    }

    private static String buildNameCards(String name, List<Card> cards) {
        String cardString = cards.stream().map(Card::toString).collect(Collectors.joining(", "));
        return String.join(": ", name, cardString);
    }

}
