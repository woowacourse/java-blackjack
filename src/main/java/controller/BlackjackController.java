package controller;

import domain.Deck;
import domain.GameResult;
import domain.GameResultCalculator;
import domain.Gamer;
import domain.SummationCardPoint;
import dto.DealerGameResultDTO;
import dto.GameResultDTO;
import dto.GamerDTO;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import view.GameResultOutputView;
import view.GamerOutputView;
import view.OutputView;
import view.YNInputView;

public class BlackjackController {
    private final Gamer dealer;
    private final List<Gamer> players;

    public BlackjackController(Gamer dealer, List<Gamer> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void startBlackjackGame(Deck deck) {
        initDealerAndPlayers(deck);
        printDealerAndPlayers();

        playersTryDraw(deck);
        dealerTryDraw(deck);

        printDealerAndPlayersWithPoint();
        printDealerAndPlayersGameResult();
    }

    private void initDealerAndPlayers(Deck deck) {
        dealerDraw(deck);
        dealerDraw(deck);
        players.forEach(player -> playerDraw(deck, player));
        players.forEach(player -> playerDraw(deck, player));
        OutputView.print("딜러와 pobi, jason에게 2장을 나누었습니다.");
    }

    private void dealerDraw(Deck deck) {
        dealer.draw(deck, cards -> {
            Random random = new Random();
            int idx = random.nextInt(cards.size());
            return cards.get(idx);
        }, new SummationCardPoint(16));
    }

    private void playerDraw(Deck deck, Gamer player) {
        player.draw(deck, cards -> {
            Random random = new Random();
            int idx = random.nextInt(cards.size());
            return cards.get(idx);
        }, new SummationCardPoint(21));
    }

    private void printDealerAndPlayers() {
        // TODO: 딜러의 카드 한장 숨기기
        GamerDTO dealerDTO = new GamerDTO(dealer.getRawName(), dealer.getRawHoldingCards(),
                dealer.getRawSummationCardPoint());
        GamerOutputView.printWithoutSummationCardPoint(dealerDTO);
        players.forEach(player -> {
            GamerDTO gamerDTO = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.getRawSummationCardPoint());
            GamerOutputView.printWithoutSummationCardPoint(gamerDTO);
        });
    }

    private void playersTryDraw(Deck deck) {
        players.forEach(player -> {
            playerTryDraw(deck, player);
        });
    }

    private void playerTryDraw(Deck deck, Gamer player) {
        boolean needToDraw = true;
        //Todo 메서드 인덴트 줄이기
        while (needToDraw && canDraw(player, new SummationCardPoint(21))) {
            OutputView.print("%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".formatted(player.getRawName()));
            needToDraw = YNInputView.getYNAsBoolean();
            if (needToDraw) {
                playerDraw(deck, player);
            }
        }
    }

    private boolean canDraw(Gamer player, SummationCardPoint threshold) {
        return !player.getSummationCardPoint().isBiggerThan(threshold);
    }

    private void dealerTryDraw(Deck deck) {
        int count = 0;
        while (canDraw(dealer, new SummationCardPoint(16))) {
            count++;
            dealerDraw(deck);
        }
        if (count != 0) {
            OutputView.print("딜러는 16이하라 " + count + "장의 카드를 더 받았습니다.\n");
        }
    }

    private void printDealerAndPlayersWithPoint() {
        GamerDTO gamerDTO = new GamerDTO(dealer.getRawName(), dealer.getRawHoldingCards(),
                dealer.getRawSummationCardPoint());
        GamerOutputView.print(gamerDTO);

        players.forEach(player -> {
            GamerDTO gamerDTO2 = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.getRawSummationCardPoint());
            GamerOutputView.print(gamerDTO2);
        });
    }

    private void printDealerAndPlayersGameResult() {
        Map<GameResult, Integer> dealerGameResultCounts = players.stream()
                .collect(Collectors.groupingBy(player -> GameResultCalculator.calculate(dealer, player),
                        Collectors.summingInt(value -> 1)));
        DealerGameResultDTO dealerGameResultDTO = new DealerGameResultDTO(dealerGameResultCounts);

        List<GameResultDTO> gameResultDTOS = players.stream()
                .map(player -> new GameResultDTO(player.getRawName(), GameResultCalculator.calculate(player, dealer)))
                .toList();

        GameResultOutputView.print(dealerGameResultDTO);
        gameResultDTOS.forEach(GameResultOutputView::print);
    }
}
