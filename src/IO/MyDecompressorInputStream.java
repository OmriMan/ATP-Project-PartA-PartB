package IO;


import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream{
    private InputStream in;

    /**
     * Constructor
     * @param in InputStream
     */
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;//Not implemented
    }

    /**
     * Function that decompresses the InputStream and saves it into b
     * @param b Byte array that saves the decompressed InputStream
     * @return saves the decompressed maze into b
     * @throws IOException
     */
    public int read(byte[] b) throws IOException{
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
        int offset = 0;


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
                //Converts pairs of bytes into a decimal number
                int remainder = in.read();
                int decimal = ((curr_byte&0xff)*256) + (remainder&0xff);

                //Converts the decimal number into a temporary binary byte array
                byte[] temp_byte_arr = Convertion.DecimalToBinaryByteArray(decimal);

                //Checks if we reached the last chunk of data
                if (offset + temp_byte_arr.length > b.length){

                    //Copies to result byte array only the number of bytes needed to retrieve the original maze
                    for (int i =temp_byte_arr.length-(b.length - offset); i < temp_byte_arr.length; i++){
                        b[offset] = temp_byte_arr[i];
                        offset++;
                    }
                    break;
                }

                //Copies the temporary byte array into the result byte array
                for (int i = 0; (i < temp_byte_arr.length) ; i++){
                    b[offset+i] = temp_byte_arr[i];
                }

                offset += temp_byte_arr.length;
            }
            curr_byte = in.read();
        }
        return 0;
    }


}
