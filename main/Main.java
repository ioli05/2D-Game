package main;

import java.io.IOException;

import fileio.FileSystem;



public class Main {

    protected static int n;
    protected static int m;

    private static Land[][] readMatrix(final FileSystem file) throws
            IOException {



        /*
         * All the elements of this matrix will be the type Land, because
         * we have to store more than just the type of that piece of Land;
         */
        Land[][] matrix = new Land[n][m];

        for (int i = 0; i < n; i++) {
            /*
             * Every line will be read entirely as a String with m characters,
             * each one being the type of element being on i,j position;
             */
            String line;
            line = file.nextWord();
            char[] cLine;
            cLine = line.toCharArray();
            for (int j = 0; j < m; j++) {

               matrix[i][j] = new Land(Character.toString(cLine[j]));

           }
       }
        return matrix;
    }

    private static Player[] readPlayer(final FileSystem file,
            final Land[][] matrix) throws IOException {

        /*
         *In variable p will be stored the number of the players;
         */
        final int p = file.nextInt();

        /*
         * In playerOrder will be stored the order of the players so that
         * we can easily access them and modify their positions;
         */

        Player[] playerOrder = new Player[p];


        for (int i = 0; i < p; i++) {
            /*
             * In variable play, type String, will be stored the initial
             * of every character name and their position on map will have
             * 2 coordinates row and column;
             */
            String play = new String();


            play = file.nextWord();

            int row = file.nextInt();
            int column = file.nextInt();

            /*
             * Using the variable play, the array playerOrder will have
             * a new player of the specified type, and will be placed
             * on the specified indexes row/column;
             */
            if (play.equals("W")) {
                playerOrder[i] = new Wizard();
            }

            if (play.equals("P")) {
                playerOrder[i] = new Pyromancer();
            }

            if (play.equals("R")) {
                playerOrder[i] = new Rogue();
            }

            if (play.equals("K")) {
                playerOrder[i] = new Knight();
            }

            if (matrix[row][column].player1 == null) {
                matrix[row][column].player1 = playerOrder[i];
            } else {

                matrix[row][column].player2 = playerOrder[i];
            }

            playerOrder[i].column = column;
            playerOrder[i].row = row;
            }
        return playerOrder;

    }

    private static void movePlayer(final Player[] playerOrder, final Land[][]
            matrix, final FileSystem file) throws IOException {

        String line;
        line = file.nextWord();


        char[] cLine;
        cLine = line.toCharArray();

        /*
         * For each player, we will read a letter representing the move.
         * If the player is not under the spell of not moving, than
         * the coordinates of the player on matrix will be modified,
         * and so his position on the map too;
         */
        int p = playerOrder.length;
        for (int j = 0; j < p; j++) {

            if (playerOrder[j].hp > 0) {
                if (playerOrder[j].spell != 0) {
                    playerOrder[j].spell--;
                } else {

                    String move = Character.toString(cLine[j]);

                    int row, column;
                    row = playerOrder[j].row;
                    column = playerOrder[j].column;

                    if (move.equals("L")) {
                        column--;
                    }

                    if (move.equals("R")) {
                        column++;
                    }

                    if (move.equals("U")) {
                        row--;
                    }

                    if (move.equals("D")) {
                        row++;
                    }

                    if (row != playerOrder[j].row || column != playerOrder[j].column) {

                        if (matrix[playerOrder[j].row][playerOrder[j].column].player1
                                == playerOrder[j]) {
                            matrix[playerOrder[j].row][playerOrder[j].column].player1
                            = null;

                        }

                        if (matrix[playerOrder[j].row][playerOrder[j].column].player2
                                == playerOrder[j]) {
                            matrix[playerOrder[j].row][playerOrder[j].column].player2
                            = null;

                        }

                        if (matrix[row][column].player1 == null) {
                            matrix[row][column].player1 = playerOrder[j];
                        } else {
                            matrix[row][column].player2 = playerOrder[j];
                        }

                        playerOrder[j].row = row;
                        playerOrder[j].column = column;

                    }

                }
            }
    }
}

    private static void overtimePlayer(Player[] playerOrder, Land[][] matrix) {
        /*
         * We will go through the entire array and modify the hp of each
         * player who has overtime damage and will still be alive;
         */

        final int p = playerOrder.length;
        for (int j = 0; j < p; j++) {

            if (playerOrder[j].dOvertime != 0
                    && playerOrder[j].lvlOvertime > 0
                    && playerOrder[j].hp > 0) {
                playerOrder[j].hp = playerOrder[j].hp
                        - playerOrder[j].dOvertime;
                playerOrder[j].lvlOvertime--;
            }

            if (playerOrder[j].hp <= 0) {

                int row = playerOrder[j].row;
                int column = playerOrder[j].column;
                /*
                 * If the hp is below zero, than we will eliminate the player
                 * from the map;
                 */

                if (matrix[row][column].player1 == playerOrder[j]) {
                    matrix[row][column].player1 = null;
                }
                if (matrix[row][column].player2 == playerOrder[j]) {
                    matrix[row][column].player2 = null;
                }
            }
        }
    }

