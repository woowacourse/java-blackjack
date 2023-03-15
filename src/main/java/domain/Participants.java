package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Participants implements Iterable<Player> {

    private final Dealer dealer;
    private final Players players;

    public Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = new Players(players);
    }

    public void initGame() {
        dealer.drawInitCardsDealer();
        drawPlayerInitCards(dealer, players);
    }

    public void drawPlayerInitCards(Dealer dealer, Players players) {
        players.getPlayers().forEach(player -> {
            addPlayerCards(player, dealer.drawInitCards());
        });
    }

    public void addPlayerCards(Player player, List<Card> cards) {
        cards.forEach(player::addCard);
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }

    public boolean addCardToDealerIfPossible() {
        return this.dealer.drawCardDealer();
    }

    public void calculateAllResults() {
        this.dealer.calculateAllResults(players.getPlayers());
    }

    public PlayerRevenues getPlayerRevenues() {
        return this.dealer.getPlayerRevenues();
    }

    @Override
    public Iterator<Player> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final Players players = Participants.this.players;

            @Override
            public boolean hasNext() {
                while (index < players.getPlayers().size() && stopPlayer(players.getPlayers().get(index))) {
                    index++;
                }
                return index < players.getPlayers().size();
            }

            private boolean stopPlayer(Player player) {
                return player.isBust() || player.isBlackjack();
            }

            @Override
            public Player next() {
                if (hasNext()) {
                    Player player = players.getPlayers().get(index);
                    index++;
                    return player;
                }
                throw new NoSuchElementException("더 이상 원소가 없습니다.");
            }
        };
    }


}
