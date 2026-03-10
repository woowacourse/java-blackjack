package dto;

import domain.Dealer;
import domain.Hand;
import domain.Player;
import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record BlackJackInitStatusDto(List<String> initStatus) {

    public static final String HEADER_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    public static final String DEALER_STATUS_FORMAT = "딜러카드: %s";
    public static final String PLAYER_STATUS_FORMAT = "%s카드: %s";

    public BlackJackInitStatusDto(Dealer dealer, List<Player> players) {
        this(new ArrayList<>());

        addHeader(players);
        addDealerStatus(dealer);
        addPlayersStatus(players);
    }

    private void addHeader(List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .toList();

        String header = String.format(HEADER_FORMAT, String.join(", ", playerNames));

        initStatus.add(header);
    }

    private void addDealerStatus(Dealer dealer) {
        String dealerStatus = String.format(DEALER_STATUS_FORMAT, getDealerHandString(dealer.getHand()));

        initStatus.add(dealerStatus);
    }

    private void addPlayersStatus(List<Player> players) {
        for (Player player : players) {
            String playerStatus = String.format(PLAYER_STATUS_FORMAT,
                    player.getName(),
                    getHandString(player.getHand()));

            initStatus.add(playerStatus);
        }
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
