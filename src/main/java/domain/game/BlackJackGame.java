package domain.game;

import domain.card.Card;
import domain.card.Cards;
import domain.user.Dealer;
import domain.user.DealerStatus;
import domain.user.Player;
import domain.user.PlayerStatus;
import domain.user.Players;
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

    private void drawPlayersDefaultCard() {
        for (Player player : players.getPlayers()) {
            player.receiveCards(cards.drawTwoCards());
        }
    }

    public void drawOneMoreCard(Player player) {
        player.receiveCard(cards.drawCard());
    }

    public void drawDealerCardUntilSatisfyingCondition() {
        while (DealerStatus.UNDER_MIN_SCORE.equals(dealer.getStatus())) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public void judgeWinner() {
        for (Player player : players.getPlayers()) {
            judgeWinnerBetweenDealer(player);
        }
    }

    private void judgeWinnerBetweenDealer(Player player) {
        if (isDraw(player)) {
            dealer.draw(player);
            return;
        }
        if (isDealerWin(player)) {
            dealer.win(player);
            return;
        }
        player.win(dealer);
    }

    private boolean isDraw(Player player) {
        boolean isBothBust = player.getStatus() == PlayerStatus.BUST && dealer.getStatus() == DealerStatus.BUST;
        boolean isSameScore = player.getScore() == dealer.getScore();
        return isBothBust || isSameScore;
    }

    private boolean isDealerWin(Player player) {
        boolean isOnlyPlayerBust = player.getStatus() == PlayerStatus.BUST && dealer.getStatus() != DealerStatus.BUST;
        boolean isDealerHasHigherScore = isBothNormal(player) && player.getScore() < dealer.getScore();
        return isOnlyPlayerBust || isDealerHasHigherScore;
    }

    private boolean isBothNormal(Player player) {
        return player.getStatus() == PlayerStatus.NORMAL && dealer.getStatus() == DealerStatus.NORMAL;
    }

    public Map<String, List<Card>> getSetUpResult() {
        Map<String, List<Card>> setUpResult = new LinkedHashMap<>();

        setUpResult.put(dealer.getName(), List.of(dealer.getFirstCard()));

        for (Player player : players.getPlayers()) {
            setUpResult.put(player.getName(), player.getCards());
        }

        return setUpResult;
    }

    public Map<String, Result> getPlayerFinalResult() {
        return players.getPlayerFinalResult();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
