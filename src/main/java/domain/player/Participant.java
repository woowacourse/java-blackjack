package domain.player;

import domain.card.Card;
import domain.vo.Cost;
import domain.vo.Name;

import java.util.List;

public class Participant {
    private final Name name;
    private final ParticipantStatus participantStatus = new ParticipantStatus();

    public Participant(Name name) {
        this.name = name;
    }

    public void addCard(Card card) {
        participantStatus.addCard(card);
    }

    public boolean isBust() {
        return participantStatus.isBust();
    }

    public boolean isBlackJack() {
        return participantStatus.isBlackJack();
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCards() {
        return participantStatus.getCards();
    }

    public int getCardSum() {
        return participantStatus.getCardSum();
    }

    public WinStatus matchResult(int score) {
        return participantStatus.matchResult(score);
    }

    public int calculateProfit(WinStatus winStatus) {
        return participantStatus.calculateProfit(winStatus);
    }

    public void addCost(int money) {
        participantStatus.addCost(money);
    }

    public void setCost(Cost cost) {
        participantStatus.setCost(cost);
    }

    public int getCost() {
        return participantStatus.getCost();
    }
}
