import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] stringKeys = new GuitarString[37];
        for (int i = 0; i < stringKeys.length; i++){
            double frequency = 440 * Math.pow(2, (i-24)/12);
            stringKeys[i] = new GuitarString(frequency);
        }

        GuitarString string = stringKeys[0];

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);

                if (index != -1){
                    string = stringKeys[index];
                    string.pluck();
                }
            }

            /* play the sample on standard audio */
            StdAudio.play(string.sample());

            /* advance the simulation of each guitar string by one step */
            string.tic();
        }
    }
}
