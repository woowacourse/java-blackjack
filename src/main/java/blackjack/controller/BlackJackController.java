package blackjack.controller;

import blackjack.controller.dto.PlayerCardsDTO;
import blackjack.controller.dto.PlayerResultDTO;
import blackjack.controller.dto.UsersProfitDTO;
import blackjack.controller.dto.UserNameDTO;
import blackjack.domain.UserDrawContinue;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.domain.player.strategy.AllCardsOpenStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    private Cards cards;

    public void play() {
        // TODO: 10줄 이하로 줄이기
        cards = Cards.createAllShuffledCards();
        // TODO: 유저 이름과 배팅 금액으로 생성방식 변경
        // Users users = new Users(InputView.getUsersName());
        Users users = new Users(new ArrayList<>());
        Dealer dealer = new Dealer();
        users.getUsers().forEach(this::betMoney);
        drawTwoCards(users, dealer);
        OutputView.printGiveTwoCardsMessage(getUserCardsDTOs(users), new PlayerCardsDTO(dealer));
        users.getUsers().forEach(this::drawCard);
        dealerDraw(dealer);
        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        OutputView.printFinalCardsMessage(getUserResultDTOs(users), new PlayerResultDTO(dealer));
        OutputView.printFinalProfits(new UsersProfitDTO(users.getProfits(dealer)));
    }

    private void betMoney(User user) {
    }

    private void drawTwoCards(Users users, Dealer dealer) {
        dealer.drawRandomTwoCards(cards);
        users.drawRandomTwoCards(cards);
    }

    private List<PlayerCardsDTO> getUserCardsDTOs(Users users) {
        return users.getUsers().stream()
            .map(PlayerCardsDTO::new)
            .collect(Collectors.toList());
    }

    private List<PlayerResultDTO> getUserResultDTOs(Users users) {
        return users.getUsers().stream()
            .map(PlayerResultDTO::new)
            .collect(Collectors.toList());
    }

    private void drawCard(User user) {
        while (user.isCanDraw()) {
            askDrawContinue(user);
        }
    }

    private void askDrawContinue(User user) {
        String yesOrNo = InputView.getYesOrNo(new UserNameDTO(user));
        if (user.isDrawContinue(new UserDrawContinue(yesOrNo))) {
            user.drawRandomOneCard(cards);
        }
        printCardsWhenDraw(user);
    }

    private void printCardsWhenDraw(User user) {
        if (!user.isDrawStop()) {
            OutputView.printPlayerCardsAndNewLine(new PlayerCardsDTO(user));
        }
    }

    private void dealerDraw(Dealer dealer) {
        while (dealer.isCanDraw()) {
            dealer.drawRandomOneCard(cards);
            OutputView.printDealerDrawCardMessage();
        }
    }
}