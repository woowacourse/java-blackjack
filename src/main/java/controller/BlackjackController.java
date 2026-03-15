package controller;

import constant.GameConstant;
import domain.card.Deck;
import domain.dto.BlackjackResultDto;
import domain.dto.FinalPlayerCardDto;
import domain.dto.PlayerCardDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.BlackjackResult;
import domain.result.ProfitResult;
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
        Players players = createPlayers(names);

        Dealer dealer = blackjackService.createDealer(cards);
        distributeInitialCards(dealer, players, cards, names);
        playRound(dealer, players, cards);

        displayFinalCards(dealer, players);
        showFinalResult(dealer, players);
    }

    private List<String> readNames() {
        return doRetry(inputView::readNames);
    }

    private Players createPlayers(List<String> names) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            domain.participant.Bet bet = doRetry(() -> inputView.readBet(name));
            playerList.add(new Player(name, bet));
        }
        return new Players(playerList);
    }

    private void distributeInitialCards(Dealer dealer, Players players, Deck cards, List<String> names) {
        outputView.displayCardDistribution(names);
        blackjackService.giveInitialCards(cards, dealer);
        blackjackService.distributeInitialCards(players, cards);
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
            outputView.displayCardContents(List.of(PlayerCardDto.from(player)));
            if (player.isBust()) {
                break;
            }
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

    private void showFinalResult(Dealer dealer, Players players) {
        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
        ProfitResult profitResult = ProfitResult.from(blackjackResult);
        outputView.displayMatchResult(BlackjackResultDto.from(profitResult));
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
