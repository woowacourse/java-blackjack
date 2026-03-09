package dto;

import domain.Dealer;
import domain.Hand;
import domain.Player;
import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record BlackJackInitStatusDto(List<String> initStatus) {
    public BlackJackInitStatusDto(Dealer dealer, List<Player> players) {
        this(new ArrayList<>());

        initStatus.add(getInitHeaderString(players.stream()
                .map(Player::getName)
                .toList()));

        initStatus.add("딜러카드: " + getDealerHandString(dealer.getHand()));
        for (Player player : players) {
            initStatus.add(getHandOutputString(player.getName(), player.getHand()));
        }
    }

    private String getInitHeaderString(List<String> names) {
        return "딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.";
    }

    private String getHandOutputString(String name, Hand hand) {
        return name + "카드: " + getHandString(hand);
    }

    // todo : hand의 toString으로 구현하는게 맞는가? (토론)
    private String getHandString(Hand hand) {
        return hand.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(", "));
    }

    private String getDealerHandString(Hand hand) {
        return hand.getCards().getFirst().getCardName();
    }
}
