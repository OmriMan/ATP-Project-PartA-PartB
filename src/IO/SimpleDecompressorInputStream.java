package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }


    @Override
    public int read() throws IOException {
        return 0;
        //Not implemented
    }

    @Override
    public int read(byte[] b) throws IOException {
        int curr_value = 0;
        int offset = 0;

        //Checks that argument has all the necessary data
        if (b.length < 16){
            try{
                throw new Exception("Cannot decompress maze - data is missing");

            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        //Extracts meta data to InputStream
        in.read(b, offset, 12);
        offset +=12;

        int curr_byte = in.read();
        //Decompresses maze into byte array of the InputStream
        while(curr_byte != -1) { //
            byte[] temp = new byte[curr_byte]; // = 3
            Arrays.fill(temp, (byte) curr_value);//Fills temp byte array with the matching 0's/1's
            System.arraycopy(temp, 0, b, offset, curr_byte); //Copies temp array to b
            offset += temp.length - 1;

            //If curr_val is 0, it will change to 1
            //If curr_val is 1, it will change to 0
            curr_value--;
            curr_value = Math.abs(curr_value);

            curr_byte = in.read();
            }

        return 0;
    }





}
