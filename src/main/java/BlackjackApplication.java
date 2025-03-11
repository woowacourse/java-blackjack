import controller.BlackjackController;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackApplication {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.run();

        Map<String, Integer> stringKeyMap = new HashMap<>();
        stringKeyMap.put("1", 100);
        stringKeyMap.put("2", 200);
        stringKeyMap.put("3", 300);

        // 변환: String -> Integer
        Map<Integer, Integer> integerKeyMap = stringKeyMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry.getKey()), // 키 변환
                        Map.Entry::getValue // 값 유지
                ));

        System.out.println(integerKeyMap); // {1=100, 2=200, 3=300}
    }
}
