package domain.game;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.DealerStatus;
import domain.user.Player;
import domain.user.PlayerStatus;
import domain.user.UserStatus;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final Cards cards;

    public BlackJackGame(List<Player> players, Dealer dealer, Cards cards) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
        this.cards = cards;
    }

    public void drawOneMoreCardForPlayer(Player player) {
        player.receiveCard(cards.drawCard());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void drawCardUntilOverSixteen() {
        while (dealer.getStatus().equals(DealerStatus.UNDER_SEVENTEEN)) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void judgeWinner() {
        int dealerScore = dealer.getScore();
        UserStatus dealerStatus = dealer.getStatus();
        for (Player player : players) {
            UserStatus playerStatus = player.getStatus();
            if (playerStatus.equals(PlayerStatus.BUST)) {
                dealer.win();
                player.lose();
                continue;
            }
            if(playerStatus.equals(PlayerStatus.NORMAL) && dealerStatus.equals(DealerStatus.BUST)) {
                dealer.lose();
                player.win();
                continue;
            }
            if(dealerScore >= player.getScore()) {
                dealer.win();
                player.lose();
            }
            if(dealerScore < player.getScore()) {
                dealer.lose();
                player.win();
            }
        }
    }

    public Map<String, Boolean> getUserFinalResult() {
        Map<String, Boolean> userFinalResult = new LinkedHashMap<>();

        for (Player player : players) {
            userFinalResult.put(player.getName(), player.isWinner());
        }

        return userFinalResult;
    }
}
