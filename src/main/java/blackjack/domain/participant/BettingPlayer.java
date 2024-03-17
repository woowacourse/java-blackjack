package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.game.GameResult;
import blackjack.domain.money.Money;
import java.util.List;
import java.util.Objects;

public class BettingPlayer {

    private final Player player;
    private final Money bet;

    public BettingPlayer(String name, int bet) {
        this.player = Player.from(name);
        this.bet = new Money(bet);
    }

    BettingPlayer(List<Card> cards, Money bet) {
        this.player = new Player(cards, new Name("name"));
        this.bet = bet;
    }

    public Money calculatePrize(Dealer dealer) {
        GameResult gameResult = GameResult.of(dealer, player);
        return gameResult.calculatePrize(bet);
    }

    public void drawStartCards(Deck deck) {
        player.drawStartCards(deck);
    }

    public Player getPlayer() {
        return player;
    }

    public Name getName() {
        return player.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingPlayer that = (BettingPlayer) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
