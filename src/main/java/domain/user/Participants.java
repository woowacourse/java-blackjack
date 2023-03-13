package domain.user;

import static domain.Result.BLACKJACK;
import static domain.Result.DRAW;
import static domain.Result.LOSE;
import static domain.Result.WIN;

import domain.Result;
import domain.card.Deck;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Participants {
    private final int INITIAL_CARDS_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants from(Map<String, Integer> playerBettingAmountTable) {
        return new Participants(Players.from(playerBettingAmountTable), new Dealer());
    }

    public void drawInitialCardsEachParticipant(Deck deck) {
        drawInitialCardsBy(dealer, deck);
        this.players.getPlayers().forEach(player -> drawInitialCardsBy(player, deck));
    }

    private void drawInitialCardsBy(User user, Deck deck) {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            user.addCard(deck.draw());
        }
    }

    public AllWinningAmountDto calculateWinningAmountOfAllPlayers() {
        int winningAmountOfDealer = 0;
        Map<String, Integer> playerWinningAmounts = new LinkedHashMap<>();
        for (Player player : this.getPlayers()) {
            int winningAmountOfPlayer = calculateWinningAmount(player);
            playerWinningAmounts.put(player.getNameValue(), winningAmountOfPlayer);
            winningAmountOfDealer -= winningAmountOfPlayer;

        }
        return new AllWinningAmountDto(winningAmountOfDealer, playerWinningAmounts);
    }

    private int calculateWinningAmount(Player player) {
        Result result = calculateResult(player);
        return result.calculateWinningAmount(player.getBettingAmountValue());
    }

    private Result calculateResult(Player player) {
        if (isBlackjackOnly(player)) {
            return BLACKJACK;
        }
        if (isLosed(player)) {
            return LOSE;
        }
        if (isWon(player)) {
            return WIN;
        }
        return DRAW;
    }

    private boolean isBlackjackOnly(Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private boolean isLosed(Player player) {
        if (player.isBust()) {
            return true;
        }
        if (this.dealer.isBlackjack() && !player.isBlackjack()) {
            return true;
        }
        if (player.calculateScore() < this.dealer.calculateScore() && !this.dealer.isBust()) {
            return true;
        }
        return false;
    }

    private boolean isWon(Player player) {
        if (this.dealer.isBust()) {
            return true;
        }
        if (!player.isBust() && player.calculateScore() > this.dealer.calculateScore()) {
            return true;
        }
        return false;
    }

    public boolean hitOrStayByDealer(Deck deck) {
        if (this.dealer.canAdd()) {
            this.dealer.addCard(deck.draw());
            return true;
        }
        return false;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }
}
