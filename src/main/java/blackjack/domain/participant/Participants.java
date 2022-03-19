package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitDto;

import java.util.List;

import static blackjack.domain.Game.DEALER_NAME;

public class Participants {

    private final Users users;
    private final Dealer dealer;

    public Participants(Users users, Dealer dealer) {
        this.users = users;
        this.dealer = dealer;
    }

    public List<String> getUserNames() {
        return users.getUserNames();
    }

    public void betting(String name, int money) {
        users.betting(name, money);
    }

    public void receiveCard(String name, Deck deck) {
        if (name.equals(DEALER_NAME)) {
            dealer.receiveCard(deck);
            return;
        }
        users.receiveCard(name, deck);
    }

    public boolean checkUserBust(String name) {
        return users.isBust(name);
    }

    public boolean checkDealerScoreStandard() {
        return dealer.checkUnderScoreStandard();
    }

    public ParticipantDto createDealerCardInfoDto() {
        return ParticipantDto.of(dealer.getName(), dealer.getHoldingCardsWithoutHidden());
    }

    public ParticipantDto createUserCardInfoDto(String name) {
        return ParticipantDto.of(name, users.holdingCards(name));
    }

    public ParticipantDto createDealerCardAndScoreDto() {
        return ParticipantDto.of(dealer.getName(), dealer.getHoldingCards(), dealer.score());
    }

    public ParticipantDto createUserCardAndScoreDto(String name) {
        return ParticipantDto.of(name, users.holdingCards(name), users.score(name));
    }

    public ProfitDto createDealerProfitDto(List<Double> userProfits) {
        return new ProfitDto(dealer.getName(), dealer.calculateProfit(userProfits));
    }

    public ProfitDto createUserProfitDto(String name) {
        return new ProfitDto(name, users.profit(name, dealer.score()));
    }

    public void changeUserStateToStand(String name) {
        users.changeToStand(name);
    }
}
