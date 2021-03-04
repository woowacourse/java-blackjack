package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Round {
    private final List<Card> shuffledCards;
    private final Dealer dealer;
    private final List<Player> players;

    public Round(List<Card> cards, Dealer dealer, List<Player> players) {
        this.shuffledCards = new ArrayList<>(cards);
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public List<Card> makeTwoCards() {
        return IntStream.range(0, 2)
                .mapToObj(count -> shuffledCards.remove(0))
                .collect(Collectors.toList());
    }

    public void initialize() {
        dealer.addFirstCards(makeTwoCards());
        players.forEach(player -> player.addFirstCards(makeTwoCards()));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Card makeOneCard() {
        return shuffledCards.remove(0);
    }

    public boolean addDealerCard() {
        if (!dealer.isGameOver(21)) {
            dealer.addCard(makeOneCard());
            return true;
        }
        return false;
    }

    public String getDealerName() {
        return Dealer.getName();
    }

    public List<String> getDealerCardStatus() {
        return dealer.getCardsStatus();
    }
}
