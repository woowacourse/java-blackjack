package controller;

import domain.*;
import util.CardsCreator;
import util.Parser;
import view.InputView;
import view.OutputView;
import view.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    void start() {
        Random random = new Random();
        // Deck 생성
        Deck deck = new Deck(CardsCreator.createLinkedCards());
        // 플레이어 이름 받기
        List<String> playerNames = Parser.parse(inputView.readPlayerName());
        List<Player> players = createPlayers(playerNames, deck, random);
        Dealer dealer = new Dealer(getBlackjackHand(deck, random));

        outputView.drawCard(dealer.getName(), playerNames);
        Participants participants = new Participants(players, dealer);
        // 카드 나눠줌
        // 각 플레이어 hands 출력
        List<String> dealerCards = cardToString(participants.getDealer().getBlackjackHand().getHands());
        outputView.showCard(participants.getDealer().getName(), dealerCards);
        for (Player player : players) {
            List<Card> hands = player.getBlackjackHand().getHands();
            outputView.showCard(player.getName(), cardToString(hands));
        }
        // 각 플레이어마다 더 받을지
        // 플레이어 HIT할지
        // HIT한 후의 Hands 출력
        for (Player player : players) {
            String name = player.getName();
            if (BlackjackHand.isBurst(player.getBlackjackHand().getTotalScore())
                    || !inputView.readNeedToHit(name)) continue;
            Card card = deck.drawCard(getRandomIdx(deck, random));
            player.addHand(card);
            outputView.showCard(name, cardToString(player.getBlackjackHand().getHands()));
        }
        // 딜러 HIT 여부에 따라 hit
        while(dealer.needsToHit()){
            Card card = deck.drawCard(getRandomIdx(deck, random));
            dealer.addHand(card);

        }
        // 모든 상태 출력
        dealerCards = cardToString(participants.getDealer().getBlackjackHand().getHands());
        outputView.showCardsAndScore(participants.getDealer().getName(), dealerCards, dealer.getBlackjackHand().getTotalScore());
        for (Player player : players) {
            List<Card> hands = player.getBlackjackHand().getHands();
            outputView.showCardsAndScore(player.getName(), cardToString(hands), player.getBlackjackHand().getTotalScore());
        }
        // 최종 결과
        // 승패 로직
        int dealerTotalScore = dealer.getBlackjackHand().getTotalScore();


        for (Player player : players) {
            int totalScore = player.getBlackjackHand().getTotalScore();
            Result result;
            if (dealerTotalScore > totalScore) result = Result.WIN;
            else if (dealerTotalScore == totalScore) {
                result = Result.DRAW;
            } else result = Result.LOSE;
            outputView.showResultStatistics(player.getName(), result.getDisplayName());
        }




    }


    private List<String> cardToString(List<Card> hands) {
        return hands.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

    }

    private List<Player> createPlayers(List<String> playerNames, Deck deck, Random random) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            BlackjackHand blackjackHand = getBlackjackHand(deck, random);

            Player player = new Player(playerName, blackjackHand);
            players.add(player);
        }
        return players;
    }


    private BlackjackHand getBlackjackHand(Deck deck, Random random) {
        Card card1 = deck.drawCard(getRandomIdx(deck, random));
        Card card2 = deck.drawCard(getRandomIdx(deck, random));

        return new BlackjackHand(List.of(card1, card2));
    }

    private static int getRandomIdx(Deck deck, Random random) {
        return random.nextInt(deck.getSize());
    }
}
