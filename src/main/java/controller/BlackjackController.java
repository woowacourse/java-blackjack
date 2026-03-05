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
    private static final int MAX_RETRY = 10;
    public static final String DEALER_NAME = "딜러";
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

        firstCardContents.add(new CardContentDto(dealer.getName(),List.of(dealer.getFirstCard())));
        for (Player player : playerList) {
            firstCardContents.add(new CardContentDto(player.getName(), player.getCards()));
        }

        OutputView.displayCardContent(firstCardContents);
        /**
         * TODO: 카드 출력하기
         * 딜러카드: 3다이아몬드
         * pobi카드: 2하트, 8스페이드
         * jason카드: 7클로버, K스페이드
         */

        Players players = new Players(playerList);
        for (Player player : players) {
            boolean hasCard = hasAdditionalCard(player.getName());
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


