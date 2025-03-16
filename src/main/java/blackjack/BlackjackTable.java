package blackjack;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.card.Deck;
import blackjack.gambler.Dealer;
import blackjack.gambler.Player;
import blackjack.gambler.PlayerName;
import blackjack.gambler.Players;
import java.util.List;

public class BlackjackTable {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackTable(List<String> playerNames) {
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

    private Players registerPlayers(List<String> names) {
        return new Players(names.stream()
                .map(PlayerName::new)
                .map(nickname -> new Player(nickname))
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

    public void addCardTo(String playerName) {
        Player player = findPlayer(playerName);
        Card card = deck.drawOneCard();
        player.addOneCard(card);
    }

    public Player findPlayer(String playerName) {
        return players.findPlayer(playerName);
    }

    public void determineDealerAdditionalCard() {
        if (dealer.isSumUnderThreshold()) {
            Card card = deck.drawOneCard();
            dealer.addOneCard(card);
        }
    }

    public boolean isDealerDrawCard() {

    }
}