    private static void battlePlayers(Land[][] matrix, int row, int column) {

                //Battle;

                /*
                 *  The variables x1, x2, x3, and x44 will store the values
                 *  of each ability of player1 and player 2;
                 */

                float x1, x2, x3, x4;
                x1 = matrix[row][column].player1.power1(matrix
                        [row][column].player2, matrix[row][column]);
                x2 = matrix[row][column].player1.power2(matrix
                        [row][column].player2, matrix[row][column]);
                x3 = matrix[row][column].player2.power1(matrix
                        [row][column].player1, matrix[row][column]);
                x4 = matrix[row][column].player2.power2(matrix
                        [row][column].player1, matrix[row][column]);

                // The hp of each player is modified;
                matrix[row][column].player1.hp -= (x3 + x4);
                matrix[row][column].player2.hp -= (x1 + x2);

                //If a player died, than the other one won experience;

                if (matrix[row][column].player1.hp <= 0
                        && matrix[row][column].player2.hp > 0) {

                    //The player2 won the battle;

                    matrix[row][column].player2.
                    xpWinner(matrix[row][column].player1);
                    float newLevel = matrix[row][column].player2.
                            calculateLevel();
                    if (newLevel > matrix[row][column].player2.nrLevels) {
                        matrix[row][column].player2.nrLevels = newLevel;
                        matrix[row][column].player2.hpNew(matrix[row][column].
                                player2);

                    }

                    matrix[row][column].player2.damagePower1 = 0;
                    matrix[row][column].player2.damagePower2 = 0;

                }

                if (matrix[row][column].player2.hp <= 0
                        && matrix[row][column].player1.hp > 0) {

                    //The player1 won the battle;

                    matrix[row][column].player1.
                    xpWinner(matrix[row][column].player2);
                    float newLevel = matrix[row][column].player1.
                            calculateLevel();
                    if (newLevel > matrix[row][column].player1.nrLevels) {
                        matrix[row][column].player1.nrLevels = newLevel;
                        matrix[row][column].player1.hpNew(matrix[row][column].
                                player1);

                    }


                    matrix[row][column].player1.damagePower1 = 0;
                    matrix[row][column].player1.damagePower2 = 0;

                }
                /*
                 *  If the hp is negative, than the player died so it will
                 *  be eliminated from the map;
                 */


                if (matrix[row][column].player1.hp <= 0) {
                    matrix[row][column].player1 = null;
                }

                if (matrix[row][column].player2.hp <= 0) {
                    matrix[row][column].player2 = null;
                }


            }

    public static void main(final String[] args) {

        try {

            FileSystem file = new FileSystem(args[0], args[1]);

            /*
             * In variables n and m will be stored the dimension of matrix, n
             * meaning the number of rows and m the number of columns;
             */

            n = file.nextInt();
            m = file.nextInt();

            Land[][] matrix = readMatrix(file);

            /*
             * In playerOrder array will be stored all players;
             */
            Player[] playerOrder = readPlayer(file, matrix);

            final int p = playerOrder.length;
            /*
             * In variable r will be stored the number of rounds;
             */
            final int r = file.nextInt();
            for (int i = 0; i < r; i++) {
                /*
                 * We move each player, we calculate the overtime damage
                 * for each one of the players and than will be all the
                 * battles;
                 */

                movePlayer(playerOrder, matrix, file);

                overtimePlayer(playerOrder, matrix);

                    for (int row = 0; row < n; row++) {
                        for (int column = 0; column < m; column++) {
                            if (matrix[row][column].player1 != null
                                    && matrix[row][column].player2 != null) {

                                    //Battle;

                                battlePlayers(matrix, row, column);
                            }
                        }

                    }
            }

           for (int i = 0; i < p; i++) {
                if (playerOrder[i].hp <= 0) {
                    file.writeWord(playerOrder[i].namePlayer + " dead");
                    file.writeNewLine();
                } else {

                    file.writeWord(playerOrder[i].namePlayer + " "
                            + (int) playerOrder[i].nrLevels + " "
                            + (int) playerOrder[i].xp + " "
                            + (int) playerOrder[i].hp + " "
                            + playerOrder[i].row + " "
                            + playerOrder[i].column);
                    file.writeNewLine();
                }

            }




         file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }



    }
}
