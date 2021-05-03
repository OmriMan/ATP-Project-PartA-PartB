package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    /**
     * Constructor
     * @param in Inputstream
     */
    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }


    @Override
    public int read() throws IOException {
        return 0;
        //Not implemented
    }

    /**
     * Function that decompresses the InputStream and saves it into b
     * @param b byte array to fill with decompressed maze
     * @return saves the decompressed maze into b
     * @throws IOException
     */
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

        int curr_byte = in.read();

        //Decompresses maze into byte array from the InputStream
        while(curr_byte != -1) {

            //Extracts meta data
            if(offset<12)
            {
                b[offset]=(byte)curr_byte;
                offset++;
            }

            //Extracts content of maze
            else
            {
                for(int i=0;i<(curr_byte);i++)
                {

                    b[offset+i]=(byte)curr_value;
                }

                offset += curr_byte;
                //If curr_val is 0, it will change to 1
                //If curr_val is 1, it will change to 0
                curr_value--;
                curr_value = Math.abs(curr_value);
            }
            curr_byte = in.read();

        }

        return 0;
    }

}
