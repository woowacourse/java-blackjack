package domain.game;

import domain.card.Card;
import domain.card.Cards;
import domain.user.Dealer;
import domain.user.DealerStatus;
import domain.user.Player;
import domain.user.PlayerStatus;
import domain.user.Players;
import domain.user.UserStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;
    private final Cards cards;

    public BlackJackGame(Players players, Dealer dealer, Cards cards) {
        this.players = players;
        this.dealer = dealer;
        this.cards = cards;
    }

    public void drawDefaultCard() {
        drawPlayersDefaultCard();
        dealer.receiveCards(cards.drawTwoCards());
    }

    // TODO: 2023/03/06 Players 컬렉션에서 동작해도 좋을 것 같다.
    private void drawPlayersDefaultCard() {
        for (Player player : players.getPlayers()) {
            player.receiveCards(cards.drawTwoCards());
        }
    }

    public void drawOneMoreCardForPlayer(Player player) {
        player.receiveCard(cards.drawCard());
    }

    public void drawCardUntilOverSixteen() {
        while (dealer.getStatus().equals(DealerStatus.UNDER_SEVENTEEN)) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public void judgeWinner() {
        for (Player player : players.getPlayers()) {
            compareDealerWithPlayer(player);
        }
    }

    private void compareDealerWithPlayer(Player player) {
        UserStatus playerStatus = player.getStatus();
        if (playerStatus.equals(PlayerStatus.BUST)) {
            dealerWin(player);
            return;
        }
        if (playerStatus.equals(PlayerStatus.NORMAL) && dealer.getStatus().equals(DealerStatus.BUST)) {
            playerWin(player);
            return;
        }
        compareScore(player);
    }

    private void playerWin(Player player) {
        dealer.lose();
        player.win();
    }

    private void dealerWin(Player player) {
        dealer.win();
        player.lose();
    }

    private void compareScore(Player player) {
        if (dealer.getScore() >= player.getScore()) {
            dealerWin(player);
        }
        playerWin(player);
    }

    public Map<String, List<Card>> getSetUpResult() {
        Map<String, List<Card>> setUpResult = new LinkedHashMap<>();

        setUpResult.put(dealer.getName(), List.of(dealer.getFirstCard()));

        for (Player player : players.getPlayers()) {
            setUpResult.put(player.getName(), player.getCards());
        }

        return setUpResult;
    }

    public Map<String, Boolean> getPlayerFinalResult() {
        return players.getPlayerFinalResult();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
