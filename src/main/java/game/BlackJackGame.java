package game;

import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;

import card.Card;
import card.CardDeck;
import card.RandomCardShuffler;
import console.InputConsole;
import console.OutputConsole;
import java.util.List;
import java.util.stream.IntStream;

public class BlackJackGame {

    private final InputConsole inputConsole;
    private final OutputConsole outputConsole;

    public BlackJackGame(InputConsole inputConsole, OutputConsole outputConsole) {
        this.inputConsole = inputConsole;
        this.outputConsole = outputConsole;
    }

    public void play() {
        CardDeck cardDeck = CardDeck.prepareDeck(new RandomCardShuffler());

        List<String> playerNames = inputConsole.readPlayerNames();
        List<Integer> bettingMoney = inputConsole.readBettingMoney(playerNames);

        Players players = new Players(IntStream.range(0, playerNames.size())
                .mapToObj(i -> new Player(playerNames.get(i), bettingMoney.get(i)))
                .toList());
        Dealer dealer = new Dealer();

        opening(players, dealer, cardDeck);
        hitOrStand(players, dealer, cardDeck);
        end(players, dealer);
    }

    private void opening(Players players, Dealer dealer, CardDeck cardDeck) {
        players.draw(cardDeck);
        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));
        Card dealerCard = dealer.getSingleCard();

        outputConsole.printInitialGame(dealerCard, players.getPlayers());
    }

    private void hitOrStand(Players players, Dealer dealer, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            decidePlayerHitOrStand(player, cardDeck);
        }
        decideDealerHitOrStand(dealer, cardDeck);
        outputConsole.printGameResult(dealer, players);
    }

    private void decidePlayerHitOrStand(Player player, CardDeck cardDeck) {
        while (!player.isBust() && inputConsole.readDrawMoreCard(player)) {
            player.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputConsole.printPlayerCard(player);
        }
    }

    private void decideDealerHitOrStand(Dealer dealer, CardDeck cardDeck) {
        while (!dealer.isBust() && !dealer.isOverDrawBound()) {
            dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputConsole.printDealerDrawMessage();
        }
    }

    private void end(Players players, Dealer dealer) {
        List<GameResult> gameResults = players.judgeGameResult(dealer);

        outputConsole.printDealerWinningResult(
                GameResult.WIN.countReversed(gameResults),
                GameResult.DRAW.countReversed(gameResults),
                GameResult.LOSE.countReversed(gameResults));

        List<String> playerNames = players.getAllPlayerNames();
        outputConsole.printWinningResult(playerNames, gameResults);
    }
}
