/*
 * Hangmen
 * Learning how to use Files and command line parameters
 * Author:       Sarah Berger
 * Last Change:  11.01.2024
 */

import java.io.*;
import java.util.*;

public class Hangmen {

    private static int won;
    private static int total;
    private static int lost;
    private static int fail;
    private static int missing;

    private static ArrayList<String> list1 = new ArrayList<>();

    /**
     * file reading
     */
    private static boolean read(String name) {
        try (BufferedReader inBuffer = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = inBuffer.readLine()) != null) {
                if (!line.trim().isEmpty() && line.matches("[a-zA-Z]+")) {
                    list1.add(line);
                } else if (!line.matches("[a-zA-Z]+") && !line.trim().isEmpty()) {
                    System.out.println("Error: Corrupted file!");
                    return false;
                }
            }
        } catch (IOException wrong) {
            File file = new File(name);
            //check if file exist, if it does the fileReade could not open the file
            if (file.exists()) System.out.println("Error: Could not read file!");
            else System.out.println("Error: File not found!");
            return false;
        }

        try {
            if (list1.isEmpty()) {
                throw new IOException("Error: Empty file!");
            }
        } catch (IOException empty) {
            System.out.println(empty.getMessage());
            return false;
        }

        total = list1.size();
        Collections.shuffle(list1);
        return true;
    }

    /**
     * a method for how to play hangmen
     */
    private static void playing(String randome, int y) {
        String guessing = "";
        ArrayList<String> miss = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        doubleSeparationLine();
        System.out.println("HANGMEN (" + total + " Word(s))");
        separationLine();
        System.out.println("Word #" + y + ":");

        String strokes = randome.replaceAll("[a-zA-Z]", "_ ").trim();

        try {
            do {
                if (miss.isEmpty()) {
                    System.out.println("\n");
                } else drawing(missing);
                System.out.println("Word: " + strokes);
                System.out.print("Misses (" + miss.size() + "/" + fail + ")");
                for (int b = 0; b < miss.size(); b++) {
                    if (b != 0) {
                        System.out.print(", " + miss.get(b));
                    } else {
                        System.out.print(": ");
                        System.out.print(miss.get(b));
                    }
                }
                System.out.println();
                System.out.print("Next guess: ");

                boolean right = false;

                guessing = scanner.nextLine().toLowerCase();

                try {
                    if (guessing.length() != 1) {
                        throw new Exception("Invalid input!");
                    }
                    if (!Character.isLetter(guessing.charAt(0))) {
                        throw new Exception("Invalid character!");
                    }
                    if (strokes.contains(guessing) || miss.contains(guessing.toUpperCase())) {
                        throw new Exception("Character already guessed!");
                    }
                } catch (Exception input) {
                    System.out.println();
                    System.out.println(input.getMessage());
                    if (!miss.isEmpty()) {
                        System.out.println();
                    }
                    continue;
                }

                for (int i = 0; i < randome.length(); i++) {
                    if (Character.toUpperCase(guessing.charAt(0)) == Character.toUpperCase(randome.charAt(i))) {
                        strokes = strokes.substring(0, 2 * i) + randome.charAt(i) + strokes.substring(2 * i + 1);
                        right = true;
                    }

                    if (!strokes.contains("_")) {
                        won++;
                        System.out.print(("\n").repeat(2));
                        System.out.println("Word: " + strokes);
                        System.out.println("Misses (" + missing + "/" + 11 + ")");
                        System.out.println();
                        System.out.println("YOU WIN!");
                        miss.clear();
                        fail = -1;
                        break;
                    }
                }
                if (!right) {
                    miss.add(guessing.toUpperCase());
                    missing++;

                }
            } while (miss.size() < fail);

            if (miss.size() == fail) {
                System.out.println();
                System.out.println("YOU LOSE!");
                lost++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this method is for the drawing of the hangmen
     */
    private static void drawing(int missing) {
        String bottom = "===";
        String top = " ".repeat(2) + "_".repeat(4);
        String side = ("\n" + " |");
        switch (missing) {

            case 0: {
                break;
            }
            case 1: {
                System.out.println();
                System.out.println(bottom);
                break;
            }
            case 2: {
                System.out.println(side.repeat(4) + "\n" + bottom);
                break;
            }
            case 3: {
                System.out.println();
                System.out.print(top);
                System.out.println(side.repeat(4) + "\n" + bottom);
                break;
            }
            case 4: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + side.repeat(3));
                System.out.println(bottom);
                break;
            }
            case 5: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + " ".repeat(3) + "|" + side.repeat(3));
                System.out.println(bottom);
                break;
            }
            case 6: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + " ".repeat(3) + "|" + side + " ".repeat(4) + "O" + side.repeat(2));
                System.out.println(bottom);
                break;
            }
            case 7: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + " ".repeat(3) + "|" + side + " ".repeat(4) + "O" + side + " ".repeat(4) + "|" + side);
                System.out.println(bottom);
                break;
            }
            case 8: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + " ".repeat(3) + "|" + side + " ".repeat(4) + "O" + side + " ".repeat(4) + "|" + side + " ".repeat(3) + "/");
                System.out.println(bottom);
                break;
            }
            case 9: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + " ".repeat(3) + "|" + side + " ".repeat(4) + "O" + side + " ".repeat(4) + "|" + side + " ".repeat(3) + "/" + " \\");
                System.out.println(bottom);
                break;
            }
            case 10: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + " ".repeat(3) + "|" + side + " ".repeat(4) + "O" + side + " ".repeat(3) + "/|" + side + " ".repeat(3) + "/" + " \\");
                System.out.println(bottom);
                break;
            }
            case 11: {
                System.out.println();
                System.out.print(top);
                System.out.println(side + "/" + " ".repeat(3) + "|" + side + " ".repeat(4) + "O" + side + " ".repeat(3) + "/|\\" + side + " ".repeat(3) + "/" + " \\");
                System.out.println(bottom);
                break;
            }
        }
        System.out.println();
    }

    /**
     * used for a double separation Line
     */
    private static void doubleSeparationLine() {
        System.out.println("=".repeat(80));
    }

    /**
     * used for a single separation Line
     */
    private static void separationLine() {
        System.out.println("-".repeat(80));
    }

    private static void results() {
        doubleSeparationLine();
        System.out.println("WINS: " + won + "/" + total);
    }

    /**
     * this method is for getting all methods together and let the player play the game
     */
    public static void main(String[] args) {    //args is the filename
        int y = 0;
        try {
            if (args.length == 0) {
                throw new IOException("Error: No file name given!");
            }
        } catch (Exception no) {
            System.out.println(no.getMessage());
            return; //end the program
        }
        String name = args[0];

        if (!read(name)) {
            return;
        }

        while ((lost + won) < total) {

            for (String random : list1) {
                missing = 0;
                fail = 11;
                y++;
                playing(random, y);
            }
        }
        results();
    }
}
