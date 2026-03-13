package controller;

import java.util.List;
import java.util.function.Supplier;

import domain.Deck;
import domain.Game;
import domain.Player;
import domain.Players;
import domain.dto.CardContentDto;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private static final int MAX_RETRY = 10;
    private final InputView inputView;
    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.inputView = new InputView();
        this.blackjackService = blackjackService;
    }

    public void run() {
        Deck deck = blackjackService.generateCards();
        List<String> names = inputNames();

//        Dealer dealer = blackjackService.createDealer(cards);
        Players players = blackjackService.createPlayers(names, deck);
        Game game = blackjackService.createGame(deck, players);
        setPlayerBetting(players);

        OutputView.displayCardDistribution(names);

        List<CardContentDto> firstCardContents = blackjackService.getCardContentDtos(game);
        OutputView.displayCardContent(firstCardContents);
//        Players players = addAdditionalCard(playerList, cards);
//
//        if (!players.isAllPlayerBurst()) {
//            blackjackService.determineAdditionalCardOfDealer(dealer, cards);
//        }
//
//        printFinalCards(dealer, players);
//
//        // 최종 승패
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
//        blackjackService.calculateBettingScore(dealer, players);
////        OutputView.displayMatchResult(blackjackResult.toResultDto());
//        OutputView.displayBettingResult(blackjackService.toBettingResultDto(dealer,players));
    }

    public void setPlayerBetting(Players playerList) {
        for (Player player : playerList) {
            int bettingScore = inputBettingPrice(player.getName());
            player.betMoney(bettingScore);
        }
    }

    public int inputBettingPrice(String name) {
        return doRetry(
                () -> {
                    return inputView.readBettingPrice(name);
                }
        );

    }

    private List<String> inputNames() {
        return doRetry(
                inputView::readNames
        );
    }

//    public Players addAdditionalCard(Players players, Cards cards) {
//        for (Player player : players) {
//            String name = player.getName();
//            boolean hasCard = hasAdditionalCard(name);
//            handCardWithRetry(player, hasCard, cards, name);
//        }
//        return players;
//    }


//    private void handCardWithRetry(Player player, boolean hasCard, Cards cards, String name) {
//        while (hasCard) {
//            if (player.isBust()) {
//                break;
//            }
//            player.add(cards.pop());
//            OutputView.displayCardContent(List.of(player.toCardContentDto()));
//            hasCard = hasAdditionalCard(name);
//        }
//    }

//    public void printFinalCards(Dealer dealer, Players players) {
//        List<FinalCardDto> finalCards = new ArrayList<>();
//        finalCards.add(dealer.toFinalCardDto());
//        for (Player player : players) {
//            finalCards.add(player.toFinalCardDto());
//        }
//        OutputView.displayFinalCard(finalCards);
//    }


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


