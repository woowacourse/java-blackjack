package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
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

    private List<Player> players;
    private Dealer dealer;
    private GameInputOutput gameInputOutput;

    public void setUpInputOutput(GameInputOutput gameInputOutput) {
        this.gameInputOutput = gameInputOutput;
    }

    public void runGame(List<Nickname> nicknames) {
        resetGame();
        registerPlayer(nicknames);
        distributeInitialCard();
        processPlayerTurns();
        processDealerTurns();
        outputGameResult();
    }

    private void resetGame() {
        players = new ArrayList<>();
        dealer = new Dealer(new CardDeck());
    }

    private void registerPlayer(List<Nickname> nicknames) {
        players = nicknames.stream()
                .map(Player::new)
                .toList();
    }

    private void distributeInitialCard() {
        dealer.drawSelfInitialCard();
        for (Player player : players) {
            List<Card> initialCards = dealer.distributePlayerInitialCard();
            player.addInitialCards(initialCards);
        }
        outputInitialCard();
    }

    private void processPlayerTurns() {
        players.forEach(this::processPlayerTurn);
    }

    private void processPlayerTurn(Player player) {
        while (player.checkHitPossibility()) {
            boolean wannaHit = gameInputOutput.executeReadIngWannaHit(player.getNickname());
            if (!wannaHit) {
                return;
            }
            Card card = dealer.distirbuteHitCard();
            player.hit(card);
            HandState hitResult = makeHandState(player);
            gameInputOutput.executePrintingHitResult(hitResult);
        }
    }

    private void processDealerTurns() {
        int drawingCount = dealer.drawSelfCardUntil();
        gameInputOutput.executePrintDealerDrawing(drawingCount);
    }

    private void outputInitialCard() {
        HiddenDealerHandState dealerHand = new HiddenDealerHandState(dealer.getDealerName(), dealer.findFirstCard());
        List<HandState> playerHands = players.stream()
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
        HandState dealerHand = new HandState(dealer.getDealerName(), dealer.getHand(), dealer.getPoint());
        List<HandState> playerHands = players.stream()
                .map(player -> new HandState(player.getNickname(), player.getHand(), player.getPoint()))
                .toList();
        FinalHands finalHands = new FinalHands(dealerHand, playerHands);
        gameInputOutput.executePrintFinalHands(finalHands);
    }

    private void outputWinningResult() {
        List<PlayerWinningResult> winningResults = new ArrayList<>();
        for (Player player : players) {
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

