package Video;

public abstract class Videoprokat
{
    public String nazwanie;
    protected String ograni4enie;
    protected String zhanr;
    protected String yaziki;
    protected String god_wipuska;
    protected boolean otdano_w_prokat;

    public abstract boolean zadat();

    public abstract boolean pokazat();

    public boolean dat_w_prokat()
    {
        if(otdano_w_prokat)
            return false;
        else
        {
            otdano_w_prokat = true;
            return true;
        }
    }
}