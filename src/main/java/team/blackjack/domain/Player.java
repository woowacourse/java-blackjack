package team.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player implements Participant {
    private final String name;
    private final List<Hand> hands;

    public Player(String name) {
        this.name = name;
        this.hands = initHands();
    }

    public List<Hand> getHands() {
        return List.copyOf(hands);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getCardInAllHands(){
        return hands.stream()
                .map(Hand::getCards)
                .flatMap(List::stream)
                .map(Card::getCardName)
                .toList();
    }

    /**
     * TODO: 추후 기능 확장시 한 라운드에 여러 개의 hand가 생기는 경우, 해당 메소드 수정 필요.
     *
     * @return
     */
    public int getScore() {
        return this.hands.getFirst().getScore();
    }

    @Override
    public void hit(Card card) {
        this.hands.getFirst().addCard(card);
    }

    private List<Hand> initHands() {
        List<Hand> hands = new ArrayList<>();
        hands.add(new Hand());
        return hands;
    }
}
