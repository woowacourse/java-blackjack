package view;

import dto.ResponsePlayerDTO;

import java.util.List;

public class OutputView {
    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialResult(List<ResponsePlayerDTO> result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size() - 1; i++) {
            sb.append(result.get(i).getName());
            sb.append(",");
        }
        sb.append(result.get(result.size() - 1).getName());
        System.out.println(sb.toString() + "에게 2장의 카드를 나누었습니다.");
        System.out.println(result.get(0).getName() + "카드: " + result.get(0).getCardInfo().substring(0,
                result.get(0).getCardInfo().indexOf(",")));
        for (int i = 1; i < result.size(); i++) {
            System.out.println(result.get(i).getName() + "카드: " + result.get(i).getCardInfo());
        }
        System.out.println();
    }
}
