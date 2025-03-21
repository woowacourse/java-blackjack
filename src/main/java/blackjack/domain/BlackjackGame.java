package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.gambler.PlayerName;
import blackjack.domain.gambler.Players;
import java.util.List;

public class BlackjackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(List<String> playerNames) {
        validatePlayerCount(playerNames);
        this.deck = Deck.initialize();
        this.dealer = new Dealer();
        this.players = registerPlayers(playerNames);
        distributeStartingHands();
    }

    public void processDealerTurn() {
        if (isDealerShouldDrawCard()) {
            dealer.addCard(deck.drawCard());
        }
        calculateTotalPayout();
    }

    public void updateBetAmount(String playerName, int betAmount) {
        findPlayer(playerName).updateBetAmount(betAmount);
    }

    public Player findPlayer(String playerName) {
        return players.findPlayer(playerName);
    }

    public String findCurrentTurnPlayerName() {
        return players.findCurrentPlayer().getPlayerName();
    }

    public void addCardTo(String playerName) {
        findPlayer(playerName).addCard(deck.drawCard());
    }

    public boolean isPlaying() {
        return players.countInProgressPlayers() != 0;
    }

    public boolean isDealerShouldDrawCard() {
        return dealer.shouldDrawCard();
    }

    public void endPlayerTurn(String playerName) {
        players.endPlayerTurn(playerName);
    }

    private void calculateTotalPayout() {
        dealer.applyBetAmounts(players);
    }

    private void distributeStartingHands() {
        dealer.drawInitializeHand(deck.drawInitialCards());
        players.drawInitializeHands(deck);
    }

    private Players registerPlayers(List<String> names) {
        return new Players(names.stream().map(PlayerName::new).map(Player::new).toList());
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.isEmpty() || playerNames.size() > 6) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 1명 이상, 6명 이하로 지정해야 합니다.");
        }
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

}
