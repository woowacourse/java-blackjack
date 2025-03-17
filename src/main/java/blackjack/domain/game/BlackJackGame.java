package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.io.GameInputOutput;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.user.Player;
import blackjack.domain.value.BettingAmount;
import blackjack.domain.value.Nickname;
import blackjack.exception.ExceptionMessage;
import java.util.List;

public class BlackJackGame {

    private final GameUserStorage users;
    private final CardDeck cardDeck;
    private final GameInputOutput gameInputOutput;
    private GameStage gameStage;

    public BlackJackGame(
            GameUserStorage gameUserStorage,
            CardDeck cardDeck,
            GameInputOutput gameInputOutput
    ) {
        this.users = gameUserStorage;
        this.cardDeck = cardDeck;
        this.gameStage = GameStage.PREPARATION;
        this.gameInputOutput = gameInputOutput;
    }

    public void processPreparation(List<Nickname> playerNicknames) {
        validateGameStage(GameStage.PREPARATION);
        registerPlayer(playerNicknames);
        addBettingAmount();
        distributeInitialCard();
        outputInitialHand();
        moveToNextStage();
    }

    public void processPlaying() {
        validateGameStage(GameStage.PLAYING);
        processAllPlayerTurn();
        processAllDealerTurn();
        moveToNextStage();
    }

    public void processOutputResult() {
        validateGameStage(GameStage.RESULT_OUTPUT);
        outputFinalHand();
        outputProfit();
        moveToNextStage();
    }

    private void validateGameStage(GameStage gameStage) {
        if (this.gameStage != gameStage) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_GAME_STAGE.getContent());
        }
    }

    private void moveToNextStage() {
        gameStage = gameStage.next();
    }

    private void registerPlayer(List<Nickname> nicknames) {
        users.registerPlayer(nicknames);
    }

    private void addBettingAmount() {
        List<Player> players = users.getPlayers();
        for (Player player : players) {
            BettingAmount bettingAmount = gameInputOutput.readBettingAmount(player.getNickname());
            player.addBettingAmount(bettingAmount);
        }
    }

    private void distributeInitialCard() {
        distributeInitialCardToDealer();
        for (Player player : users.getPlayers()) {
            distributeInitialCardToPlayer(player);
        }
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

    private void processAllPlayerTurn() {
        users.getPlayers()
                .forEach(this::processPlayerTurn);
    }

    private void processPlayerTurn(Player player) {
        while (player.canHit() &&
                gameInputOutput.readIngWannaHit(player.getNickname())) {
            Card card = cardDeck.drawCard(1).getFirst();
            player.hitUntilLimit(card);
            gameInputOutput.printingHitResult(player);
        }
    }

    private void processAllDealerTurn() {
        Dealer dealer = users.getDealer();
        int drawingCount = 0;
        while (dealer.canDraw()) {
            drawingCount++;
            Card card = cardDeck.drawCard(1).getFirst();
            dealer.addCardUntilLimit(card);
        }
        gameInputOutput.printDealerDrawing(drawingCount);
    }

    private void outputInitialHand() {
        gameInputOutput.printInitialHands(users.getDealer(), users.getPlayers());
    }

    private void outputFinalHand() {
        gameInputOutput.printFinalHands(users.getDealer(), users.getPlayers());
    }

    private void outputProfit() {
        Dealer dealer = users.getDealer();
        List<Player> players = users.getPlayers();
        List<PlayerProfit> playerProfits = players.stream()
                .map(player -> player.calculateProfit(dealer.getPoint()))
                .toList();
        gameInputOutput.printPlayerProfits(playerProfits);
    }
}

