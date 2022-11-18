package bridge.view;

import java.util.List;
import java.util.StringJoiner;

import static bridge.constant.Constants.BridgeUtils.*;
import static bridge.constant.Constants.BridgeUtils.BRIDGE_FINISH;
import static bridge.constant.Constants.Console.*;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 * <p>
 * 제공된 OutputView 클래스를 활용해 구현해야 한다.
 * OutputView의 패키지는 변경할 수 있다.
 * OutputView의 메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
 * 값 출력을 위해 필요한 메서드를 추가할 수 있다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printMap(List<String> topGameBoard, List<String> bottomGameBoard) {
        System.out.println(createMap(topGameBoard));
        System.out.println(createMap(bottomGameBoard));
        printEnter();
    }

    private static StringJoiner createMap(List<String> gameBoard) {
        StringJoiner map = new StringJoiner(BRIDGE_SEPARATOR, BRIDGE_START, BRIDGE_FINISH);

        for (String value : gameBoard) {
            map.add(value);
        }
        return map;
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printFinalGameResult(List<String> topGameBoard, List<String> bottomGameBoard) {
        System.out.println(GAME_RESULT_MESSAGE);
        printMap(topGameBoard, bottomGameBoard);
    }

    public static void printGameStatistics(boolean gameResult, int tryCnt) {
        String isGameWin = LOSE;
        if (gameResult == true) {
            isGameWin = WIN;
        }

        System.out.printf(IS_GAME_WIN_MESSAGE, isGameWin);
        printEnter();
        System.out.printf(TOTAL_TRY_CNT_MESSAGE, tryCnt);
    }

    /**
     * 게임 시작 메시지 출력
     */
    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
        printEnter();
    }

    /**
     * 한칸 띄기
     */
    public static void printEnter() {
        System.out.println("");
    }

    public static void printInputBridgeSizeMessage() {
        System.out.println(INPUT_BRIDGE_SIZE_MESSAGE);
    }

    public static void printInputDirectionMessage() {
        System.out.println(INPUT_DIRECTION_MESSAGE);
    }

    public static void printCheckRetryMessage() {
        System.out.println(CHECK_RETRY_MESSAGE);
    }
}

