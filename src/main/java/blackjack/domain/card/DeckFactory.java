package blackjack.domain.card;

/**
 * 엑을 생성하는 기능이 생각해보니, 팩토리로 구현될 이유가 있는지도 모르겠네요
 * <p>
 * 그냥 Card 를 생성하는 기능을 가지고 있는 클래스로 만들어도 될 것 같아요 이렇게 바꿔보는 것은 어떻게 생각하시나요
 * <p>
 * public interface CardFactory { Card generate(Shape shape, Symbol symbol); }
 */
public interface DeckFactory {

    Deck generate();
}
