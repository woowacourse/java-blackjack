package controller;

import static constant.BlackjackConstant.BUST_BOUND;
import static constant.BlackjackConstant.DEALER_DRAW_BOUND;
import static constant.BlackjackConstant.HIT_DRAW_COUNT;
import static constant.BlackjackConstant.INIT_DRAW_COUNT;

import domain.CardResult;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.ArrayList;
import java.util.List;
import service.BlackjackService;
import service.FinalResult;
import view.InputView;
import view.OutputView;

// FIXME: 컨트롤러에 책임이 너무 많다 -> 적합한 객체로 책임을 분산
public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(final InputView inputView, final OutputView outputView,
                               final BlackjackService blackjackService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = blackjackService;
    }

    public void run() {
        final List<Participant> participantList = addParticipants(); // 플레이어 추가
        final Participants participants = new Participants(participantList);

        final Deck deck = new Deck(); // 카드 덱 초기화

        drawInitCard(participantList, deck, participants); // 카드 최초 뽑기

        final List<Participant> players = doHitAndStand(participants, deck); // 히트 스탠드 처리

        final Participant dealer = drawDealerAdditionalCard(participants, deck); // 딜러 추가 뽑기

        printResult(participants, dealer, players); // 최종 결과 출력
    }

    private List<Participant> addParticipants() {
        final List<Name> playerNames = inputView.readPlayers(); // 플레이어 입력받기
        final List<Participant> participantList = new ArrayList<>();
        for (final Name name : playerNames) {
            participantList.add(
                    new Participant(name, new Hand(new ArrayList<>()), false));
        }
        return participantList;
    }

    private void drawInitCard(final List<Participant> participantList, final Deck deck,
                              final Participants participants) {
        for (final Participant participant : participantList) {
            final List<Card> drawnCards = blackjackService.drawCard(deck, INIT_DRAW_COUNT);
            addHandCard(participant, drawnCards);
        }
        outputView.printInitHandCard(participants); // 뽑은 카드 정보 출력
    }

    private static void addHandCard(final Participant participant, final List<Card> drawnCards) {
        for (final Card drawedCard : drawnCards) {
            participant.addHandCard(drawedCard);
        }
    }

    private List<Participant> doHitAndStand(final Participants participants, final Deck deck) {
        final List<Participant> players = participants.getPlayers();
        for (final Participant player : players) {
            hitAndStand(player, deck);
        }
        outputView.printWhiteLine();
        return players;
    }

    private void hitAndStand(final Participant player, final Deck deck) {
        while (!player.isBust() && player.getScore() != BUST_BOUND) {
            final boolean isHit = isHit(player, deck);
            // 현재 카드 출력하기
            outputView.printCurrentHandCard(player);
            // 스탠드면 끝
            if (!isHit) {
                break;
            }
        }
    }

    private boolean isHit(final Participant player, final Deck deck) {
        final boolean isHit = inputView.readHitOrStand(player.getName());  // 더 받을 지 물어봄
        if (isHit) {
            // 히트인 경우 카드 더 뽑아 추가하기
            final List<Card> drawnCards = blackjackService.drawCard(deck, HIT_DRAW_COUNT);
            addHandCard(player, drawnCards);
        }
        return isHit;
    }

    private Participant drawDealerAdditionalCard(final Participants participants, final Deck deck) {
        final Participant dealer = participants.getDealer();
        while (dealer.getScore() <= DEALER_DRAW_BOUND) {
            outputView.printDealerAdditionalDraw();

            final List<Card> drawnCards = blackjackService.drawCard(deck, HIT_DRAW_COUNT);

            addHandCard(dealer, drawnCards);
        }
        return dealer;
    }

    private void printResult(final Participants participants, final Participant dealer,
                             final List<Participant> players) {
        // 결과 출력 - 패 공개
        final List<CardResult> cardResults = participants.getCardResults();
        outputView.printCardResults(participants);

        // 최종 승패
        final List<FinalResult> finalResults = blackjackService.getFinalResults(dealer, players);
        outputView.printFinalResults(finalResults);
    }
}
