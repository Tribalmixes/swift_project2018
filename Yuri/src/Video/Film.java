package Video;

import java.util.Scanner;


public class Film extends Videoprokat
{
    protected String dlitelnost;
    public static int chislo_filmow = 0;

    public boolean zadat()
    {
        Scanner eingabe = new Scanner(System.in);

        try
        {
            System.out.print("Name: ");
            nazwanie = eingabe.nextLine();
            System.out.print("Genre: ");
            zhanr = eingabe.nextLine();
            System.out.print("Duration in min.: ");
            dlitelnost = eingabe.nextLine();
            System.out.print("Age restriction: ");
            ograni4enie = eingabe.nextLine();
            System.out.print("Languages: ");
            yaziki = eingabe.nextLine();
            System.out.print("Year of issue: ");
            god_wipuska = eingabe.nextLine();

            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean pokazat()
    {
        System.out.println("\nName: " + nazwanie);
        System.out.println("Genre:  " + zhanr);
        System.out.println("Duration in min.: " + dlitelnost);
        System.out.println("Age restriction:" + ograni4enie);
        System.out.println("Languages: " + yaziki);
        System.out.println("Year of issue: " + god_wipuska);
        if(otdano_w_prokat)
            System.out.println("Rented.");
        else
            System.out.println("Not rented.");
        return false;
    }

    public Film()
    {
        chislo_filmow++;
    }

    public Film(String k_nazwanie, String k_dlitelnost, String k_zhanr, String k_ograni4enie, String k_yaziki, String k_god_wipuska)
    {
        nazwanie = k_nazwanie;
        dlitelnost = k_dlitelnost;
        zhanr = k_zhanr;
        ograni4enie = k_ograni4enie;
        yaziki = k_yaziki;
        god_wipuska = k_god_wipuska;
        chislo_filmow++;
    }

    public String zapis_w_spisok()
    {
        String text;
        text = nazwanie + ", " + zhanr + ", " + dlitelnost + " min," + ograni4enie + " , " + yaziki + ", " + god_wipuska;
        if(otdano_w_prokat)
            text = text + " *";
        return text;
    }
}