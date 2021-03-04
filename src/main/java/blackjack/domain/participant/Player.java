package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Response;
import blackjack.domain.ResultType;

public class Player extends Participant {

    public Player(String name, Deck deck) {
        super(name, deck);
    }

    @Override
    public void drawCard(Deck deck) {
        if (!isContinue()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
        }
        getHand().addCard(deck.draw());
        if (isBust()) {
            cannotDraw();
        }
    }

    public void willContinue(Response response, Deck deck) {
        if (!response.getHitStatus()) {
            cannotDraw();
        }
    }

    public ResultType match(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSE;
        }
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return matchByScore(dealer);
    }

    private ResultType matchByScore(Dealer dealer) {
        return ResultType.getResultType(getScore() - dealer.getScore());
    }


}
