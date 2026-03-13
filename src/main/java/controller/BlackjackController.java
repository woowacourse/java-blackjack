package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import domain.Deck;
import domain.Game;
import domain.Player;
import domain.Players;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;
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
        Game game = doInitialGameSetting();

        addAdditionalCard(game);
        if (!game.isAllPlayerBurst()) {
            while (game.needAdditionalCard()) {
                game.addDealerAdditionalCard();
                OutputView.displayDealerCard();
            }
        }

        List<FinalCardDto> finalCards = blackjackService.getFinalCardDtos(game);
        OutputView.displayFinalCard(finalCards);

        // 최종 승패
        OutputView.displayMatchResult(blackjackService.getPlayerResultDto(game));
//        BlackjackResult blackjackResult = BlackjackResult.from(dealer, players);
//        blackjackService.calculateBettingScore(dealer, players);
//        OutputView.displayMatchResult(blackjackResult.toResultDto());
//        OutputView.displayBettingResult(blackjackService.toBettingResultDto(dealer,players));
    }



    private Game doInitialGameSetting() {
        Deck deck = blackjackService.generateCards();
        List<String> names = inputNames();

        Players players = blackjackService.createPlayers(names, deck);
        Game game = blackjackService.createGame(deck, players);
        setPlayerBetting(players);

        OutputView.displayCardDistribution(names);

        List<CardContentDto> firstCardContents = blackjackService.getCardContentDtos(game);
        OutputView.displayCardContent(firstCardContents);
        return game;
    }

    public void setPlayerBetting(Players playerList) {
        for (Player player : playerList) {
            int bettingScore = inputBettingPrice(player.getName());
            player.betMoney(bettingScore);
        }
    }

    private List<String> inputNames() {
        return doRetry(
                inputView::readNames
        );
    }

    public int inputBettingPrice(String name) {
        return doRetry(
                () -> {
                    return inputView.readBettingPrice(name);
                }
        );

    }

    public void addAdditionalCard(Game game) {
        for (Player player : game.getPlayers()) {
            boolean hasCard = hasAdditionalCard(player.getName());

            while (hasCard) {
                if (player.isBust()) {
                    break;
                }
                game.addCard(player);
                OutputView.displayCardContent(blackjackService.getCardContentDtos(game));
                hasCard = hasAdditionalCard(player.getName());
            }
        }
    }

    private boolean hasAdditionalCard(String name) {
        return doRetry(() -> inputView.readAdditionalCard(name));
    }

//    public void getFinalCardContentDto(Players players) {
//        List<FinalCardDto> finalCards = new ArrayList<>();
//        finalCards.add(dealer.toFinalCardDto());
//        for (Player player : players) {
//            finalCards.add(player.toFinalCardDto());
//        }
//        OutputView.displayFinalCard(finalCards);
//    }


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


