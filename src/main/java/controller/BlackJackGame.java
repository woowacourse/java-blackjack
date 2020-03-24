package controller;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
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

    public static BlackJackGame newInstance(Deck deck) {
        return new BlackJackGame(deck);
    }

    public void firstDealOut(Dealer dealer, PlayersInfo playersInfo) {
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            dealer.draw(deck.dealOut());
            playersInfo.draw(deck);
        }
    }

    public void additionalDealOut(Dealer dealer, PlayersInfo playersInfo) {
        playersInfo.getPlayersInfo()
                .forEach((player, bettingMoney) -> playerAdditionalDealOut(player));
        dealerAdditionalDealOut(dealer);
    }

    private void playerAdditionalDealOut(Player player) {
        while (player.isAvailableToDraw() && isYes(UserDto.of(player))) {
            player.draw(deck.dealOut());
            OutputView.printPlayerDealOutResult(UserDto.of(player));
        }
    }

    private boolean isYes(UserDto userDto) {
        String input = InputView.receiveYesOrNoInput(userDto);
        return YesOrNo.isYes(input);
    }

    private void dealerAdditionalDealOut(Dealer dealer) {
        while (dealer.isAvailableToDraw()) {
            dealer.draw(deck.dealOut());
            OutputView.printDealerDealOut(UserDto.of(dealer));
        }
    }
}
