package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        //not implemented
    }


    /**
     * Function that Compresses the array of bytes into the OutputStream
     * @param b Given array of bytes
     * @throws IOException
     */
    public void write(byte[] b) throws IOException {
        int counter = 0;
        int curr_val = 0;

        //Checks that argument has all the necessary data
        if (b.length < 16){
            try{
                throw new Exception("Cannot compresse maze - data is missing");

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        //Iterates over every char from the content of the byte array (every cell of the maze)
        for (int i = 12; i < b.length; i++){
            //Counts number of appearance of 0/1
            if (curr_val == b[i]) {
                counter++;
            }

            //When 0/1 sequence flips, we write to outstream how many 0/1 we have
            else {
                out.write(counter);
                counter = 0;
                curr_val = b[i];
            }

            //Checks when 0/1 sequence is longer than 255
            if (counter == 255) {
                out.write(counter);
                counter = 0;

                //If curr_val is 0, it will change to 1
                //If curr_val is 1, it will change to 0
                curr_val--;
                curr_val = Math.abs(curr_val);
            }
        }
    }



}
