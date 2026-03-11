package controller;

import domain.card.Card;
import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.Hand;
import domain.pariticipant.*;

import java.util.ArrayList;
import java.util.List;

import domain.result.MatchResult;
import view.InputView;
import view.OutputView;

import static constant.BlackjackConstant.*;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardShuffler cardShuffler;

    public BlackjackController(InputView inputView, OutputView outputView, CardShuffler cardShuffler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardShuffler = cardShuffler;
    }

    public void run() {
        Participants participants = addParticipants();

        Deck deck = Deck.initCardDeck(cardShuffler);

        drawInitCard(participants, deck);

        doHitAndStand(participants.getPlayers(), deck);

        drawDealerAdditionalCard(participants.getDealer(), deck);

        printResult(participants);
    }

    private Participants addParticipants() {
        List<Name> playerNames = inputView.readPlayers();

        List<Player> players = new ArrayList<>();
        for (Name name : playerNames) {
            BettingAmount bettingAmount = inputView.readBettingAmount(name.name()); // 베팅 금액 입력 받기
            players.add(new Player(name, new Hand(new ArrayList<>()), bettingAmount));
        }
        return new Participants(new Players(players));
    }

    private void drawInitCard(Participants participants, Deck deck) {
        participants.drawInitialCards(deck, cardShuffler);
        outputView.printInitHandCard(participants);
    }

    private static void addHandCard(Participant participant, List<Card> drawnCards) {
        for (Card drawedCard : drawnCards) {
            participant.addHandCard(drawedCard);
        }
    }

    private void doHitAndStand(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitAndStand(player, deck);
        }
        outputView.printWhiteLine();
    }

    private void hitAndStand(Player player, Deck deck) {
        while (!player.isBust() && player.getScore() != BUST_BOUND) {
            boolean isHit = doHit(player, deck);
            outputView.printCurrentHandCard(player);
            if (!isHit) { // 스탠드면 끝
                break;
            }
        }
    }

    private boolean doHit(Player player, Deck deck) {
        boolean isHit = inputView.readHitOrStand(player.getName());  // 더 받을 지 물어봄
        if (isHit) {
            // 히트인 경우 카드 더 뽑아 추가하기
            player.hitCard(deck);
        }
        return isHit;
    }

    private void drawDealerAdditionalCard(Dealer dealer, Deck deck) {
        while (dealer.shouldHit()) {
            outputView.printDealerAdditionalDraw();
            dealer.drawAdditionalCard(deck);
        }
    }

    private void printResult(Participants participants) {
        outputView.printCardResults(participants);
        MatchResult matchResult = participants.calculateMatchResult();
        outputView.printFinalResults(matchResult);
    }
}
