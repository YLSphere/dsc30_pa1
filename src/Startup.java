/**
Name: Yin Lam Lai
PID: A15779757
 */

/**
 * Contains all methods for PA1
 *
 * @author Yin Lam Lai
 * @since ${03/29/2020}
 */



public class Startup {
    // Declare constants and magic numbers
    private static final int DIVIDER = 2, SHIFT_LEFT = 10,  NUM_COLUMNS = 3,
            NUM_IN_COLUMNS = 4, NUM_ROWS = 3, NUM_IN_ROWS = 3,
            ROW_PATTERN_MAKER = 3, COLUMN_PATTERN_MAKER = 3, EVEN_CHECKER = 2,
            TWO_SPACER = 2;

    public static void main(String[] args) {
        //System.out.print(numpadSRC(0123));
    }

    // QUESTION 1
    /**
     * Checks each number in array returns whether they're even or odd
     * @param arr Input number array
     * @return Char array that shows whether each number is even or odd
     */
    public static char[] arrEvenOdd(int[] arr) {
        int arrayPlace;

        arrayPlace = 0;
        char[] result = new char[arr.length];
        for (int number: arr) {
            if (number % DIVIDER == 0) {
                result[arrayPlace] = 'E';
            } else {
                result[arrayPlace] = 'O';
            }
            arrayPlace++;
        }
        return result;
    }

    // QUESTION 2

    /**
     * Checks if subStr is a substring of mainStr
     *
     * @param mainStr main string
     * @param subStr substring
     * @return returns true if subStr is a substring of mainStr, else false
     */
    public static boolean isSubstring(String mainStr, String subStr) {
        int end = subStr.length();
        if (mainStr.length() < subStr.length()) {
            return false;
        }
        if (mainStr.length() == subStr.length()) {
            return mainStr.equals(subStr);
        } else {
            if (mainStr.substring(0, end).equals(subStr)) {
                return true;
            } else {
                return isSubstring(mainStr.substring(1), subStr);
            }
        }


    }

    // QUESTION 3

    /**
     * Checks if all other numbers in num correspond to the same row or column of a numpad as the first number of num
     *
     * @param num series of all numbers
     * @return true if numbers belong to the same row or column of a numpad, else, false
     */

    public static boolean numpadSRC(int num) {
        int arrayLen, numCopy, countNumInColumns, firstNum, rowPattern;
        arrayLen = 0; numCopy = num;
        

        if (num < SHIFT_LEFT) {
            return true;
        }
        while (numCopy > 0) {
            numCopy = numCopy / SHIFT_LEFT;
            arrayLen++;
        }
        int[] numArray = new int[arrayLen];

        // Creating an int array from initial num
        for (int n = 0; n != arrayLen; n++) {
            numArray[n] = num % SHIFT_LEFT;
            num = num / SHIFT_LEFT;
        }

        // Creating 2D arrays for columns and rows in the numpad
        int[][] columns = new int[NUM_COLUMNS][NUM_IN_COLUMNS];
        int[][] rows = new int[NUM_ROWS][NUM_IN_ROWS];

        // Filling empty 2D arrays
        for (int columnArray = 0; columnArray < NUM_COLUMNS; columnArray++) {
            for (int columnArrayPlace = 1; columnArrayPlace < NUM_IN_COLUMNS; columnArrayPlace++) {
                int columnNum = columnArray + 1 + ((columnArrayPlace - 1) * COLUMN_PATTERN_MAKER);
                columns[columnArray][columnArrayPlace] = columnNum;
            }
        }
        for (int rowArray = 0; rowArray < NUM_ROWS; rowArray++) {
            rowPattern = rowArray * ROW_PATTERN_MAKER;
            for (int rowArrayPlace = 1; rowArrayPlace < NUM_IN_ROWS + 1; rowArrayPlace++) {
                int rowNumber = rowArrayPlace + rowPattern;
                rows[rowArray][rowArrayPlace - 1] = rowNumber;
            }
        }
        firstNum = numArray[numArray.length - 1];
        int[] correctColumn = new int[NUM_IN_COLUMNS];
        countNumInColumns = 0;

        // Checking if second number in int array matches a column the first number is in, if so take said column array
        for (int[] columnArrays: columns) {
            for (int arrayNum: columnArrays) {
                if (arrayNum == firstNum) {
                    correctColumn = columnArrays; countNumInColumns++; break;
                }
            }
        }
        for (int index = 0; index < numArray.length - 1; index++) {
            for (int correctNum: correctColumn) {
                if (numArray[index] == correctNum) {
                    countNumInColumns++;
                }
            }

        }

        // If count of numbers in column match the length of the num array, return true, else, continue
        if (countNumInColumns == numArray.length) {
            return true;
        }

        // Checking if second number in int array matches a row the first number is in, if so take said row array
        int[] correctRow = new int[NUM_ROWS]; int countNumInRows = 0;
        for (int[] row: rows) {
            for (int number: row) {
                if (number == firstNum) {
                    correctRow = row; countNumInRows++; break;
                }
            }
        }

        // If count of numbers in column match the length of the num array, return true, else, return false
        for (int index = 0; index < numArray.length - 1; index++) {
            for (int correctNum: correctRow) {
                if (numArray[index] == correctNum) {
                    countNumInRows++;
                }
            }
        }

        if (countNumInRows == numArray.length) {
            return true;
        }
        return false;
    }

