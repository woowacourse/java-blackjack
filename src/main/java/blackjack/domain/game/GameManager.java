package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.io.GameInputOutput;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.user.Nickname;
import blackjack.domain.user.Player;
import java.util.List;

public class GameManager {

    private GameUserStorage users;
    private CardDeck cardDeck;
    private GameInputOutput gameInputOutput;

    public GameManager(GameUserStorage users, CardDeck cardDeck) {
        this.users = users;
        this.cardDeck = cardDeck;
    }

    public void setUpInputOutput(GameInputOutput gameInputOutput) {
        this.gameInputOutput = gameInputOutput;
    }

    public void runGame(List<Nickname> nicknames) {
        registerPlayer(nicknames);
        distributeInitialCard();
        processPlayerTurns();
        processDealerTurns();
        outputGameResult();
    }

    private void registerPlayer(List<Nickname> nicknames) {
        users.initialize(nicknames);
    }

    private void distributeInitialCard() {
        distributeInitialCardToDealer();
        for (Player player : users.getPlayers()) {
            distributeInitialCardToPlayer(player);
        }
        gameInputOutput.printInitialHands(users.getDealer(), users.getPlayers());
    }

    private void distributeInitialCardToDealer() {
        Dealer dealer = users.getDealer();
        List<Card> initialDealerCards = cardDeck.drawCard(GameRule.INITIAL_CARD_COUNT.getValue());
        dealer.addInitialCards(initialDealerCards);
    }

    private void distributeInitialCardToPlayer(Player player) {
        List<Card> initialPlayerCards = cardDeck.drawCard(GameRule.INITIAL_CARD_COUNT.getValue());
        player.addInitialCards(initialPlayerCards);
    }

    private void processPlayerTurns() {
        users.getPlayers()
                .forEach(this::processPlayerTurn);
    }

    private void processPlayerTurn(Player player) {
        while (player.checkHitPossibility() &&
                gameInputOutput.readIngWannaHit(player.getNickname())) {
            Card card = cardDeck.drawCard(1).getFirst();
            player.hit(card);
            gameInputOutput.printingHitResult(player);
        }
    }

    private void processDealerTurns() {
        Dealer dealer = users.getDealer();
        int drawingCount = 0;
        while (dealer.checkPossibilityOfDrawing()) {
            drawingCount++;
            Card card = cardDeck.drawCard(1).getFirst();
            dealer.addCardUntilLimit(card);
        }
        gameInputOutput.printDealerDrawing(drawingCount);
    }

    private void outputGameResult() {
        gameInputOutput.printFinalHands(users.getDealer(), users.getPlayers());
        GameResult gameResult = new GameResult(users.getDealer(), users.getPlayers());
        gameInputOutput.printGameResult(gameResult);
    }
}

