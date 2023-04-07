
/**
 * Second Stat Box
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21054573 and K21004839
 * @version 25/03/2022
 */
public class StatBox2 extends StatBox
{
    private static Stat currentStat;

    /**
     * Constructor for objects of class StatBox2
     */
    public StatBox2()
    {
        super();
        currentStat = getAllStats().get(1);
        createBox(currentStat.getText());
    }
    
    /**
     * @return The current Stat
     */
    public Stat getCurrentStat()
    {
        return currentStat;
    }
    
    /**
     * Assigns the new stat to the current stat
     * @param newCurrentStat
     */
    public void setCurrentStat(Stat newCurrentStat)
    {
        currentStat = newCurrentStat;
    }
}
