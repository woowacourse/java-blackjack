package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackjackJudge;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.card_hand.DealerCardHand;
import blackjack.domain.card_hand.PlayerCardHand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        // 이름 입력을 받기
        final List<String> playerNames = inputView.getPlayerNames();
        final List<Player> players = Player.createPlayers(playerNames);
        final Deck deck = new Deck();
        
        final List<PlayerCardHand> playerCardHands = players.stream()
                .map(player -> new PlayerCardHand(player, deck))
                .toList();
        
        final DealerCardHand dealerCardHand = new DealerCardHand(deck);
        
        outputView.outputDealing(playerNames);
        outputView.outputDealerCards(dealerCardHand.getInitialCards());
        for (PlayerCardHand playerCardHand : playerCardHands) {
            outputView.outputPlayerCards(playerCardHand.getPlayerName(), playerCardHand.getInitialCards());
        }
        
        for (PlayerCardHand playerCardHand : playerCardHands) {
            outputView.outputStartAdding(playerCardHand.getPlayerName());
            boolean addingCardDecision;
            do {
                if (playerCardHand.is21()) {
                    outputView.is21Warning();
                    break;
                }
                
                if (playerCardHand.isBurst()) {
                    outputView.burstWarning();
                    break;
                }
                
                
                addingCardDecision = inputView.getAddingCardDecision(playerCardHand.getPlayerName());
                if (addingCardDecision) {
                    playerCardHand.addCard(deck.draw());
                    outputView.outputCardsAndSum(playerCardHand.getCards(), playerCardHand.getSum());
                }
            } while (addingCardDecision);
        }
        
        // 딜러는 한장의 카드 머시기
        final int beforeCount = dealerCardHand.getCards().size();
        dealerCardHand.startAdding(deck);
        final int afterCount = dealerCardHand.getCards().size();
        outputView.outputDealerAddedCards(afterCount - beforeCount);
        
        // 딜러 카드 오픈
        outputView.outputDealerResult(dealerCardHand.getCards(), dealerCardHand.getSum());
        
        for (PlayerCardHand playerCardHand : playerCardHands) {
            // 플레이어 카드 오픈
            outputView.outputPlayerResult(playerCardHand.getPlayerName(), playerCardHand.getCards(), playerCardHand.getSum());
        }
        
        // 딜러 승패 개수 확인
        outputView.outputFinalWinOrLossHeader();
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerCardHand, playerCardHands);
        
        outputView.outputDealerFinalWinOrLoss(
                blackjackJudge.getDealerWinningCount(),
                blackjackJudge.getDealerDrawingCount(),
                blackjackJudge.getDealerLosingCount()
        );
        
        for (PlayerCardHand playerCardHand : playerCardHands) {
            // 플레이어 승패여부 확인
            outputView.outputPlayerFinalWinOrLoss(playerCardHand.getPlayerName(), blackjackJudge.getWinningStatusOf(playerCardHand));
        }
    }
}