    // QUESTION 4

    /**
     * Removes duplicate numbers in an int array and sorts said array
     *
     * @param arr input int array
     * @return int array with no duplicates and sorted
     */
    public static int[] createSet(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return arr;
        }

        // Sorter
        for (int i = 0; i < arr.length; i++) {
            for (int x = 1; x < arr.length - i; x++) {
                if (arr[x - 1] > arr[x]) {
                    arr[x] = arr[x] + arr[x - 1];
                    arr[x - 1] = arr[x] - arr[x - 1];
                    arr[x] = arr[x] - arr[x - 1];
                }
            }
        }
        int count = 0;
        int[] result = new int[arr.length];

        result[0] = arr[0];

        // Checking if n+1 int in the array matches the n int, if not, append to result array
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                result[i] = arr[i];
            }
        }

        for (int n : result) {
            if (n != 0) {
                count++;
            }
        }

        for (int j : arr) {
            if (j == 0) {
                count++; break;
            }
        }

        int[] resultFinal = new int[count];
        count = 0;

        for (int x = 0; x < result.length; x++) {
            if (result[x] != 0) {
                resultFinal[count] = result[x]; count++;
            }
        }
        // Resort final array
        for (int i = 0; i < resultFinal.length; i++) {
            for (int x = 1; x < resultFinal.length - i; x++) {
                if (resultFinal[x - 1] > resultFinal[x]) {
                    resultFinal[x] = resultFinal[x] + resultFinal[x - 1];
                    resultFinal[x - 1] = resultFinal[x] - resultFinal[x - 1];
                    resultFinal[x] = resultFinal[x] - resultFinal[x - 1];
                }
            }
        }

        return resultFinal;
    }

    // QUESTION 5

    /**
     * checks if set1 is a subset of set2
     *
     * @param set1 Subset
     * @param set2 Main set
     * @return true if set1 is a subset of set2, else, false
     */
    public static boolean subsetChecker(int[] set1, int[] set2) {

        if (set1.length == 0) {
            return true;
        }
        int counter = 0;

        for (int n : set1) {
            for (int x : set2) {
                if (x == n) {
                    counter++;
                }
            }
        }

        return counter == set1.length;
    }

    // QUESTION 6

    /**
     * Checks if the target number is in the int array within the index bounds of left and right
     *
     * @param arr int array
     * @param left left index bound
     * @param right right index bound
     * @param target target number
     * @return true if the target number is within the array and in bounds, else, false
     */

    public static int recursiveBinarySearch(int[] arr, int left, int right, int target) {
        if (left == right) {
            if (arr[left] == target) {
                return left;
            } else {
                return -1;
            }
        } else {
            if (arr[right] == target) {
                return right;
            } else {
                return recursiveBinarySearch(arr, left, right - 1, target);
            }
        }

    }

    // QUESTION 7

    /**
     * Encrypts string by reversing it and putting it through an atbash cypher
     *
     * @param s
     * @return encrypted string
     */
    public static String encryptString(String s) {

        String atbashUpper, alphabetLower, alphabetUpper, atbashLower;
        alphabetUpper = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower = " abcdefghijklmnopqrstuvwxyz";
        atbashUpper = " ZYXWVUTSRQPONMLKJIHGFEDCBA";
        atbashLower = " zyxwvutsrqponmlkjihgfedcba";


        //Reverser
        char[] in = s.toCharArray();
        int start = 0;
        int end = in.length - 1;
        char temp;
        while (end > start) {
            temp = in[start];
            in[start] = in[end];
            in[end] = temp;
            end--;
            start++;
        }
        String reversed = new String(in);


        String finalOut = "";
        char[] reversedArray = reversed.toCharArray();

        // Checking each char to see if they're upper case, if they are, encrypt them
        for (int indexChar = 0; indexChar < reversedArray.length; indexChar++) {
            int booleanN = 0;
            for (char symbol :(alphabetLower + alphabetUpper).toCharArray()) {
                if (symbol == reversedArray[indexChar]) {
                    booleanN = 1;
                }

            }
            if (booleanN == 0) {
                finalOut += reversedArray[indexChar];
            }

            // Checking each char to see if they're lower case, if they are, encrypt them, if not, take them directly
            // from the input string
            for (int i = 0; i < alphabetLower.length(); i++) {
                if (alphabetLower.charAt(i) == reversedArray[indexChar]) {
                    finalOut += atbashLower.charAt(i);
                } else if (alphabetUpper.charAt(i) == reversedArray[indexChar]) {
                    finalOut += atbashUpper.charAt(i);
                }
            }
        }

        return finalOut;
    }

    //QUESTION 8

    /**
     * Draws a double triangle pattern where the height of both triangles equals to the width
     *
     * @param width height of both triangles combined
     * @return drawn out pattern of both triangles where combined height is equals width
     */
    public static String drawPattern(int width) {
        int goingUp, goingDownSpace, goingUpSpace, goingDown, starAmountMax;

        if (width == 0) {
            return "";
        }
        if (width % EVEN_CHECKER == 1) {
            width++;
        }

        // Definitions of variables
        goingDownSpace = width - TWO_SPACER;
        goingUpSpace = TWO_SPACER;
        goingDown = 1;
        goingUp = (width / TWO_SPACER) - 1;
        starAmountMax = width / TWO_SPACER;
        String resultString = "";

        // Crates top portion of both triangles
        while (goingDown <= starAmountMax) {
            resultString += new String(new char[goingDown]).replace("\0", "*")
                    + new String(new char[goingDownSpace]).replace("\0", " ")
                    + new String(new char[goingDown]).replace("\0", "*") + "\n";

            goingDownSpace -= TWO_SPACER;
            goingDown += 1;
        }

        // Creates bottom parts of both triangles
        while (goingUp >= 0) {
            resultString += new String(new char[goingUp]).replace("\0", "*")
                    + new String(new char[goingUpSpace]).replace("\0", " ")
                    + new String(new char[goingUp]).replace("\0", "*") + "\n";
            goingUpSpace += TWO_SPACER;
            goingUp--;
        }
        return resultString;
    }
    // Big O Questions
    /**
     * Big O Questions
     *
     * @return int array with answers
     */
    public static int[] runtimeAnswers() {
        return new int[] {1, 1, 3, 5, 1, 2, 3, 1, 2, 4};
    }
}



