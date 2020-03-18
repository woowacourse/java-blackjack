package domain.gamer;

import domain.card.Deck;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Gamers {
    private static final int INIT_CARD_SIZE = 2;
    private static final int ADD_CARD_SIZE = 1;

    private List<Player> players;
    private Dealer dealer;

    public Gamers(String players, Dealer dealer) {
        this.players = InputUtils.splitAsDelimiter(players)
                .stream()
                .map(Player::new)
                .collect(toList());
        this.dealer = dealer;
    }

    public void initCard(Deck deck) {
        players.forEach(player -> player.addCard(deck.popCard(INIT_CARD_SIZE)));
        dealer.addCard(deck.popCard(INIT_CARD_SIZE));
    }

    public void addCardAtGamers(Deck deck) {
        dealer.addCardAtDealer(deck.popCard(ADD_CARD_SIZE));
        players.forEach(player -> drawCardOfPlayer(deck, player));
        OutputView.printAddCardAtDealer();
    }

    private void drawCardOfPlayer(Deck deck, Player player) {
        while (player.isDrawable()
                && AddCardAnswer.isYes(InputView.inputAsDrawable(player))) {
            player.addCard(deck.popCard(ADD_CARD_SIZE));
            OutputView.printGamerCard(player);
        }
    }

    public Map<Player, MatchResult> generateGameResults() {
        return players.stream()
                .collect(Collectors.toMap(player -> player,
                        player -> player.result.isMatchResult(dealer.result.getScore())));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}