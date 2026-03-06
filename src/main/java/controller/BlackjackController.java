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

        Dealer dealer = createDealer(names, cards);
        List<Player> playerList = blackjackService.createPlayers(names, cards);

        List<CardContentDto> firstCardContents = getCardContentDtos(dealer, playerList);
        OutputView.displayCardContent(firstCardContents);
        Players players = addAdditionalCard(playerList, cards);

        // TODO: player 전부 다 burst 이면 딜러 승리 처리 (dto 알맞게)
        if (!players.isAllPlayerBurst()){
            blackjackService.determineAdditionalCardOfDealer(dealer, cards);
        }

        printFinalCards(dealer, players);

        // 최종 승패
        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
        OutputView.displayMatchResult(blackjackResult.toResultDto());
    }

    public List<CardContentDto> getCardContentDtos(Dealer dealer, List<Player> playerList) {
        List<CardContentDto> firstCardContents = new ArrayList<>();
        firstCardContents.add(new CardContentDto(dealer.getName(), List.of(dealer.getFirstCard())));
        for (Player player : playerList) {
            firstCardContents.add(new CardContentDto(player.getName(), player.getCards()));
        }
        return firstCardContents;
    }

    public Players addAdditionalCard(List<Player> playerList, Cards cards) {
        Players players = new Players(playerList);
        for (Player player : players) {
            String name = player.getName();
            boolean hasCard = hasAdditionalCard(name);
            handCardWithRetry(player, hasCard, cards, name);
        }
        return players;
    }

    public Dealer createDealer(List<String> names, Cards cards) {
        Dealer dealer = new Dealer(BlackjackController.DEALER_NAME);
        OutputView.displayCardDistribution(names);
        blackjackService.giveInitialedCard(cards, dealer);
        return dealer;
    }


    private void handCardWithRetry(Player player, boolean hasCard, Cards cards, String name) {
        while (hasCard) {
            player.add(cards.pop());
            OutputView.displayCardContent(List.of(player.toCardContentDto()));
            hasCard = hasAdditionalCard(name);
        }
    }

    public void printFinalCards(Dealer dealer, Players players) {
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

    private boolean hasAdditionalCard(String name) {
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


