package view;

import card.Cards;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import player.Name;
import player.dto.PlayersCardStatusDto;

public class OutputView {

    public void printInitCardStatus(PlayersCardStatusDto cardStatus, Cards cards) {
        Map<Name, Cards> playerGameStatus = cardStatus.cards();
        String playersNames = changeNameFormat(cardStatus.getNames());
        System.out.println("\n딜러와 " + playersNames + "에게 2장을 나누었습니다.");

        System.out.println("딜러: " + cards.getCardsStatus());
        for (Name name : playerGameStatus.keySet()) {
            Cards playerCards = playerGameStatus.get(name);

            System.out.println(name.getValue() + "카드: " + playerCards.getCardsStatus());
        }
        System.out.println();
    }

    // todo: 분리하면 좋을 듯 합니다.
    private String changeNameFormat(List<Name> names) {
        return names.stream()
                .map(Name::getValue)
                .collect(Collectors.joining(", "));
    }

    public void printCardsStatus(Name name, Cards cards) {
        System.out.println(name.getValue() + "카드: " + cards.getCardsStatus());
    }
}
