package domain;

import java.util.Random;
import java.util.stream.IntStream;
import util.NameParser;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;
    private final Cards cards;

    private BlackJackGame(Players players, Dealer dealer, Cards cards) {
        this.players = players;
        this.dealer = dealer;
        this.cards = cards;
    }

    public Players players(){
        return players;
    }

    public Dealer dealer(){
        return dealer;
    }

    public Cards cards() {
        return cards;
    }

    public static BlackJackGame startGame(String participant){
        final Random random = new Random();
        final Players players = NameParser.makeNameList(participant);
        final Dealer dealer = new Dealer();
        final Cards cards = new Cards(random);

        final BlackJackGame game = new BlackJackGame(players, dealer, cards);
        game.drawInitialDealerCards(dealer, cards);
        game.drawInitialPlayerCards(players, cards);
        return game;
    }

    private void drawInitialDealerCards(Dealer dealer, Cards cards) {
        IntStream.range(0, GameRule.INITIAL_DRAW_COUNT).forEach(i -> dealer.drawCard(cards));
    }

    private void drawInitialPlayerCards(Players players, Cards cards) {
        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(player -> drawInitialCardsToPlayer(player, cards));
    }

    private void drawInitialCardsToPlayer(Player player, Cards cards) {
        IntStream.range(0, GameRule.INITIAL_DRAW_COUNT)
                .forEach(i -> player.drawCard(cards));
    }
}
