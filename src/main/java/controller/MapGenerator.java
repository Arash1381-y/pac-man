package controller;

import java.util.*;

public class MapGenerator {
    public static String getMaze() {
        return makeMaze();
    }

    private static String makeMaze() {
        char[][] mazeChart = new char[2 * 13 + 1][2 * 13 + 1];
        initializeMaze(mazeChart, 13, 13);
        ArrayList<Integer> currentPlace = new ArrayList<>(2);
        ArrayList<ArrayList<Integer>> stacks = new ArrayList<>();

        currentPlace.add(0);
        currentPlace.add(1);

        finder(mazeChart, 2 * 13 + 1, 2 * 13 + 1,
                currentPlace, stacks);

        int counter = 90;
        StringBuilder mazeToString = new StringBuilder();
        for (char[] chars : mazeChart) {
            for (char aChar : chars) {
                if (aChar == '*' || aChar == '0') {
                    mazeToString.append('0');
                } else {
                    mazeToString.append(' ');
                }
            }
        }
        Random random = new Random();
        ArrayList<Integer> randoms = new ArrayList<>();


/*        for (int i = 0; i < counter; i++) {
            int randomHouse = random.nextInt(27 * 27);
            if (randoms.contains(randomHouse)) {
                i--;
            } else {
                if (mazeToString.charAt(randomHouse) == '0') {
                    i--;
                } else {
                    mazeToString.replace(randomHouse, randomHouse, "0");
                    randoms.add(randomHouse);
                }

            }
        }*/
        return mazeToString.toString();
    }

    public static void initializeMaze(char[][] mazeChart, int row, int column) {
        for (int i = 0; i < 2 * row + 1; i++) {
            for (int j = 0; j < 2 * column + 1; j++) {
                mazeChart[i][j] = '1';
            }
        }
    }

    public static void finder(char[][] mazeChart, int row, int column,
                              ArrayList<Integer> currentPlace, ArrayList<ArrayList<Integer>> stacks) {


        firstMove(mazeChart, currentPlace, stacks);
        // marking exit place
        mazeChart[row - 1][column - 2] = 'e';

        List<Integer> randomNumberMaker = Arrays.asList(1, 2, 3, 4);
        Collections.shuffle(randomNumberMaker);

        ArrayList<Integer>[] neighbors = new ArrayList[4];
        neighborInitialize(currentPlace, neighbors);


        do {
            boolean findNextHouse;
            updateNeighborLoc(currentPlace, neighbors[0], neighbors[1], neighbors[2], neighbors[3]);
            ArrayList<Integer> randLoc = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            findNextHouse = isFindNextHouse(mazeChart, row, column, currentPlace, neighbors, randLoc);
            currentPlace = updateLoc(currentPlace, stacks, findNextHouse);
        }
        while (stacks.size() != 0);

    }

    private static boolean isFindNextHouse(char[][] mazeChart, int row, int column, ArrayList<Integer> currentPlace,
                                           ArrayList<Integer>[] neighbors, ArrayList<Integer> randLoc) {
        for (int i = 0; i < 4; i++) {
            Collections.shuffle(randLoc);
            if (randLoc.get(0) == 1 && currentPlace.get(0) > 1) {
                if (mazeChart[neighbors[0].get(0)][neighbors[0].get(1)] != '*') {
                    updateToUpperNeighbor(mazeChart, currentPlace, neighbors[0]);
                    return true;
                } else {
                    randLoc.remove(0);
                }
            } else if (randLoc.get(0) == 2 && currentPlace.get(1) < column - 2) {
                if (mazeChart[neighbors[1].get(0)][neighbors[1].get(1)] != '*') {
                    updateToRightNeighbor(mazeChart, currentPlace, neighbors[1]);
                    return true;

                } else {
                    randLoc.remove(0);
                }
            } else if (randLoc.get(0) == 3 && currentPlace.get(0) < row - 2) {
                if (mazeChart[neighbors[2].get(0)][neighbors[2].get(1)] != '*') {
                    updateToDownNeighbor(mazeChart, currentPlace, neighbors[2], currentPlace.get(0) + 1, currentPlace.get(1));
                    return true;

                } else {
                    randLoc.remove(0);
                }
            } else {
                if (currentPlace.get(1) > 1) {
                    if (mazeChart[neighbors[3].get(0)][neighbors[3].get(1)] != '*') {
                        updateToLeftNeighbor(mazeChart, currentPlace, neighbors[3], currentPlace.get(0), currentPlace.get(1) - 1);
                        return true;

                    } else {
                        randLoc.remove(0);
                    }
                } else {
                    randLoc.remove(0);
                }
            }
        }
        return false;
    }

