package blackjack.gametable;

import blackjack.gametable.card.Card;
import blackjack.gametable.card.Cards;
import blackjack.gametable.card.Deck;
import blackjack.gametable.gambler.Dealer;
import blackjack.gametable.gambler.Player;
import blackjack.gametable.gambler.PlayerName;
import blackjack.gametable.gambler.Players;
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
    }

    public void initializeGame() {
        Cards dealerInitialCards = deck.drawInitialCards();
        List<Cards> playerInitialCards = deck.drawInitialCards(getPlayersCount());
        dealer.initializeHand(dealerInitialCards);
        players.initializeHands(playerInitialCards);
    }

    public void addCardTo(String playerName) {
        Player player = findPlayer(playerName);
        Card card = deck.drawCard();
        player.addCard(card);
    }

    public Player findPlayer(String playerName) {
        return players.findPlayer(playerName);
    }

    public boolean isDealerDrawCard() {
        return dealer.shouldDrawCard();
    }

    public void determineDealerAdditionalCard() {
        if (isDealerDrawCard()) {
            Card card = deck.drawCard();
            dealer.addCard(card);
        }
    }

    public void updateBetAmount(String playerName, int betAmount) {
        findPlayer(playerName).updateBetAmount(betAmount);
    }

    public void calculateTotalPayout() {
        dealer.updateBetAmounts(players);
    }

    public int getPlayersCount() {
        return players.getPlayers().size();
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

    private Players registerPlayers(List<String> names) {
        return new Players(names.stream()
                .map(PlayerName::new)
                .map(Player::new)
                .toList());
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 최소 한 명 이상 필요합니다.");
        }
        if (playerNames.size() > 6) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 최대 여섯 명 까지 지정할 수 있습니다.");
        }
    }

}
