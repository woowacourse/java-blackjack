package blackjack.controller;

import blackjack.manager.CardsGenerator;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Players;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import java.util.List;

public class BlackjackController {

    public void run() {
        CardsGenerator cardsGenerator = new CardsGenerator();
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(cardsGenerator);

        Deck deck = blackJackInitManager.generateDeck();
        Players players = blackJackInitManager.savePlayers(List.of(), Hand::new);
        Dealer dealer = blackJackInitManager.saveDealer(Hand::new);

        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck);

        // 카드를 init 한다. (이 것은 매니저에서 하든, 컨트롤러에서 하든, 잘 생각해보자)
//        for (Player player : players.getPlayers()){
//            while ()
//                if (// 님 카드 받을 수 있음?)
//            // inputView 가고
//            // y, n이면 process 매
//
//
//        }
        // 사람 이름 입력을 받는다

        // players 저장

        // 카드 두 장 분배

        // 딜러 카드 한 장
        // 플레이어들 카드 두장 출력

        // 플레이어 한명씩 카드 받는지 여부 (이 때, Busted되거나 N을 입력하면 기회 뺏김)

        // 딜러가 더 받는지 여부  (더 받았으면 출력)

        // 딜러와 플레이어들의 카드들과 결과 값 출력 (이때 결과는 21에 가까운 최적의 값으로 선택되어야함)

        // 승패 출력

    }
}
