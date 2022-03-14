package model;

import static model.Dealer.DEALER_NAME;
import static model.card.Cards.BLACK_JACK_SCORE;

import model.cardGettable.EveryCardsGettable;

public class Player extends Participator {

    public Player(String playerName) {
        super(playerName);
        checkNameIsDealer(playerName);
        this.cardsGettableStrategy = new EveryCardsGettable();
    }

    private void checkNameIsDealer(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("이름 입력 실패 : 딜러라는 이름은 사용할 수 없습니다.");
        }
    }

    @Override
    public boolean canReceiveCard() {
        return cards.getSum() < BLACK_JACK_SCORE;
    }

    public Result matchWith(Dealer dealer) {
        return Result.of(this.cards, dealer.cards);
    }
}
