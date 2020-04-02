import java.util.Arrays;

public class Startup {
    public static void main(String[] args){
        /*
        int[] sub1 = {2, 5, 4, 3, 7, 3, 9, 12, 4, 5};
        int[] sub2 = {-9, -3, 0, 2, 5, 7, 10};
        System.out.print(numpadSRC(0123));
         */

    }
    // QUESTION 1
    public static char[] arrEvenOdd(int[] arr) {
        int divider, Array_Place;
        divider = 2;
        Array_Place = 0;
        char[] result = new char[arr.length];
        for (int number: arr){
            if (number%divider == 0){ result[Array_Place] = 'E';}
            else {
                result[Array_Place] = 'O';
            }
            Array_Place ++;
        }
        return result;
    }

    // QUESTION 2
    public static boolean isSubstring(String mainStr, String subStr){
        int end = subStr.length();
        if (mainStr.length() < subStr.length()){return false;}
        if (mainStr.length() == subStr.length()){
            return mainStr.equals(subStr);
        }
        else{
            if (mainStr.substring(0, end).equals(subStr)){return true;}
            else{return isSubstring(mainStr.substring(1), subStr);}
        }


    }

    // QUESTION 3

    public static boolean numpadSRC(int num) {
        int shiftLeft, arrayLen, numCopy, numColumns, numInColumns, numRows, numInRows, columnPattern, rowPattern,
                rowPatternMaker, countNumInColumns, firstNum;

        shiftLeft = 10;
        arrayLen = 0;
        numCopy = num;
        numColumns = 3;
        numInColumns = 4;
        numRows = 3;
        numInRows = 3;
        rowPatternMaker = 3;
        columnPattern = 3;


        if (num < 10) {return true;}
        while (numCopy > 0) {
            numCopy = numCopy / shiftLeft;
            arrayLen++;
        }

        int[] numArray = new int[arrayLen];

        for (int n = 0; n != arrayLen; n++) {
            numArray[n] = num % shiftLeft;
            num = num / shiftLeft;
        }



        int[][] columns = new int[numColumns][numInColumns];
        int[][] rows = new int[numRows][numInRows];

        for (int columnArray = 0; columnArray < numColumns; columnArray++) {
            for (int columnArrayPlace = 1; columnArrayPlace < numInColumns; columnArrayPlace++) {
                int columnNum = columnArray + 1 + ((columnArrayPlace - 1) * columnPattern);
                columns[columnArray][columnArrayPlace] = columnNum;
            }
        }

        for (int rowArray = 0; rowArray < numRows; rowArray++) {
            rowPattern = rowArray * rowPatternMaker;
            for (int rowArrayPlace = 1; rowArrayPlace < numInRows + 1; rowArrayPlace++) {
                int rowNumber = rowArrayPlace+rowPattern;
                rows[rowArray][rowArrayPlace-1] = rowNumber;
            }
        }

        firstNum = numArray[numArray.length - 1];
        int[] correctColumn = new int[numInColumns];
        countNumInColumns = 0;

        for (int[] columnArrays: columns) {
            for (int arrayNum: columnArrays) {


                if (arrayNum == firstNum) {
                    correctColumn = columnArrays;
                    countNumInColumns++;
                    break;
                }
            }
        }

        for (int index = 0; index < numArray.length-1; index++) {
            for (int correctNum: correctColumn) {
                if (numArray[index] == correctNum) {
                    countNumInColumns++;
                }
            }

        }

        if (countNumInColumns == numArray.length) {return true;}

        int[] correctRow = new int[3];
        int countNumInRows = 0;

        for (int[] row: rows) {
            for (int number: row) {
                if (number == firstNum) {
                    correctRow = row;
                    countNumInRows++;
                    break;
                }
            }
        }

        for (int index = 0; index < numArray.length-1; index++) {
            for (int correctNum: correctRow) {
                if (numArray[index] == correctNum) {
                    countNumInRows++;
                }
            }
        }

        if (countNumInRows == numArray.length) {return true;}

        return false;
    }

