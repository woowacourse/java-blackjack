package controller;

import domain.BlackjackResult;
import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.dto.BlackjackResultDto;
import domain.dto.FinalPlayerCardDto;
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
        List<String> names = readNames();

        Dealer dealer = blackjackService.createDealer(cards);
        outputView.displayCardDistribution(names);
        Players players = blackjackService.createPlayers(names, cards);

        players = playRound(dealer, players, cards);

        displayFinalCards(dealer, players);
        showFinalResult(dealer, players);
    }

    private Players playRound(Dealer dealer, Players players, Deck cards) {
        List<PlayerCardDto> firstCardContents = collectInitialCardContents(dealer, players);
        outputView.displayCardContent(firstCardContents);
        players = processPlayersTurn(players, cards);

        if (!players.areAllBust()) {
            int dealerCardCount = blackjackService.determineAdditionalCardOfDealer(dealer, cards);
            for (int i = 0; i < dealerCardCount; i++) {
                outputView.displayDealerCard();
            }
        }
        return players;
    }

    private void showFinalResult(Dealer dealer, Players players) {
        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
        outputView.displayMatchResult(BlackjackResultDto.from(blackjackResult));
    }

    public List<PlayerCardDto> collectInitialCardContents(Dealer dealer, Players players) {
        List<PlayerCardDto> initialCardContents = new ArrayList<>();
        initialCardContents.add(PlayerCardDto.fromDealer(dealer));
        for (Player player : players) {
            initialCardContents.add(PlayerCardDto.from(player));
        }
        return initialCardContents;
    }

    public Players processPlayersTurn(Players players, Deck deck) {
        for (Player player : players) {
            String name = player.getName();
            boolean wantsCard = wantsAdditionalCard(name);
            dealAdditionalCards(player, wantsCard, deck, name);
        }
        return players;
    }


    private void dealAdditionalCards(Player player, boolean wantsCard, Deck deck, String name) {
        while (wantsCard) {
            player.add(deck.pop());
            if (player.isBust()) {
                break;
            }
            outputView.displayCardContent(List.of(PlayerCardDto.from(player)));
            wantsCard = wantsAdditionalCard(name);
        }
    }

    public void displayFinalCards(Dealer dealer, Players players) {
        List<FinalPlayerCardDto> finalCards = new ArrayList<>();
        finalCards.add(FinalPlayerCardDto.from(dealer));
        for (Player player : players) {
            finalCards.add(FinalPlayerCardDto.from(player));
        }
        outputView.displayFinalCard(finalCards);
    }

    private List<String> readNames() {
        return doRetry(
                inputView::readNames
        );
    }

    private boolean wantsAdditionalCard(String name) {
        return doRetry(() -> inputView.readAdditionalCard(name));
    }

    private <T> T doRetry(Supplier<T> action) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retryCount++;
                System.out.println(e.getMessage());
            }
        }
        throw new IllegalStateException("입력 횟수를 초과했습니다.");
    }
}


