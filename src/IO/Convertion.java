package IO;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Convertion {


    /**
     * Static method that receives an integer and returns a 2 byte array in the following format: (Array[0]*256)+Array[1] = number
     * Array[0] - Number is divided in parts of 256, index 0 means number is in 0-255, index 1 means number is in 256-511 and so on...
     * Array[1] - Offset from the given part
     * @param number Given number to convert
     * @return 2 byte array containing the appropriate format
     */
    public static byte[] ConvertIntToByteFormat(int number){
        byte[] result = new byte[2];
        result[0] = (byte) ((int)number/256);
        result[1] = (byte) (number-(256*result[0]));

        return result;
    }

    /**
     * Static method that converts int decimal number to a binary byte array
     * @param number decimal number to convert to binary
     * @return a binary byte array representing the given number
     */
    public static byte[] DecimalToBinaryByteArray(int number){
        byte[] result = new byte[16];
        int index = result.length-1;
        int temp_number = number;

        Arrays.fill(result, (byte) 0); //Makes sure every byte is 0 in the beginning

        //Converts decimal number to binary
        while(temp_number>0){
            result[index] = (byte) (temp_number % 2);
            temp_number = (int)(temp_number/2);
            index--;
        }

        return result;
    }

    /**
     * Static method that receives a binary byte array and converts it to a decimal number
     * @param b binary byte array
     * @return int with decimal representation of the binary number found in b
     */
    public static int BinaryByteArrToDecimal(byte[] b){
        int result = 0;
        for (int i = 0; i < b.length; i++){
            result = (int) (result + b[i]*Math.pow(2, ((b.length-1)-i)));
        }

        return result;

    }

}
