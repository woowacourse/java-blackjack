package game;

import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;

import card.Card;
import card.CardDeck;
import card.RandomCardShuffler;
import console.InputConsole;
import console.OutputConsole;
import java.util.List;

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
        Players players = new Players(playerNames.stream()
                .map(Player::new)
                .toList());
        Dealer dealer = new Dealer();

        opening(players, dealer, cardDeck);
        hitOrStand(players, dealer, cardDeck);
        end(players, dealer);
    }

    private void opening(Players players, Dealer dealer, CardDeck cardDeck) {
        players.drawCard(cardDeck);
        dealer.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_START));
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
        while (!player.isOverBustBound() && inputConsole.readDrawMoreCard(player)) {
            player.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputConsole.printPlayerCard(player);
        }
    }

    private void decideDealerHitOrStand(Dealer dealer, CardDeck cardDeck) {
        while (!dealer.isOverBustBound() && !dealer.isOverDrawBound()) {
            dealer.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputConsole.printDealerDrawMessage();
        }
    }

    private void end(Players players, Dealer dealer) {
        List<GameResult> gameResults = players.judgeGameResult(dealer);
        List<String> playerNames = players.getAllPlayerNames();

        int winCount = GameResult.WIN.countReversedGameResult(gameResults);
        int loseCount = GameResult.LOSE.countReversedGameResult(gameResults);
        int drawCount = GameResult.DRAW.countReversedGameResult(gameResults);

        outputConsole.printDealerWinningResult(winCount, drawCount, loseCount);
        outputConsole.printWinningResult(playerNames, gameResults);
    }
}
