package IO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /**
     * Constructor
     * @param out received OutputStream
     */
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        //not implemented
    }


    /**
     * Function that Compresses the array of bytes into the OutputStream.
     * Compresses the content of the maze by dividing it to chunks of size 16 bytes
     * Each chunk is converted to a decimal number, then to a 2 byte array using the following format: (Array[0]*256)+Array[1] = number
     * @param b Given array of bytes
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        int start_index;
        byte[] temp_byte_arr;
        int temp_decimal;

        //Checks that argument has all the necessary data
        if (b.length < 16){
            try{
                throw new Exception("Cannot compresse maze - data is missing");

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        //Sends the meta data (0-12) to the Output Stream
        for (start_index = 0; start_index<12; start_index++){
            out.write(b[start_index]);
            out.flush();
        }


        //Takes chunks of 16 (binary) bytes, starting from index 12 (0-12 is meta data)
        //Casts them into a Decimal number using the Convertion class static method
        //Converts the decimal number into a 2 byte format then sends it to OutputStream
        while(start_index< b.length){

            //If last chunk of bytes is smaller than 16 - we use an adjusted buffer to convert it to decimal number
            if (start_index+16 > b.length){
                temp_byte_arr = Arrays.copyOfRange(b, start_index, b.length);
                temp_decimal = Convertion.BinaryByteArrToDecimal(temp_byte_arr);
                out.write(Convertion.ConvertIntToByteFormat(temp_decimal));
                out.flush();
                break;
            }

            //Regular case
            temp_byte_arr = Arrays.copyOfRange(b, start_index, start_index + 16 );
            temp_decimal = Convertion.BinaryByteArrToDecimal(temp_byte_arr);
            out.write(Convertion.ConvertIntToByteFormat(temp_decimal));
            out.flush();
            start_index = start_index + 16;
        }

        out.close();
    }
}
