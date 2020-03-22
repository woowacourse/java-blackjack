package controller;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.PlayersInfo;
import util.YesOrNo;
import view.InputView;
import view.OutputView;
import view.dto.UserDto;

public class BlackJackGame {

    private static final int FIRST_CARD_COUNT = 2;

    private final Deck deck;

    private BlackJackGame(Deck deck) {
        this.deck = deck;
    }

    public static BlackJackGame setDeck(Deck deck) {
        return new BlackJackGame(deck);
    }

    public void firstDealOut(Dealer dealer, PlayersInfo playersInfo) {
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            dealer.draw(deck);
            playersInfo.draw(deck);
        }
    }

    public void additionalDealOut(Dealer dealer, PlayersInfo playersInfo) {
        playersInfo.additionalDealOut(deck, this::isYes, OutputView::printPlayerDealOutResult);
        dealer.additionalDealOut(deck, OutputView::printDealerDealOut);
    }

    private boolean isYes(UserDto userDto) {
        String input = InputView.receiveYesOrNoInput(userDto);
        return YesOrNo.isYes(input);
    }
}
