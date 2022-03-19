package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitDto;

import java.util.List;

import static blackjack.domain.Game.DEALER_NAME;

public class Participants {

    private static final int PROFIT_REVERSE = -1;

    private final Users users;
    private final Dealer dealer;

    public Participants(Users users, Dealer dealer) {
        this.users = users;
        this.dealer = dealer;
    }

    public List<String> getUserNames() {
        return users.getUserNames();
    }

    public void betting(String userName, int money) {
        users.findUserByName(userName).betting(money);
    }

    public void receiveCard(String name, Deck deck) {
        if (name.equals(DEALER_NAME)) {
            dealer.receiveCard(deck);
            return;
        }
        users.findUserByName(name).receiveCard(deck);
    }

    public boolean checkUserBust(String name) {
        return users.findUserByName(name).isBust();
    }

    public boolean checkDealerScoreStandard() {
        return dealer.checkUnderScoreStandard();
    }

    public ParticipantDto createDealerCardInfoDto() {
        return ParticipantDto.of(dealer.getName(), dealer.getHoldingCardsWithoutHidden());
    }

    public ParticipantDto createUserCardInfoDto(String name) {
        return ParticipantDto.of(name, users.findUserByName(name).getHoldingCards());
    }

    public ParticipantDto createDealerCardAndScoreDto() {
        return ParticipantDto.of(dealer.getName(), dealer.getHoldingCards(), dealer.score());
    }

    public ParticipantDto createUserCardAndScoreDto(String name) {
        return ParticipantDto.of(name, users.findUserByName(name).getHoldingCards(), users.findUserByName(name).score());
    }

    public ProfitDto createDealerProfitDto() {
        return new ProfitDto(dealer.getName(), PROFIT_REVERSE * users.getTotalProfit(dealer.score()));
    }

    public ProfitDto createUserProfitDto(String name) {
        return new ProfitDto(name, users.findUserByName(name).calculateProfit(dealer.score()));
    }

    public void changeUserStateToStand(String userName) {
        users.findUserByName(userName).changeToStand();
    }
}
