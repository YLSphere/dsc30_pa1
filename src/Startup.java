/*
Name: Yin Lam Lai
PID: A15779757
 */

public class Startup {
    private static final int DIVIDER = 2, SHIFT_LEFT = 10,  NUM_COLUMNS = 3,
            NUM_IN_COLUMNS = 4, NUM_ROWS = 3, NUM_IN_ROWS = 3,
            rowPattern_MAKER = 3, COLUMN_PATTERN_MAKER = 3, EVEN_CHECKER = 2,
            TWO_SPACER = 2;

    public static void main(String[] args) {
        //System.out.print(numpadSRC(0123));
    }
    // QUESTION 1
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

        for (int n = 0; n != arrayLen; n++) {
            numArray[n] = num % SHIFT_LEFT;
            num = num / SHIFT_LEFT;
        }
        int[][] columns = new int[NUM_COLUMNS][NUM_IN_COLUMNS];
        int[][] rows = new int[NUM_ROWS][NUM_IN_ROWS];

        for (int columnArray = 0; columnArray < NUM_COLUMNS; columnArray++) {
            for (int columnArrayPlace = 1; columnArrayPlace < NUM_IN_COLUMNS; columnArrayPlace++) {
                int columnNum = columnArray + 1 + ((columnArrayPlace - 1) * COLUMN_PATTERN_MAKER);
                columns[columnArray][columnArrayPlace] = columnNum;
            }
        }
        for (int rowArray = 0; rowArray < NUM_ROWS; rowArray++) {
            rowPattern = rowArray * rowPattern_MAKER;
            for (int rowArrayPlace = 1; rowArrayPlace < NUM_IN_ROWS + 1; rowArrayPlace++) {
                int rowNumber = rowArrayPlace + rowPattern;
                rows[rowArray][rowArrayPlace - 1] = rowNumber;
            }
        }
        firstNum = numArray[numArray.length - 1];
        int[] correctColumn = new int[NUM_IN_COLUMNS];
        countNumInColumns = 0;

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
        if (countNumInColumns == numArray.length) {
            return true;
        }
        int[] correctRow = new int[NUM_ROWS]; int countNumInRows = 0;
        for (int[] row: rows) {
            for (int number: row) {
                if (number == firstNum) {
                    correctRow = row; countNumInRows++; break;
                }
            }
        }
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
    public static int[] createSet(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return arr;
        }


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


    public static String encryptString(String s) {

        String atbashUpper, alphabetLower, alphabetUpper, atbashLower;
        alphabetUpper = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower = " abcdefghijklmnopqrstuvwxyz";
        atbashUpper = " ZYXWVUTSRQPONMLKJIHGFEDCBA";
        atbashLower = " zyxwvutsrqponmlkjihgfedcba";


        //REVERSER
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
    public static String drawPattern(int width) {
        int goingUp, goingDownSpace, goingUpSpace, goingDown, starAmountMax;

        if (width == 0) {
            return "";
        }
        if (width % EVEN_CHECKER == 1) {
            width++;
        }

        goingDownSpace = width - TWO_SPACER;
        goingUpSpace = TWO_SPACER;
        goingDown = 1;
        goingUp = (width / TWO_SPACER) - 1;
        starAmountMax = width / TWO_SPACER;
        String resultString = "";

        while (goingDown <= starAmountMax) {
            resultString += new String(new char[goingDown]).replace("\0", "*")
                    + new String(new char[goingDownSpace]).replace("\0", " ")
                    + new String(new char[goingDown]).replace("\0", "*") + "\n";

            goingDownSpace -= TWO_SPACER;
            goingDown += 1;
        }
        while (goingUp >= 0) {
            resultString += new String(new char[goingUp]).replace("\0", "*")
                    + new String(new char[goingUpSpace]).replace("\0", " ")
                    + new String(new char[goingUp]).replace("\0", "*") + "\n";
            goingUpSpace += TWO_SPACER;
            goingUp--;
        }
        return resultString;
    }

    public static int[] runtimeAnswers() {
        return new int[] {1, 1, 3, 5, 1, 2, 3, 1, 2, 4};
    }
}



