package blackjack.domain;

import blackjack.constant.UserAction;
import blackjack.domain.card.Deck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.gambler.PlayerName;
import blackjack.domain.gambler.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
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

    private void distributeStartingHands() {
        dealer.drawInitializeHand(deck.drawInitialCards());
        players.drawInitializeHands(deck);
    }

    public void playGame(InputView inputView, OutputView outputView) {
        for (String playerName : getPlayerNames()) {
            playPlayerTurn(playerName, inputView, outputView);
        }
        playDealerTurn(outputView);
    }

    public void updateBetAmount(String playerName, int betAmount) {
        findPlayer(playerName).updateBetAmount(betAmount);
    }

    public void calculateTotalPayout() {
        dealer.applyBetAmounts(players);
    }

    public Player findPlayer(String playerName) {
        return players.findPlayer(playerName);
    }

    private void playPlayerTurn(String playerName, InputView inputView, OutputView outputView) {
        while (inputView.readOneMoreCardResponse(playerName).equals(UserAction.HIT)) {
            addCardTo(playerName);
            outputView.printPlayerCards(this, playerName);
        }
    }

    private void playDealerTurn(OutputView outputView) {
        if (dealer.shouldDrawCard()) {
            outputView.printDealerOneMoreCardMessage();
            dealer.addCard(deck.drawCard());
        }
        calculateTotalPayout();
    }

    private void addCardTo(String playerName) {
        findPlayer(playerName).addCard(deck.drawCard());
    }

    private Players registerPlayers(List<String> names) {
        return new Players(names.stream()
                .map(PlayerName::new)
                .map(Player::new)
                .toList());
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
