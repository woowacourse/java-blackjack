package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String name;

    WinDrawLose(String name) {
        this.name = name;
    }

    public static Map<Player, Map<WinDrawLose, Integer>> judge(Player dealer, List<Player> guests) {
        Map<Player, Map<WinDrawLose, Integer>> result = initResult(dealer, guests);
        guests = loseBustGuests(dealer, guests, result);
        if (dealer.isBust()) {
            return winNotBustedGuests(result, dealer, guests);
        }
        if (dealer.isBlackjack()) {
            return drawOrLoseGuests(dealer, guests, result);
        }
        return calculateWinDrawLose(dealer, winBlackjackGuests(dealer, guests, result), result);
    }

    private static Map<Player, Map<WinDrawLose, Integer>> initResult(Player dealer, List<Player> guests) {
        Map<Player, Map<WinDrawLose, Integer>> result = new LinkedHashMap<>();
        result.put(dealer, new EnumMap<>(WinDrawLose.class));
        for (Player player : guests) {
            result.put(player, new EnumMap<>(WinDrawLose.class));
        }
        return result;
    }

    private static List<Player> loseBustGuests(Player dealer, List<Player> guests,
                                               Map<Player, Map<WinDrawLose, Integer>> result) {
        guests.stream()
                .filter(Player::isBust)
                .forEach(guest -> {
                    result.get(dealer).merge(WIN, 1, Integer::sum);
                    result.get(guest).merge(LOSE, 1, Integer::sum);
                });
        return extractNotBustGuests(guests);
    }

    private static List<Player> extractNotBustGuests(List<Player> guests) {
        return guests.stream()
                .filter(player -> !player.isBust())
                .collect(Collectors.toList());
    }

    private static Map<Player, Map<WinDrawLose, Integer>> winNotBustedGuests(
            Map<Player, Map<WinDrawLose, Integer>> result, Player dealer,
            List<Player> nonBustedGuests) {
        for (Player guest : nonBustedGuests) {
            result.get(guest).merge(WIN, 1, Integer::sum);
            result.get(dealer).merge(LOSE, 1, Integer::sum);
        }
        return result;
    }

    private static Map<Player, Map<WinDrawLose, Integer>> drawOrLoseGuests
            (Player dealer, List<Player> guests,
             Map<Player, Map<WinDrawLose, Integer>> result) {
        guests = drawBlackjackGuests(dealer, guests, result);
        loseNotBlackjackGuests(dealer, guests, result);
        return result;
    }

    private static void loseNotBlackjackGuests(Player dealer, List<Player> guests,
                                               Map<Player, Map<WinDrawLose, Integer>> result) {
        guests.forEach(guest -> {
            result.get(dealer).merge(WIN, 1, Integer::sum);
            result.get(guest).merge(LOSE, 1, Integer::sum);
        });
    }

    private static List<Player> drawBlackjackGuests(Player dealer, List<Player> guests,
                                                    Map<Player, Map<WinDrawLose, Integer>> result) {
        guests.stream()
                .filter(Player::isBlackjack)
                .forEach(guest -> {
                    result.get(dealer).merge(DRAW, 1, Integer::sum);
                    result.get(guest).merge(DRAW, 1, Integer::sum);
                });
        return notBlackjackGuests(guests);
    }

    private static List<Player> winBlackjackGuests(Player dealer, List<Player> guests,
                                                   Map<Player, Map<WinDrawLose, Integer>> result) {
        guests.stream()
                .filter(Player::isBlackjack)
                .forEach(guest -> {
                    result.get(dealer).merge(LOSE, 1, Integer::sum);
                    result.get(guest).merge(WIN, 1, Integer::sum);
                });
        return notBlackjackGuests(guests);
    }

    private static List<Player> notBlackjackGuests(List<Player> guests) {
        return guests.stream()
                .filter(guest -> !guest.isBlackjack())
                .collect(Collectors.toList());
    }

    private static Map<Player, Map<WinDrawLose, Integer>> calculateWinDrawLose(Player dealer, List<Player> guests,
                                                                               Map<Player, Map<WinDrawLose, Integer>> result) {
        guests.forEach(guest -> {
            winDealer(dealer, guest, result);
            drawDealerAndGuest(dealer, guest, result);
            winGuest(dealer, guest, result);
        });
        return result;
    }

    private static void winDealer(Player dealer, Player guest, Map<Player, Map<WinDrawLose, Integer>> result) {
        if (dealer.getCards().calculateScore() > guest.getCards().calculateScore()) {
            result.get(dealer).merge(WIN, 1, Integer::sum);
            result.get(guest).merge(LOSE, 1, Integer::sum);
        }
    }

    private static void drawDealerAndGuest(Player dealer, Player guest, Map<Player, Map<WinDrawLose, Integer>> result) {
        if (dealer.getCards().calculateScore() == guest.getCards().calculateScore()) {
            result.get(dealer).merge(DRAW, 1, Integer::sum);
            result.get(guest).merge(DRAW, 1, Integer::sum);
        }
    }

    private static void winGuest(Player dealer, Player guest, Map<Player, Map<WinDrawLose, Integer>> result) {
        if (dealer.getCards().calculateScore() < guest.getCards().calculateScore()) {
            result.get(dealer).merge(LOSE, 1, Integer::sum);
            result.get(guest).merge(WIN, 1, Integer::sum);
        }
    }

    public String getName() {
        return name;
    }
}
