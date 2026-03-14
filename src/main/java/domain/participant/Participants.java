package domain.participant;

import domain.card.Card;
import java.util.List;
import java.util.function.Supplier;

public record Participants(Dealer dealer, Players players) {

    public static Participants of(List<PlayerName> playerNames, List<BetAmount> betAmounts) {
        Dealer dealer = new Dealer();
        Players players = new Players(playerNames, betAmounts);
        return new Participants(dealer, players);
    }

    public boolean dealerShouldHit() {
        return dealer.shouldHit();
    }

    public void drawInitialCards(Supplier<Card> cardSupplier) {
        dealer.addCard(cardSupplier.get());
        dealer.addCard(cardSupplier.get());
        players.drawInitialCards(cardSupplier);
    }

    public void drawCardsByDealer(Supplier<Card> cardSupplier) {
        dealer.addCard(cardSupplier.get());
    }

    public Player drawCardsByPlayer(String name, Supplier<Card> cardSupplier) {
        players.addCard(name, cardSupplier.get());
        return players.getPlayer(name);
    }

    public Player getPlayer(String name) {
        return players.getPlayer(name);
    }
}
