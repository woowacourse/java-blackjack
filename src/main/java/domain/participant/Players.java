package domain.participant;

import domain.result.BlackjackResult;
import java.util.Collections;
import java.util.List;

public class Players {

    private static final int MAX_PLAYERS = 5;
    private static final int MIN_PLAYERS = 1;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        validatePlayersNumber(players);
        return new Players(players);
    }

    private static void validatePlayersNumber(List<Player> players) {
        if (players.size() > MAX_PLAYERS || players.size() < MIN_PLAYERS) {
            throw new IllegalArgumentException("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
        }
    }

    public void receiveCards(Dealer dealer) {
        for (Player player : players) {
            player.receive(dealer.drawCard());
        }
    }

    public Player findByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("예기치 못한 에러가 발생했습니다."));
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public void judgeBlackjack(Dealer dealer) {
        for (Player player : players) {
            handleInitialBlackjack(dealer, player);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void calculateResult(Dealer dealer) {
        for (Player player : players) {
            handleBetResult(dealer, player);
        }
    }

    public void winAll(Dealer dealer) {
        for (Player player : players) {
            handleBustDealerAmount(dealer, player);
        }
    }

    private void handleInitialBlackjack(Dealer dealer, Player player) {
        int blackjackAmount = getBlackjackAmount(player);
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            player.increaseAmount(blackjackAmount);
            dealer.decreaseAmount(blackjackAmount);
        }
    }

    private int getBlackjackAmount(Player player) {
        return (int) Math.round(player.getBetAmount() * Money.BLACKJACK_BET_RATIO);
    }

    private void handleBetResult(Dealer dealer, Player player) {
        BlackjackResult playerResult = player.getBlackjackResult(dealer);

        if (playerResult == BlackjackResult.LOSE) {
            player.lose(dealer);
        }
        if (playerResult == BlackjackResult.WIN) {
            player.win(dealer);
        }
    }

    private void handleBustDealerAmount(Dealer dealer, Player player) {
        if (!player.isBust()) {
            player.win(dealer);
        }
    }
}
