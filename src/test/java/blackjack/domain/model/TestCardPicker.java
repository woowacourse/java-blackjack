package blackjack.domain.model;

import blackjack.domain.CardPicker;

public class TestCardPicker implements CardPicker {

    @Override
    public int pickIndex(int size){
        return 0;
    }
}
