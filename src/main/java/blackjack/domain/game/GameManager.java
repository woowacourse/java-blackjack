package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.user.Nickname;
import blackjack.domain.user.Player;
import blackjack.dto.FinalHands;
import blackjack.dto.HandState;
import blackjack.dto.HiddenDealerHandState;
import blackjack.dto.InitialHands;
import blackjack.dto.PlayerWinningResult;
import blackjack.dto.WinningState;
import java.util.ArrayList;
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
        outputInitialCard();
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
                gameInputOutput.executeReadIngWannaHit(player.getNickname())) {
            Card card = cardDeck.drawCard(1).getFirst();
            player.hit(card);
            HandState hitResult = makeHandState(player);
            gameInputOutput.executePrintingHitResult(hitResult);
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
        gameInputOutput.executePrintDealerDrawing(drawingCount);
    }

    private void outputInitialCard() {
        Dealer dealer = users.getDealer();
        HiddenDealerHandState dealerHand = new HiddenDealerHandState(dealer.getDealerName(), dealer.findFirstCard());
        List<HandState> playerHands = users.getPlayers().stream()
                .map(player -> new HandState(player.getNickname(), player.getHand(), player.getPoint()))
                .toList();
        InitialHands initialHands = new InitialHands(dealerHand, playerHands);
        gameInputOutput.executePrintInitialHands(initialHands);
    }

    private void outputGameResult() {
        outputFinalHands();
        outputWinningResult();
    }

    private void outputFinalHands() {
        Dealer dealer = users.getDealer();
        HandState dealerHand = new HandState(dealer.getDealerName(), dealer.getHand(), dealer.getPoint());
        List<HandState> playerHands = users.getPlayers().stream()
                .map(player -> new HandState(player.getNickname(), player.getHand(), player.getPoint()))
                .toList();
        FinalHands finalHands = new FinalHands(dealerHand, playerHands);
        gameInputOutput.executePrintFinalHands(finalHands);
    }

    private void outputWinningResult() {
        Dealer dealer = users.getDealer();
        List<PlayerWinningResult> winningResults = new ArrayList<>();
        for (Player player : users.getPlayers()) {
            WinningType winningType = WinningType.parse(player.getPoint(), dealer.getPoint());
            PlayerWinningResult winningResult = new PlayerWinningResult(player.getNickname(), winningType);
            winningResults.add(winningResult);
        }
        WinningState winningState = new WinningState(winningResults);
        gameInputOutput.executePrintGameResult(winningState);
    }

    private HandState makeHandState(Player player) {
        return new HandState(player.getNickname(), player.getHand(), player.getPoint());
    }
}

