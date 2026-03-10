package controller;

import static constant.BlackjackConstant.BUST_BOUND;
import static constant.BlackjackConstant.DEALER_DRAW_BOUND;
import static constant.BlackjackConstant.HIT_DRAW_COUNT;
import static constant.BlackjackConstant.INIT_DRAW_COUNT;

import domain.Card;
import domain.CardDeck;
import domain.CardResult;
import domain.HandCards;
import domain.Name;
import domain.Participant;
import domain.Participants;
import java.util.ArrayList;
import java.util.List;
import service.BlackjackService;
import service.FinalResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(InputView inputView, OutputView outputView, BlackjackService blackjackService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = blackjackService;
    }

    public void run() {
        List<Participant> participantList = addParticipants();
        Participants participants = new Participants(participantList);

        CardDeck cardDeck = CardDeck.initCardDeck();

        drawInitCard(participantList, cardDeck, participants);

        List<Participant> players = doHitAndStand(participants, cardDeck);

        Participant dealer = drawDealerAdditionalCard(participants, cardDeck);

        printResult(participants, dealer, players);
    }

    private List<Participant> addParticipants() {
        List<Name> playerNames = inputView.readPlayers();
        List<Participant> participantList = new ArrayList<>();
        for (Name name : playerNames) {
            participantList.add(
                    new Participant(name, new HandCards(new ArrayList<>()), false));
        }
        return participantList;
    }

    private void drawInitCard(List<Participant> participantList, CardDeck cardDeck, Participants participants) {
        for (Participant participant : participantList) {
            List<Card> drawnCards = blackjackService.drawCard(cardDeck, INIT_DRAW_COUNT);
            addHandCard(participant, drawnCards);
        }
        outputView.printInitHandCard(participants);
    }

    private static void addHandCard(Participant participant, List<Card> drawnCards) {
        for (Card drawedCard : drawnCards) {
            participant.addHandCard(drawedCard);
        }
    }

    private List<Participant> doHitAndStand(Participants participants, CardDeck cardDeck) {
        List<Participant> players = participants.getPlayers();
        for (Participant player : players) {
            hitAndStand(player, cardDeck);
        }
        outputView.printWhiteLine();
        return players;
    }

    private void hitAndStand(Participant player, CardDeck cardDeck) {
        while (!player.isBust() && player.getScore() != BUST_BOUND) {
            boolean isHit = isHit(player, cardDeck);
            outputView.printCurrentHandCard(player);
            if (!isHit) { // 스탠드면 끝
                break;
            }
        }
    }

    private boolean isHit(Participant player, CardDeck cardDeck) {
        boolean isHit = inputView.readHitOrStand(player.getName());  // 더 받을 지 물어봄
        if (isHit) {
            // 히트인 경우 카드 더 뽑아 추가하기
            List<Card> drawnCards = blackjackService.drawCard(cardDeck, HIT_DRAW_COUNT);
            addHandCard(player, drawnCards);
        }
        return isHit;
    }

    private Participant drawDealerAdditionalCard(Participants participants, CardDeck cardDeck) {
        Participant dealer = participants.getDealer();
        while (dealer.getScore() <= DEALER_DRAW_BOUND) {
            outputView.printDealerAdditionalDraw();

            List<Card> drawnCards = blackjackService.drawCard(cardDeck, HIT_DRAW_COUNT);

            addHandCard(dealer, drawnCards);
        }
        return dealer;
    }

    private void printResult(Participants participants, Participant dealer, List<Participant> players) {
        List<CardResult> cardResults = participants.getCardResults();
        outputView.printCardResults(participants);

        List<FinalResult> finalResults = blackjackService.getFinalResults(dealer, players);
        outputView.printFinalResults(finalResults);
    }
}
