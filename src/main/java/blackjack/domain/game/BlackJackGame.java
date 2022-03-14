package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDistributor;
import blackjack.domain.card.Cards;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;
    private final CardDistributor cardDistributor = new CardDistributor(new DeckGenerator());

    public BlackJackGame(List<Name> names) {
        this.dealer = new Dealer(new Name(Dealer.NAME), drawInitialCards());
        this.players = initializePlayers(names);
    }

    private Players initializePlayers(List<Name> names) {
        return new Players(names.stream()
                .map(name -> new Player(name, drawInitialCards()))
                .collect(Collectors.toUnmodifiableList()));
    }

    private Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_DRAW_CARD_COUNT; i++) {
            cards.add(cardDistributor.distribute());
        }
        return new Cards(cards);
    }

    public boolean isAllPlayersFinished() {
        return players.isAllPlayerFinished();
    }

    public boolean isPresentPlayerFinished() {
        return getPresentPlayer().isFinished();
    }

    public void passToNextPlayer() {
        players.passToNextPlayer();
    }

    public void drawPresentPlayer(boolean canDraw) {
        if (canDraw) {
            players.drawCardPresentPlayer(cardDistributor.distribute());
            return;
        }
        players.makePresentPlayerStay();
    }

    public boolean isDealerFinished() {
        return dealer.isFinished();
    }

    public void drawDealer() {
        dealer.drawCard(cardDistributor.distribute());
    }

    public GameResult createGameResult() {
        return new GameResult(players, dealer);
    }

    public Player getPresentPlayer() {
        return players.getPresentPlayer();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    @Override
    public String toString() {
        return "BlackJackGame{" +
                "dealer=" + dealer +
                ", players=" + players +
                ", cardDistributor=" + cardDistributor +
                '}';
    }
}
