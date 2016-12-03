import org.jtransforms.fft.DoubleFFT_1D;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class WavFFT {

    public static double[] realFFT(File file)
    {
        // Get the .wav data using the readWav class
        double[] data_to_fft = ReadWav.getWavData(file);

        /* Get the length of the array.
        Since we are feeding real numbers into the fft,
        the length of the array should be equal to the
        number of frames, which we get using the readWav class. */
        int n = (int) ReadWav.getWavFrames(file);

        // Make a new fft object
        DoubleFFT_1D fft = new DoubleFFT_1D(n);

        // Perform the realForward fft
        fft.realForward(data_to_fft);

        // Return the final data
        return data_to_fft;
    }


    public static void writeToFile(File in, File out) throws IOException
    {
        PrintWriter print_out = new PrintWriter(out);
        int i;
        double[] data_to_file = realFFT(in);

        for(i=0; i<data_to_file.length; i++){
            if(data_to_file[i] > 1){
                print_out.println(data_to_file[i]);
            } else {
                print_out.println(0);
            }

        }
        print_out.close();
    }

    // main method, solely for testing purposes
    public static void main(String[] args) {
        File fichier_son = new File("src/testingwav.wav");
        double[] test = realFFT(fichier_son); 
        int i;

        for(i=0; i<test.length; i++){
            System.out.println(test[i]);
        }
        System.out.println(test.length);


        try{
            writeToFile(fichier_son, new File("src/output.txt"));
        } catch (IOException e){
            System.out.println("error");
        }
    }

}