/*
 * Copyright (c) 2019. http://devonline.academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package academy.devonline.tictactoe.component;


import academy.devonline.tictactoe.model.GameTable;

import java.util.Random;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public class Game {

    private final DataPrinter dataPrinter;
    private final ComputerMove computerMove;
    private final UserMove userMove;
    private final WinnerVerifier winnerVerifier;
    private final DrawVerifier drawVerifier;

    public Game(final DataPrinter dataPrinter,
                final ComputerMove computerMove,
                final UserMove userMove,
                final WinnerVerifier winnerVerifier,
                final DrawVerifier drawVerifier) {
        this.dataPrinter = dataPrinter;
        this.computerMove = computerMove;
        this.userMove = userMove;
        this.winnerVerifier = winnerVerifier;
        this.drawVerifier = drawVerifier;
    }

    public void play() {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:");
        dataPrinter.printMappingTable();
        final GameTable gameTable = new GameTable();

        if (new Random().nextBoolean()) {
            computerMove.make(gameTable);
            dataPrinter.printGameTable(gameTable);
        }

        final Move[] moves = {userMove, computerMove};
        while (true) {
            boolean gameOver = false;

            for (final Move move : moves) {
                move.make(gameTable);
                dataPrinter.printGameTable(gameTable);

                if (move instanceof UserMove) {
                    if (winnerVerifier.isUserWin(gameTable)) {
                        System.out.println("YOR WIN!");
                        printGameOver();
                        return;
                    }
                } else {
                    if (winnerVerifier.isComputerWin(gameTable)) {
                        System.out.println("COMPUTER WIN!");
                        printGameOver();
                        return;
                    }
                }
                if (drawVerifier.isDraw(gameTable)) {
                    System.out.println("Sorry, DRAW!");
                    printGameOver();
                    return;
                }
            }

        }
    }

    private void printGameOver() {
        System.out.println("GAME OVER!");
    }
}

