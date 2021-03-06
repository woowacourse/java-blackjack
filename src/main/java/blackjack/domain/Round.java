package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Round {
    public static final int GAME_OVER_SCORE = 21;
    public static final int FIRST_TWO_CARD = 2;

    private final List<Card> shuffledCards;
    private final Dealer dealer;
    private final List<Player> players;

    public Round(List<Card> cards, Dealer dealer, List<Player> players) {
        this.shuffledCards = new ArrayList<>(cards);
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
        startRound();
    }

    public List<Card> drawCards(int number) {
        return Stream.generate(() -> shuffledCards.remove(0))
                .limit(number)
                .collect(Collectors.toList());
    }

    public void startRound() {
        dealer.addFirstCards(drawCards(FIRST_TWO_CARD));
        players.forEach(player -> player.addFirstCards(drawCards(FIRST_TWO_CARD)));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Card drawExtraCard() {
        return shuffledCards.remove(0);
    }

    public boolean addDealerCard() {
        if (!dealer.isGameOver(GAME_OVER_SCORE)) {
            dealer.addCard(drawExtraCard());
            return true;
        }
        return false;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<String> getDealerCardStatus() {
        return dealer.getCardsStatus();
    }
}