    // QUESTION 4
    public static int[] createSet(int[] arr){

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

        for (int i = 1; i<arr.length; i++){
            if (arr[i] != arr[i-1]){result[i] = arr[i];}
        }

        for (int n : result){
            if (n != 0){count++;}
        }

        for (int j : arr){
            if (j == 0){count ++; break;}
        }

        int[] result_final = new int[count];
        count = 0;

        for (int x = 0; x < result.length; x++){

            if (result[x] != 0){result_final[count] = result[x]; count++;}
        }

        for (int i = 0; i < result_final.length; i++) {
            for (int x = 1; x < result_final.length - i; x++) {
                if (result_final[x - 1] > result_final[x]) {
                    result_final[x] = result_final[x] + result_final[x - 1];
                    result_final[x - 1] = result_final[x] - result_final[x - 1];
                    result_final[x] = result_final[x] - result_final[x - 1];
                }
            }
        }



        return result_final;
    }

    // QUESTION 5
    public static boolean subsetChecker(int[] set1, int[] set2){

        if (set1.length == 0){return true;}
        int counter = 0;

        for (int n : set1){
            for (int x : set2){
                if (x == n){counter++;}
            }
        }

        return counter == set1.length;
    }

    // QUESTION 6

    public static int recursiveBinarySearch(int[] arr, int left, int right, int target){
        if (left == right){
            if (arr[left] == target){return left;}
            else{return -1;}
        }
        else{
            if (arr[right] == target){return right;}
            else {return recursiveBinarySearch(arr, left, right-1, target);}
        }

    }


    public static String encryptString(String s){

        String atbash_upper, alphabet_lower, alphabet_upper, atbash_lower;
        alphabet_upper = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet_lower = " abcdefghijklmnopqrstuvwxyz";
        atbash_upper = " ZYXWVUTSRQPONMLKJIHGFEDCBA";
        atbash_lower = " zyxwvutsrqponmlkjihgfedcba";


        //REVERSER
        char[] in = s.toCharArray();
        int start=0;
        int end=in.length-1;
        char temp;
        while(end>start){
            temp = in[start];
            in[start]=in[end];
            in[end] = temp;
            end--;
            start++;
        }
        String reversed = new String(in);


        String final_out = "";
        char[] reversed_array = reversed.toCharArray();

        for (int index_char=0; index_char<reversed_array.length; index_char++){
            int boolean_n = 0;
            for (char symbol :(alphabet_lower+alphabet_upper).toCharArray()){
                if (symbol == reversed_array[index_char]){boolean_n = 1;}

            }
            if (boolean_n == 0){final_out += reversed_array[index_char];}
            for (int i = 0; i < alphabet_lower.length(); i++){
                if (alphabet_lower.charAt(i) == reversed_array[index_char]) {final_out+= atbash_lower.charAt(i);}
                else if (alphabet_upper.charAt(i) == reversed_array[index_char]) {final_out+= atbash_upper.charAt(i);}
            }
        }

        return final_out;
    }

    //QUESTION 8
    public static String drawPattern(int width){
        int even_checker, going_up, two_spacer, going_down_space, going_up_space,going_down, star_amount_max;
        even_checker = 2;
        two_spacer = 2;




        if (width == 0){return "";}
        if (width % even_checker == 1){ width++; }

        going_down_space = width-two_spacer;
        going_up_space = two_spacer;

        going_down = 1;
        going_up = (width/two_spacer)-1;

        star_amount_max = width/two_spacer;

        String result_string = "";



        while (going_down <= star_amount_max) {
            result_string += new String(new char[going_down]).replace("\0", "*") +
                    new String(new char[going_down_space]).replace("\0", " ") +
                    new String(new char[going_down]).replace("\0", "*") + "\n";

            going_down_space -= two_spacer;
            going_down += 1;
        }
        while (going_up >= 0) {
            result_string += new String(new char[going_up]).replace("\0", "*") +
                    new String(new char[going_up_space]).replace("\0", " ") +
                    new String(new char[going_up]).replace("\0", "*") + "\n";
            going_up_space += two_spacer;
            going_up -= 1;
        }
        return result_string;
    }

    public static int[] runtimeAnswers() {
        return new int[] {1, 1, 3, 1, 1, 2, 3, 1, 2, 4};
    }
}




