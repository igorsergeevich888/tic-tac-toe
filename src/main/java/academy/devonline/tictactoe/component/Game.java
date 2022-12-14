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


import academy.devonline.tictactoe.model.game.GameTable;
import academy.devonline.tictactoe.model.game.Player;

import java.util.Random;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public class Game {

    private final DataPrinter dataPrinter;
    private final Player player1;

    private final Player player2;
    private final WinnerVerifier winnerVerifier;
    private final DrawVerifier drawVerifier;

    private final GameOverHandler gameOverHandler;

    private final boolean canSecondPlaterMakeFirstMove;

    public Game(final DataPrinter dataPrinter,
                final Player player1,
                final Player player2,
                final WinnerVerifier winnerVerifier,
                final DrawVerifier drawVerifier,
                final GameOverHandler gameOverHandler, final boolean canSecondPlaterMakeFirstMove) {
        this.dataPrinter = dataPrinter;
        this.player1 = player1;
        this.player2 = player2;
        this.winnerVerifier = winnerVerifier;
        this.drawVerifier = drawVerifier;
        this.gameOverHandler = gameOverHandler;
        this.canSecondPlaterMakeFirstMove = canSecondPlaterMakeFirstMove;
    }

    public void play() {

        dataPrinter.printInstructions();
        final GameTable gameTable = new GameTable();

        if (canSecondPlaterMakeFirstMove && new Random().nextBoolean()) {
            player2.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
        }


        final Player[] players = {player1, player2};
        while (true) {
            for (final Player player : players) {
                player.makeMove(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (winnerVerifier.isWinner(gameTable, player)) {
                    dataPrinter.printInfoMessage(player.toString() + "WIN!");
                    gameOverHandler.gameOver();
                    return;
                }
                if (drawVerifier.isDraw(gameTable)) {
                    dataPrinter.printInfoMessage("Sorry, DRAW!");
                    gameOverHandler.gameOver();
                    return;
                }

            }
        }
    }


}

