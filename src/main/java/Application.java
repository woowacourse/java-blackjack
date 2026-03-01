import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Integer> cards = new ArrayList<>();

        // 첫번째 테스트
        cards.add(10);
        cards.add(12);
        Hand hand = new Hand(cards);
        boolean burst = hand.isBurst();
        System.out.println(burst);


        // 두번째 테스트
        cards.clear();
        cards.add(2);
        cards.add(3);
        hand = new Hand(cards);
        burst = hand.isBurst();
        System.out.println(burst);


        // 세번째 테스트
        cards.clear();
        cards.add(21);
        hand = new Hand(cards);
        burst = hand.isBurst();
        System.out.println(burst);


        // 네번째 테스트
        cards.clear();
        cards.add(2);
        cards.add(3);
        cards.add(4);
        cards.add(5);
        hand = new Hand(cards);
        burst = hand.isBurst();
        System.out.println(burst);

        // 다섯번째 테스트
        cards.clear();
        cards.add(10);
        cards.add(1);
        cards.add(13);
        hand = new Hand(cards);
        burst = hand.isBurst();
        System.out.println(burst);

        // 여섯번째 테스트
        cards.clear();
        cards.add(1);
        cards.add(1);
        cards.add(5); // 17 이어야함.
        hand = new Hand(cards);
        burst = hand.isBurst();
        System.out.println(burst);
        System.out.println(hand.getTotal());

        // 사실: 케이스를 6개로 늘리면서 Hand 클래스를 수정해야 했음
        // 문제: ACE가 1일 때와 11일 때에 다르게 처리해야 함.
    }
}
