import java.io.*;
import java.util.Scanner;
import Video.Film;
import Video.*;



public class Control
{
    private static int chislo_filmow = 0;
    private static final int FILM_MAX = 50;
    private static Film[] spisok_filmow = new Film[FILM_MAX];
    private static String delFilmName;
    private static Scanner eingabe = new Scanner(System.in);


    public static void main(String[] argumente)
    {
        boolean konez = false;
        do
        {
            System.out.println("============" + "==========");
            System.out.println("\nMain menu");
            System.out.println("\n(1) Show all list with movies");
            System.out.println("(2) New movie");
            System.out.println("(3) Rent a movie");
            System.out.println("\n(0) Exit");
            try
            {
                System.out.print("\nMake a choice:");
                short wibor = eingabe.nextShort();

                switch(wibor)
                {
                    case 1:
                        pokazat_spisok(chislo_filmow);
                        break;
                    case 2:
                        if(chislo_filmow < FILM_MAX && chislo_filmow >= 0)
                            chislo_filmow = nowiy_film(chislo_filmow);
                        else
                            System.out.println("List is full!");
                        break;
                    case 3:
                        if(spisok_filmow[film_wibor(chislo_filmow)].dat_w_prokat())
                            System.out.println("OK");
                        else
                            System.out.println("The movie is on rent!");
                        break;
                    case 0:
                        System.out.println("\nGood bye...");
                        konez = true;
                        break;
                    default:
                        System.out.println("\nNe prawelniy wibor");
                }

                if(wibor == 0)
                    konez = true;
            }
            catch(Exception e)
            {
                System.out.println("Input only numbers!");
            }
        }
        while(!konez);
    }

    static int nowiy_film(int film_nomer)
    {
        System.out.println("\nInput new movie");
        System.out.println("==========");
        spisok_filmow[film_nomer] = new Film();

        while(spisok_filmow[film_nomer].zadat() == false)
        {
            System.out.println("Is not correct");
        }
        System.out.println("New movie added.");
        if(film_nomer + 1 > 1)
            sortirowat(film_nomer + 1);
        return (film_nomer + 1);
    }

    static void pokazat_spisok(int film_nomer)
    {
        System.out.println("\nList with movies");
        System.out.println("==========\n");
        for(int i = 0; i < film_nomer; i++)
        {
            System.out.print((i + 1) + ") ");
            System.out.println(spisok_filmow[i].zapis_w_spisok());
        }
    }

    static void sortirowat(int film_nomer)
    {
        Film puffer;
        for(int prowerka = 0; prowerka < film_nomer; prowerka++)
            for(int posiziya = 0; posiziya < film_nomer - 1; posiziya++)
                if(spisok_filmow[posiziya].zapis_w_spisok().compareTo(spisok_filmow[posiziya + 1].zapis_w_spisok()) > 0)
                {
                    puffer = spisok_filmow[posiziya];
                    spisok_filmow[posiziya] = spisok_filmow[posiziya + 1];
                    spisok_filmow[posiziya + 1] = puffer;
                }
    }

    static int film_wibor(int film_nomer)
    {
        boolean konez = false;
        short wibor = 0;
        do
        {
            pokazat_spisok(film_nomer);
            try
            {
                System.out.print("\nChoice: ");
                wibor = eingabe.nextShort();

                if(wibor > 0 && wibor <= film_nomer)
                    konez = true;
                else
                    System.out.println("Invalid choice!");
            }
            catch(Exception e)
            {
                System.out.println("Only numbers!");
            }
        }
        while(!konez);
        return (wibor - 1);
    }

    private static void deleteFilm()
    {

        System.out.print("Enter the name of deleting film:");
        delFilmName = eingabe.nextLine();

        for(int i = 0; i < chislo_filmow; i++)
        {
            if(spisok_filmow[i].nazwanie.equals(delFilmName))
            {
                for(int k = i; k < chislo_filmow; k++)
                {
                    spisok_filmow[k] = spisok_filmow[k + 1];
                }
                chislo_filmow -= 1;
            }
        }
    }

    private static void saveFilms() throws FileNotFoundException, IOException
    {
        ObjectOutputStream oOutStream = new ObjectOutputStream(new FileOutputStream("films.dat"));
        for(int i = 0; i < chislo_filmow; i++)
        {
            oOutStream.writeObject(spisok_filmow[i]);
        }
        oOutStream.close();
    }

    private static void loadFilms() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream fInStream = new FileInputStream("films.dat");
        ObjectInputStream oInStream = new ObjectInputStream(fInStream);
        int i = 0;
        while(fInStream.available() != 0)
        {

            spisok_filmow[i] = (Film) oInStream.readObject();
            i++;
        }
        chislo_filmow = i;
        oInStream.close();
    }
}