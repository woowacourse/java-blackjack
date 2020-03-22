package domain.gamer;

import domain.card.Deck;
import view.InputView;
import view.OutputView;

import java.util.Collections;
import java.util.List;

public class Gamers {
    private static final int INIT_CARD_SIZE = 2;

    private List<Player> players;
    private Dealer dealer;

    public Gamers(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void initCard(Deck deck) {
        players.forEach(player -> player.addCard(deck.popCard(INIT_CARD_SIZE)));
        dealer.addCard(deck.popCard(INIT_CARD_SIZE));
    }

    public void addCardAtGamers(Deck deck) {
        players.forEach(player -> drawCardOfPlayer(deck, player));
        dealer.addCardAtDealer(deck);
    }

    private void drawCardOfPlayer(Deck deck, Player player) {
        while (player.isDrawable()
                && AddCardAnswer.isYes(InputView.inputAsDrawable(player))) {
            player.addCard(deck.popCard());
            OutputView.printGamerCard(player);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}