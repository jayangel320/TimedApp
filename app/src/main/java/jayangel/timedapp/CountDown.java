package jayangel.timedapp;



public class CountDown {

    public String time(long mTime)
    {
        long minutes = mTime / (60 * 1000);
        long seconds = (mTime - minutes * (60 * 1000)) / (60 * 10);
        return String.format("%02d:%02d", minutes, seconds);
    }
}