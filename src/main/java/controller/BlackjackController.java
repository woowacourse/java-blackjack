package controller;

import domain.*;
import domain.dto.FinalCardDto;
import domain.dto.PlayerCardDto;
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
        List<String> names = inputNames();

        Dealer dealer = blackjackService.createDealer(cards);
        outputView.displayCardDistribution(names);
        List<Player> playerList = blackjackService.createPlayers(names, cards);

        List<PlayerCardDto> firstCardContents = collectInitialCardContents(dealer, playerList);
        outputView.displayCardContent(firstCardContents);
        Players players = processPlayersTurn(playerList, cards);

        if (!players.areAllBust()) {
            int dealerCardCount = blackjackService.determineAdditionalCardOfDealer(dealer, cards);
            for (int i = 0; i < dealerCardCount; i++) {
                outputView.displayDealerCard();
            }
        }

        displayFinalCards(dealer, players);

        // 최종 승패
        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
        outputView.displayMatchResult(blackjackResult.toResultDto());
    }

    public List<PlayerCardDto> collectInitialCardContents(Dealer dealer, List<Player> playerList) {
        List<PlayerCardDto> initialCardContents = new ArrayList<>();
        initialCardContents.add(dealer.toOpeningCardDto());
        for (Player player : playerList) {
            initialCardContents.add(player.toPlayerCardDto());
        }
        return initialCardContents;
    }

    public Players processPlayersTurn(List<Player> playerList, Deck deck) {
        Players players = new Players(playerList);
        for (Player player : players) {
            String name = player.getName();
            boolean wantsCard = wantsAdditionalCard(name);
            dealAdditionalCards(player, wantsCard, deck, name);
        }
        return players;
    }


    private void dealAdditionalCards(Player player, boolean wantsCard, Deck deck, String name) {
        while (wantsCard) {
            if (player.isBust()) {
                break;
            }
            player.add(deck.pop());
            outputView.displayCardContent(List.of(player.toPlayerCardDto()));
            wantsCard = wantsAdditionalCard(name);
        }
    }

    public void displayFinalCards(Dealer dealer, Players players) {
        List<FinalCardDto> finalCards = new ArrayList<>();
        finalCards.add(dealer.toFinalCardDto());
        for (Player player : players) {
            finalCards.add(player.toFinalCardDto());
        }
        outputView.displayFinalCard(finalCards);
    }

    private List<String> inputNames() {
        return doRetry(
                inputView::readNames
        );
    }

    private boolean wantsAdditionalCard(String name) {
        return doRetry(() -> inputView.readAdditionalCard(name));
    }

    private <T> T doRetry(Supplier<T> action) {
        int retryCount = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retryCount++;
                System.out.println(e.getMessage());

                if (retryCount >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }
}


