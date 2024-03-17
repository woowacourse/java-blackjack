package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.BettingAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.stream.IntStream;

public class Blackjack {
    private final Deck deck;

    public Blackjack(Deck deck) {
        this.deck = deck;
    }

    public Players createPlayers(List<Name> names, List<BettingAmount> bettingAmounts) {
        Dealer dealer = Dealer.createDefaultDealer(drawTwo());
        List<GamePlayer> gamePlayers = IntStream.range(0, names.size())
                                                .mapToObj(i -> new GamePlayer(names.get(i), drawTwo(), bettingAmounts.get(i)))
                                                .toList();
        return new Players(dealer, gamePlayers);
    }

    public void drawCard(Player player) {
        player.drawCard(deck.draw());
    }

    private Cards drawTwo() {
        return new Cards(List.of(deck.draw(), deck.draw()));
    }
}
