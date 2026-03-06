package blackjack.controller;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.TrumpCard;
import blackjack.dto.DealResult;
import blackjack.dto.GameResult;
import blackjack.dto.PlayerHandResult;
import blackjack.service.BlackjackGame;
import blackjack.service.RandomShuffleStrategy;
import blackjack.util.Parser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    private final BlackjackGame blackjackGame;

    public BlackjackController(BlackjackGame blackjackGame) {
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        List<String> names = inputName();
        Players players = blackjackGame.generatePlayers(names);
        Dealer dealer = blackjackGame.generateDealer();
        Deck deck = initDeck();

        // 2장씩 나눠주기
        players = blackjackGame.deal(players, deck);
        dealer = blackjackGame.deal(dealer, deck);
        OutputView.printDealResult(DealResult.from(players, dealer));

        // 플레이어 턴
        List<Player> resultPlayer = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            player = playPlayersTurn(player, deck);
            resultPlayer.add(player);
        }

        players = Players.of(resultPlayer);
        // 딜러 턴
        while (dealer.shouldHit()) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            TrumpCard card = deck.draw();
            dealer = dealer.receive(card);
        }

        OutputView.printGameResult(GameResult.from(players, dealer));

        // 승패 계산 및 출력
        System.out.println("\n## 최종 승패");
        int dealerWinCount = 0;
        int dealerLoseCount = 0;

        for (Player player : players.getPlayers()) {
            if (isDealerWin(dealer, player)) {
                dealerWinCount++;
            }
            if (isPlayerWin(dealer, player)) {
                dealerLoseCount++;
            }
        }

        OutputView.printDealerFinalResult(dealerWinCount, dealerLoseCount);

        for (Player player : players.getPlayers()) {
            String result = calculatePlayerResult(dealer, player);
            OutputView.printPlayerFinalResult(player.name(), result);
        }

    }


    private Player playPlayersTurn(Player player, Deck deck) {
        while (player.canHit()) {
            String answer = InputView.readYesOrNo(player.name());
            Parser.notEmpty(answer);
            if ("y".equals(answer)) {
                TrumpCard newCard = deck.draw();
                player = player.receiveCard(newCard);
                OutputView.printCurrentPlayerHand(PlayerHandResult.from(player));
            } else if ("n".equals(answer)) {
                break;
            }
        }
        return player;
    }

    private List<String> inputName() {
        String input = readPlayNames();
        Parser.notEmpty(input);
        return splitDelimiter(input);
    }

    private Deck initDeck() {
        return Deck.create(new RandomShuffleStrategy());
    }

    private String calculatePlayerResult(Dealer dealer, Player player) {
        int dealerScore = dealer.score();
        int playerScore = player.score();

        // 플레이어가 버스트
        if (playerScore > 21) {
            return "패";
        }

        // 딜러가 버스트
        if (dealerScore > 21) {
            return "승";
        }

        // 둘 다 21 이하일 때
        if (dealerScore > playerScore) {
            return "패";
        }

        if (dealerScore < playerScore) {
            return "승";
        }

        return "무";
    }

    private boolean isDealerWin(Dealer dealer, Player player) {
        int dealerScore = dealer.score();
        int playerScore = player.score();

        // 플레이어 버스트
        if (playerScore > 21) {
            return true;
        }

        // 딜러 버스트
        if (dealerScore > 21) {
            return false;
        }

        // 둘 다 21 이하
        return dealerScore > playerScore;
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        int dealerScore = dealer.score();
        int playerScore = player.score();

        // 플레이어 버스트
        if (playerScore > 21) {
            return false;
        }

        // 딜러 버스트
        if (dealerScore > 21) {
            return true;
        }

        // 둘 다 21 이하
        return playerScore > dealerScore;
    }

}
