package controller;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
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

    public BlackjackController() {
        this.inputView = new InputView();
    }

    public void run() {
        Cards cards = CardGenerator.generate();
        cards.shuffle();
        List<String> names = inputNames();
        List<Player> playerList = new ArrayList<>();
        Dealer dealer = new Dealer(DEALER_NAME);

        OutputView.displayCardDistribution(names);
        dealer.add(cards.pop());
        dealer.add(cards.pop());

        for (String name : names) {
            Player player = new Player(name);
            player.add(cards.pop());
            player.add(cards.pop());
            playerList.add(player);
        }

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

        // TODO: 딜러 더 받기
        determineAdditionalCard(dealer, cards);
    }

    private void determineAdditionalCard(Dealer dealer, Cards cards) {
        if (dealer.needAdditionalCard()) {
            dealer.add(cards.pop());
            OutputView.displayDealerCard();
        }
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


