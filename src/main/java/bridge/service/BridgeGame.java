package bridge.service;

import bridge.domain.Bridge;
import bridge.domain.GameBoard;
import bridge.domain.GameResult;

import java.util.ArrayList;
import java.util.List;

import static bridge.constant.Constants.BridgeSign.*;
import static bridge.view.InputView.readMoving;
import static bridge.view.OutputView.printInputDirectionMessage;
import static bridge.view.OutputView.printMap;


/**
 * 다리 건너기 게임을 관리하는 클래스
 * <p>
 * 제공된 BridgeGame 클래스를 활용해 구현해야 한다.
 * BridgeGame에 필드(인스턴스 변수)를 추가할 수 있다.
 * BridgeGame의 패키지는 변경할 수 있다.
 * BridgeGame의 메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
 * 게임 진행을 위해 필요한 메서드를 추가 하거나 변경할 수 있다.
 * <p>
 * ★ BridgeGame 클래스에서 InputView, OutputView 를 사용하지 않는다.
 */
public class BridgeGame {
    GameBoard topGameBoard, bottomGameBoard;
    boolean isGameLose = false;
    int tryCount = 0;


    /**
     * [제공된 메서드]
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    private void move(String nowBridge) {
        String movingDirection = inputMovingDirection();
        String movingResult = getMovingResult(nowBridge, movingDirection);

        checkDirection(movingDirection, movingResult);

        if (movingResult.equals(FAIL)) {
            isGameLose = true;
        }
    }

    public void startMove(Bridge bridge) {
        for (String nowBridge : bridge.getBridge()) {
            move(nowBridge);
            printMap(topGameBoard, bottomGameBoard);

            if (isGameLose) {
                break;
            }
        }
    }

    private String inputMovingDirection() {
        printInputDirectionMessage();
        String movingDirection;

        try {
            movingDirection = readMoving();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            movingDirection = inputMovingDirection();
        }
        return movingDirection;
    }


    private String getMovingResult(String bridgeDirection, String direction) {
        if (direction.equals(bridgeDirection)) {
            return PASS;
        }
        return FAIL;
    }

    private void checkDirection(String movingDirection, String movingResult) {
        if (movingDirection.equals(UP)) {
            topGameBoard.addMovingResult(movingResult);
            bottomGameBoard.addMovingResult(EMPTY);
        } else if (movingDirection.equals(DOWN)) {
            bottomGameBoard.addMovingResult(movingResult);
            topGameBoard.addMovingResult(EMPTY);
        }
    }


    /**
     * [제공된 메서드]
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void resetGame() {
        resetGameValue();
        tryCount++;
    }

    private void resetGameValue() {
        isGameLose = false;
        topGameBoard = new GameBoard(new ArrayList<>());
        bottomGameBoard = new GameBoard(new ArrayList<>());
    }

    public boolean checkIsGameLose() {
        return isGameLose;
    }

    public GameResult getFinalGameResult() {
        List<GameBoard> gameBoards = List.of(topGameBoard, bottomGameBoard);
        return new GameResult(gameBoards, isGameLose, tryCount);
    }
}
