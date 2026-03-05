package controller;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import service.BlackjackService;
import utils.generator.CardGenerator;
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

        List<CardContentDto> firstCardContents = new ArrayList<>();
        firstCardContents.add(new CardContentDto(dealer.getName(), List.of(dealer.getFirstCard())));
        for (Player player : playerList) {
            firstCardContents.add(new CardContentDto(player.getName(), player.getCards()));
        }
        OutputView.displayCardContent(firstCardContents);
        
        Players players = new Players(playerList);
        for (Player player : players) {
            String name = player.getName();
            boolean hasCard = hasAdditionalCard(name);
            handCardWithRetry(player, hasCard, cards, name);
        }

        blackjackService.determineAdditionalCard(dealer, cards);
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


