package domain.game;

import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.domain.PlayerNameAndBettingDto;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class BlackJackGame {
    private static final int INITIAL_DRAW_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Cards cards;
    private final ResultCalculator resultCalculator;

    private BlackJackGame(Players players, Dealer dealer, Cards cards) {
        this.players = players;
        this.dealer = dealer;
        this.cards = cards;
        this.resultCalculator = new ResultCalculator();
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

    public GameResult calculateResult() {
        return resultCalculator.calculate(dealer, players);
    }

    public int getDealerProfit() {
        return -players.getTotalProfit();
    }

    public static BlackJackGame startGame(List<PlayerNameAndBettingDto> playerInfos) {
        final Random random = new Random();
        final Players players = new Players(playerInfos.stream()
                .map(info -> new Player(info.name(), info.betting()))
                .toList());
        final Dealer dealer = new Dealer();
        final Cards cards = new Cards(random);
        final BlackJackGame game = new BlackJackGame(players, dealer, cards);
        game.drawInitialDealerCards();
        game.drawInitialPlayerCards();
        return game;
    }

    private void drawInitialDealerCards() {
        IntStream.range(0, INITIAL_DRAW_COUNT).forEach(i -> dealer.drawCard(cards));
    }

    private void drawInitialPlayerCards() {
        players.forEachPlayer(this::drawInitialCardsToPlayer);
    }

    private void drawInitialCardsToPlayer(Player player) {
        IntStream.range(0, INITIAL_DRAW_COUNT)
                .forEach(i -> player.drawCard(cards));
    }
}
