package controller;

import config.CardDeckFactory;
import domain.BlackJack;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Players;
import view.InputUntilValid;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        // 세팅
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();

        // 1. 이름 입력받고 플레이어 생성

        Players players = Players.from(inputView.readPlayersName());
        Dealer dealer = new Dealer(cardDeck);

        // 블랙잭 시작
        BlackJack blackJack = new BlackJack(players, dealer);

        // 2. 딜러와 플레이어에게 카드 나눠주기
        blackJack.hitCardsToParticipant();

        outputView.printParticipant(players, dealer);

        // 3. 카드 더 받을지 물어보기 x 플레이어 수
        blackJack.drawPlayers(() -> InputUntilValid.validatePlayerAnswer(inputView::askPlayerForHitOrStand), outputView::printPlayerDeck);

        // 4. 딜러 카드 받기
        blackJack.drawDealer();

//        // 5. 결과
//        blackJack.calculateResult();
//
//        // 6. 최종 승패
        blackJack.calculateWinner();
    }
}
