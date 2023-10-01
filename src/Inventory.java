import java.util.*; // too lazy, might as well get everything lmao


/**
 *  @code Inventory class manages the user's collection of creatures
 * 
 *  @author Shinoruba
 *  @author JSTP8330
 *  @version 1.0
 */
public class Inventory 
{
    private ArrayList<Creature> creatures;
    private Creature activeCreature;

    public Inventory()
    {
        creatures = new ArrayList<>();
    }
}
