package controller;

import domain.*;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlackjackController {
    public static final String DEALER_NAME = "딜러";
    private static final int MAX_RETRY = 10;
    private final InputView inputView;
    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.inputView = new InputView();
        this.blackjackService = blackjackService;
    }

    public void run() {
        Cards cards = blackjackService.generateCards();
        List<String> names = inputNames();

        Dealer dealer = blackjackService.createDealer(cards);
        OutputView.displayCardDistribution(names);
        List<Player> playerList = blackjackService.createPlayers(names, cards);

        List<CardContentDto> firstCardContents = collectInitialCardContents(dealer, playerList);
        OutputView.displayCardContent(firstCardContents);
        Players players = processPlayersTurn(playerList, cards);

        if (!players.areAllBust()) {
            blackjackService.determineAdditionalCardOfDealer(dealer, cards);
        }

        displayFinalCards(dealer, players);

        // 최종 승패
        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
        OutputView.displayMatchResult(blackjackResult.toResultDto());
    }

    public List<CardContentDto> collectInitialCardContents(Dealer dealer, List<Player> playerList) {
        List<CardContentDto> firstCardContents = new ArrayList<>();
        firstCardContents.add(new CardContentDto(dealer.getName(), List.of(dealer.getFirstCard())));
        for (Player player : playerList) {
            firstCardContents.add(new CardContentDto(player.getName(), player.getCards()));
        }
        return firstCardContents;
    }

    public Players processPlayersTurn(List<Player> playerList, Cards deck) {
        Players players = new Players(playerList);
        for (Player player : players) {
            String name = player.getName();
            boolean wantsCard = wantsAdditionalCard(name);
            dealAdditionalCards(player, wantsCard, deck, name);
        }
        return players;
    }


    private void dealAdditionalCards(Player player, boolean wantsCard, Cards deck, String name) {
        while (wantsCard) {
            if (player.isBust()) {
                break;
            }
            player.add(deck.pop());
            OutputView.displayCardContent(List.of(player.toCardContentDto()));
            wantsCard = wantsAdditionalCard(name);
        }
    }

    public void displayFinalCards(Dealer dealer, Players players) {
        List<FinalCardDto> finalCards = new ArrayList<>();
        finalCards.add(dealer.toFinalCardDto());
        for (Player player : players) {
            finalCards.add(player.toFinalCardDto());
        }
        OutputView.displayFinalCard(finalCards);
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
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }
}


