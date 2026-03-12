package controller;

import constant.GameConstant;
import domain.card.Deck;
import domain.dto.BlackjackResultDto;
import domain.dto.FinalPlayerCardDto;
import domain.dto.PlayerCardDto;
import domain.participant.BetMap;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.BlackjackResult;
import exception.BlackjackException;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlackjackController {
    private static final int MAX_RETRY = 10;
    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.blackjackService = blackjackService;
    }

    public void run() {
        Deck cards = blackjackService.generateCards();
        List<String> names = readNames();
        BetMap betMap = readBets(names);

        Dealer dealer = blackjackService.createDealer(cards);
        Players players = initializePlayers(names, cards);
        playRound(dealer, players, cards);

        displayFinalCards(dealer, players);
        showFinalResult(dealer, players, betMap);
    }

    private List<String> readNames() {
        return doRetry(inputView::readNames);
    }

    private BetMap readBets(List<String> names) {
        BetMap betMap = new BetMap();
        for (String name : names) {
            betMap.addBetAmountOf(name, doRetry(() -> inputView.readBet(name)));
        }
        return betMap;
    }

    private Players initializePlayers(List<String> names, Deck cards) {
        outputView.displayCardDistribution(names);
        return blackjackService.createPlayers(names, cards);
    }

    private void playRound(Dealer dealer, Players players, Deck cards) {
        List<PlayerCardDto> firstCardContents = collectInitialCardContents(dealer, players);
        outputView.displayCardContents(firstCardContents);
        processPlayersTurn(players, cards);
        processDealerTurn(dealer, players, cards);
    }

    private List<PlayerCardDto> collectInitialCardContents(Dealer dealer, Players players) {
        List<PlayerCardDto> initialCardContents = new ArrayList<>();
        initialCardContents.add(PlayerCardDto.fromDealer(dealer));
        for (Player player : players) {
            initialCardContents.add(PlayerCardDto.from(player));
        }
        return initialCardContents;
    }

    private void processPlayersTurn(Players players, Deck deck) {
        for (Player player : players) {
            String name = player.getName();
            boolean wantsCard = wantsAdditionalCard(name);
            dealAdditionalCards(player, wantsCard, deck, name);
        }
    }

    private boolean wantsAdditionalCard(String name) {
        return doRetry(() -> inputView.readAdditionalCard(name));
    }

    private void dealAdditionalCards(Player player, boolean wantsCard, Deck deck, String name) {
        while (wantsCard) {
            player.add(deck.pop());
            if (player.isBust()) {
                break;
            }
            outputView.displayCardContents(List.of(PlayerCardDto.from(player)));
            wantsCard = wantsAdditionalCard(name);
        }
    }

    private void processDealerTurn(Dealer dealer, Players players, Deck cards) {
        if (!players.areAllBust()) {
            int dealerCardCount = blackjackService.determineAdditionalCardOfDealer(dealer, cards);
            for (int i = 0; i < dealerCardCount; i++) {
                outputView.displayDealerCard(GameConstant.DEALER_HIT_THRESHOLD);
            }
        }
    }

    private void displayFinalCards(Dealer dealer, Players players) {
        List<FinalPlayerCardDto> finalCards = new ArrayList<>();
        finalCards.add(FinalPlayerCardDto.from(dealer));
        for (Player player : players) {
            finalCards.add(FinalPlayerCardDto.from(player));
        }
        outputView.displayFinalCard(finalCards);
    }

    private void showFinalResult(Dealer dealer, Players players, BetMap betMap) {
        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players, betMap);
        outputView.displayMatchResult(BlackjackResultDto.from(blackjackResult));
    }

    private <T> T doRetry(Supplier<T> action) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY) {
            try {
                return action.get();
            } catch (BlackjackException e) {
                retryCount++;
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        throw new BlackjackException("입력 횟수를 초과했습니다.");
    }
}


