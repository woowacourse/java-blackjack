package controller;

import static constant.BlackjackConstant.BUST_BOUND;
import static constant.BlackjackConstant.DEALER_DRAW_BOUND;
import static constant.BlackjackConstant.HIT_DRAW_COUNT;
import static constant.BlackjackConstant.INIT_DRAW_COUNT;

import domain.Card;
import domain.CardDeck;
import domain.CardResult;
import domain.HandCards;
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
        List<String> strPlayers = inputView.readPlayers(); // 플레이어 입력받기

        // 플레이어 추가
        List<Participant> participantList = new ArrayList<>();
        for (String name : strPlayers) {
            participantList.add(
                    new Participant(name, new HandCards(new ArrayList<>()), false));
        }
        Participants participants = new Participants(participantList);

        CardDeck cardDeck = CardDeck.initCardDeck(); // 카드 덱 초기화

        // 카드 최초 뽑기
        for (Participant participant : participantList) {
            List<Card> drawnCards = blackjackService.drawCard(cardDeck, INIT_DRAW_COUNT);
            addHandCard(participant, drawnCards);
        }

        // 뽑은 카드 정보 출력
        outputView.printInitHandCard(participants);

        // 히트 & 스탠드
        // 버스트이거나 21이면 자동 스탠드
        List<Participant> players = participants.getPlayers();
        for (Participant player : players) {
            hitAndStand(player, cardDeck);
        }
        outputView.printWhiteLine();

        // 딜러
        Participant dealer = participants.getDealer();
        while (dealer.getScore() <= DEALER_DRAW_BOUND) {
            // 16이하라 카드 뽑는다 출력
            outputView.printDealerAdditionalDraw();

            List<Card> drawnCards = blackjackService.drawCard(cardDeck, HIT_DRAW_COUNT);

            addHandCard(dealer, drawnCards);
        }

        // 결과 출력 - 패 공개
        List<CardResult> cardResults = participants.getCardResults();
        outputView.printCardResults(participants);

        // 최종 승패
        List<FinalResult> finalResults = blackjackService.getFinalResults(dealer, players);
        outputView.printFinalResults(finalResults);
    }

    private void hitAndStand(Participant player, CardDeck cardDeck) {
        while (!player.isBust() && player.getScore() != BUST_BOUND) {
            boolean isHit = isHit(player, cardDeck);
            // 현재 카드 출력하기
            outputView.printCurrentHandCard(player);
            // 스탠드면 끝
            if (!isHit) {
                break;
            }
        }
    }

    private boolean isHit(Participant player, CardDeck cardDeck) {
        // 더 받을 지 물어봄
        boolean isHit = inputView.readHitOrStand(player.getName());
        if (isHit) {
            // 히트인 경우 카드 더 뽑아 추가하기
            List<Card> drawnCards = blackjackService.drawCard(cardDeck, HIT_DRAW_COUNT);
            addHandCard(player, drawnCards);
        }
        return isHit;
    }

    private static void addHandCard(Participant participant, List<Card> drawnCards) {
        for (Card drawedCard : drawnCards) {
            participant.addHandCard(drawedCard);
        }
    }
}