    private static void neighborInitialize(ArrayList<Integer> currentPlace, ArrayList<Integer>[] neighbors) {
        neighbors[0] = new ArrayList<>(2);
        firstLocOfNeighbor(neighbors, 0, currentPlace.get(0) - 2, currentPlace.get(1));

        neighbors[1] = new ArrayList<>(2);
        firstLocOfNeighbor(neighbors, 1, currentPlace.get(0), currentPlace.get(1) + 2);

        neighbors[2] = new ArrayList<>(2);
        firstLocOfNeighbor(neighbors, 2, currentPlace.get(0) + 2, currentPlace.get(1));

        neighbors[3] = new ArrayList<>(2);

        firstLocOfNeighbor(neighbors, 3, currentPlace.get(0), currentPlace.get(1) - 2);
    }

    private static void firstLocOfNeighbor(ArrayList<Integer>[] testing, int LocationOfNeighbor,
                                           int numberForRow, Integer numberForColumn) {
        testing[LocationOfNeighbor].add(numberForRow);
        testing[LocationOfNeighbor].add(numberForColumn);
    }

    private static void firstMove(char[][] mazeChart, ArrayList<Integer> currentPlace,
                                  ArrayList<ArrayList<Integer>> stacks) {
        mazeChart[0][currentPlace.get(1)] = 'e';
        currentPlace.set(0, 1);
        ArrayList<Integer> currentPlaceDeepCopy = new ArrayList<>(currentPlace);
        stacks.add(currentPlaceDeepCopy);
        mazeChart[currentPlace.get(0)][currentPlace.get(1)] = '*';
    }

    private static ArrayList<Integer> updateLoc(ArrayList<Integer> currentPlace,
                                                ArrayList<ArrayList<Integer>> stacks, boolean find) {
        if (find) {
            ArrayList<Integer> currentPlaceDeepCopy = new ArrayList<>(currentPlace);
            stacks.add(currentPlaceDeepCopy);
        } else {
            currentPlace = new ArrayList<>(stacks.get(stacks.size() - 1));
            stacks.remove(stacks.size() - 1);
        }
        return currentPlace;
    }

    private static void updateToLeftNeighbor(char[][] mazeChart, ArrayList<Integer> currentPlace,
                                             ArrayList<Integer> leftNeighbor, Integer currentRow, int currentCul) {
        mazeChart[currentRow][currentCul] = '0';
        currentPlace.set(1, leftNeighbor.get(1));
        mazeChart[currentPlace.get(0)][currentPlace.get(1)] = '*';
    }

    private static void updateToDownNeighbor(char[][] mazeChart, ArrayList<Integer> currentPlace,
                                             ArrayList<Integer> downNeighbor, int currentRow, Integer currentCul) {
        mazeChart[currentRow][currentCul] = '0';
        currentPlace.set(0, downNeighbor.get(0));
        mazeChart[currentPlace.get(0)][currentPlace.get(1)] = '*';
    }

    private static void updateToRightNeighbor(char[][] mazeChart, ArrayList<Integer> currentPlace,
                                              ArrayList<Integer> rightNeighbor) {
        mazeChart[currentPlace.get(0)][currentPlace.get(1) + 1] = '0';
        currentPlace.set(1, rightNeighbor.get(1));
        mazeChart[currentPlace.get(0)][currentPlace.get(1)] = '*';
    }

    private static void updateToUpperNeighbor(char[][] mazeChart, ArrayList<Integer> currentPlace,
                                              ArrayList<Integer> upNeighbor) {
        mazeChart[currentPlace.get(0) - 1][currentPlace.get(1)] = '0';
        currentPlace.set(0, upNeighbor.get(0));
        mazeChart[currentPlace.get(0)][currentPlace.get(1)] = '*';
    }

    private static void updateNeighborLoc(ArrayList<Integer> currentPlace, ArrayList<Integer> upNeighbor,
                                          ArrayList<Integer> rightNeighbor, ArrayList<Integer> downNeighbor,
                                          ArrayList<Integer> leftNeighbor) {
        upNeighbor.set(0, currentPlace.get(0) - 2);
        upNeighbor.set(1, currentPlace.get(1));

        rightNeighbor.set(0, currentPlace.get(0));
        rightNeighbor.set(1, currentPlace.get(1) + 2);

        downNeighbor.set(0, currentPlace.get(0) + 2);
        downNeighbor.set(1, currentPlace.get(1));

        leftNeighbor.set(0, currentPlace.get(0));
        leftNeighbor.set(1, currentPlace.get(1) - 2);
    }
}