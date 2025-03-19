package blackjack.gametable;

import blackjack.constant.UserAction;
import blackjack.gametable.card.Deck;
import blackjack.gametable.gambler.Dealer;
import blackjack.gametable.gambler.Player;
import blackjack.gametable.gambler.PlayerName;
import blackjack.gametable.gambler.Players;
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
        initializeGame();
    }

    private void initializeGame() {
        dealer.initializeHand(deck.drawInitialCards());
        players.initializeHands(deck.drawInitialCards(getPlayersCount()));
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
        dealer.updateBetAmounts(players);
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

    public int getPlayersCount() {
        return players.getPlayers().size();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
