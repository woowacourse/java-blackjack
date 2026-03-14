package domain.participant;

import domain.BetMoney;
import domain.card.Deck;
import domain.dto.RoundResult;
import domain.dto.TotalResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {
    private static final String ALREADY_EXIST_NAME = "[ERROR] 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> players) {
        validate(players);
        return new Players(players.stream()
                .map(Player::createReady)
                .toList());
    }

    private static void validate(List<String> players) {
        HashSet<String> uniqueNames = new HashSet<>(players);

        if (uniqueNames.size() != players.size()) {
            throw new IllegalArgumentException(ALREADY_EXIST_NAME);
        }
    }

    public void giveCardsToEachPlayers(Deck deck, int quantity) {
        for (Player player : players) {
            player.addCards(deck.drawWithAmount(quantity));
        }
    }

    public void hitStandEachPlayers(Consumer<Player> hitStandFunc) {
        for (Player player : players) {
            hitStandFunc.accept(player);
        }
    }

    public void setBetMoneyEachPlayers(Function<Player, Long> getBetMoneyFunc) {
        for (Player player : players) {
            player.setBetMoney(getBetMoneyFunc.apply(player));
        }
    }

    public TotalResult getResults(Dealer dealer) {
        List<RoundResult> roundResults = new ArrayList<>();
        BetMoney dealerProfit = BetMoney.ZERO;
        for (Player player : players) {
            BetMoney result = player.getResult(dealer);
            dealerProfit = dealerProfit.sub(result);
            roundResults.add(new RoundResult(player, result));
        }
        return new TotalResult(roundResults, dealerProfit);
    }

    public List<Player> getPlayers() {
        return players.stream()
                .map(Player::copyOf)
                .toList();
    }
}
